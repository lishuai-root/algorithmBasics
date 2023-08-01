package leetcode;

/**
 * @description: Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
 * @author: LiShuai
 * @createDate: 2023/7/31 22:11
 * @version: 1.0
 */

public class LeetCode_713 {

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int len = nums.length, sum = 1, ans = 0;
        int left = 0, right = 0, p = 0;
        while (right < len) {
            if (nums[right] == 0) {
                sum = 1;
                left = ++right;
                ans += right;
                p = right + 1;
            } else {
                sum *= nums[right++];
                while (left < right && sum >= k) {
                    sum /= nums[left++];
                }
                ans += (right - left) + p;
            }
        }
        return ans;
    }
}
