package leetcode;

/**
 * @description: Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 * @author: LISHUAI
 * @createDate: 2022/6/2 22:09
 * @version: 1.0
 */

public class LeetCode_1277 {

    private static int[][][] preSum;

    public static void main(String[] args) {
//        int[][] matrix =
//                {
//                        {0, 1, 1, 1},
//                        {1, 1, 1, 1},
//                        {0, 1, 1, 1}
//                };
//        int[][] matrix =
//                {
//                        {1, 0, 1},
//                        {1, 1, 0},
//                        {1, 1, 0}
//                };
        int[][] matrix =
                {
                        {1, 0, 1, 0, 1},
                        {1, 0, 0, 1, 1},
                        {0, 1, 0, 1, 1},
                        {1, 0, 0, 1, 1}
                };
        int i = countSquares(matrix);
        System.out.println(i);
    }

    public static int countSquares(int[][] matrix) {
        int rowLen = matrix.length, colLen = matrix[0].length;
        int[][] cache = new int[rowLen][colLen];
        int ans = 0, len;

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (matrix[i][j] == 1) {
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        int skip = cache[i - 1][j - 1] - 1;
                        len = countSquaresProcess(matrix, i, j, i + skip, j + skip);
                    } else {
                        len = countSquaresProcess(matrix, i, j, i, j);
                    }
                    cache[i][j] = len;
                    ans += len;
                }
            }
        }
        return ans;
    }

    private static int countSquaresProcess(int[][] matrix, int x, int y, int cx, int cy) {

        int size = cx - x, rowLen = matrix.length, colLen = matrix[0].length;
        int curX = cx, curY = cy;
        while (curX < rowLen && curY < colLen) {
            if (isCol(matrix, x, curX, curY) && isRow(matrix, y, curY, curX)) {
                size++;
            } else {
                break;
            }
            curX++;
            curY++;
        }
        return size;
    }

    private static boolean isRow(int[][] matrix, int start, int cur, int row) {
        while (cur >= start) {
            if (matrix[row][cur] != 1) {
                return false;
            }
            cur--;
        }
        return true;
    }

    private static boolean isCol(int[][] matrix, int start, int cur, int col) {

        while (cur >= start) {
            if (matrix[cur][col] != 1) {
                return false;
            }
            cur--;
        }
        return true;
    }
}
