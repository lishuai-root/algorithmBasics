package leetcode;

/**
 * @description: Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * <p>
 * Implement the WordDictionary class:
 * <p>
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 * @author: LISHUAI
 * @createDate: 2023/3/18 20:01
 * @version: 1.0
 */

public class LeetCode_211 {

    class WordDictionary {

        private Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.childes[c - 'a'] == null) {
                    node.childes[c - 'a'] = new Node();
                }
                node = node.childes[c - 'a'];
            }
            node.isWord = true;
        }

        public boolean search(String word) {
            return search(root, word, 0);
        }

        private boolean search(Node node, String word, int start) {
            while (start < word.length()) {
                char c = word.charAt(start++);
                if (c == '.') {
                    for (int i = 0; i < node.childes.length; i++) {
                        if (node.childes[i] != null && search(node.childes[i], word, start)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    if (node.childes[c - 'a'] == null) {
                        return false;
                    }
                    node = node.childes[c - 'a'];
                }
            }
            return node.isWord;
        }

        class Node {
            Node[] childes;
            boolean isWord;

            public Node() {
                childes = new Node[26];
            }
        }
    }
}
