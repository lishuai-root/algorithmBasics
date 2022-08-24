package leetcode;

/**
 * @description: You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * <p>
 * The area of an island is the number of cells with a value 1 in the island.
 * <p>
 * Return the maximum area of an island in grid. If there is no island, return 0.
 * @author: LISHUAI
 * @createDate: 2022/7/15 22:55
 * @version: 1.0
 */

public class LeetCode_695 {

    private static final int[][] TMP = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
//        int[][] grid = {
//                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
//        };
//        int[][] grid = {{1, 1}};
        int[][] grid = {{1, 1}, {1, 0}};
        int i = maxAreaOfIsland(grid);
        System.out.println(i);
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int rLen = grid.length, cLen = grid[rLen - 1].length;
        boolean[][] bls = new boolean[rLen][cLen];
        int ans = 0;
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                ans = Math.max(ans, maxAreaOfIslandProcess(grid, bls, i, j));
            }
        }
        return ans;
    }

    private static int maxAreaOfIslandProcess(int[][] grid, boolean[][] bls, int row, int col) {
        if (bls[row][col] || grid[row][col] == 0) {
            return 0;
        }
        bls[row][col] = true;
        int ans = 1;
        int rLen = grid.length, cLen = grid[rLen - 1].length;
        for (int[] ints : TMP) {
            int r = row + ints[0];
            int c = col + ints[1];
            if (r >= 0 && r < rLen && c >= 0 && c < cLen) {
                ans += maxAreaOfIslandProcess(grid, bls, r, c);
            }
        }
        return ans;
    }
}
