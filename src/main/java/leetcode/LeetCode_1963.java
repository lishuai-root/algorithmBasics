package leetcode;

/**
 * @description: You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.
 * <p>
 * A string is called balanced if and only if:
 * <p>
 * It is the empty string, or
 * It can be written as AB, where both A and B are balanced strings, or
 * It can be written as [C], where C is a balanced string.
 * You may swap the brackets at any two indices any number of times.
 * <p>
 * Return the minimum number of swaps to make s balanced.
 * @author: LISHUAI
 * @createDate: 2022/12/28 14:07
 * @version: 1.0
 */

public class LeetCode_1963 {

    public static int minSwaps(String s) {
        int len = s.length();
        int index = 0;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '[') {
                index++;
            } else if (index > 0) {
                index--;
            }
        }
        return (index + 1) >> 1;
    }
}
