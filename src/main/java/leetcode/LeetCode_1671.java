package leetcode;

/**
 * @description: You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.
 * @author: LISHUAI
 * @createDate: 2022/6/25 21:34
 * @version: 1.0
 */

public class LeetCode_1671 {
    private static int[] head;

    public static void main(String[] args) {
//        int[] nums = {23, 47, 63, 72, 81, 99, 88, 55, 21, 33, 32};
        int[] nums = {9, 8, 1, 7, 6, 5, 4, 3, 2, 1};
        int i = minimumMountainRemovals(nums);
        System.out.println(i);
    }

    public static int minimumMountainRemovals(int[] nums) {
        head = new int[nums.length];
        int[] tail = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, tail[j]);
                }
            }
            tail[i] = max + 1;
        }
        int len = nums.length, max = 0;
        for (int i = 0; i < len; i++) {
            int h = getR(nums, i);
            if (h > 1 && tail[i] > 1) {
                max = Math.max(max, tail[i] + h - 1);
            }
        }
        return len - max;
    }

    private static int getR(int[] nums, int index) {
        int max = 0;
        for (int i = 0; i < index; i++) {
            if (nums[i] < nums[index]) {
                max = Math.max(max, head[i]);
            }
        }
        head[index] = max + 1;
        return head[index];
    }
}
