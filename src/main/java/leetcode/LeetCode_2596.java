package leetcode;

/**
 * @description: There is a knight on an n x n chessboard. In a valid configuration, the knight starts at the top-left cell of the board and visits every cell on the board exactly once.
 * <p>
 * You are given an n x n integer matrix grid consisting of distinct integers from the range [0, n * n - 1] where grid[row][col] indicates that the cell (row, col) is the grid[row][col]th cell that the knight visited. The moves are 0-indexed.
 * <p>
 * Return true if grid represents a valid configuration of the knight's movements or false otherwise.
 * <p>
 * Note that a valid knight move consists of moving two squares vertically and one square horizontally, or two squares horizontally and one square vertically. The figure below illustrates all the possible eight moves of a knight from some cell.
 * @author: LiShuai
 * @createDate: 2023/6/3 22:13
 * @version: 1.0
 */

public class LeetCode_2596 {

    private static final int[][] TEMP = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

    public static boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int rLen = grid.length, cLen = grid[0].length;
        int target = rLen * cLen - 1;
        int cur = 0, row = 0, col = 0;

        while (cur < target) {
            boolean b = false;
            for (int[] next : TEMP) {
                int r = next[0] + row;
                int c = next[1] + col;
                if (r >= 0 && r < rLen && c >= 0 && c < cLen && grid[r][c] == cur + 1) {
                    row = r;
                    col = c;
                    b = true;
                    break;
                }
            }
            if (!b) {
                return false;
            }
            ++cur;
        }
        return true;
    }
}
