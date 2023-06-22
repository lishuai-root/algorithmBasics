package leetcode;

/**
 * @description: You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * @author: LiShuai
 * @createDate: 2023/6/22 21:36
 * @version: 1.0
 */

public class LeetCode_714 {

    public static int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[] dp = new int[len + 1];

        for (int i = len - 1; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < len; j++) {
                max = Math.max(max, prices[j] - prices[i] - fee + dp[j + 1]);
            }
            dp[i] = Math.max(max, dp[i + 1]);
        }
        return dp[0];
    }

    public static int maxProfit_02(int[] prices, int fee) {
        int len = prices.length, max = 0, pre = 0;

        for (int i = len - 1; i >= 0; i--) {
            int t = pre;
            pre = Math.max(max - prices[i] - fee, t);
            max = Math.max(max, prices[i] + t);
        }
        return pre;
    }
}
