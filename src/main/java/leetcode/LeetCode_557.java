package leetcode;

/**
 * @description: Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 * @author: LISHUAI
 * @createDate: 2022/9/22 13:16
 * @version: 1.0
 */

public class LeetCode_557 {

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        String s1 = reverseWords(s);
        System.out.println(s1);
    }

    public static String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                right = i - 1;
                while (left < right) {
                    char c = chars[left];
                    chars[left++] = chars[right];
                    chars[right--] = c;
                }
                left = i + 1;
            }
        }
        right = chars.length - 1;
        while (left < right) {
            char c = chars[left];
            chars[left++] = chars[right];
            chars[right--] = c;
        }
        return String.valueOf(chars);
    }
}
