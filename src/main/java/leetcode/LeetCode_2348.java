package leetcode;

/**
 * @description: Given an integer array nums, return the number of subarrays filled with 0.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * @author: LISHUAI
 * @createDate: 2023/3/21 21:15
 * @version: 1.0
 */

public class LeetCode_2348 {

    public static long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        int left = 0, right = 0, len = nums.length;
        while (left < len) {
            while (left < len && nums[left] != 0) {
                left++;
            }
            right = left;
            while (right < len && nums[right] == 0) {
                ans += (right - left + 1);
                right++;
            }
            left = right + 1;
        }
        return ans;
    }
}
