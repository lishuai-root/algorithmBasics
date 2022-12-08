package leetcode;

/**
 * @description: You are given an array of strings words. Each element of words consists of two lowercase English letters.
 * <p>
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.
 * <p>
 * Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
 * <p>
 * A palindrome is a string that reads the same forward and backward.
 * @author: LISHUAI
 * @createDate: 2022/12/1 3:24
 * @version: 1.0
 */

public class LeetCode_2131 {

    public static void main(String[] args) {
//        String[] words = {"lc", "cl", "gg"};
//        String[] words = {"ab", "ty", "yt", "lc", "cl", "ab"};
        String[] words = {"cc", "ll", "xx"};
        int i = longestPalindrome(words);
        System.out.println(i);
        System.out.println(longestPalindrome_02(words));
    }

    public static int longestPalindrome_02(String[] words) {
        int[][] dp = new int[26][26];
        int ans = 0, q = 0;
        for (String s : words) {
            int r = s.charAt(0) - 'a';
            int c = s.charAt(1) - 'a';
            dp[r][c]++;
        }
        for (String s : words) {
            int r = s.charAt(0) - 'a';
            int c = s.charAt(1) - 'a';
            int p = Math.min(dp[r][c], dp[c][r]);
            if (r == c) {
                if ((p & 1) != 0) {
                    --p;
                    q = 2;
                }
                ans += (p << 1);
            } else {
                ans += (p << 2);
                dp[c][r] -= p;
            }
            dp[r][c] -= p;
        }
        return ans + q;
    }

    public static int longestPalindrome(String[] words) {
        int len = words.length;
        char[] chars = new char[len << 1];
        return longestPalindromeProcess(words, chars, 0);
    }

    private static int longestPalindromeProcess(String[] words, char[] chars, int k) {
        if (k >= chars.length) {
            if (isPalindrome(chars, 0, k - 1)) {
                return k;
            }
            return 0;
        }
        int p2 = 0;
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (s.length() == 0) {
                continue;
            }
            words[i] = "";
            int len = s.length();
            for (int j = 0; j < len; j++) {
                chars[k + j] = s.charAt(j);
            }
            p2 = Math.max(p2, longestPalindromeProcess(words, chars, k + len));
            if (isPalindrome(chars, 0, k + len - 1)) {
                p2 = Math.max(p2, k + len);
            }
            words[i] = s;
        }
        return p2;
    }

    private static boolean isPalindrome(char[] chars, int left, int right) {
        while (left <= right) {
            if (chars[left++] != chars[right--]) {
                return false;
            }
        }
        return true;
    }
}
