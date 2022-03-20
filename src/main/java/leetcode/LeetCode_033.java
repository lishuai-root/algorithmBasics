package leetcode;

/**
 * @description: There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function,
 * nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * @author: LISHUAI
 * @createDate: 2021/11/16 19:26
 * @version: 1.0
 */

public class LeetCode_033 {

    public int search(int[] nums, int target) {

        int index = -1;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == target) {

                index = i;

                break;
            }
        }

        return index;
    }
}
