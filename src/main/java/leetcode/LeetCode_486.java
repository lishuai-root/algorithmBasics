package leetcode;

/**
 * @description: You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.
 * <p>
 * Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0. At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1. The player adds the chosen number to their score. The game ends when there are no more elements in the array.
 * <p>
 * Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, and you should also return true. You may assume that both players are playing optimally.
 * @author: LiShuai
 * @createDate: 2023/7/28 20:06
 * @version: 1.0
 */

public class LeetCode_486 {


    public static void main(String[] args) {
//        int[] nums = {1, 5, 233, 7};
        int[] nums = {1, 5, 2};
        boolean b = PredictTheWinner(nums);
        System.out.println(b);
        System.out.println(PredictTheWinner_dp(nums));
    }

    public static boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int sum = 0;
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        sum = preSum[0];
        for (int i = 1; i < len; i++) {
            sum += nums[i];
            preSum[i] = sum;
        }
        int ans = PredictTheWinnerProcess(nums, preSum, 0, len - 1);
        return (ans << 1) < sum;
    }

    private static int PredictTheWinnerProcess(int[] nums, int[] preSum, int left, int right) {
        if (left > right) {
            return 0;
        }
        int sum = (left == 0 ? preSum[right] : preSum[right] - preSum[left - 1]);
        int p1 = PredictTheWinnerProcess(nums, preSum, left + 1, right) + nums[left];
        int p2 = PredictTheWinnerProcess(nums, preSum, left, right - 1) + nums[right];

        return sum - Math.max(p1, p2);
    }

    public static boolean PredictTheWinner_dp(int[] nums) {
        int len = nums.length, sum = 0;
        int[] dp = new int[len];

        for (int i = len - 1; i >= 0; --i) {
            sum += nums[i];
            int s = nums[i];
            for (int j = i + 1; j < len; j++) {
                s += nums[j];
                dp[j] = s - Math.max(dp[j] + nums[i], dp[j - 1] + nums[j]);
            }
        }
        return (dp[len - 1] << 1) <= sum;
    }
}
