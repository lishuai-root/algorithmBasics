package leetcode;

/**
 * @description: You are given a list of strings of the same length words and a string target.
 * <p>
 * Your task is to form target using the given words under the following rules:
 * <p>
 * target should be formed from left to right.
 * To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
 * Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
 * Repeat the process until you form the string target.
 * Notice that you can use multiple characters from the same string in words provided the conditions above are met.
 * <p>
 * Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2023/4/16 14:48
 * @version: 1.0
 */

public class LeetCode_1639 {

    private static final int MOD = 1000000007;
    private static Integer[][] cache;

    public static int numWays(String[] words, String target) {
        int len = words[0].length();
        cache = new Integer[len][target.length()];
        return numWaysProcess(words, target, 0, len, 0);
    }

    private static int numWaysProcess(String[] words, String target, int k, int len, int index) {
        if (index >= target.length()) {
            return 1;
        }
        if (k >= len) {
            return 0;
        }
        if (cache[k][index] != null) {
            return cache[k][index];
        }
        int ans = numWaysProcess(words, target, k + 1, len, index) % MOD;

        for (String word : words) {
            if (word.charAt(k) == target.charAt(index)) {
                ans += numWaysProcess(words, target, k + 1, len, index + 1);
                ans %= MOD;
            }
        }
        cache[k][index] = ans;
        return ans;
    }


    public static int numWays_dp(String[] words, String target) {
        int len = words[0].length(), size = target.length();
        int[] dp = new int[size + 1];
        dp[size] = 1;
        int[][] sum = new int[len][26];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                sum[i][c]++;
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            int p = Math.max(0, size - len + i);
            for (int j = p; j < size; j++) {
                int c = target.charAt(j) - 'a';
                long l = (long) sum[i][c] * dp[j + 1];
                dp[j] += (l % MOD);
                dp[j] %= MOD;
            }
        }
        return dp[0];
    }
}
