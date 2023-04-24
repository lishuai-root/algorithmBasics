package leetcode;

/**
 * @description: Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * <p>
 * You must do it in place.
 * @author: LISHUAI
 * @createDate: 2023/4/12 22:22
 * @version: 1.0
 */

public class LeetCode_073 {

    public static void setZeroes(int[][] matrix) {
        int rLen = matrix.length, cLen = matrix[0].length;
        boolean[] row = new boolean[rLen];
        boolean[] col = new boolean[cLen];
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
