package leetcode;

/**
 * @description: Given a 2D integer array matrix, return the transpose of matrix.
 * <p>
 * The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.
 * @author: LISHUAI
 * @createDate: 2022/6/2 21:59
 * @version: 1.0
 */

public class LeetCode_867 {

    public static int[][] transpose(int[][] matrix) {
        int rowLen = matrix.length, colLen = matrix[0].length;
        if (rowLen == colLen) {
            for (int i = 0; i < rowLen; i++) {
                for (int j = i + 1; j < rowLen; j++) {
                    matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                    matrix[j][i] = matrix[i][j] ^ matrix[j][i];
                    matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                }
            }
            return matrix;
        } else {
            int[][] ans = new int[colLen][rowLen];
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    ans[j][i] = matrix[i][j];
                }
            }
            return ans;
        }
    }
}
