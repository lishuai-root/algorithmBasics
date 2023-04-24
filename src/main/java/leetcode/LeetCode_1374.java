package leetcode;

/**
 * @description: Given an integer n, return a string with n characters such that each character in such string occurs an odd number of times.
 * <p>
 * The returned string must contain only lowercase English letters. If there are multiples valid strings, return any of them.
 * @author: LISHUAI
 * @createDate: 2023/4/19 21:05
 * @version: 1.0
 */

public class LeetCode_1374 {

    public static String generateTheString(int n) {
        if ((n & 1) == 1) {
            return "a".repeat(n);
        } else {
            return "a".repeat(n - 1) + "b";
        }
    }
}
