package leetcode;

/**
 * @description: You are given a string allowed consisting of distinct characters and an array of strings words.
 * A string is consistent if all characters in the string appear in the string allowed.
 * <p>
 * Return the number of consistent strings in the array words.
 * @author: LISHUAI
 * @createDate: 2022/5/31 21:38
 * @version: 1.0
 */

public class LeetCode_1684 {

    public static int countConsistentStrings(String allowed, String[] words) {
        int cur = 0, ans = 0;
        for (int i = 0; i < allowed.length(); i++) {
            cur |= (1 << (allowed.charAt(i) - 'a'));
        }

        a:
        for (String s : words) {
            for (int i = 0; i < s.length(); i++) {
                if ((cur & (1 << (s.charAt(i) - 'a'))) == 0) {
                    continue a;
                }
            }
            ans++;
        }
        return ans;
    }

    public static int countConsistentStrings_02(String allowed, String[] words) {
        int ans = 0;
        int[] sum = new int[26];
        for (int i = 0; i < allowed.length(); i++) {
            sum[allowed.charAt(i) - 'a']++;
        }

        a:
        for (String s : words) {
            for (int i = 0; i < s.length(); i++) {
                if (sum[s.charAt(i) - 'a'] == 0) {
                    continue a;
                }
            }
            ans++;
        }
        return ans;
    }
}

