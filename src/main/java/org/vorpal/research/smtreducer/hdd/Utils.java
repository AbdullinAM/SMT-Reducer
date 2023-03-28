package org.vorpal.research.smtreducer.hdd;

import java.util.ArrayList;
import java.util.List;

public final class Utils {
    private static final String LABEL = "label=\"";

    private Utils() {

    }

    public static String getLabel(String line) {
        int labelIndex = line.indexOf(LABEL);
        int lastQuoteIndex = line.lastIndexOf("\"");
        String contradiction = line.substring(labelIndex + LABEL.length(), lastQuoteIndex);
        int indexOfFalse = contradiction.lastIndexOf("false");
        if (indexOfFalse == -1) {
            return contradiction;
        }
        String str = "(=";
        int startIndex = contradiction.indexOf(str) + str.length();
        return contradiction.substring(startIndex, indexOfFalse - 1).trim();
    }

    public static List<List<Node>> split(List<Node> s, int n) {
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
