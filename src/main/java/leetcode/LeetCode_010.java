package leetcode;

/**
 * @description: https://leetcode.com/problems/regular-expression-matching/
 * <p>
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 * @author: LISHUAI
 * @createDate: 2021/11/9 22:08
 * @version: 1.0
 */

public class LeetCode_010 {

    public static void main(String[] args) {

//        String s = "aa", p = "a";
//        String s = "aaa", p = "aaaa";
//        String s = "aab", p = "c*a*b";
        String s = "ab", p = ".*..";
//        String s = "mississippi", p = "mis*is*p*.";
//        String s = "ab", p = ".*c";

        boolean match = isMatch(s, p);

        System.out.println(match);
        System.out.println(isMatch_dp(s, p));

    }

    public static boolean isMatch(String s, String p) {
        boolean ans = false;
        ans = isMatchProcess(s, p, s.length() - 1, p.length() - 1);
        return ans;
    }

    private static boolean isMatchProcess(String s, String p, int startS, int startP) {
        if (startP < 0) {
            return startS < 0;
        }

        boolean ans = false;
        if (startS >= 0 && (p.charAt(startP) == '.' || s.charAt(startS) == p.charAt(startP))) {
            ans = isMatchProcess(s, p, startS - 1, startP - 1);
        } else if (p.charAt(startP) == '*') {
            ans = isMatchProcess(s, p, startS, startP - 2);
            if (!ans && startS >= 0 && (p.charAt(startP - 1) == '.' || s.charAt(startS) == p.charAt(startP - 1))) {
                ans = isMatchProcess(s, p, startS - 1, startP)
                        || isMatchProcess(s, p, startS - 1, startP - 2);
            }
        }
        return ans;
    }

    public static boolean isMatch_dp(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        char[] sc = s.toCharArray(), pc = p.toCharArray();
        dp[0][0] = true;

        for (int i = -1; i < sLen; i++) {
            for (int j = 0; j < pLen; j++) {
                boolean ans = false;
                if (i >= 0 && (pc[j] == '.' || sc[i] == pc[j])) {
                    ans = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    ans = dp[i + 1][j - 1];
                    if (!ans && i >= 0 && (pc[j - 1] == '.' || sc[i] == pc[j - 1])) {
                        ans = dp[i][j + 1] || dp[i][j - 1];
                    }
                }
                dp[i + 1][j + 1] = ans;
            }
        }
        return dp[sLen][pLen];
    }

}




