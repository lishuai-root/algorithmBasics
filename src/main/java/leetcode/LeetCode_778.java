package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 * <p>
 * The rain starts to fall. At time t, the depth of the water everywhere is t.
 * You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
 * You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 * <p>
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 * @author: LISHUAI
 * @createDate: 2022/4/28 23:50
 * @version: 1.0
 */

public class LeetCode_778 {
    private static final int[][] next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        int[][] grid = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
//        int[][] grid = {{0, 2}, {1, 3}};
//        int[][] grid = {{3, 2}, {0, 1}};
        int i = swimInWater(grid);
        System.out.println(i);
    }

    public static int swimInWater(int[][] grid) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });

        int row = grid.length, col = grid[0].length, ans = 0;
        int[][] dp = new int[row][col];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        dp[0][0] = grid[0][0];
        queue.add(new int[]{0, 0, dp[0][0]});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == row - 1 && cur[1] == col - 1) {
                ans = cur[2];
                break;
            }
            for (int[] ints : next) {
                int r = cur[0] + ints[0];
                int c = cur[1] + ints[1];
                if (r >= 0 && r < row && c >= 0 && c < col) {
                    int[] tmp = new int[]{r, c, Math.max(cur[2], grid[r][c])};
                    if (dp[r][c] > tmp[2]) {
                        dp[r][c] = tmp[2];
                        queue.add(tmp);
                    }
                }
            }
        }
        return ans;
    }


//    public static int swimInWater_dp(int[][] grid) {
//        int row = grid.length, col = grid[0].length;
//        int[][] dp = new int[row][col];
//        dp[row - 1][col - 1] = grid[row - 1][col - 1];
//
//        for
//    }
}
