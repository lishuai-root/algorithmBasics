package leetcode;

import java.util.HashMap;

/**
 * @description: Given a string s, return the number of different non-empty palindromic subsequences in s. Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * A subsequence of a string is obtained by deleting zero or more characters from the string.
 * <p>
 * A sequence is palindromic if it is equal to the sequence reversed.
 * <p>
 * Two sequences a1, a2, ... and b1, b2, ... are different if there is some i for which ai != bi.
 * @author: LISHUAI
 * @createDate: 2023/4/24 20:41
 * @version: 1.0
 */

public class LeetCode_730 {

    /**
     * teacher method case
     *
     * @param str
     * @return
     */
    public static int countPalindromicSubsequences(String str) {
        int mod = 1000000007;
        char[] s = str.toCharArray();
        int n = s.length;
        int[] right = new int[n];
        int[] left = new int[n];
        HashMap<Character, Integer> last = new HashMap<>();
        for (int i = 0; i < n; i++) {
            left[i] = last.getOrDefault(s[i], -1);
            last.put(s[i], i);
        }
        last.clear();
        for (int i = n - 1; i >= 0; i--) {
            right[i] = last.getOrDefault(s[i], n);
            last.put(s[i], i);
        }
        long[][] dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s[i] == s[j]) {
                    int l = Math.min(j, right[i]);
                    int r = Math.max(i, left[j]);
                    if (l > r) { // 内部不再有l和r位置的字符了！
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    } else if (l == r) { // 内部仅有一个！
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    } else { // 内部有>=2个
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[l + 1][r - 1] + mod;
                    }
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1] + mod;
                }
                dp[i][j] %= mod;
            }
        }
        return (int) dp[0][n - 1];
    }
}
