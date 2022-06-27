package main;

import generated.SMTLIBLexer;
import generated.SMTLIBParser;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    private static final String INPUT_NAME = "input";
    private static final String SOLVER_RES_PATH = "res";

    private static final Path PROJECT_DIR = Path.of(System.getProperty("user.dir"));
    public static final Path RESOURCE_PATH = PROJECT_DIR.resolve(Path.of("src/main/resources"));
    public static final String INPUT_TEMP = INPUT_NAME + "_temp";
    private static final Path temp = RESOURCE_PATH.resolve(INPUT_TEMP);
    private static final Path COMPILATION_RES = RESOURCE_PATH.resolve(SOLVER_RES_PATH);
    private static final Path GRAPH_FILE_NAME = Path.of("proof.dot");
    private static final String NODE_PREFIX = "node_";

    private static final String LABEL = "label=\"";

    private static final String NODE_1 = NODE_PREFIX + "1 ";
    private static final String NODE_2 = NODE_PREFIX + "2 ";
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        executeZ3(INPUT_NAME);
        String contradiction = getContradiction();
        Files.copy(RESOURCE_PATH.resolve(INPUT_NAME), temp, StandardCopyOption.REPLACE_EXISTING);

        try (InputStream inputStream = Files.newInputStream(temp)) {
            SMTLIBLexer lexer = new SMTLIBLexer(CharStreams.fromStream(inputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SMTLIBParser parser = new SMTLIBParser(tokens);
            parser.setBuildParseTree(true);
            Node treeRoot = Node.from(parser.script());
            hdd(treeRoot, contradiction);
            writeTreeBackToFile(treeRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getContradiction() throws IOException {
        String contradiction = null;
        try (BufferedReader reader = Files.newBufferedReader(RESOURCE_PATH.resolve(GRAPH_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(NODE_1) || line.startsWith(NODE_2)) {
                    contradiction = getLabel(line);
                    break;
                }
            }
            if (line == null) {
                throw new IllegalStateException();
            }
        }
        return contradiction;
    }

    private static void writeTreeBackToFile(Node treeRoot) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(temp,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Node command : treeRoot.children()) {
                recursiveTreeWrite(writer, command);
                writer.newLine();
            }
        }
    }

    private static void recursiveTreeWrite(Writer writer, Node subTree) throws IOException {
        if (subTree == null) {
            return;
        }
        if (subTree.children().isEmpty()) {
            writer.write(" ");
            writer.write(subTree.tree().toString());
            return;
        }
        for (int i = 0; i < subTree.children().size(); i++) {
            recursiveTreeWrite(writer, subTree.children().get(i));
        }
    }

    private static void executeZ3(String fileName) throws IOException, InterruptedException {
        String[] command = {"z3", fileName};
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectOutput(new File(String.valueOf(COMPILATION_RES)));
        builder.directory(new File(String.valueOf(RESOURCE_PATH))).start().waitFor();
    }

    private static String getLabel(String line) {
        int labelIndex = line.indexOf(LABEL);
        int lastQuoteIndex = line.lastIndexOf("\"");
        String contradiction = line.substring(labelIndex + LABEL.length(), lastQuoteIndex);
        int indexOfFalse = contradiction.lastIndexOf("false");
        if (indexOfFalse == -1) {
            return contradiction;
        }
        String str = "(=";
        int startIndex = contradiction.indexOf(str) + str.length();
        return contradiction.substring(startIndex, indexOfFalse - 1).trim();
    }

    private static void hdd(Node tree, String contradiction) throws IOException, InterruptedException {
        List<Node> levelNodes = new ArrayList<>();
        filterChildNodes(tree, levelNodes);
        while (!levelNodes.isEmpty()) {
            ddMin(levelNodes, tree, contradiction);
            int levelSize = levelNodes.size();
            for (int i = 0; i < levelSize; i++) {
                Node node = levelNodes.get(i);
                filterChildNodes(node, levelNodes);
            }
            levelNodes = levelNodes.subList(levelSize, levelNodes.size());
        }
    }

    private static void filterChildNodes(Node tree, List<Node> levelNodes) {
        List<Node> children = tree.children();
        for (Node node : children) {
            if (unavailableToOptimize(node)) {
                continue;
            }
            levelNodes.add(node);
        }
    }

    private static boolean unavailableToOptimize(Node node) {
        String stringNode = node.toString();
        return stringNode.equals("(")
                || stringNode.equals(")")
                || stringNode.contains("get-proof-graph")
                || stringNode.contains("check-sat");
    }

    private static void ddMin(
            List<Node> levelNodes, Node tree, String contradiction) throws IOException, InterruptedException {
        int n = 2;
        while (levelNodes.size() > 1) {
            List<List<Node>> parts = split(levelNodes, n);
            boolean configurationFailed = false;
            for (List<Node> part : parts) {
                LOGGER.info("Start removing:");
                for (Node node : part) {
                    LOGGER.info(String.valueOf(node));
                    node.parent().children().set(node.indexInParent(), null);
                }
                LOGGER.info("End removing");
                if (fallWithNewConfiguration(tree, contradiction)) {
                    n = Math.max(n - 1, 2);
                    LOGGER.info("Good, we can ignore this part");
                    levelNodes.removeAll(part);
                    configurationFailed = true;
                    break;
                }
                for (Node node : part) {
                    node.parent().children().set(node.indexInParent(), node);
                }
            }
            if (!configurationFailed) {
                if (n == levelNodes.size()) {
                    break;
                }

                // increase set granularity
                n = Math.min(n * 2, levelNodes.size());
            }
        }
    }

    private static boolean fallWithNewConfiguration(
            Node tree, String contradiction) throws IOException, InterruptedException {
        writeTreeBackToFile(tree);
        executeZ3(INPUT_TEMP);
        try (BufferedReader reader = Files.newBufferedReader(COMPILATION_RES)) {
            String line;
            int i = 0;
            boolean unsat = false;
            LOGGER.info("Compilation res:");
            while ((line = reader.readLine()) != null) {
                LOGGER.info(line);
                if (line.equals("unsat")) {
                    unsat = true;
                }
                i++;
            }
            LOGGER.info("End");
            if (i > 1 || !unsat) {
                return false;
            }
            return getContradiction().equals(contradiction);
        }
    }

    private static List<List<Node>> split(List<Node> s, int n) {
        List<List<Node>> subsets = new ArrayList<>();
        int position = 0;
        for (int i = 0; i < n; i++) {
            List<Node> subset = s.subList(position, position + (s.size() - position) / (n - i));
            subsets.add(subset);
            position += subset.size();
        }
        return subsets;
    }

    private static void paint(SMTLIBParser parser, SMTLIBParser.ScriptContext tree) {
        JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(
                parser.getRuleNames()), tree);
        viewer.setScale(1); // Scale a little
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


