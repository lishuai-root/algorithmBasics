package leetcode;

/**
 * @description: Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.
 * <p>
 * A subarray is defined as a contiguous sequence of numbers in an array.
 * <p>
 * A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi  < numsi+1. Note that a subarray of size 1 is ascending.
 * @author: LISHUAI
 * @createDate: 2023/5/14 19:43
 * @version: 1.0
 */

public class LeetCode_1800 {

    public static int maxAscendingSum(int[] nums) {
        int ans = 0, sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                ans = Math.max(ans, sum);
                sum = 0;
            }
            sum += nums[i];
        }
        return Math.max(ans, sum);
    }
}
