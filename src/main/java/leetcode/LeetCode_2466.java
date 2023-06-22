package leetcode;

/**
 * @description: Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:
 * <p>
 * Append the character '0' zero times.
 * Append the character '1' one times.
 * This can be performed any number of times.
 * <p>
 * A good string is a string constructed by the above process having a length between low and high (inclusive).
 * <p>
 * Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2023/5/13 23:58
 * @version: 1.0
 */

public class LeetCode_2466 {

    private static final int MOD = 1000000007;

    public static int countGoodStrings(int low, int high, int zero, int one) {
        return countGoodStringsProcess(low, high, zero, one, 0);
    }

    private static int countGoodStringsProcess(int low, int high, int zero, int one, int index) {
        if (index > high) {
            return 0;
        }
        int p1 = countGoodStringsProcess(low, high, zero, one, index + zero);
        int p2 = countGoodStringsProcess(low, high, zero, one, index + one);
        return (p1 + p2 + (index >= low ? 1 : 0)) % MOD;
    }


    public static int countGoodStrings_dp(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];

        for (int i = high; i >= 0; i--) {
            int p1 = (i + zero) > high ? 0 : dp[i + zero];
            int p2 = (i + one) > high ? 0 : dp[i + one];
            dp[i] = (p1 + p2 + (i >= low ? 1 : 0)) % MOD;
        }
        return dp[0];
    }
}

