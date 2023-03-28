package org.vorpal.research.smtreducer.z3;

import generated.SMTLIBLexer;
import generated.SMTLIBParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.vorpal.research.smtreducer.condition.ConditionChecker;
import org.vorpal.research.smtreducer.hdd.HDDManager;
import org.vorpal.research.smtreducer.hdd.Node;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import static org.vorpal.research.smtreducer.Main.LOGGER;

public class Z3Manager {
    private static final String SIMPLIFIED = "_simplified";

    public static Path minimize(ConditionChecker checker, Duration timeout) throws IOException, InterruptedException {
        if (!checker.isValid()) {
            LOGGER.error("Formula does not satisfy the condition {}", checker.getWorkingFile());
            return null;
        }

        try (InputStream inputStream = Files.newInputStream(checker.getWorkingFile())) {
            SMTLIBLexer lexer = new SMTLIBLexer(CharStreams.fromStream(inputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SMTLIBParser parser = new SMTLIBParser(tokens);
            parser.setBuildParseTree(true);
            Node treeRoot = Node.from(parser.script());
            HDDManager hddManager = new HDDManager(checker, timeout);
            hddManager.hdd(treeRoot);
            Path path = checker.getWorkingFile();
            Path res = path.resolveSibling(path.getFileName().toString() + Z3Manager.SIMPLIFIED);
            Files.move(checker.getWorkingFile(), res, StandardCopyOption.ATOMIC_MOVE);
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
