package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * @author: LISHUAI
 * @createDate: 2021/11/22 19:48
 * @version: 1.0
 */

public class LeetCode_139 {
    public static void main(String[] args) {
        String s = "abcd";

        List<String> wordDict = new ArrayList<>();

        wordDict.add("a");
        wordDict.add("abc");
        wordDict.add("b");
        wordDict.add("cd");
//        wordDict.add("aaaaaa");
//        wordDict.add("aaaaaaa");
//        wordDict.add("aaaaaaaa");
//        wordDict.add("aaaaaaaaa");
//        wordDict.add("aaaaaaaaaa");
//        wordDict.add("ba");


        boolean b = wordBreak_02(s, wordDict);

        System.out.println(b);
    }

    public static boolean wordBreak(String s, List<String> wordDict) {

        for (int i = 0; i < wordDict.size(); i++) {

            if (wordDict.get(i).equals(s.substring(0, wordDict.get(i).length()))) {

                boolean process = process(s, wordDict.get(i).length(), s.length(), wordDict);

                if (process) {

                    return true;
                }
            }
        }

        return false;
    }

    private static boolean process(String s, int left, int right, List<String> wordDict) {

        if (left >= right) {

            return true;
        }

        boolean bl = false;

        String line;

        int len;

        for (int i = 0; !bl && i < wordDict.size(); i++) {

            int l = left, r = right;

            line = wordDict.get(i);

            len = line.length();

            for (int j = 1; !bl && len * j <= r - l &&
                    line.equals(s.substring(l, l + len)); j++) {

                l += wordDict.get(i).length();

                bl = bl || process(s, l, r, wordDict);

            }
        }


        return bl;
    }

    public static boolean wordBreak_02(String s, List<String> wordDict) {

        Node head = new Node(), node;

        int len = s.length();

        int[] dp = new int[len + 1];

        char[] chars;

        int index;

        for (String word : wordDict) {

            node = head;

            chars = word.toCharArray();

            for (char c : chars) {

                if (node.nexts[c - 'a'] == null) {

                    node.nexts[c - 'a'] = new Node();
                }

                node = node.nexts[c - 'a'];
            }

            node.isWord = true;
        }

        chars = s.toCharArray();

        dp[len] = 1;

        for (int i = len - 1; i >= 0; i--) {

            node = head;

            for (int j = i; j < len; j++) {

                node = node.nexts[chars[j] - 'a'];

                if (node == null) {

                    break;
                }

                if (node.isWord) {

                    dp[i] += dp[j + 1];
                }
            }
        }

        for (int i : dp) {

            System.out.print(i + "   ");
        }

        System.out.println();

        return dp[0] != 0;
    }
//
//    public static boolean wordBreak_teacher(String s, List<String> wordDict) {
//        Node root = new Node();
//        for (String str : wordDict) {
//            char[] chs = str.toCharArray();
//            Node node = root;
//            int index = 0;
//            for (int i = 0; i < chs.length; i++) {
//                index = chs[i] - 'a';
//                if (node.nexts[index] == null) {
//                    node.nexts[index] = new Node();
//                }
//                node = node.nexts[index];
//            }
//            node.end = true;
//        }
//        char[] str = s.toCharArray();
//        int N = str.length;
//        int[] dp = new int[N + 1];
//        dp[N] = 1;
//        for (int i = N - 1; i >= 0; i--) {
//            Node cur = root;
//            for (int end = i; end < N; end++) {
//                cur = cur.nexts[str[end] - 'a'];
//                if (cur == null) {
//                    break;
//                }
//                if (cur.end) {
//                    dp[i] += dp[end + 1];
//                }
//            }
//        }
//        return dp[0] != 0;
//    }

    public static class Node {

        Node[] nexts;

        boolean isWord;

        public Node() {

            nexts = new Node[26];

            isWord = false;
        }
    }
}
