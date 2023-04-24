package leetcode;

/**
 * @description: You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 * <p>
 * <p>
 * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
 * @author: LISHUAI
 * @createDate: 2022/12/24 17:52
 * @version: 1.0
 */

public class LeetCode_790 {

    private static final int MOD = 1000000007;
    private static Long[][] cache;

    public static void main(String[] args) {
//        int n = 4;
//        int n = 3;
//        int n = 30;
        int n = 40;
        int i = numTilings(n);
        System.out.println(i);
        System.out.println(numTilings_dp(n));
        System.out.println(1000000006L * 6);
    }

    public static int numTilings(int n) {
        cache = new Long[n + 1][n + 1];
        return (int) numTilingsProcess(n, 0, 0) % 1000000007;
    }

    private static long numTilingsProcess(int n, int index1, int index2) {
        if (index1 == n && index2 == n) {
            return 1;
        }
        if (index1 > n || index2 > n) {
            return 0;
        }
        if (cache[index1][index2] != null) {
            return cache[index1][index2];
        }
        long p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0, p6 = 0, p7 = 0;
        if (index1 == index2) {
            p1 = numTilingsProcess(n, index1 + 1, index2 + 1);
            p2 = numTilingsProcess(n, index1 + 1, index2 + 2);
            p3 = numTilingsProcess(n, index1 + 2, index2 + 1);
        }
        if (index1 + 1 == index2) {
            p4 = numTilingsProcess(n, index1 + 2, index2 + 1);
        }
        if (index2 + 1 == index1) {
            p5 = numTilingsProcess(n, index1 + 1, index2 + 2);
        }
        p6 = numTilingsProcess(n, index1 + 2, index2);
        p7 = Math.max(p6, numTilingsProcess(n, index1, index2 + 2));

        cache[index1][index2] = Long.valueOf((p1 + p2 + p3 + p4 + p5 + p7) % 100000007);
        return ((p1 + p2 + p3 + p4 + p5 + p7) % 100000007);
    }

    public static int numTilings_dp(int n) {
        long[][] dp = new long[n + 3][n + 3];
        dp[n][n] = 1;

        for (int i = n; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i == n && j == n) {
                    dp[i][j] = 1;
                    continue;
                }
                long p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0, p6 = 0, p7 = 0;
                if (i == j) {
                    p1 = (dp[i + 1][j + 1] + dp[i + 1][j + 2] + dp[i + 2][j + 1]);
                }
                if (i + 1 == j) {
                    p4 = dp[i + 2][j + 1];
                }
                if (j + 1 == i) {
                    p5 = dp[i + 1][j + 2];
                }
                p6 = dp[i + 2][j];
                p7 = Math.max(p6, dp[i][j + 2]);

                dp[i][j] = (p1 + p4 + p5 + p7);
            }
        }
        return (int) dp[0][0] % 1000000007;
    }

    public static int numTilings_other(int N) {
        if (N == 1) return 1;
        if (N == 2) return 2;
        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 3]) % MOD;
        }
        return (int) dp[N];
    }
}
