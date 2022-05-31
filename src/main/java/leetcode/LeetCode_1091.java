package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 * <p>
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * <p>
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 * @author: LISHUAI
 * @createDate: 2022/5/16 21:41
 * @version: 1.0
 */

public class LeetCode_1091 {

    private static final int[][] tmp = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

    public static void main(String[] args) {
//        int[][] grid = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
//        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
//        int[][] grid = {{0, 1}, {1, 0}};
        int[][] grid = {
                {0, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0},
                {0, 1, 1, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };
        int i = shortestPathBinaryMatrix(grid);
        System.out.println(i);
    }

    public static int shortestPathBinaryMatrix_02(int[][] grid) {
        int rowLen = grid.length, colLen = grid[0].length, ans = -1;
        if (grid[0][0] != 0 || grid[rowLen - 1][colLen - 1] != 0) {
            return ans;
        }

        boolean[][] ways = new boolean[rowLen][colLen];
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });
        queue.offer(new int[]{0, 0, 1});
        ways[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curs = queue.poll();

            if (curs[0] == rowLen - 1 && curs[1] == colLen - 1) {
                ans = curs[2];
                break;
            }
            for (int[] ints : tmp) {
                int r = curs[0] + ints[0];
                int c = curs[1] + ints[1];
                if (r >= 0 && r < rowLen && c >= 0 && c < colLen && grid[r][c] == 0 && !ways[r][c]) {
                    queue.offer(new int[]{r, c, curs[2] + 1});
                    ways[r][c] = true;
                }
            }
        }
        return ans;
    }

    private static boolean connected(int[][] grid, int row, int col, boolean[][] bl) {
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return true;
        }

        boolean ans = false;
        for (int[] ints : tmp) {
            int r = row + ints[0];
            int c = col + ints[1];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && !bl[r][c]) {
                bl[r][c] = true;
                ans = connected(grid, r, c, bl);
                bl[r][c] = false;
                if (ans) {
                    break;
                }
            }
        }
        return ans;
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {
        boolean[][] bl = new boolean[grid.length][grid[0].length];
        int ans = shortestPathBinaryMatrixProcess(grid, 0, 0, bl);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int shortestPathBinaryMatrixProcess(int[][] grid, int row, int col, boolean[][] bl) {
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            if (grid[row][col] == 0) {
                return 1;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        if (grid[row][col] != 0) {
            return Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;

        for (int[] ints : tmp) {
            int r = row + ints[0];
            int c = col + ints[1];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && !bl[r][c]) {
                bl[r][c] = true;
                ans = Math.min(ans, shortestPathBinaryMatrixProcess(grid, r, c, bl));
                bl[r][c] = false;
            }
        }
        return ans == Integer.MAX_VALUE ? ans : ans + 1;
    }
}
