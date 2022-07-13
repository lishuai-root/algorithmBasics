package leetcode;

/**
 * @description: Given an integer array nums, handle multiple queries of the following type:
 * <p>
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 * <p>
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 * @author: LISHUAI
 * @createDate: 2022/6/4 18:34
 * @version: 1.0
 */

public class LeetCode_303 {

    class NumArray {

        int[] preSum;

        public NumArray(int[] nums) {
            int len = nums.length;
            preSum = new int[len];
            preSum[0] = nums[0];
            for (int i = 1; i < len; i++) {
                preSum[i] = preSum[i - 1] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            if (left == 0) {
                return preSum[right];
            } else {
                return preSum[right] - preSum[left - 1];
            }
        }
    }
}
