package leetcode;

/**
 * @description: You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
 * <p>
 * Find and return the maximum profit you can achieve.
 * @author: LISHUAI
 * @createDate: 2022/12/17 18:01
 * @version: 1.0
 */

public class LeetCode_122 {

    public static int maxProfit(int[] prices) {
        int len = prices.length, index = -1, ans = 0;
        int[] stack = new int[len];

        for (int i = 0; i < len; i++) {
            int max = 0;
            while (index != -1 && prices[i] >= prices[stack[index]]) {
                max = Math.max(max, prices[i] - prices[stack[index--]]);
            }
            ans += max;
            stack[++index] = i;
        }
        return ans;
    }

}
