package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given an array of strings products and a string searchWord.
 * <p>
 * Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 * <p>
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 * @author: LISHUAI
 * @createDate: 2022/6/23 11:54
 * @version: 1.0
 */

public class LeetCode_1268 {

    public static void main(String[] args) {
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        List<List<String>> list = suggestedProducts(products, searchWord);
        for (List<String> l : list) {
            for (String s : l) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        PrefixTree root = makePrefixTree(products);
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            ans.add(new ArrayList<>());
        }

        for (int i = 0; i < searchWord.length() && root != null; i++) {
            root = root.values[searchWord.charAt(i) - 'a'];
            if (root != null) {
                getStrings(root, ans.get(i), 3);
            }
        }
        return ans;
    }

    private static int getStrings(PrefixTree root, List<String> list, int size) {
        if (size == 0 || root == null) {
            return size;
        }
        if (root.isEnd) {
            list.add(root.val);
            size--;
        }
        for (int i = 0; i < root.values.length && size > 0; i++) {
            size = getStrings(root.values[i], list, size);
        }
        return size;
    }

    private static PrefixTree makePrefixTree(String[] products) {
        PrefixTree root = new PrefixTree();
        for (String str : products) {
            PrefixTree node = root;
            for (int i = 0; i < str.length(); i++) {
                int c = str.charAt(i) - 'a';
                if (node.values[c] == null) {
                    node.values[c] = new PrefixTree();
                }
                node.size++;
                node = node.values[c];
            }
            node.isEnd = true;
            node.val = str;
        }
        return root;
    }

    static class PrefixTree {
        PrefixTree[] values;
        boolean isEnd;
        String val;
        int size;

        public PrefixTree() {
            values = new PrefixTree[26];
            isEnd = false;
            val = null;
            size = 0;
        }
    }
}
