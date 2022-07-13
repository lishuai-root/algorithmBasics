package leetcode;

/**
 * @description: Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 * <p>
 * In one step, you can delete exactly one character in either string.
 * @author: LISHUAI
 * @createDate: 2022/6/14 10:55
 * @version: 1.0
 */

public class LeetCode_583 {

    public static void main(String[] args) {
//        String word1 = "park", word2 = "spake";
        String word1 = "dinitrophenylhydrazine", word2 = "acetylphenylhydrazine";
//        String word1 = "leetcode", word2 = "etco";
        int i = minDistance_dp(word1, word2);
        System.out.println(i);
    }

    public static int minDistance(String word1, String word2) {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        return minDistanceProcess(chars1, chars2, 0, 0);
    }

    private static int minDistanceProcess(char[] chars1, char[] chars2, int index1, int index2) {
        if (index1 >= chars1.length) {
            return chars2.length - index2;
        }
        if (index2 >= chars2.length) {
            return chars1.length - index1;
        }
        int p3 = Integer.MAX_VALUE;
        if (chars1[index1] == chars2[index2]) {
            p3 = minDistanceProcess(chars1, chars2, index1 + 1, index2 + 1);
        }

        int p1 = minDistanceProcess(chars1, chars2, index1 + 1, index2);
        int p2 = minDistanceProcess(chars1, chars2, index1, index2 + 1);
        return Math.min(Math.min(p1, p2) + 1, p3);
    }

    public static int minDistance_dp(String word1, String word2) {
        int row = word1.length(), col = word2.length();
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i < col; i++) {
            dp[row][i] = col - i;
        }

        for (int i = row - 1; i >= 0; --i) {
            dp[i][col] = row - i;
            for (int j = col - 1; j >= 0; --j) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]) + 1, dp[i + 1][j + 1]);
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }

        return dp[0][0];
    }
}











