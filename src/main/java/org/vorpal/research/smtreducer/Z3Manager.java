package org.vorpal.research.smtreducer;

import generated.SMTLIBLexer;
import generated.SMTLIBParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.Duration;

import static org.vorpal.research.smtreducer.Main.LOGGER;

public class Z3Manager {
    private static final String NODE_PREFIX = "node_";
    private static final String NODE_1 = NODE_PREFIX + "1 ";
    private static final String NODE_2 = NODE_PREFIX + "2 ";
    private static final String SOLVER_OUTPUT = "solver_output";
    private static final String SIMPLIFIED = "_simplified";
    private static final String PROOF_FILE = "proof.dot";

    private final Path workingDir;
    private final Path temp;

    private Z3Manager(Path input) {
        workingDir = input.getParent();
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
        Path solverOutput = workingDir.resolve(SOLVER_OUTPUT);
        builder.redirectOutput(solverOutput.toFile());
        builder.directory(file.getParent().toFile()).start().waitFor();
    }

    private String getContradiction() throws IOException {
        String contradiction = null;
        try (BufferedReader reader = Files.newBufferedReader(workingDir.resolve(PROOF_FILE))) {
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
            String newContradiction = getContradiction();
            LOGGER.info("New contradiction {}", newContradiction);
            boolean equals = newContradiction.equals(contradiction);
            LOGGER.info("Is equals with initial contradiction ({}) ", equals);
            return equals;
        }
        return false;
    }

    private boolean logIsValid() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(workingDir.resolve(SOLVER_OUTPUT))) {
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

    public static Path minimize(Path file, Duration timeout) throws IOException, InterruptedException {
        Z3Manager z3Manager = new Z3Manager(file);
        z3Manager.execute(file);
        if (!z3Manager.logIsValid()) {
            LOGGER.error("Formula is not unsat or wrong on file {}", file);
            return null;
        }
        String contradiction = z3Manager.getContradiction();
        Files.copy(file, z3Manager.temp, StandardCopyOption.REPLACE_EXISTING);

        try (InputStream inputStream = Files.newInputStream(z3Manager.temp)) {
            SMTLIBLexer lexer = new SMTLIBLexer(CharStreams.fromStream(inputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SMTLIBParser parser = new SMTLIBParser(tokens);
            parser.setBuildParseTree(true);
            Node treeRoot = Node.from(parser.script());
            HDDManager hddManager = new HDDManager(z3Manager, timeout);
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
