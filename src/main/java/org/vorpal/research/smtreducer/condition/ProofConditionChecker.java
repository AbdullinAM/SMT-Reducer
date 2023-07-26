package org.vorpal.research.smtreducer.condition;

import org.vorpal.research.smtreducer.hdd.Node;
import org.vorpal.research.smtreducer.hdd.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.List;

import static org.vorpal.research.smtreducer.Main.LOGGER;

public class ProofConditionChecker implements ConditionChecker {
    private static final String NODE_PREFIX = "node_";
    private static final String NODE_1 = NODE_PREFIX + "1 ";
    private static final String NODE_2 = NODE_PREFIX + "2 ";
    private static final String TEMP_FORMULA = "temp.smt2";
    private static final String SOLVER_OUTPUT = "solver_output";
    private static final String SOLVER_ERROR = "solver_error";
    private static final String PROOF_FILE = "proof.dot";
    private static final String PRODUCE_PROOFS = "(set-option :produce-proofs true)";
    private static final String CHECK_SAT = "(check-sat)";
    private static final String GET_PROOF_GRAPH = "(get-proof-graph)";
    private final Path workingFile;
    private final Path workingDir;
    private final String proofCondition;

    private final Duration formulaeTimeout;

    public ProofConditionChecker(Path input, Duration timeout) {
        formulaeTimeout = timeout;
        workingFile = initializeWorkingFile(input);
        workingDir = workingFile.getParent();
        execute(workingFile);
        proofCondition = getContradiction();
    }

    private void execute(Path file) {
        try {
            String[] command = {"z3", "-T:" + formulaeTimeout.getSeconds(), file.getFileName().toString()};
            ProcessBuilder builder = new ProcessBuilder(command);
            Path solverOutput = workingDir.resolve(SOLVER_OUTPUT);
            Path solverError = workingDir.resolve(SOLVER_OUTPUT);
            builder.redirectOutput(solverOutput.toFile());
            builder.redirectError(solverError.toFile());
            builder.directory(file.getParent().toFile()).start().waitFor();
        } catch (IOException | InterruptedException e) {
            throw new IllegalStateException();
        }
    }

    private String getContradiction() {
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
        } catch (IOException e) {
            throw new IllegalStateException();
        }
        return contradiction;
    }

    private boolean logIsValid(Path formulae) {
        Path parentDir = formulae.getParent();
        try {
            if (parentDir.resolve(SOLVER_ERROR).toFile().exists()) {
                List<String> errorLog = Files.readAllLines(parentDir.resolve(SOLVER_ERROR));
                if (!errorLog.isEmpty()) {
                    return false;
                }
            }
        } catch (IOException e) {
            return false;
        }
        try (BufferedReader reader = Files.newBufferedReader(parentDir.resolve(SOLVER_OUTPUT))) {
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
        } catch (IOException e) {
            return false;
        }
    }

    private static Path initializeWorkingFile(Path path) {
        Path tempDir;
        try {
            tempDir = Files.createTempDirectory("smt-reducer");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path resultingFile = tempDir.resolve(path.getFileName());
        try (BufferedReader reader = Files.newBufferedReader(path);
             BufferedWriter writer = Files.newBufferedWriter(resultingFile,
                     StandardOpenOption.CREATE,
                     StandardOpenOption.WRITE,
                     StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(PRODUCE_PROOFS);
            writer.newLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String filteredLine = line
                        .replace(PRODUCE_PROOFS, "")
                        .replace(CHECK_SAT, "")
                        .replace(GET_PROOF_GRAPH, "");
                if (!filteredLine.isEmpty()) {
                    writer.write(filteredLine);
                    writer.newLine();
                }
            }
            writer.write(CHECK_SAT);
            writer.newLine();
            writer.write(GET_PROOF_GRAPH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultingFile;
    }

    @Override
    public Path getWorkingFile() {
        return workingFile;
    }

    @Override
    public boolean isValid() {
        execute(workingFile);
        return logIsValid(workingFile);
    }

    @Override
    public boolean isPreserved(Node tree) {
        try {
            Path tempFile = workingDir.resolve(TEMP_FORMULA);
            Files.writeString(tempFile, tree.toString());
            execute(tempFile);
            String newContradiction = getContradiction();
            boolean preserved = logIsValid(tempFile) && proofCondition.equals(newContradiction);
            if (preserved) {
                Files.copy(tempFile, workingFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return preserved;
        } catch (IOException e) {
            return false;
        }
    }
}
