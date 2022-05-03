package leetcode;

/**
 * @description: Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 * @author: LISHUAI
 * @createDate: 2022/4/13 23:21
 * @version: 1.0
 */

public class LeetCode_059 {

    public static void main(String[] args) {
        int[][] matrix = generateMatrix(20);
        for (int[] ints : matrix) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        generateMatrixProcess(matrix, 0, 0, 0, 1, 1);
        return matrix;
    }

    private static void generateMatrixProcess(int[][] matrix, int row, int col, int r, int c, int value) {
        if (row >= matrix.length || row < 0 || col >= matrix[0].length || col < 0 || matrix[row][col] != 0) {
            return;
        }
        matrix[row][col] = value;
        if (r != 0) {
            if (r > 0 && (row + r >= matrix.length || matrix[row + r][col] != 0)) {
                r = 0;
                c = -1;
            } else if (r < 0 && (row + r < 0 || matrix[row + r][col] != 0)) {
                r = 0;
                c = 1;
            }
        } else if (c != 0) {
            if (c > 0 && (col + c >= matrix[0].length || matrix[row][col + c] != 0)) {
                r = 1;
                c = 0;
            } else if (c < 0 && (col + c < 0 || matrix[row][col + c] != 0)) {
                r = -1;
                c = 0;
            }
        }

        generateMatrixProcess(matrix, row + r, col + c, r, c, value + 1);
    }
}
