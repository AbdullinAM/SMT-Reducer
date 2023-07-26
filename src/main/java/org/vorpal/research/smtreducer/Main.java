package org.vorpal.research.smtreducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vorpal.research.smtreducer.condition.ConditionChecker;
import org.vorpal.research.smtreducer.condition.ProofConditionChecker;
import org.vorpal.research.smtreducer.condition.TimeoutChecker;
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

    enum Mode {
        PROOF,
        TIMEOUT
    }

    public static void main(String[] args) {
        Path inputFile = Paths.get(args[0]);
        Duration ddTimeout = Duration.ofSeconds(Long.parseLong(args[1]));
        Mode mode = Mode.valueOf(args[2].toUpperCase());
        Duration formulaeTimeout = Duration.ofSeconds(Long.parseLong(args[3]));

        ConditionChecker checker;
        switch (mode) {
            case PROOF:
                checker = new ProofConditionChecker(inputFile, formulaeTimeout);
                break;
            case TIMEOUT:
                checker = new TimeoutChecker(inputFile, formulaeTimeout);
                break;
            default:
                LOGGER.error("Unknown mode: " + mode.name());
                System.exit(1);
                return;
        }
        reduce(inputFile, ddTimeout, checker);
    }

    private static void reduce(Path path, Duration timeout, ConditionChecker checker) {
        List<Double> percentages = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        if (count.getAndIncrement() >= 10) {
            return;
        }
        try {
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


