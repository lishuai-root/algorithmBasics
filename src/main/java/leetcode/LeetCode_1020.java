package leetcode;

/**
 * @description: You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 * <p>
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
 * <p>
 * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
 * @author: LISHUAI
 * @createDate: 2023/4/8 0:28
 * @version: 1.0
 */

public class LeetCode_1020 {
    private static final int[][] TEMP = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        int[][] grid = {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        int i = numEnclaves(grid);
        System.out.println(i);
    }

    public static int numEnclaves(int[][] grid) {
        int rLen = grid.length, cLen = grid[0].length;
        boolean[][] bls = new boolean[rLen][cLen];
        int ans = 0;
        for (int i = 0; i < rLen; i++) {
            ans += numEnclavesProcess(grid, i, 0, bls);
            ans += numEnclavesProcess(grid, i, cLen - 1, bls);
        }
        for (int i = 1; i < cLen - 1; i++) {
            ans += numEnclavesProcess(grid, 0, i, bls);
            ans += numEnclavesProcess(grid, rLen - 1, i, bls);
        }
        int sum = 0;
        for (int[] ints : grid) {
            for (int j = 0; j < cLen; j++) {
                sum += ints[j];
            }
        }
        return sum - ans;
    }

    private static int numEnclavesProcess(int[][] grid, int row, int col, boolean[][] bls) {
        int rLen = grid.length, cLen = grid[0].length;
        if (row < 0 || row == rLen || col < 0 || col == cLen || grid[row][col] == 0 || bls[row][col]) {
            return 0;
        }
        int ans = 1;
        bls[row][col] = true;
        for (int[] next : TEMP) {
            ans += numEnclavesProcess(grid, row + next[0], col + next[1], bls);
        }
        return ans;
    }
}
