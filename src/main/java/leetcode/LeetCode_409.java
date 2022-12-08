package leetcode;

/**
 * @description: Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 * <p>
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 * @author: LISHUAI
 * @createDate: 2022/12/1 4:16
 * @version: 1.0
 */

public class LeetCode_409 {
    public static void main(String[] args) {
        String s = "abccccdd";
//        String s = "a";
        int i = longestPalindrome(s);
        System.out.println(i);
    }

    public static int longestPalindrome(String s) {
        int[] chars = new int[26 << 1];
        char[] sa = s.toCharArray();
        int ans = 0;
        for (char c : sa) {
            int p = c - 'A';
            if (p >= 32) {
                p = p - 6;
            }
            chars[p]++;
        }
        for (int i : chars) {
            ans += ((i >> 1) << 1);
        }
        return ans == s.length() ? ans : ans + 1;
    }

    public static int longestPalindrome_02(String s) {
        int[] chars = new int[26 << 1];
        char[] sa = s.toCharArray();
        int ans = 0;
        for (char c : sa) {
            int p = c - 'A';
            if (p >= 32) {
                p = p - 6;
            }
            if ((chars[p] & 1) != 0) {
                ans += 2;
                chars[p]--;
            } else {
                chars[p]++;
            }
        }
        return ans == s.length() ? ans : ans + 1;
    }
}
