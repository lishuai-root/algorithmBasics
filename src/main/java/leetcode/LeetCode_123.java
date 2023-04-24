package leetcode;

/**
 * @description: You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * @author: LISHUAI
 * @createDate: 2022/12/17 18:21
 * @version: 1.0
 */

public class LeetCode_123 {

    public static void main(String[] args) {
//        int[] prices = {6, 1, 3, 2, 4, 7};
//        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices = {3, 4};
        int i = maxProfit(prices);
        System.out.println(i);
        System.out.println(maxProfit_02(prices));
    }

    public static int maxProfit_02(int[] prices) {
        int ans = 0, max = 0, ansMax = Integer.MIN_VALUE, min = prices[0];

        for (int price : prices) {
            ans = Math.max(ans, price + ansMax);
            max = Math.max(max, price - min);
            ansMax = Math.max(ansMax, max - price);
            min = Math.min(min, price);
        }
        return ans;
    }

    public static int maxProfit(int[] prices) {
        int len = prices.length, index = -1, preMax = 0, min = prices[0], ans = 0;
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                max = Math.max(max, prices[i] - prices[j] + dp[j]);
            }
            ans = Math.max(ans, max);
            preMax = Math.max(preMax, prices[i] - min);
            dp[i] = preMax;
            min = Math.min(min, prices[i]);
        }
        return ans;
    }
}
