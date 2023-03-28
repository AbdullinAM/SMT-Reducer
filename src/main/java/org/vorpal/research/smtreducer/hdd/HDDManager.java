package org.vorpal.research.smtreducer.hdd;

import org.vorpal.research.smtreducer.condition.ConditionChecker;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.vorpal.research.smtreducer.Main.LOGGER;

@SuppressWarnings("unused")
public class HDDManager {

    private final ConditionChecker checker;
    private final Duration timeout;

    public HDDManager(ConditionChecker checker) {
        this(checker, Duration.ofSeconds(100));
    }
    public HDDManager(ConditionChecker checker, Duration timeout) {
        this.checker = checker;
        this.timeout = timeout;
    }

    public void hdd(Node tree) {
        long start = System.currentTimeMillis();
        List<Node> levelNodes = filterMandatoryCommands(tree);
        boolean finished = ddMin(levelNodes, tree, start);
        if (!finished) {
            return;
        }
        levelNodes = filterNonAsserts(levelNodes);
        while (!levelNodes.isEmpty()) {
            finished = ddMin(levelNodes, tree, start);
            if (!finished) {
                return;
            }
            int levelSize = levelNodes.size();
            for (int i = 0; i < levelSize; i++) {
                Node node = levelNodes.get(i);
                filterChildNodes(node, levelNodes);
            }
            levelNodes = levelNodes.subList(levelSize, levelNodes.size());
        }
    }

    private List<Node> filterNonAsserts(List<Node> levelNodes) {
        int levelSize = levelNodes.size();
        for (int i = 0; i < levelSize; i++) {
            Node node = levelNodes.get(i);
            String command = node.children().get(1).toString().trim();
            if (!command.equals("assert") && !command.equals("let")) {
                continue;
            }
            filterChildNodes(node, levelNodes);
        }
        levelNodes = levelNodes.subList(levelSize, levelNodes.size());
        return levelNodes;
    }

    private void filterChildNodes(Node tree, List<Node> levelNodes) {
        List<Node> children = tree.children();
        for (Node node : children) {
            if (unavailableToOptimize(node)) {
                continue;
            }
            levelNodes.add(node);
        }
    }

    private static boolean unavailableToOptimize(Node node) {
        String stringNode = node.toString().trim();
        return stringNode.equals("(")
                || stringNode.equals(")")
                || stringNode.equals("assert");
    }

    private List<Node> filterMandatoryCommands(Node tree) {
        return tree
                .children()
                .stream()
                .filter(node -> {
                    String stringNode = node.toString();
                    return !(
                            stringNode.contains("get-proof-graph") ||
                                    stringNode.contains("check-sat") ||
                                    stringNode.contains("produce-proofs"));
                })
                .collect(Collectors.toList());
    }

    private boolean ddMin(
            List<Node> levelNodes,
            Node tree,
            long start
    ) {
        int n = 2;
        while (levelNodes.size() > 1) {
            List<List<Node>> parts = Utils.split(levelNodes, n);
            boolean configurationFailed = false;
            for (List<Node> part : parts) {
                if (System.currentTimeMillis() - start >= timeout.toMillis()) {
                    LOGGER.info("TIMEOUT HDD");
                    return false;
                }
                LOGGER.info("Start removing:");
                removePart(part);
                LOGGER.info("End removing");
                if (checker.isPreserved(tree)) {
                    n = Math.max(n - 1, 2);
                    LOGGER.info("Good, we can ignore this part");
                    levelNodes.removeAll(part);
                    configurationFailed = true;
                    break;
                }
                returnPart(part);
            }
            if (!configurationFailed) {
                if (n == levelNodes.size()) {
                    break;
                }

                // increase set granularity
                n = Math.min(n * 2, levelNodes.size());
            }
        }
        return true;
    }

    private void removePart(List<Node> part) {
        for (Node node : part) {
            LOGGER.info(String.valueOf(node));
            node.parent().children().set(node.indexInParent(), null);
        }
    }

    private void returnPart(List<Node> part) {
        for (Node node : part) {
            node.parent().children().set(node.indexInParent(), node);
        }
    }
}
