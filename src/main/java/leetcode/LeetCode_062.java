package leetcode;

/**
 * @description: A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * @author: LISHUAI
 * @createDate: 2021/11/20 18:46
 * @version: 1.0
 */

public class LeetCode_062 {

    public static void main(String[] args) {

        int i = uniquePaths_02(3, 7);

        System.out.println(i);
    }

    public static int uniquePaths(int m, int n) {

        return process(m, n, 1, 1);
    }

    public static int process(int m, int n, int row, int col) {

        if (row == m && col == n) {

            return 1;
        }


        int rowNum = 0, colNum = 0;

        if (row < m) {

            rowNum = process(m, n, row + 1, col);
        }

        if (col < n) {

            colNum = process(m, n, row, col + 1);
        }


        return rowNum + colNum;
    }


    public static int uniquePaths_01(int m, int n) {

        int[][] ints = new int[m][n];

        for (int i = 0; i < m; i++) {

            ints[i][n - 1] = 1;
        }

        for (int i = 0; i < n - 1; i++) {

            ints[m - 1][i] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {

            for (int j = n - 2; j >= 0; j--) {

                ints[i][j] = ints[i + 1][j] + ints[i][j + 1];
            }
        }

        return ints[0][0];
    }

    public static int uniquePaths_02(int m, int n) {

        int[] ints = new int[n];

        for (int i = 0; i < n; i++) {

            ints[i] = 1;
        }

        for (int i = 0; i < m - 1; i++) {

            for (int j = n - 2; j >= 0; j--) {

                ints[j] = ints[j] + ints[j + 1];
            }
        }

        return ints[0];
    }
}
