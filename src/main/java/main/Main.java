package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static final String SUFFIX = "_appended";
    private static final String PRODUCE_PROOFS = "(set-option :produce-proofs true)";
    private static final String CHECK_SAT = "(check-sat)";
    private static final String GET_PROOF_GRAPH = "(get-proof-graph)";

    public static void main(String[] args) throws IOException, InterruptedException {
//        Z3Manager.minimize(Path.of("src/main/resources/0.smtlib"));
        test();
    }

    private static void test() {
        Path projectDir = Path.of(System.getProperty("user.dir"));
        List<Double> percentages = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        try (Stream<Path> list = Files.list(projectDir.resolve(Path.of("src/main/resources/inputs/formulae-karg/")))) {
            list
                    .filter(path -> path.toString().endsWith(".smtlib"))
                    .forEach((path -> {
                        if (count.getAndIncrement() >= 10) {
                            return;
                        }
                        Path appendedFile = path.resolveSibling(path.getFileName() + SUFFIX);
                        rewriteFiles(path, appendedFile);
                        try {
                            Path res = Z3Manager.minimize(appendedFile);
                            if (res == null) {
                                LOGGER.info("There was sat formula");
                            } else {
                                long initialSize = Files.size(path);
                                double percentage = (initialSize - Files.size(res)) * 1.0 / initialSize;
                                LOGGER.info(String.valueOf(percentage));
                                Thread.sleep(1000L);
                                percentages.add(percentage);
                            }
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("ALL:");
        percentages.forEach(percentage -> LOGGER.info(percentage.toString()));
    }

    private static void rewriteFiles(Path path, Path newFile) {
        try (BufferedReader reader = Files.newBufferedReader(path);
             BufferedWriter writer = Files.newBufferedWriter(newFile,
                     StandardOpenOption.CREATE,
                     StandardOpenOption.WRITE,
                     StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(PRODUCE_PROOFS);
            writer.newLine();
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            writer.write(CHECK_SAT);
            writer.newLine();
            writer.write(GET_PROOF_GRAPH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


