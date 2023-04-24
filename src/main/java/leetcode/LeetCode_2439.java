package leetcode;

/**
 * @description: You are given a 0-indexed array nums comprising of n non-negative integers.
 * <p>
 * In one operation, you must:
 * <p>
 * Choose an integer i such that 1 <= i < n and nums[i] > 0.
 * Decrease nums[i] by 1.
 * Increase nums[i - 1] by 1.
 * Return the minimum possible value of the maximum integer of nums after performing any number of operations.
 * @author: LISHUAI
 * @createDate: 2023/4/5 22:47
 * @version: 1.0
 */

public class LeetCode_2439 {

    public static int minimizeArrayValue(int[] nums) {
        int ans = nums[0];
        long sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            if (nums[i] > ans) {
                int k = (int) ((sum + i) / (i + 1));
                ans = Math.max(ans, k);
            }
        }
        return ans;
    }

}
