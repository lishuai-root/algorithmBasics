package leetcode;

/**
 * @description: Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 * <p>
 * Return the running sum of nums.
 * @author: LISHUAI
 * @createDate: 2022/6/1 21:45
 * @version: 1.0
 */

public class LeetCode_1480 {

    public static int[] runningSum(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }

        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }
}
