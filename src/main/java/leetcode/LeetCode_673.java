package leetcode;

/**
 * @description: Given an integer array nums, return the number of longest increasing subsequences.
 * <p>
 * Notice that the sequence has to be strictly increasing.
 * @author: LiShuai
 * @createDate: 2023/7/21 21:21
 * @version: 1.0
 */

public class LeetCode_673 {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        int numberOfLIS = findNumberOfLIS(nums);
        System.out.println(numberOfLIS);
    }

    public static int findNumberOfLIS(int[] nums) {
        int len = nums.length, ans = 0, maxLen = 0;
        int[][] dp = new int[len][2];

        for (int i = len - 1; i >= 0; --i) {
            int k = 0, m = 1;
            for (int j = i + 1; j < len; j++) {
                if (nums[i] < nums[j]) {
                    if (k < dp[j][0]) {
                        k = dp[j][0];
                        m = dp[j][1];
                    } else if (k == dp[j][0]) {
                        m += dp[j][1];
                    }
                }
            }
            dp[i][0] = k + 1;
            dp[i][1] = m;
            if (dp[i][0] > maxLen) {
                ans = dp[i][1];
                maxLen = dp[i][0];
            } else if (dp[i][0] == maxLen) {
                ans += dp[i][1];
            }
        }
        return ans;
    }
}
