package leetcode;

/**
 * @description: Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 * <p>
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 * @author: LISHUAI
 * @createDate: 2023/5/6 22:41
 * @version: 1.0
 */

public class LeetCode_1456 {

    private static final int[] TEMP = new int[26];

    static {
        TEMP[0] = 1;
        TEMP['e' - 'a'] = 1;
        TEMP['i' - 'a'] = 1;
        TEMP['o' - 'a'] = 1;
        TEMP['u' - 'a'] = 1;
    }

    public static int maxVowels(String s, int k) {
        int cur = 0;
        for (int i = 0; i < k; i++) {
            cur += TEMP[s.charAt(i) - 'a'];
        }
        int ans = cur;
        for (int i = k; i < s.length(); i++) {
            cur += TEMP[s.charAt(i) - 'a'];
            cur -= TEMP[s.charAt(i - k) - 'a'];
            ans = Math.max(ans, cur);
        }
        return ans;
    }
}
