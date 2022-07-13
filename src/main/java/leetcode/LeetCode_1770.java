package leetcode;

/**
 * @description: You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
 * <p>
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 * <p>
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 * Return the maximum score after performing m operations.
 * @author: LISHUAI
 * @createDate: 2022/6/26 22:38
 * @version: 1.0
 */

public class LeetCode_1770 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3}, multipliers = {3, 2, 1};
        int[] nums = {-5, -3, -3, -2, 7, 1}, multipliers = {-10, -5, 3, 4, 6};
        int i = maximumScore_dp(nums, multipliers);
        System.out.println(i);
    }

    public static int maximumScore(int[] nums, int[] multipliers) {
        return maximumScoreProcess(nums, multipliers, 0, 0);
    }

    private static int maximumScoreProcess(int[] nums, int[] multipliers, int index, int left) {
        if (index >= multipliers.length) {
            return 0;
        }

        int p1 = maximumScoreProcess(nums, multipliers, index + 1, left + 1) + multipliers[index] * nums[left];
        int p2 = maximumScoreProcess(nums, multipliers, index + 1, left) + multipliers[index] * nums[nums.length - index + left - 1];
        return Math.max(p1, p2);
    }

    public static int maximumScore_dp(int[] nums, int[] multipliers) {
        int numsLen = nums.length, mulLen = multipliers.length;
        int[] dp = new int[numsLen + 1];

        for (int i = mulLen - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int p1 = dp[j + 1] + multipliers[i] * nums[j];
                int p2 = dp[j] + multipliers[i] * nums[numsLen - i + j - 1];
                dp[j] = Math.max(p1, p2);
            }
        }
        return dp[0];
    }

}
