package leetcode;

/**
 * @description: You are given an integer array prices representing the daily price history of a stock, where prices[i] is the stock price on the ith day.
 * <p>
 * A smooth descent period of a stock consists of one or more contiguous days such that the price on each day is lower than the price on the preceding day by exactly 1. The first day of the period is exempted from this rule.
 * <p>
 * Return the number of smooth descent periods.
 * @author: LISHUAI
 * @createDate: 2023/3/21 21:23
 * @version: 1.0
 */

public class LeetCode_2110 {

    public static long getDescentPeriods(int[] prices) {

        int left = 0, right = 1, len = prices.length;
        long ans = 0;

        while (right < len) {
            if (prices[right] + 1 == prices[right - 1]) {
                ans += (right - left);
            } else {
                left = right;
            }
            right++;
        }
        return ans + len;
    }
}
