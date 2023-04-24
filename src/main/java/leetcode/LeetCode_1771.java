package leetcode;

import java.util.Arrays;

/**
 * @description: You are given two strings, word1 and word2. You want to construct a string in the following manner:
 * <p>
 * Choose some non-empty subsequence subsequence1 from word1.
 * Choose some non-empty subsequence subsequence2 from word2.
 * Concatenate the subsequences: subsequence1 + subsequence2, to make the string.
 * Return the length of the longest palindrome that can be constructed in the described manner. If no palindromes can be constructed, return 0.
 * <p>
 * A subsequence of a string s is a string that can be made by deleting some (possibly none) characters from s without changing the order of the remaining characters.
 * <p>
 * A palindrome is a string that reads the same forward as well as backward.
 * @author: LISHUAI
 * @createDate: 2023/4/18 20:18
 * @version: 1.0
 */

public class LeetCode_1771 {

    private static Integer[][] cache;

    public static int longestPalindrome(String word1, String word2) {
        int[] chars = new int[26];
        Arrays.fill(chars, -1);
        for (int i = 0; i < word2.length(); i++) {
            int c = word2.charAt(i) - 'a';
            chars[c] = i;
        }
        int ans = 0;
        cache = new Integer[word1.length() + word2.length()][word1.length() + word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            int c = word1.charAt(i) - 'a';
            if (chars[c] != -1) {
                ans = Math.max(ans, longestPalindromeProcess(word1 + word2, i, word1.length() + chars[c]));
            }
        }
        return ans;
    }

    private static int longestPalindromeProcess(String word, int index1, int index2) {
        if (index1 > index2) {
            return 0;
        }
        if (index1 == index2) {
            return 1;
        }
        if (cache[index1][index2] != null) {
            return cache[index1][index2];
        }

        int p1 = 0, p2 = 0, p3 = 0;
        if (word.charAt(index1) == word.charAt(index2)) {
            p3 = longestPalindromeProcess(word, index1 + 1, index2 - 1) + 2;
        } else {
            p1 = longestPalindromeProcess(word, index1 + 1, index2);
            p2 = longestPalindromeProcess(word, index1, index2 - 1);
        }
        cache[index1][index2] = Math.max(p3, Math.max(p1, p2));
        return cache[index1][index2];
    }


    public static int longestPalindrome_dp(String word1, String word2) {
        String word = word1 + word2;
        int len = word.length(), len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len][len];

        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (word.charAt(i) == word.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        int[] chars = new int[26];
        Arrays.fill(chars, -1);
        for (int i = 0; i < word2.length(); i++) {
            int c = word2.charAt(i) - 'a';
            chars[c] = i;
        }
        int ans = 0;
        for (int i = 0; i < word1.length(); i++) {
            int c = word1.charAt(i) - 'a';
            if (chars[c] != -1) {
                ans = Math.max(ans, dp[i][len1 + chars[c]]);
            }
        }
        return ans;
    }
}
