package leetcode;

/**
 * @description: Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * @author: LISHUAI
 * @createDate: 2021/11/16 21:01
 * @version: 1.0
 */

public class LeetCode_034 {


    public static int[] searchRange(int[] nums, int target) {

        int[] result = new int[]{-1, -1};

        if (nums.length < 1) {

            return result;
        }

        int left = 0, right = nums.length - 1;

        while (left < nums.length && nums[left] != target) {

            left++;
        }


        while (right >= left && nums[right] != target) {

            right--;
        }

        if (left <= right) {

            result[0] = left;

            result[1] = right;
        }

        return result;
    }
}
