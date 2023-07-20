package leetcode;

/**
 * @description: You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time).
 * <p>
 * Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps. Since the answer may be too large, return it modulo 109 + 7.
 * @author: LiShuai
 * @createDate: 2023/6/23 21:24
 * @version: 1.0
 */

public class LeetCode_1269 {

    public static int numWays(int steps, int arrLen) {
        int len = Math.min(steps / 2 + 1, arrLen), t = 1000000007;
        int[][] dp = new int[steps + 1][len + 1];
        dp[steps][0] = 1;

        for (int i = steps - 1; i >= 0; --i) {
            dp[i][0] = (dp[i + 1][0] + dp[i + 1][1]) % t;
            for (int j = 1; j < len; j++) {
                dp[i][j] = ((dp[i + 1][j] + dp[i + 1][j + 1]) % t + dp[i + 1][j - 1]) % t;
            }
        }
        return dp[0][0];
    }

    public static int numWays_02(int steps, int arrLen) {
        int len = Math.min(steps / 2 + 1, arrLen), t = 1000000007;
        int[] dp = new int[len + 1];
        dp[0] = 1;

        for (int i = steps - 1; i >= 0; --i) {
            int p = dp[0];
            dp[0] = (dp[0] + dp[1]) % t;
            for (int j = 1; j < len; j++) {
                int q = dp[j];
                dp[j] = ((p + dp[j + 1]) % t + dp[j]) % t;
                p = q;
            }
        }
        return dp[0];
    }
}
