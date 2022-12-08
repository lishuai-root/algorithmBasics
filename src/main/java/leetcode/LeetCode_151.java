package leetcode;

/**
 * @description: Given an input string s, reverse the order of the words.
 * <p>
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * <p>
 * Return a string of the words in reverse order concatenated by a single space.
 * <p>
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 * @author: LISHUAI
 * @createDate: 2022/12/4 2:22
 * @version: 1.0
 */

public class LeetCode_151 {

    public static void main(String[] args) {
//        String s = "the sky is blue";
        String s = "a good   example";
//        String s = "  hello world  ";
        String s1 = reverseWords(s);
        System.out.println(s1);
        System.out.println(reverseWords_02(s));
        System.out.println(reverseWords_03(s));
    }

    public static String reverseWords(String s) {
        char[] chars = new char[s.length() + 1];
        int left = s.length() - 1, right = s.length(), index = 0;

        while (true) {
            while (left >= 0 && s.charAt(left) == ' ') {
                left--;
            }
            if (left < 0) {
                break;
            }
            right = left + 1;
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
            }
            for (int i = left + 1; i < right; i++) {
                chars[index++] = s.charAt(i);
            }
            chars[index++] = ' ';
        }
        return String.valueOf(chars, 0, Math.max(0, index - 1));
    }

    public static String reverseWords_02(String s) {
        char[] chars = new char[s.length() + 1];
        int left = s.length() - 1, right = s.length(), index = 0;

        while (left >= 0) {
            if (s.charAt(left) == ' ') {
                if (left + 1 < right) {
                    for (int i = left + 1; i < right; i++) {
                        chars[index++] = s.charAt(i);
                    }
                    chars[index++] = ' ';
                }
                right = left;
            }
            left--;
        }
        if (left + 1 < right) {
            for (int i = left + 1; i < right; i++) {
                chars[index++] = s.charAt(i);
            }
            chars[index++] = ' ';
        }
        return String.valueOf(chars, 0, Math.max(0, index - 1));
    }

    public static String reverseWords_03(String s) {
        String[] split = s.split(" ");
        StringBuilder sbr = new StringBuilder(s.length());

        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].length() != 0) {
                sbr.append(split[i])
                        .append(' ');
            }
        }
        return sbr.substring(0, sbr.length() - 1);
    }
}
