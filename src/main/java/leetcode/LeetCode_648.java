package leetcode;

import java.util.List;

/**
 * @description: In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word successor. For example, when the root "an" is followed by the successor word "other", we can form a new word "another".
 * <p>
 * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the successors in the sentence with the root forming it. If a successor can be replaced by more than one root, replace it with the root that has the shortest length.
 * <p>
 * Return the sentence after the replacement.
 * @author: LiShuai
 * @createDate: 2023/7/13 22:13
 * @version: 1.0
 */

public class LeetCode_648 {

    public static void main(String[] args) {
        System.out.println((int) ' ');
    }

    public static String replaceWords(List<String> dictionary, String sentence) {
        dictionary.sort((a, b) -> a.length() - b.length());
        int len = sentence.length();
        StringBuilder sbr = new StringBuilder(len);
        int index = 0;
        while (index < len) {
            int i = sentence.indexOf(' ', index);
            if (i == -1) {
                i = len;
            }
            String work = sentence.substring(index, i);
            for (String str : dictionary) {
                if (work.startsWith(str)) {
                    work = str;
                    break;
                }
            }
            sbr.append(work)
                    .append(' ');
            index = i + 1;
        }
        if (sbr.charAt(sbr.length() - 1) == ' ') {
            sbr.delete(sbr.length() - 1, sbr.length());
        }
        return sbr.toString();
    }


    public static String replaceWords_02(List<String> dictionary, String sentence) {
        PreTree root = makeTree(dictionary);
        int len = sentence.length();
        StringBuilder sbr = new StringBuilder(len);
        int index = 0;
        while (index < len) {
            int i = sentence.indexOf(' ', index);
            if (i == -1) {
                i = len;
            }
            String work = findDictionary(root, sentence.substring(index, i));
            sbr.append(work)
                    .append(' ');
            index = i + 1;
        }
        if (sbr.charAt(sbr.length() - 1) == ' ') {
            sbr.delete(sbr.length() - 1, sbr.length());
        }
        return sbr.toString();
    }

    private static String findDictionary(PreTree root, String word) {
        PreTree node = root;
        for (int i = 0; i < word.length() && !node.isEnd; i++) {
            int c = word.charAt(i) - 'a';
            if (node.children[c] == null) {
                return word;
            }
            node = node.children[c];
        }
        return node.isEnd ? node.value : word;
    }

    private static PreTree makeTree(List<String> dictionary) {
        PreTree root = new PreTree();
        for (String str : dictionary) {
            PreTree node = root;
            for (int i = 0; i < str.length(); i++) {
                int c = str.charAt(i) - 'a';
                if (node.children[c] == null) {
                    node.children[c] = new PreTree();
                }
                node = node.children[c];
            }
            node.isEnd = true;
            node.value = str;
        }
        return root;
    }

    static class PreTree {
        PreTree[] children;
        boolean isEnd;

        String value;

        public PreTree() {
            this.children = new PreTree[26];
            this.isEnd = false;
        }
    }
}
