package leetcode;

/**
 * @description: Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.
 * <p>
 * Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].
 * <p>
 * For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 * @author: LiShuai
 * @createDate: 2023/7/3 22:54
 * @version: 1.0
 */

public class LeetCode_859 {

    public static void main(String[] args) {
        String s = "ab", goal = "ab";
        boolean b = buddyStrings(s, goal);
        System.out.println(b);
    }

    public static boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int len = s.length(), index = 0, max = 0;
        int[] cache = new int[2];
        int[] chars = new int[26];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (index == 2) {
                    return false;
                }
                cache[index++] = i;
            }
            max = Math.max(max, ++chars[s.charAt(i) - 'a']);
        }
        if (index == 1) {
            return false;
        }
        if (index == 0) {
            return max > 1;
        }
        return s.charAt(cache[0]) == goal.charAt(cache[1]) && s.charAt(cache[1]) == goal.charAt(cache[0]);
    }
}
