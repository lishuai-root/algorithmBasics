package leetcode;

/**
 * @description: Given a 2D matrix matrix, handle multiple queries of the following type:
 * <p>
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 * <p>
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * @author: LISHUAI
 * @createDate: 2022/6/4 18:07
 * @version: 1.0
 */

public class LeetCode_304 {

    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        int i = numMatrix.sumRegion(2, 1, 4, 3);
        System.out.println(i);
    }

    static class NumMatrix {

        int[][] curMatrix;

        public NumMatrix(int[][] matrix) {
            int rowLen = matrix.length, colLen = matrix[0].length;
            curMatrix = new int[rowLen][colLen];
            for (int i = 0; i < rowLen; i++) {
                int sum = 0;
                for (int j = 0; j < colLen; j++) {
                    if (i - 1 >= 0) {
                        curMatrix[i][j] = curMatrix[i - 1][j];
                    }
                    sum += matrix[i][j];
                    curMatrix[i][j] += sum;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int all = curMatrix[row2][col2];
            int a = col1 - 1 >= 0 ? curMatrix[row2][col1 - 1] : 0;
            int b = col1 - 1 >= 0 && row1 - 1 >= 0 ? curMatrix[row1 - 1][col1 - 1] : 0;
            int c = row1 - 1 >= 0 ? curMatrix[row1 - 1][col2] : 0;
            return all - a - c + b;
        }
    }
}
