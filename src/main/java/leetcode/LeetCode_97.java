package leetcode;

/**
 * @description: Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * <p>
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 * @author: LISHUAI
 * @createDate: 2022/7/7 10:27
 * @version: 1.0
 */

public class LeetCode_97 {

    private static Boolean[][][] cache;

    public static void main(String[] args) {
//        String s1 = "a", s2 = "b", s3 = "a";
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";
//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        boolean b = isInterleave_dp(s1, s2, s3);
        System.out.println(b);
        b = isInterleave(s1, s2, s3);
        System.out.println(b);
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        cache = new Boolean[s1.length() + 1][s2.length() + 1][s3.length()];
        return isInterleaveProcess(s1, s2, s3, 0, 0);
    }

    private static boolean isInterleaveProcess(String s1, String s2, String s3, int index1, int index2, int index3) {
        if (index3 >= s3.length()) {
            return true;
        }
        if (cache[index1][index2][index3] != null) {
            return cache[index1][index2][index3];
        }

        boolean ans = false;
        if (index1 < s1.length() && s1.charAt(index1) == s3.charAt(index3)) {
            ans = isInterleaveProcess(s1, s2, s3, index1 + 1, index2, index3 + 1);
        }

        if (!ans && index2 < s2.length() && s2.charAt(index2) == s3.charAt(index3)) {
            ans = isInterleaveProcess(s1, s2, s3, index1, index2 + 1, index3 + 1);
        }
        cache[index1][index2][index3] = ans;
        return ans;
    }

    private static boolean isInterleaveProcess(String s1, String s2, String s3, int index1, int index3) {
        if (index3 >= s3.length()) {
            return true;
        }
        int index2 = index3 - index1;
        if (cache[index1][index2][index3] != null) {
            return cache[index1][index2][index3];
        }

        boolean ans = false;
        if (index1 < s1.length() && s1.charAt(index1) == s3.charAt(index3)) {
            ans = isInterleaveProcess(s1, s2, s3, index1 + 1, index3 + 1);
        }

        if (!ans && index2 < s2.length() && s2.charAt(index2) == s3.charAt(index3)) {
            ans = isInterleaveProcess(s1, s2, s3, index1, index3 + 1);
        }
        cache[index1][index2][index3] = ans;
        return ans;
    }

    private static boolean equals(String s1, String s2, int index1, int index2) {
        if (s1.length() - index1 != s2.length() - index2) {
            return false;
        }
        int len = s1.length() - index1;
        for (int i = 0; i < len; i++) {
            if (s1.charAt(index1 + i) != s2.charAt(index2 + i)) {
                return false;
            }
        }
        return true;
    }


    public static boolean isInterleave_dp(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        String smallStr = len1 <= len2 ? s1 : s2;
        String longStr = len2 >= len1 ? s2 : s1;
        len1 = smallStr.length();
        boolean[] dp = new boolean[len1 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i] = true;
        }
        char[] chars1 = smallStr.toCharArray(), chars2 = longStr.toCharArray(), chars3 = s3.toCharArray();

        for (int i = len3 - 1; i >= 0; i--) {
            int size = Math.min(len1, i);
            for (int j = 0; j <= size; j++) {
                int index2 = i - j;
                boolean ans = false;
                if (j < len1 && chars1[j] == chars3[i]) {
                    ans = dp[j + 1];
                }
                if (!ans && index2 < chars2.length && chars2[index2] == chars3[i]) {
                    ans = dp[j];
                }
                dp[j] = ans;
            }
        }
        return dp[0];
    }
}








