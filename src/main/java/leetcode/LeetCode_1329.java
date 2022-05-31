package leetcode;

import java.util.Arrays;

/**
 * @description: A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end.
 * For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].
 * <p>
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
 * @author: LISHUAI
 * @createDate: 2022/5/16 22:39
 * @version: 1.0
 */

public class LeetCode_1329 {

    public static int[][] diagonalSort(int[][] mat) {
        int rowLen = mat.length, colLen = mat[0].length;
        int[] sort = new int[rowLen + colLen];
        int index = 0;

        for (int i = 0; i < colLen; i++) {
            int r = 0, c = i;
            while (r < rowLen && c < colLen) {
                sort[index++] = mat[r++][c++];
            }

            Arrays.sort(sort, 0, index);

            while (--r >= 0 && --c >= 0) {
                mat[r][c] = sort[--index];
            }
        }

        for (int i = 1; i < rowLen; i++) {
            int r = i, c = 0;
            while (r < rowLen && c < colLen) {
                sort[index++] = mat[r++][c++];
            }
            Arrays.sort(sort, 0, index);
            while (--r >= 0 && --c >= 0) {
                mat[r][c] = sort[--index];
            }
        }

        return mat;
    }
}
