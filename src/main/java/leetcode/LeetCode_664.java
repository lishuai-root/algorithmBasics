package leetcode;

import java.util.Arrays;

/**
 * @description: There is a strange printer with the following two special properties:
 * <p>
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
 * Given a string s, return the minimum number of turns the printer needed to print it.
 * @author: LiShuai
 * @createDate: 2023/7/30 19:57
 * @version: 1.0
 */

public class LeetCode_664 {

    public int strangePrinter(String s) {
        int n = s.length();
        char[] sChar = s.toCharArray();
        int[][] dp = new int[n][n];
        for (int[] in : dp) Arrays.fill(in, -1);
        return Util(0, n - 1, sChar, dp);
    }

    public int Util(int i, int j, char[] sChar, int[][] dp) {
        if (i > j) {
            return 0;
        }

        if (dp[i][j] != -1) return dp[i][j];

        int firstLetter = sChar[i];
        // in case, current character is not repeated in the rest of the string
        int answer = 1 + Util(i + 1, j, sChar, dp);
        for (int k = i + 1; k <= j; k++) {
            // if repeated then update the answer
            if (sChar[k] == firstLetter) {
                // splitting from i -> k - 1(remove the last character)
                // and from k + 1 -> j
                int betterAnswer = Util(i, k - 1, sChar, dp) + Util(k + 1, j, sChar, dp);
                answer = Math.min(answer, betterAnswer);
            }
        }
        return dp[i][j] = answer;
    }
}
