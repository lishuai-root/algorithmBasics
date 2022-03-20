package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/9 20:14
 * @version: 1.0
 */

public class LeetCode_322 {

    public static void main(String[] args) {

        int[] coins = {1};
        int amount = 2;

        int i = coinChange_02(coins, amount);

        System.out.println(i);
    }


    public static int coinChange(int[] coins, int amount) {

        int process = process(coins, amount, 0, 0);
        return process;
    }


    public static int process(int[] coins, int amount, int index, int size) {

        if (amount == 0) {

            return size;
        }

        if (index == coins.length) {

            return Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; coins[index] * i <= amount; i++) {

            min = Math.min(process(coins, amount - (coins[index] * i), index + 1, size + i), min);

        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static int coinChange_02(int[] coins, int amount) {

        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i < amount; i++) {

            dp[coins.length][i] = Integer.MAX_VALUE;
        }

        int min = Integer.MAX_VALUE, s;
        for (int i = coins.length - 1; i >= 0; i--) {

            for (int j = 0; j < amount; j++) {

                for (int k = 0; k <= j; k++) {

                    if (coins[i] <= k && dp[i + 1][coins[i] * k] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i + 1][coins[i] * k], dp[i][j]);
                    }
                }

            }

        }

        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }
}
