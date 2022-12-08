package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.
 * <p>
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
 * <p>
 * Return true if a and b are alike. Otherwise, return false.
 * @author: LISHUAI
 * @createDate: 2022/12/1 23:31
 * @version: 1.0
 */

public class LeetCode_1704 {
    private static final Set<Character> VOWELS;

    static {
        VOWELS = new HashSet<>();
        VOWELS.add('a');
        VOWELS.add('e');
        VOWELS.add('i');
        VOWELS.add('o');
        VOWELS.add('u');
        VOWELS.add('A');
        VOWELS.add('E');
        VOWELS.add('I');
        VOWELS.add('O');
        VOWELS.add('U');
    }

    public static boolean halvesAreAlike(String s) {
        char[] chars = s.toCharArray();
        int ans = 0, len = chars.length;
        for (int i = 0; i < len >>> 1; i++) {
            if (VOWELS.contains(chars[i])) {
                ans++;
            }
        }
        for (int i = len >>> 1; i < len; i++) {
            if (VOWELS.contains(chars[i])) {
                ans--;
            }
        }
        return ans == 0;
    }
}
