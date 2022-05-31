package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * <p>
 * From each cell, you can either move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 * @author: LISHUAI
 * @createDate: 2022/5/19 21:10
 * @version: 1.0
 */

public class LeetCode_329 {

    private static final int[][] TMP = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static Integer[][] cache;

    public static int longestIncreasingPath(int[][] matrix) {
        int ans = 0, rowLen = matrix.length, colLen = matrix[0].length;

        cache = new Integer[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                ans = Math.max(ans, longestIncreasingPathProcess(matrix, i, j));
            }
        }
        return ans;
    }

    private static int longestIncreasingPathProcess(int[][] matrix, int row, int col) {

        if (cache[row][col] != null) {
            return cache[row][col];
        }
        int ans = 1;

        for (int[] ints : TMP) {
            int r = row + ints[0];
            int c = col + ints[1];
            if (r >= 0 && r < matrix.length && c >= 0 && c < matrix[0].length && matrix[row][col] < matrix[r][c]) {
                ans = Math.max(ans, longestIncreasingPathProcess(matrix, r, c) + 1);
            }
        }
        cache[row][col] = ans;
        return ans;
    }


    public static int longestIncreasingPath_02(int[][] matrix) {
        int ans = 0, rowLen = matrix.length, colLen = matrix[0].length;
        int r, c;
        int[][] maxNum = new int[rowLen][colLen];
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] bls;
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                queue.offer(new int[]{i, j, 1});
                maxNum[i][j] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            bls = new boolean[rowLen][colLen];
            for (int i = 0; i < size; i++) {
                int[] curs = queue.poll();
                for (int[] ints : TMP) {
                    r = curs[0] + ints[0];
                    c = curs[1] + ints[1];
                    if (r >= 0 && r < rowLen && c >= 0 && c < colLen && matrix[curs[0]][curs[1]] < matrix[r][c]) {
                        if (maxNum[curs[0]][curs[1]] + 1 > maxNum[r][c]) {
                            maxNum[r][c] = maxNum[curs[0]][curs[1]] + 1;
                            ans = Math.max(ans, maxNum[curs[0]][curs[1]] + 1);
                            if (!bls[r][c]) {
                                queue.offer(new int[]{r, c, curs[2] + 1});
                                bls[r][c] = true;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
}
