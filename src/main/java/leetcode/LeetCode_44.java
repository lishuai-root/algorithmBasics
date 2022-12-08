package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/7/26 21:37
 * @version: 1.0
 */

public class LeetCode_44 {

    public static void main(String[] args) {
        fn_001();
    }

    private static void fn_001() {
//        String s = "aa", p = "a";
//        String s = "adceb", p = "*a*b";
//        String s = "abcabczzzde", p = "*abc???de*";
//        String s = "abcdefde", p = "abc*def";
        String s = "babaaababaabababbbbbbaabaabbabababbaababbaaabbbaaab", p = "***bba**a*bbba**aab**b";

        boolean match = isMatch(s, p);
        System.out.println(match);
        System.out.println(isMatch_dp(s, p));
        System.out.println(isMatch_dp_02(s, p));

    }

    /**
     * 错误的
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        return isMatchProcess(s, p, 0, 0);
    }

    public static boolean isMatchProcess(String s, String p, int startS, int startP) {
        if (startP >= p.length()) {
            return startS >= s.length();
        }

        boolean ans = false;
        if (startS < s.length() && (s.charAt(startS) == p.charAt(startP) || p.charAt(startP) == '?')) {
            ans = isMatchProcess(s, p, startS + 1, startP + 1);
        } else if (p.charAt(startP) == '*') {
            ans = isMatchProcess(s, p, startS, startP + 1);
            if (!ans && startS < s.length()) {
                ans = isMatchProcess(s, p, startS + 1, startP)
                        | isMatchProcess(s, p, startS + 1, startP + 1);
            }
        }
        return ans;
    }

    public static boolean isMatch_dp(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        char[] sc = s.toCharArray(), pc = p.toCharArray();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[sLen][pLen] = true;
        for (int i = pLen - 1; i >= 0; i--) {
            if (pc[i] == '*') {
                dp[sLen][i] = dp[sLen][i + 1];
            }
        }

        for (int i = sLen - 1; i >= 0; i--) {
            for (int j = pLen - 1; j >= 0; j--) {
                if (pc[j] == '?' || sc[i] == pc[j]) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (pc[j] == '*') {
                    dp[i][j] = dp[i][j + 1] | dp[i + 1][j + 1] | dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }


    public static boolean isMatch_dp_02(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        char[] sc = s.toCharArray(), pc = p.toCharArray();
        boolean[][] dp = new boolean[2][pLen + 1];
        int cur = 0, pre = 1;
        dp[pre][pLen] = true;
        for (int i = pLen - 1; i >= 0; i--) {
            if (pc[i] == '*') {
                dp[pre][i] = dp[pre][i + 1];
            }
        }

        for (int i = sLen - 1; i >= 0; i--) {
            for (int j = pLen - 1; j >= 0; j--) {
                if (pc[j] == '?' || sc[i] == pc[j]) {
                    dp[cur][j] = dp[pre][j + 1];
                } else if (pc[j] == '*') {
                    dp[cur][j] = dp[cur][j + 1] | dp[pre][j];
                } else {
                    dp[cur][j] = false;
                }
            }
            pre = cur;
            cur = (++cur) & 1;
            dp[pre][pLen] = false;
        }
        return dp[pre][0];
    }

}