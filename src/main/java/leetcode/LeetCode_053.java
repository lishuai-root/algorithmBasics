package leetcode;

/**
 * @description: Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * A subarray is a contiguous part of an array.
 * @author: LISHUAI
 * @createDate: 2021/11/18 21:23
 * @version: 1.0
 */

public class LeetCode_053 {

    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int i = maxSubArray(arr);

        System.out.println(i);
    }

    public static int maxSubArray(int[] nums) {

        int maxNum = Integer.MIN_VALUE;

        int sum = 0;

        for (int num : nums) {

            sum += num;

            maxNum = Math.max(sum, maxNum);

            sum = Math.max(sum, 0);
        }

        return maxNum;
    }
}
