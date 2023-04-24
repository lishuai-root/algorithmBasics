package leetcode;

/**
 * @description: You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
 * <p>
 * Train tickets are sold in three different ways:
 * <p>
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 * <p>
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 * @author: LISHUAI
 * @createDate: 2023/3/28 22:37
 * @version: 1.0
 */

public class LeetCode_983 {

    private static Integer[][] cache;

    public static void main(String[] args) {
        int[] days = {1, 4, 6, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 21, 22, 23, 27, 28};
        int[] costs = {3, 13, 45};
        int i = mincostTickets(days, costs);
        System.out.println(i);
        System.out.println(mincostTickets_dp(days, costs));
    }

    public static int mincostTickets(int[] days, int[] costs) {
        cache = new Integer[days.length][396];
        return mincostTicketsProcess(days, costs, 0, 0);
    }

    private static int mincostTicketsProcess(int[] days, int[] costs, int index, int d) {
        if (index >= days.length) {
            return 0;
        }

        if (cache[index][d] != null) {
            return cache[index][d];
        }
        int ans = Integer.MAX_VALUE;
        if (d >= days[index]) {
            ans = mincostTicketsProcess(days, costs, index + 1, d);
        } else {
            ans = Math.min(ans, mincostTicketsProcess(days, costs, index + 1, days[index]) + costs[0]);
            ans = Math.min(ans, mincostTicketsProcess(days, costs, index + 1, days[index] + 6) + costs[1]);
            ans = Math.min(ans, mincostTicketsProcess(days, costs, index + 1, days[index] + 29) + costs[2]);
        }

        cache[index][d] = ans;
        return ans;
    }

    public static int mincostTickets_dp(int[] days, int[] costs) {
        if (days.length == 0) {
            return 0;
        }
        int len = days.length;
        int size = days[len - 1] + 30;
        int[] dp = new int[size];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = days[i] - 1; j >= 0; j--) {
                dp[j] = Math.min(dp[days[i]] + costs[0], Math.min(dp[days[i] + 6] + costs[1], dp[days[i] + 29] + costs[2]));
            }
        }
        return dp[0];
    }
}
