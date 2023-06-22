package leetcode;

import java.util.Arrays;

/**
 * @description: You are given two 0-indexed arrays nums and cost consisting each of n positive integers.
 * <p>
 * You can do the following operation any number of times:
 * <p>
 * Increase or decrease any element of the array nums by 1.
 * The cost of doing one operation on the ith element is cost[i].
 * <p>
 * Return the minimum total cost such that all the elements of the array nums become equal.
 * @author: LiShuai
 * @createDate: 2023/6/21 21:57
 * @version: 1.0
 */

public class LeetCode_2448 {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 2}, cost = {2, 3, 1, 14};
        long l = minCost(nums, cost);
        System.out.println(l);
    }

    public static long minCost(int[] nums, int[] cost) {
        int len = nums.length;
        Integer[] indexes = new Integer[len];
        for (int i = 0; i < len; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, (a, b) -> nums[a] - nums[b]);
        long[] dp = new long[len];
        long sum = cost[indexes[len - 1]];
        for (int i = len - 2; i >= 0; --i) {
            dp[i] = dp[i + 1] + (nums[indexes[i + 1]] - nums[indexes[i]]) * sum;
            sum += cost[indexes[i]];
        }
        long ans = dp[0], total = 0;
        sum = cost[indexes[0]];
        for (int i = 1; i < len; i++) {
            total += (nums[indexes[i]] - nums[indexes[i - 1]]) * sum;
            ans = Math.min(ans, total + dp[i]);
            sum += cost[indexes[i]];
        }
        return ans;
    }
}
