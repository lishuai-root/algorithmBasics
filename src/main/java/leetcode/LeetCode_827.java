package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * <p>
 * Return the size of the largest island in grid after applying this operation.
 * <p>
 * An island is a 4-directionally connected group of 1s.
 * @author: LISHUAI
 * @createDate: 2022/5/15 21:22
 * @version: 1.0
 */

public class LeetCode_827 {
    private static int[][] tmp = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        int[][] grid = {{1, 1}, {1, 0}};
        int i = largestIsland(grid);
        System.out.println(i);
    }

    public static int largestIsland(int[][] grid) {
        int rowLen = grid.length, colLen = grid[0].length;
        int[][] dp = new int[rowLen][colLen];
        int index = 1, max = 0, unionMax = 0;
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (dp[i][j] == 0 && grid[i][j] != 0) {
                    int num = largestIslandProcess(grid, dp, i, j, index);
                    list.add(num);
                    max = Math.max(max, num);
                    index++;
                }
            }
        }

        int[] bits = new int[(index + 31) >>> 5];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] == 0) {
                    int ans = 1;
                    Arrays.fill(bits, 0);
                    for (int[] ints : tmp) {
                        int r = i + ints[0];
                        int c = j + ints[1];
                        if (r < dp.length && r >= 0 && c < dp[0].length && c >= 0 && dp[r][c] != 0) {
                            if ((bits[dp[r][c] >>> 5] & (1 << (dp[r][c] & 31))) == 0) {
                                ans += list.get(dp[r][c]);
                                bits[dp[r][c] >>> 5] = bits[dp[r][c] >>> 5] | (1 << (dp[r][c] & 31));
                            }
                        }
                    }
                    unionMax = Math.max(unionMax, ans);
                }
            }
        }
        return Math.max(unionMax, max);
    }

    private static int largestIslandProcess(int[][] grid, int[][] dp, int row, int col, int index) {

        if (grid[row][col] == 0) {
            return 0;
        }
        dp[row][col] = index;
        int ans = 1;
        for (int[] ints : tmp) {
            int r = row + ints[0];
            int c = col + ints[1];
            if (r < dp.length && r >= 0 && c < dp[0].length && c >= 0 && dp[r][c] == 0) {
                ans += largestIslandProcess(grid, dp, r, c, index);
            }
        }
        return ans;
    }
}
