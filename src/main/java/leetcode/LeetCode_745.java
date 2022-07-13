package leetcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @description: Design a special dictionary that searches the words in it by a prefix and a suffix.
 * <p>
 * Implement the WordFilter class:
 * <p>
 * WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 * @author: LISHUAI
 * @createDate: 2022/6/23 10:12
 * @version: 1.0
 */

public class LeetCode_745 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/stringArr.txt"));
        String line = scanner.nextLine();
        String[] split = line.split(",");
        WordFilter wordFilter = new WordFilter(split);
        int f = wordFilter.f("aaaababba", "aababbab");
        System.out.println(f);
        System.out.println(split[578]);
        System.out.println(split[248]);
    }

    static class WordFilter {
        PrefixTree root;

        public WordFilter(String[] words) {
            root = makePrefixTree(words);
        }

        public int f(String prefix, String suffix) {
            PrefixTree node = getNode(root, prefix);
            int ans = -1;
            if (node != null) {
                ans = fProcess(node, suffix);
            }
            return ans;
        }

        public int fProcess(PrefixTree root, String suffix) {
            if (root == null) {
                return -1;
            }

            int ans = -1;
            if (root.isEnd && getFProcess(root, suffix, suffix.length() - 1)) {
                ans = root.index;
            }
            for (PrefixTree node : root.values) {
                ans = Math.max(ans, fProcess(node, suffix));
            }
            return ans;
        }

        public boolean getFProcess(PrefixTree root, String suffix, int index) {
            if (index < 0) {
                return true;
            }

            if (root == null) {
                return false;
            }

            if (root.pre.values[suffix.charAt(index) - 'a'] != root) {
                return false;
            }
            return getFProcess(root.pre, suffix, index - 1);
        }


        public PrefixTree getNode(PrefixTree root, String prefix) {
            for (int i = 0; i < prefix.length(); i++) {
                int cur = prefix.charAt(i) - 'a';
                if (root.values[cur] == null) {
                    return null;
                }
                root = root.values[cur];
            }
            return root;
        }

        public PrefixTree makePrefixTree(String[] words) {
            PrefixTree root = new PrefixTree(null);
            for (int i = 0; i < words.length; i++) {
                String line = words[i];
                PrefixTree node = root;
                for (int j = 0; j < line.length(); j++) {
                    int cur = line.charAt(j) - 'a';
                    if (node.values[cur] == null) {
                        node.values[cur] = new PrefixTree(node);
                    }
                    node = node.values[cur];
                }
                node.isEnd = true;
                node.index = Math.max(node.index, i);
            }

            return root;
        }
    }

    static class PrefixTree {
        PrefixTree[] values;
        boolean isEnd;
        PrefixTree pre;
        int index;

        public PrefixTree(PrefixTree pre) {
            this.values = new PrefixTree[26];
            this.isEnd = false;
            this.pre = pre;
            this.index = 0;
        }
    }
}
