package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/12/18 18:58
 * @version: 1.0
 */

public class LeetCode_396 {

    public static int maxRotateFunction(int[] nums) {

        int max = Integer.MIN_VALUE, n;

        for (int i = 0; i < nums.length; i++) {

            n = 0;
            for (int j = 0; j < nums.length; j++) {

                n += nums[(i + j) % nums.length] * j;
            }

            max = Math.max(max, n);
        }

        return max;
    }


    public static int maxRotateFunction_02(int[] nums) {

        int preSum = 0;

        int max = 0, number = 0;

        for (int i = 0; i < nums.length; i++) {

            preSum += nums[i];

            number += i * nums[i];
        }

        max = number;

        for (int i = 1; i < nums.length; i++) {

            number = (number - preSum) + nums[i - 1] * nums.length;

            max = Math.max(max, number);
        }

        return max;
    }
}






















