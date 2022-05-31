package leetcode;

/**
 * @description: A peak element is an element that is strictly greater than its neighbors.
 * <p>
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks,
 * return the index to any of the peaks.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * You must write an algorithm that runs in O(log n) time.
 * <p>
 * 3 is a peak element and your function should return the index number 2.
 * @author: LISHUAI
 * @createDate: 2022/5/29 19:20
 * @version: 1.0
 */

public class LeetCode_162 {
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1 || nums[0] > nums[1]) {
            return 0;
        }
        int len = nums.length;
        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }

        for (int i = 1; i < len - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return 0;
    }
}
