package leetcode;

/**
 * @description: Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 * @author: LISHUAI
 * @createDate: 2022/4/30 19:10
 * @version: 1.0
 */

public class LeetCode_209 {

    public static void main(String[] args) {
//        int target = 11;
//        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1};
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
//        int target = 4;
//        int[] nums = {1, 4, 4};
        int i = minSubArrayLen(target, nums);
        System.out.println(i);
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, ans = Integer.MAX_VALUE, sum = 0;

        while (right < nums.length) {
            sum += nums[right++];
            while (sum >= target) {
                ans = Math.min(ans, right - left);
                sum -= nums[left++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
