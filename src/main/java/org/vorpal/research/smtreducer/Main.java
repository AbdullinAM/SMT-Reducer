package org.vorpal.research.smtreducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vorpal.research.smtreducer.condition.ProofConditionChecker;
import org.vorpal.research.smtreducer.z3.Z3Manager;

import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String EXTENSION = ".smt2";
    public static final String SUFFIX = "_reduced" + EXTENSION;

    public static void main(String[] args) {
        test(Paths.get(args[0]), Duration.ofSeconds(Long.parseLong(args[1])));
    }

    private static void test(Path path, Duration timeout) {
        List<Double> percentages = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        if (count.getAndIncrement() >= 10) {
            return;
        }
        try {
            ProofConditionChecker checker = new ProofConditionChecker(path);
            Path result = Z3Manager.minimize(checker, timeout);
            if (result == null) {
                LOGGER.info("There was sat formula");
            } else {
                long initialSize = Files.size(path);
                double percentage = (initialSize - Files.size(result)) * 1.0 / initialSize;
                LOGGER.info(String.valueOf(percentage));
                Path resultingFile = path.resolveSibling(path.getFileName() + SUFFIX);
                Files.copy(result, resultingFile, StandardCopyOption.REPLACE_EXISTING);
                Thread.sleep(1000L);
                percentages.add(percentage);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("ALL:");
        percentages.forEach(percentage -> LOGGER.info(percentage.toString()));
    }
}


