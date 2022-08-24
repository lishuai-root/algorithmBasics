package leetcode;

/**
 * @description: Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * @author: LISHUAI
 * @createDate: 2022/7/28 21:39
 * @version: 1.0
 */

public class LeetCode_242 {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] sum = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sum[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--sum[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram_02(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] sum = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            sum[s.charAt(i) - 'a']++;
            sum[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (sum[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
