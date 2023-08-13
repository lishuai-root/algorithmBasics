package leetcode;

import java.util.Arrays;

/**
 * @description: Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * You may assume the input array always has a valid answer.
 * @author: LiShuai
 * @createDate: 2023/8/7 20:32
 * @version: 1.0
 */

public class LeetCode_324 {

    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};
        wiggleSort(nums);
    }

    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[nums.length + 1];
        int len = (nums.length + 1) >> 1, index = 0, k = nums.length - 1;
        for (int i = len - 1; i >= 0; --i) {
            ans[index++] = nums[i];
            ans[index++] = nums[k--];
        }
        System.arraycopy(ans, 0, nums, 0, nums.length);
    }
}
