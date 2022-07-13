package leetcode;

/**
 * @description: Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * <p>
 * Return the sum of the three integers.
 * <p>
 * You may assume that each input would have exactly one solution.
 * @author: LISHUAI
 * @createDate: 2022/6/3 3:13
 * @version: 1.0
 */

public class LeetCode_016 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 0};
        int target = 1;
        int i = threeSumClosest(nums, target);
        System.out.println(i);
    }

    public static int threeSumClosest(int[] nums, int target) {
        return threeSumClosestProcess(nums, target, 0, 3, 0);
    }

    private static int threeSumClosestProcess(int[] nums, int target, int index, int size, int sum) {
        if (size == 0) {
            return sum;
        }
        if (index >= nums.length) {
            return Integer.MAX_VALUE;
        }
        int p1 = threeSumClosestProcess(nums, target, index + 1, size, sum);
        int p2 = threeSumClosestProcess(nums, target, index + 1, size - 1, sum + nums[index]);
        if (p1 == Integer.MAX_VALUE && p2 == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (p1 == Integer.MAX_VALUE) {
            return p2;
        }
        if (p2 == Integer.MAX_VALUE) {
            return p1;
        }
        return Math.abs(p1 - target) < Math.abs(p2 - target) ? p1 : p2;
    }

}
