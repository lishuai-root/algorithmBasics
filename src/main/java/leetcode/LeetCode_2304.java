package leetcode;

/**
 * @description: You are given a 0-indexed m x n integer matrix grid consisting of distinct integers from 0 to m * n - 1. You can move in this matrix from a cell to any other cell in the next row. That is, if you are in cell (x, y) such that x < m - 1, you can move to any of the cells (x + 1, 0), (x + 1, 1), ..., (x + 1, n - 1). Note that it is not possible to move from cells in the last row.
 * <p>
 * Each possible move has a cost given by a 0-indexed 2D array moveCost of size (m * n) x n, where moveCost[i][j] is the cost of moving from a cell with value i to a cell in column j of the next row. The cost of moving from cells in the last row of grid can be ignored.
 * <p>
 * The cost of a path in grid is the sum of all values of cells visited plus the sum of costs of all the moves made. Return the minimum cost of a path that starts from any cell in the first row and ends at any cell in the last row.
 * @author: LISHUAI
 * @createDate: 2023/4/2 20:15
 * @version: 1.0
 */

public class LeetCode_2304 {

    public static int minPathCost(int[][] grid, int[][] moveCost) {
        int rowLen = grid.length, colLen = grid[0].length;
        int[][] dp = new int[rowLen][colLen];
        System.arraycopy(grid[rowLen - 1], 0, dp[rowLen - 1], 0, colLen);

        for (int i = rowLen - 2; i >= 0; i--) {
            for (int j = 0; j < colLen; j++) {
                int min = Integer.MAX_VALUE;
                int c = grid[i][j];
                for (int k = 0; k < colLen; k++) {
                    min = Math.min(min, dp[i + 1][k] + moveCost[c][k]);
                }
                dp[i][j] = min + c;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i : dp[0]) {
            ans = Math.min(ans, i);
        }
        return ans;
    }
}
