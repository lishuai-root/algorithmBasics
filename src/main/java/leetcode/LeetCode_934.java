package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 * <p>
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
 * <p>
 * You may change 0's to 1's to connect the two islands to form one island.
 * <p>
 * Return the smallest number of 0's you must flip to connect the two islands.
 * @author: LISHUAI
 * @createDate: 2023/5/21 21:51
 * @version: 1.0
 */

public class LeetCode_934 {

    private static final int[][] TEMP = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) {
        int[][] grid = {{0, 1}, {1, 0}};
        int i = shortestBridge(grid);
        System.out.println(i);
    }

    public static int shortestBridge(int[][] grid) {
        int rLen = grid.length, cLen = grid[rLen - 1].length;
        int[][] cache = new int[rLen][cLen];
        int index = 1;
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (grid[i][j] == 1 && cache[i][j] == 0) {
                    shortestBridgeFlag(grid, cache, i, j, index);
                    index++;
                }
            }
        }
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        int[][] flags = new int[rLen][cLen];
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (cache[i][j] == 1) {
                    queue.offer(new int[]{i, j, 0});
                    flags[i][j] = 1;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] curs = queue.poll();
            for (int[] next : TEMP) {
                int r = curs[0] + next[0];
                int c = curs[1] + next[1];
                if (r >= 0 && r < rLen && c >= 0 && c < cLen && flags[r][c] == 0) {
                    if (cache[r][c] == 2) {
                        return curs[2];
                    }
                    queue.offer(new int[]{r, c, curs[2] + 1});
                    flags[r][c] = 1;
                }
            }
        }
        return -1;
    }

    private static void shortestBridgeFlag(int[][] grid, int[][] cache, int row, int col, int index) {
        int rLen = grid.length, cLen = grid[rLen - 1].length;
        if (row < 0 || row >= rLen || col < 0 || col >= cLen || grid[row][col] == 0 || cache[row][col] != 0) {
            return;
        }
        cache[row][col] = index;
        for (int[] next : TEMP) {
            shortestBridgeFlag(grid, cache, row + next[0], col + next[1], index);
        }
    }
}
