package leetcode;

/**
 * @description: You are given an m x n integer array grid where grid[i][j] could be:
 * <p>
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square,
 * that walk over every non-obstacle square exactly once.
 * <p>
 * here is exactly one starting cell and one ending cell.
 * <p>
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 * @author: LISHUAI
 * @createDate: 2022/4/11 21:35
 * @version: 1.0
 */

public class LeetCode_980 {

    private static final int[][] P = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int ans, startR, startC, endR, endC;

    public static void main(String[] args) {
//        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
//        int[][] grid = {{0, 1}, {2, 0}};
        int i = uniquePathsIII(grid);
        System.out.println(i);
    }

    public static int uniquePathsIII(int[][] grid) {
        boolean[][] bs = new boolean[grid.length][grid[0].length];
        int size = 0;
        ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    startR = i;
                    startC = j;
                    bs[i][j] = true;
                    ++size;
                }
                if (grid[i][j] == -1) {
                    bs[i][j] = true;
                    ++size;
                }
            }
        }
        size = grid.length * grid[0].length - size;
        uniquePathsIIIProcess(grid, bs, startR, startC, size);
        return ans;
    }

    private static void uniquePathsIIIProcess(int[][] grid, boolean[][] bs, int row, int col, int size) {

        if (size == 0 && grid[row][col] == 2) {
            ++ans;
            return;
        }

        int r, c;
        for (int[] ints : P) {
            r = row + ints[0];
            c = col + ints[1];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[r].length && !bs[r][c]) {
                bs[r][c] = true;
                uniquePathsIIIProcess(grid, bs, r, c, size - 1);
                bs[r][c] = false;
            }
        }
    }
}
