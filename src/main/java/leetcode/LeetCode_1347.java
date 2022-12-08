package leetcode;

/**
 * @description: You are given two strings of the same length s and t. In one step you can choose any character of t and replace it with another character.
 * <p>
 * Return the minimum number of steps to make t an anagram of s.
 * <p>
 * An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 * @author: LISHUAI
 * @createDate: 2022/12/3 0:42
 * @version: 1.0
 */

public class LeetCode_1347 {

    public static void main(String[] args) {
//        String s = "bab", t = "aba";
//        String s = "leetcode", t = "practice";
        String s = "anagram", t = "mangaar";
        int i = minSteps(s, t);
        System.out.println(i);
    }

    public static int minSteps(String s, String t) {
        int[] countS = new int[26], countT = new int[26];
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            countS[s.charAt(i) - 'a']++;
            countT[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < countS.length; i++) {
            ans += Math.max(0, countT[i] - countS[i]);
        }
        return ans;
    }
}
