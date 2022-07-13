package leetcode;

/**
 * @description: Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * <p>
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * @author: LISHUAI
 * @createDate: 2022/7/11 22:14
 * @version: 1.0
 */

public class LeetCode_1143 {

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
//        String text1 = "abc", text2 = "abc";
//        String text1 = "abc", text2 = "def";
        int i = longestCommonSubsequence_dp(text1, text2);
        System.out.println(i);
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        return longestCommonSubsequenceProcess(text1, text2, 0, 0);
    }

    private static int longestCommonSubsequenceProcess(String text1, String text2, int index1, int index2) {
        if (index1 >= text1.length() || index2 >= text2.length()) {
            return 0;
        }

        int p1 = longestCommonSubsequenceProcess(text1, text2, index1, index2 + 1);
        int p2 = longestCommonSubsequenceProcess(text1, text2, index1 + 1, index2);
        int p3 = 0;
        if (text1.charAt(index1) == text2.charAt(index2)) {
            p3 = longestCommonSubsequenceProcess(text1, text2, index1 + 1, index2 + 1) + 1;
        }
        return Math.max(p1, Math.max(p2, p3));
    }

    public static int longestCommonSubsequence_dp(String text1, String text2) {
        char[] chars1 = text1.toCharArray(), chars2 = text2.toCharArray();
        int len1 = chars1.length, len2 = chars2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int p1 = Math.max(dp[i][j + 1], dp[i + 1][j]);
                int p2 = 0;
                if (chars1[i] == chars2[j]) {
                    p2 = dp[i + 1][j + 1] + 1;
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][0];
    }
}
