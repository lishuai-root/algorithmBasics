package leetcode;

/**
 * @description: You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * <p>
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * The answer is guaranteed to fit into a signed 32-bit integer.
 * @author: LISHUAI
 * @createDate: 2023/4/14 20:04
 * @version: 1.0
 */

public class LeetCode_518 {

    private static Integer[][] cache;

    public static void main(String[] args) {
        int[] coins = {102, 89, 76, 63, 50, 37, 24, 11};
        int amount = 5000;
        int i = change_dp(amount, coins);
        System.out.println(i);
    }

    public static int change(int amount, int[] coins) {
        cache = new Integer[coins.length][amount + 1];
        return changeProcess(coins, 0, amount);
    }

    private static int changeProcess(int[] coins, int index, int amount) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || index >= coins.length) {
            return 0;
        }
        if (cache[index][amount] != null) {
            return cache[index][amount];
        }
        int ans = changeProcess(coins, index + 1, amount) + changeProcess(coins, index, amount - coins[index]);
        cache[index][amount] = ans;
        return ans;
    }

    public static int change_dp(int amount, int[] coins) {
        int len = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = len - 1; i >= 0; i--) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
