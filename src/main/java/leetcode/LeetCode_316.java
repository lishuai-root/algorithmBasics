package leetcode;

/**
 * @description: Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 * @author: LISHUAI
 * @createDate: 2022/3/18 17:10
 * @version: 1.0
 */

public class LeetCode_316 {

    public static void main(String[] args) {
        String s = "bcabc";
//        String s = "abacb";
        String s1 = removeDuplicateLetters(s);
        System.out.println(s1);
    }

    public static String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        int[] exists = new int[26];
        char[] chars = s.toCharArray();
        char[] stack = new char[26];
        int index = -1;
        for (char c : chars) {
            count[c - 'a']++;
        }

        for (char aChar : chars) {
            count[aChar - 'a']--;
            if (exists[aChar - 'a'] > 0) {
                continue;
            }
            while (index != -1 && aChar < stack[index] && count[stack[index] - 'a'] > 0) {
                exists[stack[index--] - 'a']--;
            }
            stack[++index] = aChar;
            exists[aChar - 'a']++;

        }
        return String.valueOf(stack, 0, index + 1);
    }
}
