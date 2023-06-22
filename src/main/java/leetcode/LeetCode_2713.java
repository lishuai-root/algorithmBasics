package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Given a 1-indexed m x n integer matrix mat, you can select any cell in the matrix as your starting cell.
 * <p>
 * From the starting cell, you can move to any other cell in the same row or column, but only if the value of the destination cell is strictly greater than the value of the current cell. You can repeat this process as many times as possible, moving from cell to cell until you can no longer make any moves.
 * <p>
 * Your task is to find the maximum number of cells that you can visit in the matrix by starting from some cell.
 * <p>
 * Return an integer denoting the maximum number of cells that can be visited.
 * @author: LiShuai
 * @createDate: 2023/6/18 20:00
 * @version: 1.0
 */

public class LeetCode_2713 {

    private static Integer[][] cache;

    private static int[] dp;
    private static int[][] preNum;

    public static void main(String[] args) {
//        int[][] mat = {{-8}, {-3}, {6}};
        int[][] mat = {{-9, 4, -8},
                {3, -4, -8}};
//        int[][] mat = {{1}, {1}, {1}, {1}};
//        int[][] mat = {{3, 1}, {3, 4}};
//        int[][] mat = {{3, 1, 6}, {-9, 5, 7}};
        int i = maxIncreasingCells_02(mat);
        System.out.println(i);
    }

    public static int maxIncreasingCells(int[][] mat) {
        int rowLwn = mat.length, colLen = mat[0].length;
        cache = new Integer[rowLwn][colLen];
        int ans = 0;
        for (int i = 0; i < rowLwn; i++) {
            for (int j = 0; j < colLen; j++) {
                ans = Math.max(ans, maxIncreasingCellsProcess(mat, i, j));
            }
        }
        return ans;
    }

    private static int maxIncreasingCellsProcess(int[][] mat, int row, int col) {
        if (cache[row][col] != null) {
            return cache[row][col];
        }
        int max = 1;
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][col] > mat[row][col]) {
                max = Math.max(max, maxIncreasingCellsProcess(mat, i, col) + 1);
            }
        }
        for (int i = 0; i < mat[0].length; i++) {
            if (mat[row][i] > mat[row][col]) {
                max = Math.max(max, maxIncreasingCellsProcess(mat, row, i) + 1);
            }
        }
        cache[row][col] = max;
        return max;
    }

    public static int maxIncreasingCells_02(int[][] mat) {
        int rowLwn = mat.length, colLen = mat[0].length;
        List<int[]>[] dp = new ArrayList[rowLwn + colLen];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new ArrayList<>();
        }
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> mat[b[0]][b[1]] - mat[a[0]][a[1]]);
        for (int i = 0; i < rowLwn; i++) {
            for (int j = 0; j < colLen; j++) {
                queue.offer(new int[]{i, j, mat[i][j]});
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            List<int[]> lr = dp[cur[0]];
            List<int[]> cl = dp[rowLwn + cur[1]];
            int[] row = null, col = null;
            for (int i = lr.size() - 1; i >= 0; i--) {
                int[] t = lr.get(i);
                if (t[0] > cur[2]) {
                    row = t;
                    break;
                }
            }

            for (int i = cl.size() - 1; i >= 0; i--) {
                int[] t = cl.get(i);
                if (t[0] > cur[2]) {
                    col = t;
                    break;
                }
            }
            cur[2] = Math.max((row == null ? 1 : row[1] + 1), (col == null ? 1 : col[1] + 1));
            if (lr.isEmpty()) {
                lr.add(new int[]{mat[cur[0]][cur[1]], cur[2]});
            } else {
                row = lr.get(lr.size() - 1);
                if (row[0] != mat[cur[0]][cur[1]]) {
                    lr.add(new int[]{mat[cur[0]][cur[1]], cur[2]});
                } else {
                    row[1] = Math.max(row[1], cur[2]);
                }
            }
            if (cl.isEmpty()) {
                cl.add(new int[]{mat[cur[0]][cur[1]], cur[2]});
            } else {
                col = cl.get(cl.size() - 1);
                if (col[0] != mat[cur[0]][cur[1]]) {
                    cl.add(new int[]{mat[cur[0]][cur[1]], cur[2]});
                } else {
                    col[1] = Math.max(col[1], cur[2]);
                }
            }
            ans = Math.max(ans, cur[2]);
        }
        return ans;
    }
}
