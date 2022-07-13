package leetcode;

/**
 * @description: Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.
 * <p>
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.
 * @author: LISHUAI
 * @createDate: 2022/6/8 19:20
 * @version: 1.0
 */

public class LeetCode_720 {

    public static void main(String[] args) {
//        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        String[] words = {"w", "wo", "wor", "worl", "world"};
        String s = longestWord(words);
        System.out.println(s);
    }

    public static String longestWord(String[] words) {
        int maxSize = 0;
        String ans = "";
        PrefixTree root = makeTree(words);

        for (String s : words) {
            if (s.length() >= maxSize && inWords(root, s)) {
                if (s.length() == maxSize) {
                    ans = min(s, ans);
                } else {
                    ans = s;
                }
                maxSize = s.length();
            }
        }
        return ans;
    }

    private static String min(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return s2;
        }
        if (s1.length() < s2.length()) {
            return s1;
        }
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) < s2.charAt(i)) {
                return s1;
            } else if (s1.charAt(i) > s2.charAt(i)) {
                return s2;
            }
        }
        return s1;
    }

    private static boolean inWords(PrefixTree root, String s) {
        PrefixTree node = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (node.values[c - 'a'] == null) {
                return false;
            }
            node = node.values[c - 'a'];
            if (!node.isEnd) {
                return false;
            }
        }
        return node.isEnd;
    }

    private static PrefixTree makeTree(String[] words) {
        PrefixTree root = new PrefixTree();
        for (String s : words) {
            PrefixTree node = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (node.values[c - 'a'] == null) {
                    node.values[c - 'a'] = new PrefixTree();
                }
                node = node.values[c - 'a'];
            }
            node.isEnd = true;
        }
        return root;
    }


    static class PrefixTree {
        PrefixTree[] values;
        boolean isEnd;

        public PrefixTree() {
            this.values = new PrefixTree[26];
            this.isEnd = false;
        }
    }
}
