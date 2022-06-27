package main;

import org.antlr.v4.runtime.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public record Node(Tree tree, main.Node parent, List<Node> children, int indexInParent) {

    public static Node from(Tree tree) {
        Node root = new Node(tree, null, new ArrayList<>(tree.getChildCount()), -1);
        fillTree(root, tree);
        return root;
    }

    private static void fillTree(Node node, Tree tree) {
        for (int i = 0; i < tree.getChildCount(); i++) {
            Tree childTree = tree.getChild(i);
            Node child = new Node(childTree, node, new ArrayList<>(childTree.getChildCount()), i);
            node.children.add(child);
            fillTree(child, childTree);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        walkTree(res, this);
        return res.toString();
    }

    private void walkTree(StringBuilder res, Node subTree) {
        if (subTree == null) {
            return;
        }
        if (subTree.children().isEmpty()) {
            res.append(" ").append(subTree.tree().toString());
            return;
        }
        for (int i = 0; i < subTree.children().size(); i++) {
            walkTree(res, subTree.children().get(i));
        }
    }
}
