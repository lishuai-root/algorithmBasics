package leetcode;

/**
 * @description: Given an integer array nums,
 * you need to find one continuous subarray that if you only sort this subarray in ascending order,
 * then the whole array will be sorted in ascending order.
 * <p>
 * Return the shortest such subarray and output its length.
 * @author: LISHUAI
 * @createDate: 2022/3/22 20:17
 * @version: 1.0
 */

public class LeetCode_581 {

    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int unsortedSubarray = findUnsortedSubarray(nums);
        System.out.println(unsortedSubarray);
    }

    public static int findUnsortedSubarray(int[] nums) {
        int left = 0, right = 0, index = 1, max, min;
        while (index < nums.length && nums[index] >= nums[index - 1]) {
            index++;
        }

        if (index >= nums.length) {
            return 0;
        }

        left = index - 1;
        right = index;
        max = nums[left];
        min = nums[index];

        while (++index < nums.length) {
            if (nums[index] < max) {
                right = index;
            }
            max = Math.max(max, nums[index]);
            min = Math.min(min, nums[index]);
        }
        while (--left >= 0 && nums[left] > min) ;

        return right - left;
    }
}
