package leetcode;

/**
 * @description: Given two integers dividend and divisor, divide two integers without using multiplication,
 * division, and mod operator.
 * <p>
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem,
 * if the quotient is strictly greater than 231 - 1, then return 231 - 1,
 * and if the quotient is strictly less than -231, then return -231.
 * @author: LISHUAI
 * @createDate: 2021/12/2 21:41
 * @version: 1.0
 */

public class LeetCode_29 {

    public static int divide(int dividend, int divisor) {

        if (divisor == 0) {

            return 0;
        }

        if (divisor == 1 && (dividend == Integer.MIN_VALUE)) {

            return Integer.MIN_VALUE;
        }

        if (divisor == -1 && (dividend == Integer.MIN_VALUE)) {

            return Integer.MAX_VALUE;
        }

        return dividend / divisor;
    }
}
