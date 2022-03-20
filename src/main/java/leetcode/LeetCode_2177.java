package leetcode;

/**
 * @description: Given an integer num, return three consecutive integers (as a sorted array) that sum to num.
 * If num cannot be expressed as the sum of three consecutive integers, return an empty array.
 * @author: LISHUAI
 * @createDate: 2022/2/23 19:31
 * @version: 1.0
 */

public class LeetCode_2177 {

    public static long[] sumOfThree(long num) {
        long[] ans;
        if (num % 3 == 0) {
            ans = new long[3];
            ans[0] = (num / 3) - 1;
            ans[1] = num / 3;
            ans[2] = (num / 3) + 1;
        } else {
            ans = new long[0];
        }
        return ans;
    }
}
