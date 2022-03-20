package leetcode;

/**
 * @description: Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * <p>
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 * @author: LISHUAI
 * @createDate: 2022/3/2 20:13
 * @version: 1.0
 */

public class LeetCode_392 {

    public static boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        int sIndex = 0, tIndex = 0;

        while (sIndex < s.length() && tIndex < t.length()) {
            while (tIndex < t.length() && s.charAt(sIndex) != t.charAt(tIndex)) {
                tIndex++;
            }
            if (tIndex >= t.length()) {
                return false;
            }
            sIndex++;
            tIndex++;
        }
        return sIndex == s.length();
    }
}















