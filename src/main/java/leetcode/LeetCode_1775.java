package leetcode;

/**
 * @description: You are given an integer array nums and an integer goal.
 * <p>
 * You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal.
 * That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).
 * <p>
 * Return the minimum possible value of abs(sum - goal).
 * <p>
 * Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.
 * @author: LISHUAI
 * @createDate: 2022/3/8 23:03
 * @version: 1.0
 */

public class LeetCode_1775 {

    public static void main(String[] args) {
//        int[] nums = {7, -9, 15, -2};
//        int goal = -5;
//        int[] nums = {5, -7, 3, 5};
//        int goal = 6;
        int[] nums = {1, 2, 3};
        int goal = -7;
        int i = minAbsDifference(nums, goal);
        System.out.println(i);
    }

    public static int minAbsDifference(int[] nums, int goal) {

        int min = Math.abs(goal);
//        for (int i = 0; i < nums.length; i++) {
        min = Math.min(min, minAbsDifferenceProcess(nums, goal, 0, 0));
//        }
        return min;
    }

    private static int minAbsDifferenceProcess(int[] nums, int goal, int index, int sum) {

        if (index == nums.length) {
            return Math.abs(sum - goal);
        }

        int cur = Math.abs(sum - goal);
        int n = Math.min(minAbsDifferenceProcess(nums, goal, index + 1, sum), minAbsDifferenceProcess(nums, goal, index + 1, sum + nums[index]));
        return Math.min(cur, n);
    }

//    public static int minAbsDifference_02(int[] nums, int goal) {
//
//    }
}
