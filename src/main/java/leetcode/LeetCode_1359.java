package leetcode;

/**
 * @description: Given n orders, each order consist in pickup and delivery services.
 * <p>
 * Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
 * <p>
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * @author: LISHUAI
 * @createDate: 2022/3/6 16:50
 * @version: 1.0
 */

public class LeetCode_1359 {

    private static final int MOD = 1000000007;
    private static Long[][] cache;

    public static void main(String[] args) {
        int n = 3;
        int i = countOrders(n);
        System.out.println(i);
        System.out.println(countOrders_02(n));
    }

    public static int countOrders(int n) {
        cache = new Long[n + 1][n + 1];
        return (int) countOrdersProcess(n, 0);
    }

    private static long countOrdersProcess(int p, int d) {
        if (p == 0 && d == 0) {
            return 1;
        }
        if (cache[p][d] != null) {
            return cache[p][d];
        }
        long ans1 = 0, ans2 = 0;
        if (p > 0) {
            ans1 = countOrdersProcess(p - 1, d + 1) * p;
        }
        if (d > 0) {
            ans2 = countOrdersProcess(p, d - 1) * d;
        }
        cache[p][d] = (ans1 + ans2) % MOD;
        return cache[p][d];
    }


    public static int countOrders_02(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = (dp[i - 1] * i) % MOD;
        }

        for (int i = 1; i <= n; ++i) {
            dp[0] = (dp[1] * i) % MOD;
            for (int j = 1; j <= n - i; j++) {
                dp[j] = ((dp[j + 1] * i) + ((dp[j - 1] * j))) % MOD;
            }
        }
        return (int) dp[0];
    }
}
