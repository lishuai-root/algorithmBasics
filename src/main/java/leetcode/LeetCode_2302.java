package leetcode;

/**
 * @description: The score of an array is defined as the product of its sum and its length.
 * <p>
 * For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.
 * Given a positive integer array nums and an integer k, return the number of non-empty subarrays of nums whose score is strictly less than k.
 * <p>
 * A subarray is a contiguous sequence of elements within an array.
 * @author: LISHUAI
 * @createDate: 2023/3/19 21:58
 * @version: 1.0
 */

public class LeetCode_2302 {

    public long countSubarrays(int[] nums, long k) {

        int left = 0, right = 0, len = nums.length;
        long ans = 0, sum = 0;

        while (right < len) {
            sum += nums[right++];
            while (sum * (right - left) >= k) {
                sum -= nums[left++];
            }
            ans += (right - left);
        }
        return ans;
    }

}
