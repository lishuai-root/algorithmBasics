package leetcode;

/**
 * @description: You are given a string s, which contains stars *.
 * <p>
 * In one operation, you can:
 * <p>
 * Choose a star in s.
 * Remove the closest non-star character to its left, as well as remove the star itself.
 * Return the string after all stars have been removed.
 * <p>
 * Note:
 * <p>
 * The input will be generated such that the operation is always possible.
 * It can be shown that the resulting string will always be unique.
 * @author: LISHUAI
 * @createDate: 2022/12/25 14:37
 * @version: 1.0
 */

public class LeetCode_2390 {

    public static String removeStars(String s) {
        int len = s.length();
        char[] chars = new char[len];
        int index = len, count = 0;

        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '*') {
                count++;
            } else if (count > 0) {
                count--;
            } else {
                chars[--index] = c;
            }
        }
        return String.valueOf(chars, index, len - index);
    }
}
