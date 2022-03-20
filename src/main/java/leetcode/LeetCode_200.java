package leetcode;

/**
 * @description: Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * @author: LISHUAI
 * @createDate: 2021/11/26 22:54
 * @version: 1.0
 */

public class LeetCode_200 {

    public static void main(String[] args) {

        char[][] arr = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};

        int i = numIslands_02(arr);

        System.out.println(i);
    }

    public static int numIslands(char[][] grid) {

        int result = 0;

        boolean[][] bl = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (!bl[i][j] && grid[i][j] == '1') {

                    result++;

                    process(grid, bl, i, j);
                }
            }
        }

        return result;
    }

    public static void process(char[][] grid, boolean[][] bl, int row, int col) {

        bl[row][col] = true;

        if (row - 1 >= 0 && !bl[row - 1][col] && grid[row - 1][col] == '1') {

            process(grid, bl, row - 1, col);
        }

        if (row + 1 < grid.length && !bl[row + 1][col] && grid[row + 1][col] == '1') {

            process(grid, bl, row + 1, col);
        }

        if (col - 1 >= 0 && !bl[row][col - 1] && grid[row][col - 1] == '1') {

            process(grid, bl, row, col - 1);
        }

        if (col + 1 < grid[0].length && !bl[row][col + 1] && grid[row][col + 1] == '1') {

            process(grid, bl, row, col + 1);
        }
    }

    public static int numIslands_02(char[][] grid) {

        int rowLen = grid.length, colLen = grid[0].length;

        if (rowLen == 1) {

            int r = grid[0][colLen - 1] == '1' ? 1 : 0;

            for (int i = colLen - 2; i >= 0; i--) {

                if (grid[0][i] == '1' && grid[0][i] != grid[0][i + 1]) {

                    r++;
                }
            }

            return r;
        }

        if (colLen == 1) {

            int r = grid[rowLen - 1][0] == '1' ? 1 : 0;

            for (int i = rowLen - 2; i >= 0; i--) {

                if (grid[i][0] == '1' && grid[i][0] != grid[i + 1][0]) {

                    r++;
                }
            }

            return r;
        }

        int[][] dp = new int[rowLen][colLen];

        dp[rowLen - 1][colLen - 1] = grid[rowLen - 1][colLen - 1] == '1' ? 1 : 0;

        for (int i = rowLen - 2; i >= 0; i--) {

            if (grid[i][colLen - 1] == '0' || (grid[i][colLen - 1] == '1' && grid[i + 1][colLen - 1] == '1')) {

                dp[i][colLen - 1] = dp[i + 1][colLen - 1];
            } else {

                dp[i][colLen - 1] = dp[i + 1][colLen - 1] + 1;
            }
        }

        for (int i = colLen - 2; i >= 0; i--) {

            if (grid[rowLen - 1][i] == '0' || (grid[rowLen - 1][i] == '1' && grid[rowLen - 1][i + 1] == '1')) {

                dp[rowLen - 1][i] = dp[rowLen - 1][i + 1];
            } else {

                dp[rowLen - 1][i] = dp[rowLen - 1][i + 1] + 1;
            }
        }

        for (int i = rowLen - 2; i >= 0; i--) {

            for (int j = colLen - 2; j >= 0; j--) {

                if (grid[i][j] == '1') {

                    if (grid[i + 1][j] == '1' || grid[i][j + 1] == '1') {

                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                    } else {

                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]) + 1;
                    }

                } else {

                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }

            }
        }

        for (int i = 0; i < rowLen; i++) {

            for (int j = 0; j < colLen; j++) {

                System.out.print(dp[i][j] + "   ");
            }

            System.out.println();
        }

        return dp[0][0];
    }
}
