package leetcode;

/**
 * @description: Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * @author: LISHUAI
 * @createDate: 2021/11/20 23:54
 * @version: 1.0
 */

public class LeetCode_064 {

    public static void main(String[] args) {

        int[][] ints = new int[][]{
                {1, 2, 3},
                {4, 5, 6}

        };

        int i = minPathSum_04(ints);
        int i1 = minPathSum_02(ints);

        System.out.println(i);
        System.out.println(i1);
    }


    public static int minPathSum(int[][] grid) {

        return process(grid, 0, 0, 0);
    }

    public static int process(int[][] grid, int row, int col, int sum) {

        if (row == grid.length - 1 && col == grid[0].length - 1) {

            return sum + grid[row][col];
        }

        if (row >= grid.length || col >= grid[0].length) {

            return Integer.MAX_VALUE;
        }

        sum += grid[row][col];

        int r = Integer.MAX_VALUE, c = Integer.MAX_VALUE;

        if (row + 1 < grid.length) {

            r = process(grid, row + 1, col, sum);
        }

        if (col + 1 < grid[0].length) {

            c = process(grid, row, col + 1, sum);
        }


        return Math.min(r, c);
    }


    public static int minPathSum_02(int[][] grid) {

        int rowLen = grid.length, colLen = grid[0].length;

        int[][] ints = new int[rowLen][colLen];

        ints[0][0] = grid[0][0];

        for (int i = 1; i < rowLen; i++) {

            ints[i][0] = ints[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < colLen; i++) {

            ints[0][i] = ints[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < rowLen; i++) {

            for (int j = 1; j < colLen; j++) {

                ints[i][j] = Math.min(ints[i - 1][j], ints[i][j - 1]) + grid[i][j];
            }
        }

        return ints[rowLen - 1][colLen - 1];
    }


    public static int minPathSum_03(int[][] grid) {

        int rowLen = grid.length, colLen = grid[0].length;

        int[] ints = new int[colLen];

        ints[0] = grid[0][0];

        for (int i = 1; i < colLen; i++) {

            ints[i] = ints[i - 1] + grid[0][i];
        }

        for (int i = 1; i < rowLen; i++) {

            ints[0] = ints[0] + grid[i][0];

            for (int j = 1; j < colLen; j++) {

                ints[j] = Math.min(ints[j - 1], ints[j]) + grid[i][j];
            }
        }

        return ints[colLen - 1];
    }


    public static int minPathSum_04(int[][] grid) {

        int rowLen = grid.length, colLen = grid[0].length;

        for (int i = 1; i < rowLen; i++) {

            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < colLen; i++) {

            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < rowLen; i++) {

            for (int j = 1; j < colLen; j++) {

                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[rowLen - 1][colLen - 1];
    }

}
