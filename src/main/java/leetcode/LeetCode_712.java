package leetcode;

/**
 * @description: Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
 * @author: LISHUAI
 * @createDate: 2022/6/14 11:43
 * @version: 1.0
 */

public class LeetCode_712 {

    public static void main(String[] args) {
//        String s1 = "sea", s2 = "eat";
        String s1 = "delete", s2 = "leet";
        int i = minimumDeleteSum(s1, s2);
        System.out.println(i);
    }

    public static int minimumDeleteSum(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int row = chars1.length, col = chars2.length;

        int[][] dp = new int[row + 1][col + 1];
        for (int i = col - 1; i >= 0; --i) {
            dp[row][i] = dp[row][i + 1] + chars2[i];
        }

        for (int i = row - 1; i >= 0; --i) {
            dp[i][col] = dp[i + 1][col] + chars1[i];
            for (int j = col - 1; j >= 0; --j) {
                if (chars1[i] == chars2[j]) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + chars1[i], dp[i][j + 1] + chars2[j]);
                }
            }
        }

        return dp[0][0];
    }
}
