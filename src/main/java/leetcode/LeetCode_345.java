package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: Given a string s, reverse only all the vowels in the string and return it.
 * <p>
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 * @author: LISHUAI
 * @createDate: 2022/12/1 4:31
 * @version: 1.0
 */

public class LeetCode_345 {

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

    public static void main(String[] args) {
//        String s = "hello";
        String s = "leetcode";
        String s1 = reverseVowels(s);
        System.out.println(s1);
    }

    public static String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = -1, right = chars.length;
        while (true) {
            while (++left < right && !VOWELS.contains(chars[left])) ;
            while (--right > left && !VOWELS.contains(chars[right])) ;
            if (left >= right) {
                break;
            }
            char c = chars[left];
            chars[left] = chars[right];
            chars[right] = c;
        }
        return String.valueOf(chars);
    }
}
