package leetcode;

/**
 * @description: There is a group of n members, and a list of various crimes they could commit. The ith crime generates a profit[i] and requires group[i] members to participate in it. If a member participates in one crime, that member can't participate in another crime.
 * <p>
 * Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit, and the total number of members participating in that subset of crimes is at most n.
 * <p>
 * Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2023/4/23 2:31
 * @version: 1.0
 */

public class LeetCode_879 {

    private static final int MOD = 1000000007;
    private static int[][][] cache;

    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        cache = new int[n + 1][group.length + 1][group.length * 100 + 1];
        return profitableSchemesProcess(group, profit, minProfit, n, 0, 0);
    }

    private static int profitableSchemesProcess(int[] group, int[] profit, int minProfit, int n, int index, int sum) {

        if (n < 0) {
            return 0;
        }
        if (index >= group.length) {
            return sum >= minProfit ? 1 : 0;
        }
        if (cache[n][index][sum] != 0) {
            return cache[n][index][sum] == -1 ? 0 : cache[n][index][sum];
        }
        int ans = 0;
        ans = (ans + profitableSchemesProcess(group, profit, minProfit, n, index + 1, sum)) % MOD;
        ans = (ans + profitableSchemesProcess(group, profit, minProfit, n - group[index], index + 1, sum + profit[index])) % MOD;
        cache[n][index][sum] = ans == 0 ? -1 : ans;
        return ans;
    }

    public static int profitableSchemes_dp(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length;
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][minProfit] = 1;
        }

        for (int i = len - 1; i >= 0; i--) {
            for (int j = n; j >= group[i]; j--) {
                for (int k = 0; k <= minProfit; k++) {
                    int p = Math.min(minProfit, k + profit[i]);
                    dp[j][k] = (dp[j][k] + dp[j - group[i]][p]) % MOD;
                }
            }
        }
        return dp[n][0];
    }
}
