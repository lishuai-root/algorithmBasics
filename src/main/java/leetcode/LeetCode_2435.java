package leetcode;

/**
 * @description: You are given a 0-indexed m x n integer matrix grid and an integer k. You are currently at position (0, 0) and you want to reach position (m - 1, n - 1) moving only down or right.
 * <p>
 * Return the number of paths where the sum of the elements on the path is divisible by k. Since the answer may be very large, return it modulo 109 + 7.
 * @author: LiShuai
 * @createDate: 2023/6/1 21:24
 * @version: 1.0
 */

public class LeetCode_2435 {
    private static final int MOD = (int) (10E9 + 7);
    private static Integer[][][] cache;

    public static void main(String[] args) {
        int[][] grid = {{5, 2, 4}, {3, 0, 5}, {0, 7, 2}};
        int k = 3;
        int i = numberOfPaths_dp(grid, k);
        System.out.println(i);
    }

    public static int numberOfPaths(int[][] grid, int k) {
        int rLen = grid.length, cLen = grid[0].length;
        cache = new Integer[rLen][cLen][k];
        return numberOfPathsProcess(grid, k, 0, 0, grid[0][0] % k);
    }

    private static int numberOfPathsProcess(int[][] grid, int k, int row, int col, int sum) {
        int rLen = grid.length, cLen = grid[0].length;
        if (row == rLen - 1 && col == cLen - 1) {
            return sum == 0 ? 1 : 0;
        }
        if (cache[row][col][sum] != null) {
            return cache[row][col][sum];
        }
        int ans = 0;
        if (col + 1 < cLen) {
            ans = numberOfPathsProcess(grid, k, row, col + 1, (sum + (grid[row][col + 1] % k)) % k);
        }
        if (row + 1 < rLen) {
            ans += numberOfPathsProcess(grid, k, row + 1, col, (sum + (grid[row + 1][col] % k)) % k);
        }
        cache[row][col][sum] = ans % 1000000007;
        return ans % 1000000007;
    }

    public static int numberOfPaths_dp(int[][] grid, int k) {
        int rLen = grid.length, cLen = grid[0].length;
        int[][][] dp = new int[rLen + 1][cLen + 1][k];
        int mod = 1000000007;
        dp[rLen - 1][cLen - 1][0] = 1;

        for (int i = rLen - 1; i >= 0; i--) {
            for (int j = cLen - 1; j >= 0; j--) {
                for (int l = 0; l < k; l++) {
                    if (i + 1 < rLen) {
                        dp[i][j][l] = dp[i + 1][j][(l + (grid[i + 1][j] % k)) % k];
                    }
                    if (j + 1 < cLen) {
                        dp[i][j][l] += dp[i][j + 1][(l + (grid[i][j + 1] % k)) % k];
                    }
                    dp[i][j][l] %= mod;
                }
            }
        }
        return dp[0][0][grid[0][0] % k];
    }
}
