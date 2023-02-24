package org.vorpal.research.smtreducer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.vorpal.research.smtreducer.Main.LOGGER;

public class HDDManager {
    private static final long TIMEOUT_MILLIS = 200 * 1000L;
    private final Z3Manager z3Manager;

    public HDDManager(Z3Manager z3Manager) {
        this.z3Manager = z3Manager;
    }

    public void hdd(Node tree, String contradiction) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Node> levelNodes = filterMandatoryCommands(tree);
        boolean finished = ddMin(levelNodes, tree, contradiction, start);
        if (!finished) {
            return;
        }
        levelNodes = filterNonAsserts(levelNodes);
        while (!levelNodes.isEmpty()) {
            finished = ddMin(levelNodes, tree, contradiction, start);
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
            List<Node> levelNodes, Node tree, String contradiction, long start) throws IOException, InterruptedException {
        int n = 2;
        while (levelNodes.size() > 1) {
            List<List<Node>> parts = Utils.split(levelNodes, n);
            boolean configurationFailed = false;
            for (List<Node> part : parts) {
                if (System.currentTimeMillis() - start >= TIMEOUT_MILLIS) {
                    LOGGER.info("TIMEOUT HDD");
                    return false;
                }
                LOGGER.info("Start removing:");
                removePart(part);
                LOGGER.info("End removing");
                if (z3Manager.fallWithNewConfiguration(tree, contradiction)) {
                    n = Math.max(n - 1, 2);
                    LOGGER.info("Good, we can ignore this part");
                    levelNodes.removeAll(part);
                    configurationFailed = true;
                    break;
                }
                for (Node node : part) {
                    node.parent().children().set(node.indexInParent(), node);
                }
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
}
