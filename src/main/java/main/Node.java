package main;

import org.antlr.v4.runtime.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public final class Node {
    private final Tree tree;
    private final Node parent;
    private final List<Node> children;
    private final int indexInParent;
    private String stringNode;

    public Node(Tree tree, Node parent, List<Node> children, int indexInParent) {
        this.tree = tree;
        this.parent = parent;
        this.children = children;
        this.indexInParent = indexInParent;
    }

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
        if (stringNode != null) {
            return stringNode;
        }
        StringBuilder res = new StringBuilder();
        walkTree(res, this);
        stringNode = res.toString();
        return stringNode;
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

    public Tree tree() {
        return tree;
    }

    public Node parent() {
        return parent;
    }

    public List<Node> children() {
        return children;
    }

    public int indexInParent() {
        return indexInParent;
    }
}
