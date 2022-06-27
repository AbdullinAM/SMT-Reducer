package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.Main.LOGGER;

public final class HDD {
    private final Z3Manager z3Manager;

    public HDD(Z3Manager z3Manager) {
        this.z3Manager = z3Manager;
    }

    public void hdd(Node tree, String contradiction) throws IOException, InterruptedException {
        List<Node> levelNodes = new ArrayList<>();
        filterChildNodes(tree, levelNodes);
        while (!levelNodes.isEmpty()) {
            ddMin(levelNodes, tree, contradiction);
            int levelSize = levelNodes.size();
            for (int i = 0; i < levelSize; i++) {
                Node node = levelNodes.get(i);
                filterChildNodes(node, levelNodes);
            }
            levelNodes = levelNodes.subList(levelSize, levelNodes.size());
        }
    }

    private static void filterChildNodes(Node tree, List<Node> levelNodes) {
        List<Node> children = tree.children();
        for (Node node : children) {
            if (unavailableToOptimize(node)) {
                continue;
            }
            levelNodes.add(node);
        }
    }

    private static boolean unavailableToOptimize(Node node) {
        String stringNode = node.toString();
        return stringNode.equals("(")
                || stringNode.equals(")")
                || stringNode.contains("get-proof-graph")
                || stringNode.contains("check-sat");
    }

    private static void ddMin(
            List<Node> levelNodes, Node tree, String contradiction) throws IOException, InterruptedException {
        int n = 2;
        while (levelNodes.size() > 1) {
            List<List<Node>> parts = split(levelNodes, n);
            boolean configurationFailed = false;
            for (List<Node> part : parts) {
                LOGGER.info("Start removing:");
                for (Node node : part) {
                    LOGGER.info(String.valueOf(node));
                    node.parent().children().set(node.indexInParent(), null);
                }
                LOGGER.info("End removing");
                if (fallWithNewConfiguration(tree, contradiction)) {
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
    }

    private static List<List<Node>> split(List<Node> s, int n) {
        List<List<Node>> subsets = new ArrayList<>();
        int position = 0;
        for (int i = 0; i < n; i++) {
            List<Node> subset = s.subList(position, position + (s.size() - position) / (n - i));
            subsets.add(subset);
            position += subset.size();
        }
        return subsets;
    }
}
