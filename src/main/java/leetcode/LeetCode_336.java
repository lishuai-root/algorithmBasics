package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.
 * @author: LISHUAI
 * @createDate: 2022/9/17 22:32
 * @version: 1.0
 */

public class LeetCode_336 {

    public static void main(String[] args) {
//        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        String[] words = {"a", ""};
        List<List<Integer>> lists = palindromePairs(words);
        for (List<Integer> list : lists) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        PrefixTree root = makePrefixTree(words);
        List<List<Integer>> ans = new ArrayList<>(words.length);
        char[] chars = new char[301];
        int empty = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == 0) {
                empty = i;
                break;
            }
        }

        for (int i = 0; i < words.length; i++) {
            List<Integer> list = new ArrayList<Integer>();
            PrefixTree node = searchNode(root, words[i], list);
            searchNodeProcess(node, chars, -1, list);
            for (int j : list) {
                if (i != j) {
                    ArrayList<Integer> l = new ArrayList<>();
                    l.add(i);
                    l.add(j);
                    ans.add(l);
                }
            }
            if (empty != -1 && palindromeString(words[i].toCharArray(), 0, words[i].length() - 1)) {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(i);
                l.add(empty);
                ans.add(l);
            }
        }
        return ans;
    }

    private static void searchNodeProcess(PrefixTree node, char[] chars, int index, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.end && palindromeString(chars, 0, index)) {
            list.add(node.index);
        }

        int c = node.bit;
        while (c != 0) {
            int x = -c & c;
            int y = 32 - Integer.numberOfLeadingZeros(x - 1);
            PrefixTree n = node.values[y];
            chars[++index] = (char) (y + 'a');
            searchNodeProcess(n, chars, index, list);
            index--;
            c ^= x;
        }
    }

    private static boolean palindromeString(char[] chars, int left, int right) {
        if (right < left) {
            return false;
        }
        while (left <= right && chars[left] == chars[right]) {
            left++;
            right--;
        }
        return left > right;
    }

    private static PrefixTree searchNode(PrefixTree root, String line, List<Integer> list) {
        char[] chars = line.toCharArray();
        for (int i = 0; i < line.length(); i++) {
            int x = chars[i] - 'a';
            if (root.values[x] == null) {
                return null;
            }
            root = root.values[x];
            if (root.end && palindromeString(chars, i + 1, line.length() - 1)) {
                list.add(root.index);
            }
        }
        if (root.end) {
            list.add(root.index);
        }
        return root;
    }

    private static PrefixTree makePrefixTree(String[] words) {
        PrefixTree root = new PrefixTree();
        for (int i = 0; i < words.length; i++) {
            PrefixTree node = addTree(root, words[i]);
            node.end = true;
            node.index = i;
        }
        return root;
    }

    private static PrefixTree addTree(PrefixTree root, String line) {
        char[] chars = line.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            int x = chars[i] - 'a';
            if (root.values[x] == null) {
                root.values[x] = new PrefixTree();
            }
            root.bit |= (1 << x);
            root = root.values[x];
        }
        root.end = true;
        return root;
    }


    static class PrefixTree {
        boolean end;
        PrefixTree[] values;
        int index;

        int bit;
        List<Integer> palindrome;

        public PrefixTree() {
            this.values = new PrefixTree[26];
            this.index = -1;
            this.end = false;
            this.palindrome = new ArrayList<>();
            this.bit = 0;
        }
    }
}
