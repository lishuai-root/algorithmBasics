package leetcode;

/**
 * @description: You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 * <p>
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * @author: LISHUAI
 * @createDate: 2022/12/23 14:32
 * @version: 1.0
 */

public class LeetCode_309 {

    public static int maxProfit(int[] prices) {
        return maxProfitProcess(prices, 0, 0);
    }

    private static int maxProfitProcess(int[] prices, int start, int end) {
        if (end >= prices.length) {
            return 0;
        }
        int p2 = maxProfitProcess(prices, end + 2, end + 2) + prices[end] - prices[start];
        int p1 = maxProfitProcess(prices, start, end + 1);
        int p3 = maxProfitProcess(prices, start + 1, end + 1);
        return Math.max(p1, Math.max(p2, p3));
    }

    public static int maxProfit_dp(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len + 2][len + 2];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j >= i; j--) {
                int p1 = dp[j + 2][j + 2] + prices[j] - prices[i];
                int p2 = Math.max(dp[i][j + 1], dp[i + 1][j + 1]);
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][0];
    }

    public static int maxProfit_dp_02(int[] prices) {
        int len = prices.length;
        int[] dp = new int[len + 2];

        for (int i = len - 1; i >= 0; i--) {
            int max = 0;
            for (int j = len - 1; j > i; j--) {
                int p1 = dp[j + 2] + prices[j] - prices[i];
                int p2 = dp[i + 1];
                max = Math.max(max, Math.max(p1, p2));
            }
            dp[i] = max;
        }
        return dp[0];
    }

    public static int maxProfit_dp_03(int[] prices) {
        int len = prices.length;
        int[] dp = new int[len + 2];
        int maxIndex = len - 1;

        for (int i = len - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[maxIndex + 2] + prices[maxIndex] - prices[i], dp[i + 1]);
            if (dp[i + 2] + prices[i] > dp[maxIndex + 2] + prices[maxIndex]) {
                maxIndex = i;
            }
        }
        return dp[0];
    }

    public static int maxProfit_dp_04(int[] prices) {
        int len = prices.length;
        int max = 0, pre1 = 0, pre2 = 0;

        for (int i = len - 1; i >= 0; i--) {
            int p = Math.max(max - prices[i], pre1);
            max = Math.max(max, pre2 + prices[i]);
            pre2 = pre1;
            pre1 = p;
        }
        return pre1;
    }
}
