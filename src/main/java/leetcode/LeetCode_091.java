package leetcode;

/**
 * @description: A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * <p>
 * s contains only digits and may contain leading zero(s).
 * @author: LISHUAI
 * @createDate: 2022/4/18 17:49
 * @version: 1.0
 */

public class LeetCode_091 {

    public static void main(String[] args) {
//        String s = "06";
//        String s = "226";
//        String s = "10";
//        String s = "12";
        String s = "111111111111111111111111111111111111111111111";
        int i = numDecodings_dp(s);
        System.out.println(i);
    }

    public static int numDecodings(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        return numDecodingsProcess(s, 0);
    }

    private static int numDecodingsProcess(String s, int index) {
        if (index >= s.length()) {
            return 1;
        }
        if (s.charAt(index) == '0') {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i <= 2 && index + i <= s.length(); i++) {
            if (Integer.parseInt(s.substring(index, index + i)) <= 26) {
                ans += numDecodingsProcess(s, index + i);
            }
        }
        return ans;
    }

    public static int numDecodings_dp(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];

        dp[s.length()] = 1;
        dp[s.length() - 1] = s.charAt(s.length() - 1) == '0' ? 0 : 1;
        for (int i = s.length() - 2; i >= 0; --i) {
            if (s.charAt(i) != '0') {
                dp[i] = dp[i + 1] + (Integer.parseInt(s.substring(i, i + 2)) <= 26 ? dp[i + 2] : 0);
            }
        }
        return dp[0];
    }
}
