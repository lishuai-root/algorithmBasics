package leetcode;

/**
 * @description: Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.
 * <p>
 * For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
 * <p>
 * Implement the StreamChecker class:
 * <p>
 * StreamChecker(String[] words) Initializes the object with the strings array words.
 * boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
 * @author: LISHUAI
 * @createDate: 2022/5/9 20:08
 * @version: 1.0
 */

public class LeetCode_1032 {

    public static void main(String[] args) {
        StringBuilder sbr = new StringBuilder();
        sbr.append('1');
        System.out.println(sbr.toString());
        sbr.append('2');
        System.out.println(sbr.toString());
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

    class StreamChecker {
        PrefixTree root;
        StringBuilder sbr;

        public StreamChecker(String[] words) {
            root = initPrefixTree(words);
            sbr = new StringBuilder();
        }

        private PrefixTree initPrefixTree(String[] words) {
            PrefixTree root = new PrefixTree();
            PrefixTree node;
            for (String s : words) {
                char[] chars = s.toCharArray();
                node = root;
                for (int i = chars.length - 1; i >= 0; i--) {
                    char c = chars[i];
                    if (node.values[c - 'a'] == null) {
                        node.values[c - 'a'] = new PrefixTree();
                    }
                    node = node.values[c - 'a'];
                    node.count++;
                }
                node.endCount++;
                node.isEnd = true;
            }
            return root;
        }

        private boolean exists(PrefixTree root, StringBuilder sbr) {
            PrefixTree node = root;
            for (int i = sbr.length() - 1; i >= 0; i--) {
                char c = sbr.charAt(i);
                if (node.values[c - 'a'] != null) {
                    node = node.values[c - 'a'];
                    if (node.isEnd) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
            return false;
        }

        public boolean query(char letter) {
            sbr.append(letter);
            return exists(root, sbr);
        }
    }
}
