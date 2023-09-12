package leetcode;

/**
 * @description: You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.
 * <p>
 * We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:
 * <p>
 * The subarray consists of exactly 2 equal elements. For example, the subarray [2,2] is good.
 * The subarray consists of exactly 3 equal elements. For example, the subarray [4,4,4] is good.
 * The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
 * Return true if the array has at least one valid partition. Otherwise, return false.
 * @author: LiShuai
 * @createDate: 2023/8/13 14:42
 * @version: 1.0
 */

public class LeetCode_2369 {

    public static boolean validPartition(int[] nums) {
        return validPartitionProcess(nums, 0);
    }

    private static boolean validPartitionProcess(int[] nums, int left) {
        if (left >= nums.length) {
            return true;
        }
        boolean ans = false;
        if (left + 2 < nums.length) {
            if ((nums[left] == nums[left + 1] && nums[left + 1] == nums[left + 2])
                    || (nums[left + 1] - nums[left] == 1 && nums[left + 2] - nums[left + 1] == 1)) {
                ans = validPartitionProcess(nums, left + 3);
            }
        }
        if (!ans && left + 1 < nums.length) {
            if (nums[left] == nums[left + 1]) {
                ans = validPartitionProcess(nums, left + 2);
            }
        }
        return ans;
    }

    public static boolean validPartition_dp(int[] nums) {
        int len = nums.length;
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;
        dp[len - 2] = nums[len - 2] == nums[len - 1];

        for (int i = len - 3; i >= 0; --i) {
            int p1 = nums[i + 1] - nums[i];
            int p2 = nums[i + 2] - nums[i + 1];

            if (p1 == p2 && (p1 == 0 || p1 == 1)) {
                dp[i] = dp[i + 3];
            }
            if (!dp[i] && p1 == 0) {
                dp[i] = dp[i + 2];
            }
        }
        return dp[0];
    }
}
