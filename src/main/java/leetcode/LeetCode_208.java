package leetcode;

/**
 * @description: A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
 * There are various applications of this data structure, such as autocomplete and spellchecker.
 * <p>
 * Implement the Trie class:
 * <p>
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 * @author: LISHUAI
 * @createDate: 2021/11/27 20:36
 * @version: 1.0
 */

public class LeetCode_208 {

    class Trie {

        private final Node head;

        public Trie() {

            head = new Node();
        }

        public void insert(String word) {

            Node node = head;

            char[] chars = word.toCharArray();

            int index;

            for (char c : chars) {

                index = c - 'a';

                if (node.nexts[index] == null) {

                    node.nexts[index] = new Node();
                }

                node = node.nexts[index];
            }

            node.isWord = true;
        }

        public boolean search(String word) {

            Node node = head;

            char[] chars = word.toCharArray();

            int index;

            for (char c : chars) {

                index = c - 'a';

                if (node.nexts[index] == null) {

                    return false;
                }

                node = node.nexts[index];
            }

            return node.isWord;
        }

        public boolean startsWith(String prefix) {

            Node node = head;

            char[] chars = prefix.toCharArray();

            int index;

            for (char c : chars) {

                index = c - 'a';

                if (node.nexts[index] == null) {

                    return false;
                }

                node = node.nexts[index];
            }

            return true;
        }

        class Node {

            Node[] nexts;

            boolean isWord = false;

            public Node() {

                nexts = new Node[26];
            }

        }
    }
}
