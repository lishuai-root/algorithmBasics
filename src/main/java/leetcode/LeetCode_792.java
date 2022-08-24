package leetcode;

/**
 * @description: Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * <p>
 * For example, "ace" is a subsequence of "abcde".
 * @author: LISHUAI
 * @createDate: 2022/7/20 18:20
 * @version: 1.0
 */

public class LeetCode_792 {

    public static void main(String[] args) {
        String s = "dsahjpjauf";
        String[] words = {"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"};
        int i = numMatchingSubseq_03(s, words);
        System.out.println(i);
    }

    /**
     * 这是一个注释
     *
     * @param s
     * @param words
     * @return
     */
    public static int numMatchingSubseq(String s, String[] words) {
        int[] indexes = new int[words.length];
        int ans = 0, len = s.length();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            for (int j = 0; j < indexes.length; j++) {
                if (indexes[j] < words[j].length() && c == words[j].charAt(indexes[j])) {
                    ++indexes[j];
                    if (indexes[j] >= words[j].length()) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }


    public static int numMatchingSubseq_02(String s, String[] words) {

        int head = 0, tail = 0, sLen = s.length(), wLen = words.length;
        int queueLen = (1 << (32 - Integer.numberOfLeadingZeros(wLen - 1))) - 1;
        int[][] indexes = new int[queueLen + 1][2];
        int size = wLen, ans = 0;
        for (int i = 0; i < wLen; i++) {
            indexes[tail++] = new int[]{i, 0};
        }
        tail &= queueLen;

        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            int k = size;
            size = 0;
            for (int j = 0; j < k; j++) {
                int[] cur = indexes[head++];
                head &= queueLen;
                String line = words[cur[0]];
                if (c == line.charAt(cur[1])) {
                    cur[1]++;
                    if (cur[1] >= line.length()) {
                        ans++;
                        continue;
                    }
                }
                indexes[tail++] = cur;
                tail &= queueLen;
                size++;
            }
        }
        return ans;
    }

    public static int numMatchingSubseq_03(String s, String[] words) {
        int head = 0, tail = 0, sLen = s.length(), wLen = words.length;
        int queueLen = (1 << (32 - Integer.numberOfLeadingZeros(wLen - 1))) - 1;
        PrefixNode[] queue = new PrefixNode[queueLen + 1];
        PrefixNode root = makePrefix(words);
        queue[tail++] = root;
        int size = tail, ans = 0;

        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            int k = size;
            size = 0;
            for (int j = 0; j < k; j++) {
                PrefixNode node = queue[head++];
                head &= queueLen;
                if (node.values[c - 'a'] != null) {
                    queue[tail++] = node.values[c - 'a'];
                    size++;
                    tail &= queueLen;
                    if (node.values[c - 'a'].isEnd) {
                        ans += node.values[c - 'a'].size;
                    }
                    node.values[c - 'a'] = null;
                    node.valSize--;
                }
                if (node.valSize > 0) {
                    size++;
                    queue[tail++] = node;
                    tail &= queueLen;
                }
            }
        }
        return ans;
    }

    private static PrefixNode makePrefix(String[] words) {
        PrefixNode root = new PrefixNode('0');
        for (String line : words) {
            PrefixNode node = root;
            for (int j = 0; j < line.length(); j++) {
                int c = line.charAt(j) - 'a';
                if (node.values[c] == null) {
                    node.values[c] = new PrefixNode(line.charAt(j));
                    node.valSize++;
                }
                node = node.values[c];
                node.count++;
            }
            node.isEnd = true;
            node.size++;
        }

        return root;
    }

    static class PrefixNode {
        PrefixNode[] values;
        char val;
        boolean isEnd = false;
        int size = 0, count = 0, valSize = 0;

        public PrefixNode(char c) {
            this.values = new PrefixNode[26];
            this.val = c;
        }
    }
}
