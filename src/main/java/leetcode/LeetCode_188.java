package leetcode;

/**
 * @description: You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * @author: LISHUAI
 * @createDate: 2022/9/10 22:01
 * @version: 1.0
 */

public class LeetCode_188 {

    public static void main(String[] args) {
//        int[] prices = new int[]{2, 4, 1};
//        int k = 2;
//        int[] prices = new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
//        int k = 2;
        int[] prices = new int[]{3, 2, 6, 5, 0, 3};
        int k = 2;
//        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
//        int k = 2;
        int i = maxProfit(k, prices);
        System.out.println(i);
        int i2 = maxProfit_02(k, prices);
        System.out.println(i2);
        int i1 = maxProfit_dp(k, prices);
        System.out.println(i1);
        int i3 = maxProfit_teacher(k, prices);
        System.out.println(i3);
    }

    public static int maxProfit(int k, int[] prices) {
        return maxProfitProcess(prices, k, 0, 0, 0);
    }

    public static int maxProfit_02(int k, int[] prices) {
        return maxProfitProcess(prices, 0, k);
    }

    private static int maxProfitProcess(int[] prices, int k, int index, int n, int pre) {
        if (index >= prices.length) {
            return 0;
        }

        int ans;
        if (n == 1) {
            int p1 = maxProfitProcess(prices, k, index + 1, 0, 0) + prices[index] - prices[pre];
            int p2 = maxProfitProcess(prices, k, index + 1, n, pre);
            ans = Math.max(p1, p2);
        } else {
            if (k == 0) {
                return 0;
            }
            int p3 = maxProfitProcess(prices, k - 1, index + 1, 1, index);
            int p4 = maxProfitProcess(prices, k, index + 1, 0, index);
            ans = Math.max(p3, p4);
        }
        return ans;
    }

    private static int maxProfitProcess(int[] prices, int index, int k) {
        if (k <= 0 || index >= prices.length) {
            return 0;
        }
        int ans = 0;

        int p2 = maxProfitProcess(prices, index + 1, k);

        for (int i = index + 1; i < prices.length; i++) {
            if (prices[i] > prices[index]) {
                int p = maxProfitProcess(prices, i + 1, k - 1) + prices[i] - prices[index];
                ans = Math.max(ans, p);
            }
        }
        return Math.max(ans, p2);
    }

    public static int maxProfit_dp(int k, int[] prices) {
        int len = prices.length;
        if (k >= (len >>> 1)) {
            return allTrans(prices);
        }
        int[][] dp = new int[k + 1][len + 1];

        for (int i = k - 1; i >= 0; i--) {
            for (int j = len - 1; j >= 0; j--) {
                int p = dp[i][j + 1];
                for (int l = j + 1; l < len; l++) {
                    if (prices[l] > prices[j]) {
                        p = Math.max(p, dp[i + 1][l + 1] + prices[l] - prices[j]);
                    }
                }
                dp[i][j] = p;
            }
        }
        return dp[0][0];
    }

    public static int maxProfit_teacher(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        if (K >= N / 2) {
            return allTrans(prices);
        }
        int[][] dp = new int[K + 1][N];
        int ans = 0;
        for (int tran = 1; tran <= K; tran++) {
            int pre = dp[tran][0];
            int best = pre - prices[0];
            for (int index = 1; index < N; index++) {
                pre = dp[tran - 1][index];
                dp[tran][index] = Math.max(dp[tran][index - 1], prices[index] + best);
                best = Math.max(best, pre - prices[index]);
                ans = Math.max(dp[tran][index], ans);
            }
        }
        return ans;
    }

    public static int allTrans(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }
}
