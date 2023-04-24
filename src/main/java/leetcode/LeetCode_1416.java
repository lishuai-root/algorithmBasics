package leetcode;

/**
 * @description: A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is printed as a string of digits s and all we know is that all integers in the array were in the range [1, k] and there are no leading zeros in the array.
 * <p>
 * Given the string s and the integer k, return the number of the possible arrays that can be printed as s using the mentioned program. Since the answer may be very large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2023/4/23 19:52
 * @version: 1.0
 */

public class LeetCode_1416 {
    private static final int MOD = 1000000007;
    private static String INTEGER_MAX;
    private static Integer[][] cache;

    public static void main(String[] args) {
//        String s = "1000";
//        int k = 10000;
        String s = "29332377127524136126230870622335669657843168529455118482387145131383156269464392206602503504142";
        int k = 818;
        int i = numberOfArrays_dp(s, k);
        System.out.println(i);
    }

    public static int numberOfArrays(String s, int k) {
        INTEGER_MAX = k + "";
        cache = new Integer[s.length()][s.length()];
        return numberOfArraysProcess(s, 0, 0);
    }

    private static int numberOfArraysProcess(String s, int start, int end) {
        if (start >= s.length()) {
            return 1;
        }
        if (s.charAt(start) == '0' || end >= s.length()) {
            return 0;
        }
        if (cache[start][end] != null) {
            return cache[start][end];
        }
        int ans = numberOfArraysProcess(s, start, end + 1) % MOD;
        if (canCase(s, start, end, INTEGER_MAX)) {
            ans = (ans + numberOfArraysProcess(s, end + 1, end + 1)) % MOD;
        }
        cache[start][end] = ans;
        return ans;
    }

    private static boolean canCase(String s, int start, int end, String max) {
        int len = end - start + 1;
        if (len < max.length()) {
            return true;
        }
        if (len > max.length()) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (s.charAt(start + i) < max.charAt(i)) {
                return true;
            } else if (s.charAt(start + i) > max.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    public static int numberOfArrays_dp(String s, int k) {
        String ks = k + "";
        int len = s.length();
        int size = ks.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        long sum = 1;

        for (int i = len - 1; i >= 0; i--) {
            int j = Math.min(size, len - i);
            if (s.charAt(i) != '0') {
                if (canCase(s, i, i + j - 1, ks)) {
                    dp[i] = (int) (sum % MOD);
                } else {
                    dp[i] = (int) ((sum - dp[i + j]) % MOD);
                }
            }
            if (j == size) {
                sum -= dp[i + j];
            }
            sum += dp[i];
        }
        return dp[0];
    }
}
