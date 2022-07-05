package main;

import generated.SMTLIBLexer;
import generated.SMTLIBParser;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import static main.Main.LOGGER;

public class Z3Manager {
    private static final String NODE_PREFIX = "node_";
    private static final String NODE_1 = NODE_PREFIX + "1 ";
    private static final String NODE_2 = NODE_PREFIX + "2 ";
    private static final String SOLVER_RES_PATH = "res";
    private static final String SIMPLIFIED = "_simplified";
    private static final Path GRAPH_FILE_NAME = Path.of("proof.dot");
    private static final Path PROJECT_DIR = Path.of(System.getProperty("user.dir"));
    public static final Path RESOURCE_PATH
            = PROJECT_DIR.resolve(Path.of("src" + File.separator + "main" + File.separator + "resources"));
    private static final Path COMPILATION_RES = RESOURCE_PATH.resolve(SOLVER_RES_PATH);
    public final Path inputFile;
    private final Path temp;

    private Z3Manager(Path input) {
        inputFile = input;
        temp = withSuffix(input, "_temp");
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

    private void execute(Path file) throws IOException, InterruptedException {
        String[] command = {"z3", file.getFileName().toString()};
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectOutput(new File(String.valueOf(COMPILATION_RES)));
        builder.directory(new File(file.getParent().toString())).start().waitFor();
    }

    private static String getContradiction(Path inputFile) throws IOException {
        String contradiction = null;
        try (BufferedReader reader = Files.newBufferedReader(inputFile.getParent().resolve(GRAPH_FILE_NAME))) {
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

    public boolean fallWithNewConfiguration(Node tree, String contradiction) throws IOException, InterruptedException {
        writeTreeBackToFile(tree);
        execute(temp);
        if (logIsValid()) {
            String newContradiction = getContradiction(temp);
            LOGGER.info("New contradiction {}", newContradiction);
            boolean equals = newContradiction.equals(contradiction);
            LOGGER.info("Is equals with initial contradiction ({}) ", equals);
            return equals;
        }
        return false;
    }

    private boolean logIsValid() throws IOException {
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
            return i == 1 && unsat;
        }
    }

    public static Path minimize(Path file) throws IOException, InterruptedException {
        Files.deleteIfExists(file.getParent().resolve(GRAPH_FILE_NAME));
        Files.deleteIfExists(COMPILATION_RES);
        Z3Manager z3Manager = new Z3Manager(file);
        z3Manager.execute(file);
        if (!z3Manager.logIsValid()) {
            LOGGER.error("Formula is not unsat or wrong on file {}", file);
            return null;
        }
        String contradiction = getContradiction(file);
        Files.copy(file, z3Manager.temp, StandardCopyOption.REPLACE_EXISTING);

        try (InputStream inputStream = Files.newInputStream(z3Manager.temp)) {
            SMTLIBLexer lexer = new SMTLIBLexer(CharStreams.fromStream(inputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SMTLIBParser parser = new SMTLIBParser(tokens);
            parser.setBuildParseTree(true);
            Node treeRoot = Node.from(parser.script());
            HDDManager hddManager = new HDDManager(z3Manager);
            hddManager.hdd(treeRoot, contradiction);
            z3Manager.writeTreeBackToFile(treeRoot);
            Path res = withSuffix(file, SIMPLIFIED);
            Files.move(z3Manager.temp, res, StandardCopyOption.ATOMIC_MOVE);
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static Path withSuffix(Path path, String suffix) {
        return path.resolveSibling(path.getFileName().toString() + suffix);
    }
}
