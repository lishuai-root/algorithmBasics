package leetcode;

import java.util.Arrays;

/**
 * @description: Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 * <p>
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * @author: LISHUAI
 * @createDate: 2022/8/7 13:33
 * @version: 1.0
 */

public class LeetCode_1220 {


    private int a = 0, e = 1, i = 2, o = 3, u = 4;

    public int countVowelPermutation(int n) {
        int mod = 1_000_000_000 + 7;
        long dp[][] = new long[n + 1][5];
        Arrays.fill(dp[1], 1);

        for (int j = 2; j <= n; j++) {
            dp[j][a] = dp[j - 1][e];
            dp[j][e] = (dp[j - 1][a] + dp[j - 1][i]) % mod;
            dp[j][i] = (Arrays.stream(dp[j - 1]).sum() - dp[j - 1][i]) % mod;
            dp[j][o] = (dp[j - 1][i] + dp[j - 1][u]) % mod;
            dp[j][u] = dp[j - 1][a];
        }
        return (int) (Arrays.stream(dp[n]).sum() % mod);
    }
}
