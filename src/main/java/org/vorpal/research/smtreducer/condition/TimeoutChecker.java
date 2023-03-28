package org.vorpal.research.smtreducer.condition;

import org.vorpal.research.smtreducer.hdd.Node;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.List;

import static org.vorpal.research.smtreducer.Main.LOGGER;

public class TimeoutChecker implements ConditionChecker {
    private static final String TEMP_FORMULA = "temp.smt2";
    private static final String SOLVER_OUTPUT = "solver_output";
    private static final String SOLVER_ERROR = "solver_error";
    private static final String CHECK_SAT = "(check-sat)";
    private final Path workingFile;
    private final Path workingDir;

    private final Duration timeout;

    public TimeoutChecker(Path input, Duration timeout) {
        workingFile = initializeWorkingFile(input);
        workingDir = workingFile.getParent();

        this.timeout = timeout;

        execute(workingFile);
    }

    private void execute(Path file) {
        try {
            String[] command = {"z3", "-T:" + timeout.toSeconds(), file.getFileName().toString()};
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
            boolean timeout = false;
            LOGGER.info("Compilation res:");
            while ((line = reader.readLine()) != null) {
                LOGGER.info(line);
                if (line.equals("timeout")) {
                    timeout = true;
                }
                i++;
            }
            LOGGER.info("End");
            return i == 1 && timeout;
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
            writer.newLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String filteredLine = line.replace(CHECK_SAT, "");
                if (!filteredLine.isEmpty()) {
                    writer.write(filteredLine);
                    writer.newLine();
                }
            }
            writer.write(CHECK_SAT);
            writer.newLine();
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
            boolean preserved = logIsValid(tempFile);
            if (preserved) {
                Files.copy(tempFile, workingFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return preserved;
        } catch (IOException e) {
            return false;
        }
    }
}
