package leetcode;

/**
 * @description: You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * @author: LISHUAI
 * @createDate: 2021/11/26 22:19
 * @version: 1.0
 */

public class LeetCode_198 {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 9, 3, 1};

        int rob = rob_02(arr);

        System.out.println(rob);
    }

    public static int rob(int[] nums) {

        return process(nums, 0);
    }

    public static int process(int[] nums, int left) {

        if (left >= nums.length) {

            return 0;
        }

        int maxMoney = 0;

        maxMoney = Math.max(maxMoney, process(nums, left + 2) + nums[left]);

        maxMoney = Math.max(maxMoney, process(nums, left + 1));

        return maxMoney;
    }

    public static int rob_02(int[] nums) {

        if (nums.length == 1) {

            return nums[0];
        }

        if (nums.length == 2) {

            return Math.max(nums[0], nums[1]);
        }

        int len = nums.length;

        int[] dp = new int[len];

        dp[len - 1] = nums[len - 1];

        dp[len - 2] = Math.max(nums[len - 2], nums[len - 1]);

        for (int i = len - 3; i >= 0; i--) {

            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }

        return dp[0];
    }
}
