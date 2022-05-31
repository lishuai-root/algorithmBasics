package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
 * <p>
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 * @author: LISHUAI
 * @createDate: 2022/5/9 18:06
 * @version: 1.0
 */

public class LeetCode_472 {

    private static PrefixTree treeRoot;

    public static void main(String[] args) {
//        String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        String[] words = {"cat", "dog", "catdog"};
        List<String> list = findAllConcatenatedWordsInADict(words);
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        treeRoot = makePrefixTree(words);
        List<String> ans = new ArrayList<>();

        for (String s : words) {
            char[] chars = s.toCharArray();
            remove(treeRoot, chars);
            if (findAllConcatenatedWordsInADictProcess(treeRoot, chars, 0)) {
                ans.add(s);
            }
            add(treeRoot, chars);
        }
        return ans;
    }

    private static boolean findAllConcatenatedWordsInADictProcess(PrefixTree node, char[] chars, int index) {
        if (index >= chars.length) {
            return node.isEnd;
        }
        node = node.values[chars[index] - 'a'];
        if (node == null || node.count == 0) {
            return false;
        }
        boolean ans = false;
        if (node.isEnd) {
            ans = findAllConcatenatedWordsInADictProcess(treeRoot, chars, index + 1);
        }
        if (!ans) {
            ans = findAllConcatenatedWordsInADictProcess(node, chars, index + 1);
        }
        return ans;
    }

    private static void remove(PrefixTree root, char[] chars) {
        PrefixTree node = root;
        for (char c : chars) {
            node = node.values[c - 'a'];
            node.count--;
        }
        node.endCount--;
        node.isEnd = node.endCount > 0;
    }

    private static void add(PrefixTree root, char[] chars) {
        PrefixTree node = root;
        for (char c : chars) {
            if (node.values[c - 'a'] == null) {
                node.values[c - 'a'] = new PrefixTree();
            }
            node.values[c - 'a'].count++;
            node = node.values[c - 'a'];
        }
        node.isEnd = true;
        node.endCount++;
    }

    private static PrefixTree makePrefixTree(String[] words) {
        PrefixTree root = new PrefixTree();
        for (String s : words) {
            char[] chars = s.toCharArray();
            add(root, chars);
        }
        return root;
    }

    static class PrefixTree {
        PrefixTree[] values;
        boolean isEnd;
        int count, endCount;

        public PrefixTree() {
            this.values = new PrefixTree[26];
            this.isEnd = false;
            this.count = 0;
            this.endCount = 0;
        }
    }
}
