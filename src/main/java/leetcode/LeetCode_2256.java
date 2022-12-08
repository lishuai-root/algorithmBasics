package leetcode;

/**
 * @description: You are given a 0-indexed integer array nums of length n.
 * <p>
 * The average difference of the index i is the absolute difference between the average of the first i + 1 elements of nums and the average of the last n - i - 1 elements. Both averages should be rounded down to the nearest integer.
 * <p>
 * Return the index with the minimum average difference. If there are multiple such indices, return the smallest one.
 * <p>
 * Note:
 * <p>
 * The absolute difference of two numbers is the absolute value of their difference.
 * The average of n elements is the sum of the n elements divided (integer division) by n.
 * The average of 0 elements is considered to be 0.
 * @author: LISHUAI
 * @createDate: 2022/12/5 3:08
 * @version: 1.0
 */

public class LeetCode_2256 {

    public static void main(String[] args) {
//        int[] nums = {2, 5, 3, 9, 5, 3};
        int[] nums = {4, 2, 0};
        int i = minimumAverageDifference(nums);
        System.out.println(i);
    }

    public static int minimumAverageDifference(int[] nums) {
        long startSum = 0, endSum = 0, minA = Integer.MAX_VALUE;
        for (int i : nums) {
            endSum += i;
        }
        int minI = 0, len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            startSum += nums[i];
            endSum -= nums[i];
            long c = Math.abs(startSum / (i + 1) - (i + 1 == len ? 0 : endSum / (len - i - 1)));
            if (c < minA) {
                minA = c;
                minI = i;
            }
        }
        return minI;
    }
}
