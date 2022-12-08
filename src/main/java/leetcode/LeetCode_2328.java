package leetcode;

/**
 * @description: You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.
 * <p>
 * Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell. Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * Two paths are considered different if they do not have exactly the same sequence of visited cells.
 * @author: LISHUAI
 * @createDate: 2022/12/7 0:51
 * @version: 1.0
 */

public class LeetCode_2328 {

    private static final int[][] TMP = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int T = 1000000007;

    private static int[][] dp;

    public static void main(String[] args) {
//        int[][] grid = {{1, 1}, {3, 4}};
        int[][] grid = {{1}, {2}};
        int i = countPaths(grid);
        System.out.println(i);
    }

    public static int countPaths(int[][] grid) {
        int rLen = grid.length, cLen = grid[rLen - 1].length;
        boolean[][] bls = new boolean[rLen][cLen];
        dp = new int[rLen][cLen];
        int ans = 0;
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                int p = countPathsProcess(grid, i, j, bls);
                ans += (p % T);
                ans %= T;
            }
        }
        return ans;
    }

    private static int countPathsProcess(int[][] grid, int row, int col, boolean[][] bls) {

        if (bls[row][col]) {
            return dp[row][col];
        }
        bls[row][col] = true;
        int ans = 1;
        for (int[] ints : TMP) {
            int r = row + ints[0];
            int c = col + ints[1];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[r].length && grid[r][c] > grid[row][col]) {
                int p = countPathsProcess(grid, r, c, bls);
                ans += (p % T);
                ans %= T;
            }
        }
        dp[row][col] = ans;
        return ans;
    }
}
