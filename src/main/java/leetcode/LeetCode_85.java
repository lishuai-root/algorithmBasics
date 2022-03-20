package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/4 18:35
 * @version: 1.0
 * <p>
 * Given a rows x cols binary matrix filled with 0's and 1's,
 * find the largest rectangle containing only 1's and return its area.
 */

public class LeetCode_85 {

    public static void main(String[] args) {

        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

//        char[][] matrix = {{'0'}, {'1'}};

        int i = maximalRectangle(matrix);

        System.out.println(i);
    }

    public static int maximalRectangle(char[][] matrix) {

        if (matrix.length < 1 || matrix[0].length < 1) {

            return 0;
        }

        int result = 0, index = -1, nowChar, max = 0, leftIndex;

        int rowLen = matrix.length, colsLen = matrix[0].length;

        int[][] matrixBak = new int[matrix.length][matrix[0].length];

        int[] stack = new int[Math.max(rowLen, colsLen)];

        for (int i = 0; i < colsLen; i++) {

            for (int j = 0; j < rowLen; j++) {

                while (index != -1 && matrix[j][i] < matrix[stack[index]][i]) {

                    nowChar = stack[index--];

                    matrixBak[nowChar][i] = (j - nowChar) * (matrix[nowChar][i] - 48);
                }

                stack[++index] = j;

            }

            while (index != -1 && matrix[stack[index]][i] == '1') {

                nowChar = stack[index--];

                matrixBak[nowChar][i] = (rowLen - nowChar) * (matrix[nowChar][i] - 48);
            }

            index = -1;
        }

        for (int i = 0; i < rowLen; i++) {

            for (int j = 0; j < colsLen; j++) {

                while (index != -1 && matrixBak[i][j] <= matrixBak[i][stack[index]]) {

                    nowChar = stack[index--];

                    leftIndex = index == -1 ? -1 : stack[index];

                    max = Math.max(max, (j - leftIndex - 1) * matrixBak[i][nowChar]);
                }

                stack[++index] = j;
            }

            while (index != -1) {

                nowChar = stack[index--];

                leftIndex = index == -1 ? -1 : stack[index];

                max = Math.max(max, (colsLen - leftIndex - 1) * matrixBak[i][nowChar]);

            }

            index = -1;
        }


        return max;
    }

    private static int process() {

        int result = 0;


        return result;
    }

}
