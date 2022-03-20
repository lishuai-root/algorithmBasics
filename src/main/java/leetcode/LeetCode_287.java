package leetcode;

/**
 * @description: Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * <p>
 * There is only one repeated number in nums, return this repeated number.
 * <p>
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 * @author: LISHUAI
 * @createDate: 2021/11/28 13:29
 * @version: 1.0
 */

public class LeetCode_287 {

    public int findDuplicate(int[] nums) {

        int[] arr = new int[nums.length];

        for (int i : nums) {

            if (arr[i] != 0) {

                return i;
            }

            arr[i] = i;
        }

        return 0;
    }
}
