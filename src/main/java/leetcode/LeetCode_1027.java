package leetcode;

/**
 * @description: Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.
 * <p>
 * Note that:
 * <p>
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).
 * @author: LiShuai
 * @createDate: 2023/6/23 20:50
 * @version: 1.0
 */

public class LeetCode_1027 {

    public static int longestArithSeqLength(int[] nums) {
        int len = nums.length, max = 1000, ans = 0;
        int[][] dp = new int[len][max + 1];

        for (int i = len - 2; i >= 0; --i) {
            for (int j = len - 1; j > i; --j) {
                int t = nums[j] - nums[i] + 500;
                dp[i][t] = Math.max(dp[j][t], 1) + 1;
                ans = Math.max(ans, dp[i][t]);
            }
        }
        return ans;
    }
}
