package main;

import generated.SMTLIBLexer;
import generated.SMTLIBParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import static main.Main.LOGGER;

public class Z3Manager {
    private static final String NODE_PREFIX = "node_";
    private static final String NODE_1 = NODE_PREFIX + "1 ";
    private static final String NODE_2 = NODE_PREFIX + "2 ";
    private static final String SOLVER_RES_PATH = "res";
    private static final Path GRAPH_FILE_NAME = Path.of("proof.dot");
    private static final Path PROJECT_DIR = Path.of(System.getProperty("user.dir"));
    private static final Path RESOURCE_PATH = PROJECT_DIR.resolve(Path.of("src/main/resources"));
    private static final Path COMPILATION_RES = RESOURCE_PATH.resolve(SOLVER_RES_PATH);
    public final String inputTempName;
    private final Path temp;

    private Z3Manager(String inputName) {
        inputTempName = inputName + "_temp";
        temp = RESOURCE_PATH.resolve(inputTempName);
    }

    private void writeTreeBackToFile(Node treeRoot) throws IOException {
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

    private void recursiveTreeWrite(Writer writer, Node subTree) throws IOException {
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

    private void execute(String fileName) throws IOException, InterruptedException {
        String[] command = {"z3", fileName};
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectOutput(new File(String.valueOf(COMPILATION_RES)));
        builder.directory(new File(String.valueOf(RESOURCE_PATH))).start().waitFor();
    }

    private static String getContradiction() throws IOException {
        String contradiction = null;
        try (BufferedReader reader = Files.newBufferedReader(RESOURCE_PATH.resolve(GRAPH_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(NODE_1) || line.startsWith(NODE_2)) {
                    contradiction = Utils.getLabel(line);
                    break;
                }
            }
            if (line == null) {
                throw new IllegalStateException();
            }
        }
        return contradiction;
    }

    public boolean fallWithNewConfiguration(
            Node tree, String contradiction) throws IOException, InterruptedException {
        writeTreeBackToFile(tree);
        execute(inputTempName);
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

    public static void minimize(String input) throws IOException, InterruptedException {
        Z3Manager z3Manager = new Z3Manager(input);
        z3Manager.execute(input);
        String contradiction = getContradiction();
        Files.copy(RESOURCE_PATH.resolve(input), z3Manager.temp, StandardCopyOption.REPLACE_EXISTING);

        try (InputStream inputStream = Files.newInputStream(z3Manager.temp)) {
            SMTLIBLexer lexer = new SMTLIBLexer(CharStreams.fromStream(inputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SMTLIBParser parser = new SMTLIBParser(tokens);
            parser.setBuildParseTree(true);
            Node treeRoot = Node.from(parser.script());

            HDDManager hddManager = new HDDManager(z3Manager);
            hddManager.hdd(treeRoot, contradiction);
            z3Manager.writeTreeBackToFile(treeRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
