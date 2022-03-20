package leetcode;

/**
 * @description: You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * <p>
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * @author: LISHUAI
 * @createDate: 2021/11/21 19:58
 * @version: 1.0
 */

public class LeetCode_121 {

    public static void main(String[] args) {

        int[] arr = new int[]{7, 1, 5, 3, 6, 4};

        int i = maxProfit_02(arr);

        System.out.println(i);
    }

    public static int maxProfit(int[] prices) {

        int[] stack = new int[prices.length];

        int index = -1, max = 0;

        for (int i = 0; i < prices.length; i++) {

            while (index != -1 && prices[i] < stack[index]) {

                max = Math.max(max, stack[index--] - stack[0]);
            }

            stack[++index] = prices[i];
        }

        while (index > 0) {

            max = Math.max(max, stack[index--] - stack[0]);
        }

        return max;
    }

    public static int maxProfit_02(int[] prices) {

        int max = 0;

        int minNum = prices[0], maxNum = prices[0];

        for (int i = 1; i < prices.length; i++) {

            if (maxNum < prices[i]) {

                maxNum = prices[i];
            } else if (minNum > prices[i]) {

                max = Math.max(max, maxNum - minNum);

                maxNum = prices[i];

                minNum = prices[i];
            }
        }

        return Math.max(max, maxNum - minNum);
    }
}
