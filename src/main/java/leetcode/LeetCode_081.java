package leetcode;

/**
 * @description: There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 * <p>
 * Before being passed to your function,
 * nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 * <p>
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
 * <p>
 * You must decrease the overall operation steps as much as possible.
 * <p>
 * Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates.
 * Would this affect the runtime complexity? How and why?
 * @author: LISHUAI
 * @createDate: 2022/3/28 17:46
 * @version: 1.0
 */

public class LeetCode_081 {

    public static boolean search(int[] nums, int target) {
        for (int i : nums) {
            if (i == target) {
                return true;
            }
        }
        return false;
    }
}
