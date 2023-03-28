package org.vorpal.research.smtreducer.condition;

import org.vorpal.research.smtreducer.hdd.Node;

import java.nio.file.Path;

public interface ConditionChecker {
    Path getWorkingFile();
    boolean isValid();
    boolean isPreserved(Node tree);
}
