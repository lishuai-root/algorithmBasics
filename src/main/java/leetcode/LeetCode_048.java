package leetcode;

/**
 * @description: You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * @author: LISHUAI
 * @createDate: 2021/11/17 22:14
 * @version: 1.0
 */

public class LeetCode_048 {

    public static void main(String[] args) {

        int size = 4;

        int[][] ints = makeDoubleArray(size, size);

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                System.out.print(ints[i][j] + "   ");
            }

            System.out.println();
        }

        rotate(ints);

        System.out.println("---------------------");

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                System.out.print(ints[i][j] + "   ");
            }

            System.out.println();
        }
    }

    public static int[][] makeDoubleArray(int row, int col) {

        int[][] arr = new int[row][col];

        int num = 1;

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {

                arr[i][j] = num++;
            }
        }

        return arr;
    }

    /**
     * 先上下翻转，再对角线翻转
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {

        int len = matrix[0].length, maxIndex = len - 1;

        for (int i = 0; i < len; i++) {

            for (int j = 0; j < len / 2; j++) {

                matrix[j][i] = matrix[j][i] ^ matrix[maxIndex - j][i];
                matrix[maxIndex - j][i] = matrix[j][i] ^ matrix[maxIndex - j][i];
                matrix[j][i] = matrix[j][i] ^ matrix[maxIndex - j][i];
            }

        }


        for (int i = 0; i < len; i++) {

            for (int j = i; j < len; j++) {

                if (i != j) {
                    matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                    matrix[j][i] = matrix[i][j] ^ matrix[j][i];
                    matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                }

            }
        }

    }
}
