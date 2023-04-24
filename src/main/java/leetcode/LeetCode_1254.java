package leetcode;

/**
 * @description: Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 * <p>
 * Return the number of closed islands.
 * @author: LISHUAI
 * @createDate: 2023/4/6 22:04
 * @version: 1.0
 */

public class LeetCode_1254 {

    private static final int[][] TEMP = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static int closedIsland(int[][] grid) {
        int rLen = grid.length, cLen = grid[0].length;
        boolean[][] bls = new boolean[rLen][cLen];
        int ans = 0;
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (grid[i][j] == 0 && !bls[i][j] && closedIslandProcess(grid, i, j, bls)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static boolean closedIslandProcess(int[][] grid, int row, int col, boolean[][] bls) {
        if (grid[row][col] == 1) {
            return true;
        }
        bls[row][col] = true;
        boolean ans = true;
        int rLen = grid.length, cLen = grid[0].length;
        if (row == 0 || row == rLen - 1 || col == 0 || col == cLen - 1) {
            ans = false;
        }

        for (int[] next : TEMP) {
            int r = row + next[0];
            int c = col + next[1];
            if (r >= 0 && r < rLen && c >= 0 && c < cLen && !bls[r][c]) {
                ans &= closedIslandProcess(grid, r, c, bls);
            }
        }
        return ans;
    }
}
