package leetcode;

/**
 * @description: Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 * <p>
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some signs on the cells of the grid that point outside the grid.
 * <p>
 * You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.
 * <p>
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 * <p>
 * Return the minimum cost to make the grid have at least one valid path.
 * @author: LISHUAI
 * @createDate: 2022/9/3 23:26
 * @version: 1.0
 */

public class LeetCode_1368 {

    private static final int[][] TMP = {{}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static Integer[][][] cache;

    public static void main(String[] args) {
//        int[][] grid = {{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}};
//        int[][] grid = {{1, 1, 3}, {3, 2, 2}, {1, 1, 4}};
        int[][] grid = {{3, 4, 3}, {2, 2, 2}, {2, 1, 1}, {4, 3, 2}, {2, 1, 4}, {2, 4, 1}, {3, 3, 3}, {1, 4, 2}, {2, 2, 1}, {2, 1, 1}, {3, 3, 1}, {4, 1, 4}, {2, 1, 4}, {3, 2, 2}, {3, 3, 1}, {4, 4, 1}, {1, 2, 2}, {1, 1, 1}, {1, 3, 4}, {1, 2, 1}, {2, 2, 4}, {2, 1, 3}, {1, 2, 1}, {4, 3, 2}, {3, 3, 4}, {2, 2, 1}, {3, 4, 3}, {4, 2, 3}, {4, 4, 4}};
//        int[][] grid = {
//                {1, 1, 3},
//                {2, 2, 2},
//                {4, 4, 1}
//        };
        int i = minCost(grid);
        System.out.println(i);
    }

    public static int minCost(int[][] grid) {
        int rLen = grid.length, cLen = grid[rLen - 1].length;
        boolean[][] bs = new boolean[rLen][cLen];
        cache = new Integer[rLen][cLen][5];
        return minCostProcess(grid, 0, 0, bs);
    }

    private static int minCostProcess(int[][] grid, int row, int col, boolean[][] bs) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length) {
            return Integer.MAX_VALUE;
        }
        if (row == grid.length - 1 && col == grid[row].length - 1) {
            return 0;
        }
        if (bs[row][col]) {
            return Integer.MAX_VALUE;
        }
//        if (cache[row][col] != null) {
//            return cache[row][col];
//        }
        bs[row][col] = true;
        int[] ints = TMP[grid[row][col]];
        int ans;
        if (cache[row][col][grid[row][col]] != null) {
            ans = cache[row][col][grid[row][col]];
        } else {
            ans = minCostProcess(grid, row + ints[0], col + ints[1], bs);
            cache[row][col][grid[row][col]] = ans;
        }
        if (ans != 0) {
            for (int i = 1; i < TMP.length; i++) {
                if (i != grid[row][col]) {
                    ints = TMP[i];
                    int p;
                    if (cache[row][col][i] != null) {
                        p = cache[row][col][i];
                    } else {
                        p = minCostProcess(grid, row + ints[0], col + ints[1], bs);
                        cache[row][col][i] = p;
                    }
                    if (p != Integer.MAX_VALUE) {
                        p++;
                    }
                    ans = Math.min(ans, p);
                }
            }
        }
        bs[row][col] = false;
//        cache[row][col] = ans;
        return ans;
    }
}
