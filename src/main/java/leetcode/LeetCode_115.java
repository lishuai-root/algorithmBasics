package leetcode;

/**
 * @description: Given two strings s and t, return the number of distinct
 * subsequences
 * of s which equals t.
 * <p>
 * The test cases are generated so that the answer fits on a 32-bit signed integer.
 * @author: LISHUAI
 * @createDate: 2023/4/2 21:39
 * @version: 1.0
 */

public class LeetCode_115 {

    private static Integer[][] cache;

    public static int numDistinct(String s, String t) {
        cache = new Integer[s.length()][t.length()];
        return numDistinctProcess(s, t, 0, 0);
    }

    private static int numDistinctProcess(String s, String t, int indexS, int indexT) {
        if (indexT >= t.length()) {
            return 1;
        }
        if (indexS >= s.length()) {
            return 0;
        }

        if (cache[indexS][indexT] != null) {
            return cache[indexS][indexT];
        }
        int p1 = numDistinctProcess(s, t, indexS + 1, indexT);
        int p2 = 0;
        if (s.charAt(indexS) == t.charAt(indexT)) {
            p2 = numDistinctProcess(s, t, indexS + 1, indexT + 1);
        }
        cache[indexS][indexT] = p1 + p2;
        return p1 + p2;
    }

    public static int numDistinct_dp(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        int[] dp = new int[tLen + 1];
        dp[tLen] = 1;

        for (int i = sLen - 1; i >= 0; i--) {
            int j = Math.max(0, tLen - sLen + i);
            for (; j < tLen; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[j] += dp[j + 1];
                }
            }
        }
        return dp[0];
    }
}
