package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array nums of positive integers and a positive integer k.
 * <p>
 * A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
 * <p>
 * Return the number of non-empty beautiful subsets of the array nums.
 * <p>
 * A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.
 * @author: LiShuai
 * @createDate: 2023/8/1 20:38
 * @version: 1.0
 */

public class LeetCode_2597 {

    public static void main(String[] args) {
//        int[] nums = {10, 4, 5, 7, 2, 1};
//        int k = 3;
//        int[] nums = {10, 2, 6, 4, 5, 7, 3, 9, 1, 8};
//        int k = 3;
        int[] nums = {253, 101, 68, 106, 270, 154, 60, 141, 147, 46};
        int k = 3;
        int i = beautifulSubsets(nums, k);
        System.out.println(i);
    }

    public static int beautifulSubsets(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        int[] ns = new int[nums[len - 1] + 1];
        Integer[][] dp = new Integer[len][1 << len];
        return beautifulSubsetsProcess(nums, k, 0, 0, ns, dp);
    }

    private static int beautifulSubsetsProcess(int[] nums, int k, int index, int bits, int[] ns, Integer[][] dp) {
        if (index >= nums.length) {
            return 0;
        }
        if (dp[index][bits] != null) {
            return dp[index][bits];
        }
        int p1 = beautifulSubsetsProcess(nums, k, index + 1, bits, ns, dp);
        int p2 = 0;
        if (nums[index] - k < 0 || ns[nums[index] - k] == 0) {
            ns[nums[index]] = 1;
            p2 = beautifulSubsetsProcess(nums, k, index + 1, bits | (1 << index), ns, dp) + 1;
            ns[nums[index]] = 0;
        }
        dp[index][bits] = p1 + p2;
        return p1 + p2;
    }
}
