package leetcode;

import java.util.Arrays;

/**
 * @description: You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
 * <p>
 * Increment the large integer by one and return the resulting array of digits.
 * @author: LISHUAI
 * @createDate: 2022/7/14 20:36
 * @version: 1.0
 */

public class LeetCode_066 {

    public static int[] plusOne(int[] digits) {
        int len = digits.length;
        int[] ans = new int[len + 1];
        int index = len + 1, pre = 1;

        for (int i = len - 1; i >= 0; i--) {
            int cur = pre + digits[i];
            ans[--index] = cur == 10 ? 0 : cur;
            pre = Math.max(0, cur - 9);
        }
        if (pre != 0) {
            ans[--index] = pre;
        }
        return Arrays.copyOfRange(ans, index, len + 1);
    }
}
