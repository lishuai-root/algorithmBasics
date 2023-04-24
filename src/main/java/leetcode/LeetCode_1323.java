package leetcode;

/**
 * @description: You are given a positive integer num consisting only of digits 6 and 9.
 * <p>
 * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
 * @author: LISHUAI
 * @createDate: 2023/3/5 14:10
 * @version: 1.0
 */

public class LeetCode_1323 {

    public static int maximum69Number(int num) {
        char[] chars = ("" + num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '6') {
                chars[i] = '9';
                break;
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
