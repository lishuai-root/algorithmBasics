package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * @description: You are a hiker preparing for an upcoming hike.
 * You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col).
 * You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
 * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 * <p>
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * <p>
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 * @author: LISHUAI
 * @createDate: 2022/4/28 21:10
 * @version: 1.0
 */

public class LeetCode_1631 {
    private static final int[][] next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static Integer[][] cache;
    private static long size = 0;

    public static void main(String[] args) {
//        int[][] heights = {{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};
//        int[][] heights = {{8, 3, 2, 5, 2, 10, 7, 1, 8, 9},
//                {1, 4, 9, 1, 10, 2, 4, 10, 3, 5},
//                {4, 10, 10, 3, 6, 1, 3, 9, 8, 8},
//                {4, 4, 6, 10, 10, 10, 2, 10, 8, 8},
//                {9, 10, 2, 4, 1, 2, 2, 6, 5, 7},
//                {2, 9, 2, 6, 1, 4, 7, 6, 10, 9},
//                {8, 8, 2, 10, 8, 2, 3, 9, 5, 3},
//                {2, 10, 9, 3, 5, 1, 7, 4, 5, 6},
//                {2, 3, 9, 2, 5, 10, 2, 7, 1, 8},
//                {9, 10, 4, 10, 7, 4, 9, 3, 1, 6}
//        };
//        int[][] heights = {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
//        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
//        int[][] heights = {{3}};
//        int[][] heights = {{1, 10, 6, 7, 9, 10, 4, 9}};

        for (int i = 0; i < 10000; i++) {
            int[][] heights = makeArray(5, 5);
//            System.out.println("minimumEffortPath(heights) ： " + minimumEffortPath(heights) + ", minimumEffortPath_02(heights) ： " + minimumEffortPath_02(heights));
            int i1 = minimumEffortPath(heights);
            int i2 = minimumEffortPath_02(heights);
            if (i1 != i2) {
                System.out.println("minimumEffortPath(heights) ： " + i1 + ", minimumEffortPath_02(heights) ： " + i2);
            }
        }
//        int[][] heights = makeArray(5, 5);
//        int i = minimumEffortPath(heights);
//        System.out.println(i);
//        int i1 = minimumEffortPath_02(heights);
//        System.out.println(i1);
    }

    private static int[][] makeArray(int row, int col) {
        int size = row * col * 10;
        int[][] ans = new int[row][col];
        Random random = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[i][j] = random.nextInt(size);
            }
        }
        return ans;
    }

    public static int minimumEffortPath(int[][] heights) {
        boolean[][] bls = new boolean[heights.length][heights[0].length];
        bls[0][0] = true;
        return minimumEffortPathProcess(heights, 0, 0, bls);
    }

    private static int minimumEffortPathProcess(int[][] heights, int row, int col, boolean[][] bls) {
        if (row == heights.length - 1 && col == heights[row].length - 1) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (int[] ints : next) {
            int r = row + ints[0];
            int c = col + ints[1];
            if (r >= 0 && r < heights.length && c >= 0 && c < heights[r].length && !bls[r][c]) {
                bls[r][c] = true;
                ans = Math.min(ans, Math.max(minimumEffortPathProcess(heights, r, c, bls), Math.abs(heights[row][col] - heights[r][c])));
                bls[r][c] = false;
            }
        }

        return ans;
    }

    public static int minimumEffortPath_02(int[][] heights) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });

        int row = heights.length, col = heights[0].length, ans = 0;
        int[][] dp = new int[row][col];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        queue.add(new int[]{0, 0, 0});
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
                    int[] tmp = new int[]{r, c, Math.max(cur[2], Math.abs(heights[cur[0]][cur[1]] - heights[r][c]))};
                    if (dp[r][c] > tmp[2]) {
                        dp[r][c] = tmp[2];
                        queue.add(tmp);
                    }
                }
            }
        }
        return ans;
    }

    public static int minimumEffortPath_dp(int[][] heights) {
        int row = heights.length, col = heights[row - 1].length;
        int[][] dp = new int[row][col];
//        dp[row - 1][col - 1] = 0;
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = Math.max(dp[i + 1][col - 1], Math.abs(heights[i][col - 1] - heights[i + 1][col - 1]));
        }
        for (int i = col - 2; i >= 0; i--) {
            dp[row - 1][i] = Math.max(dp[row - 1][i + 1], Math.abs(heights[row - 1][i] - heights[row - 1][i + 1]));
        }
        for (int i = heights.length - 2; i >= 0; i--) {
            for (int j = heights[i].length - 2; j >= 0; j--) {
                int cur = Integer.MAX_VALUE;
                for (int[] ints : next) {
                    int r = i + ints[0];
                    int c = j + ints[1];
                    if (r >= 0 && r < row && c >= 0 && c < col) {
                        cur = Math.min(cur, Math.max(dp[r][c], Math.abs(heights[i][j] - heights[r][c])));
                    }
                }
//                dp[i][j] = Math.min(Math.max(dp[i + 1][j], Math.abs(heights[i][j] - heights[i + 1][j])),
//                        Math.max(dp[i][j + 1], Math.abs(heights[i][j] - heights[i][j + 1])));
                dp[i][j] = cur;
            }
        }

        for (int[] ints : dp) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        return dp[0][0];
    }

}
