package leetcode;

/**
 * @description: Given an m x n binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 * @author: LISHUAI
 * @createDate: 2021/11/27 21:14
 * @version: 1.0
 */

public class LeetCode_221 {

    public static void main(String[] args) {

        char[][] chars = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        int i = maximalSquare_04(chars);

        System.out.println(i);
    }

    public static int maximalSquare(char[][] matrix) {

        int rowLen = matrix.length, colLen = matrix[0].length, MaxArea = 0;

        boolean[][] bl = new boolean[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {

            for (int j = 0; j < colLen; j++) {

                MaxArea = Math.max(MaxArea, process(matrix, 1, 1, i, j, bl));
            }
        }

        return MaxArea;
    }

    public static int process(char[][] matrix, int w, int h, int row, int col, boolean[][] bl) {

        if (matrix[row][col] != '1') {

            return 0;
        }

        int area = 0;

        if (w == h) {

            area = w * h;
        }

        bl[row][col] = true;

        if (row + 1 < matrix.length && !bl[row + 1][col]) {

            area = Math.max(area, process(matrix, w, h + 1, row + 1, col, bl));
        }

        if (row - 1 >= 0 && !bl[row - 1][col]) {

            area = Math.max(area, process(matrix, w, h + 1, row - 1, col, bl));
        }

        if (col + 1 < matrix.length && !bl[row][col + 1]) {

            area = Math.max(area, process(matrix, w + 1, h, row, col + 1, bl));
        }

        if (col - 1 >= 0 && !bl[row][col - 1]) {

            area = Math.max(area, process(matrix, w, h + 1, row, col - 1, bl));
        }


        return area;
    }

    public static int maximalSquare_02(char[][] matrix) {

        int rowLen = matrix.length, colLen = matrix[0].length, MaxArea = 0;

        boolean[][] bl = new boolean[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {

            for (int j = 0; j < colLen; j++) {

                if (matrix[i][j] == '1') {

                    MaxArea = Math.max(MaxArea, process_03(matrix, 1, i, j, bl));
                }
            }
        }

        return MaxArea;
    }

    public static int process_02(char[][] matrix, int size, int row, int col, boolean[][] bl) {

        int area = size * size;

        if (size + row + 1 > matrix.length || size + col + 1 > matrix[0].length) {

            return area;
        }

        for (int i = row; i <= row + size; i++) {

            if (matrix[i][col + size] != '1') {

                return area;
            }
        }

        for (int i = col; i <= col + size - 1; i++) {

            if (matrix[row + size][i] != '1') {

                return area;
            }
        }

        area = (size + 1) * (size + 1);

        return Math.max(area, process_02(matrix, size + 1, row, col, bl));
    }

    public static int process_03(char[][] matrix, int size, int row, int col, boolean[][] bl) {

        int area = size * size;

        while (size + row + 1 <= matrix.length && size + col + 1 <= matrix[0].length) {

            for (int i = row; i <= row + size; i++) {

                if (matrix[i][col + size] != '1') {

                    return area;
                }
            }

            for (int i = col; i <= col + size - 1; i++) {

                if (matrix[row + size][i] != '1') {

                    return area;
                }
            }

            size++;

            area = size * size;
        }

        return area;
    }

    public static int maximalSquare_03(char[][] matrix) {

        int rowLen = matrix.length, colLen = matrix[0].length;

        int size, area = 0;

        for (int i = 0; i < rowLen; i++) {

            for (int j = 0; j < colLen; j++) {

                if (matrix[i][j] == '1') {

                    size = 1;

                    area = Math.max(area, 1);

                    a:
                    while (size + i + 1 <= matrix.length && size + j + 1 <= matrix[0].length) {

                        for (int m = i; m <= i + size; m++) {

                            if (matrix[m][j + size] != '1') {

                                break a;
                            }
                        }

                        for (int n = j; n <= j + size - 1; n++) {

                            if (matrix[i + size][n] != '1') {

                                break a;
                            }
                        }

                        size++;

                        area = Math.max(area, size);
                    }
                }
            }
        }

        return area * area;
    }

    public static int maximalSquare_04(char[][] matrix) {

        int rowLen = matrix.length, colLen = matrix[0].length;

        int size = 0, area = 0;

        for (int i = 0; i < rowLen - size; i++) {

            for (int j = 0; j < colLen - size; j++) {

                if (matrix[i][j] == '1') {

                    size = 1;

                    area = Math.max(area, 1);

                    a:
                    while (size + i + 1 <= matrix.length && size + j + 1 <= matrix[0].length) {

                        for (int m = i; m <= i + size; m++) {

                            if (matrix[m][j + size] != '1') {

                                break a;
                            }
                        }

                        for (int n = j; n <= j + size - 1; n++) {

                            if (matrix[i + size][n] != '1') {

                                break a;
                            }
                        }

                        size++;

                        area = Math.max(area, size);
                    }
                }
            }
        }

        return area * area;
    }

    /**
     * teacher method
     *
     * @param m
     * @return
     */
    public static int maximalSquare_05(char[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int[][] dp = new int[N + 1][M + 1];
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (m[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int j = 1; j < M; j++) {
            if (m[0][j] == '1') {
                dp[0][j] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (m[i][j] == '1') {
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j],
                                    dp[i][j - 1]),
                            dp[i - 1][j - 1])
                            + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }
}
