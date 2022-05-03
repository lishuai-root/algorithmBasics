package leetcode;

/**
 * @description: You are given an m x n binary matrix grid.
 * <p>
 * A move consists of choosing any row or column and toggling each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).
 * <p>
 * Every row of the matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
 * <p>
 * Return the highest possible score after making any number of moves (including zero moves).
 * @author: LISHUAI
 * @createDate: 2022/5/3 14:18
 * @version: 1.0
 */

public class LeetCode_861 {

    public static void main(String[] args) {
//        int[][] grid = {{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
//        int[][] grid = {{0, 1}, {0, 1}, {0, 1}, {0, 0}};
        int[][] grid = {{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
//        int[][] grid = {{0, 1}, {1, 1}};
        int i = matrixScore(grid);
        System.out.println(i);
    }

    public static int matrixScore(int[][] grid) {
        int row = grid.length, col = grid[0].length;

        for (int i = 0; i < row; i++) {
            if (grid[i][0] == 0) {
                changeRow(grid, i, col);
            }
        }
        int ans = 0;
        for (int i = 0; i < col; i++) {
            int x = 0;
            for (int[] ints : grid) {
                x += ints[i];
            }
            if (x < ((row + 1) >>> 1)) {
                ans += ((1 << (col - i - 1)) * (row - x));
            } else {
                ans += ((1 << (col - i - 1)) * x);
            }
        }

        return ans;
    }

    private static void changeRow(int[][] grid, int r, int c) {
        for (int i = 0; i < c; i++) {
            grid[r][i] = grid[r][i] ^ 1;
        }
    }
}
