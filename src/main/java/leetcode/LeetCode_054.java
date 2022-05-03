package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given an m x n matrix, return all elements of the matrix in spiral order.
 * @author: LISHUAI
 * @createDate: 2022/4/18 17:30
 * @version: 1.0
 */

public class LeetCode_054 {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        boolean[][] bl = new boolean[matrix.length][matrix[0].length];
        generateMatrixProcess(matrix, 0, 0, 0, 1, result, bl);
        return result;
    }

    private static void generateMatrixProcess(int[][] matrix, int row, int col, int r, int c, List<Integer> result, boolean[][] bl) {
        if (row >= matrix.length || row < 0 || col >= matrix[0].length || col < 0 || bl[row][col]) {
            return;
        }
        result.add(matrix[row][col]);
        bl[row][col] = true;
        if (r != 0) {
            if (r > 0 && (row + r >= matrix.length || bl[row + r][col])) {
                r = 0;
                c = -1;
            } else if (r < 0 && (row + r < 0 || bl[row + r][col])) {
                r = 0;
                c = 1;
            }
        } else if (c != 0) {
            if (c > 0 && (col + c >= matrix[0].length || bl[row][col + c])) {
                r = 1;
                c = 0;
            } else if (c < 0 && (col + c < 0 || bl[row][col + c])) {
                r = -1;
                c = 0;
            }
        }

        generateMatrixProcess(matrix, row + r, col + c, r, c, result, bl);
    }

    public static List<Integer> spiralOrder_02(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        boolean[][] bl = new boolean[matrix.length][matrix[0].length];

        int row = 0, col = 0, r = 0, c = 1;

        while (row < matrix.length && row >= 0 && col < matrix[0].length && col >= 0 && !bl[row][col]) {
            result.add(matrix[row][col]);
            bl[row][col] = true;
            if (r != 0) {
                if (r > 0 && (row + r >= matrix.length || bl[row + r][col])) {
                    r = 0;
                    c = -1;
                } else if (r < 0 && (row + r < 0 || bl[row + r][col])) {
                    r = 0;
                    c = 1;
                }
            } else if (c != 0) {
                if (c > 0 && (col + c >= matrix[0].length || bl[row][col + c])) {
                    r = 1;
                    c = 0;
                } else if (c < 0 && (col + c < 0 || bl[row][col + c])) {
                    r = -1;
                    c = 0;
                }
            }
            row += r;
            col += c;
        }
        return result;
    }
}
