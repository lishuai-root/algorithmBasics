package leetcode;

import java.util.Arrays;

/**
 * @description: Given an integer array nums sorted in non-decreasing order,
 * return an array of the squares of each number sorted in non-decreasing order.
 * @author: LISHUAI
 * @createDate: 2022/6/7 20:29
 * @version: 1.0
 */

public class LeetCode_977 {

    public static int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }
}
