package leetcode;

/**
 * @description: You are given two strings s and t. In one step, you can append any character to either s or t.
 * <p>
 * Return the minimum number of steps to make s and t anagrams of each other.
 * <p>
 * An anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 * @author: LISHUAI
 * @createDate: 2022/12/3 1:24
 * @version: 1.0
 */

public class LeetCode_2186 {

    public static int minSteps(String s, String t) {
        int[] count = new int[26];
        int ans = 0, minLen = Math.min(s.length(), t.length());
        int index = 0;
        while (index < minLen) {
            count[s.charAt(index) - 'a']++;
            count[t.charAt(index) - 'a']--;
            index++;
        }
        while (index < s.length()) {
            count[s.charAt(index) - 'a']++;
            index++;
        }
        while (index < t.length()) {
            count[t.charAt(index) - 'a']--;
            index++;
        }
        for (int j : count) {
            ans += Math.abs(j);
        }
        return ans;
    }
}
