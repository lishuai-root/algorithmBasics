package leetcode;

/**
 * @description: Given a string s and a character letter, return the percentage of characters in s that equal letter rounded down to the nearest whole percent.
 * @author: LISHUAI
 * @createDate: 2023/1/13 21:37
 * @version: 1.0
 */

public class LeetCode_2278 {

    public static int percentageLetter(String s, char letter) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (letter == s.charAt(i)) {
                ans++;
            }
        }
        return ans * 100 / s.length();
    }
}
