package leetcode;

/**
 * @description: Write an efficient algorithm that searches for a target value in an m x n integer matrix.
 * The matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * @author: LISHUAI
 * @createDate: 2021/11/28 10:48
 * @version: 1.0
 */

public class LeetCode_240 {

    public static void main(String[] args) {

        int[][] arr = new int[][]{{1, 4}, {2, 5}};

        boolean b = searchMatrix_03(arr, 6);

        System.out.println(b);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        int col = matrix[0].length - 1;

        for (int i = 0; i < matrix.length; i++) {

            if (matrix[i][0] > target) {

                return false;
            }

            if (matrix[i][col] == target || matrix[i][0] == target) {

                return true;

            } else if (matrix[i][col] > target && matrix[i][0] < target) {

                for (int j = 0; j < col; j++) {

                    if (matrix[i][j] == target) {

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean searchMatrix_02(int[][] matrix, int target) {

        if (matrix.length == 1 && matrix[0].length == 1) {

            return matrix[0][0] == target;
        }

        int row = 0, col = 0;

        while (row < matrix.length && col < matrix[0].length) {

            if (matrix[row][col] == target) {

                return true;
            } else if (matrix[row][col] < target) {


                row++;

                col++;

            } else {

                for (int i = 0; i < row; i++) {

                    if (matrix[i][col] == target) {

                        return true;
                    }
                }

                for (int i = 0; i < col; i++) {

                    if (matrix[row][i] == target) {

                        return true;
                    }
                }

                row++;

                col++;

            }
        }

        if (row == matrix.length && col != matrix[0].length) {

            row--;

            while (col < matrix[0].length) {

                if (matrix[row][col] == target) {

                    return true;
                } else if (matrix[row][col] < target) {

                    col++;
                } else {
                    for (int i = 0; i <= row; i++) {

                        if (matrix[i][col] == target) {

                            return true;
                        }
                    }
                    col++;
                }
            }
        }


        if (col == matrix[0].length && row != matrix.length) {

            col--;

            while (row < matrix.length) {

                if (matrix[row][col] == target) {
                    return true;
                } else if (matrix[row][col] < target) {
                    row++;
                } else {
                    for (int i = 0; i <= col; i++) {

                        if (matrix[row][i] == target) {

                            return true;
                        }
                    }

                    row++;
                }
            }
        }


        return false;
    }

    public static boolean searchMatrix_03(int[][] matrix, int target) {

        int row = matrix.length, col = matrix[0].length;

        for (int i = 0; i < matrix.length; i++) {

            if (matrix[i][0] == target) {

                return true;
            } else if (matrix[i][0] > target) {

                row = i;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {

            if (matrix[0][i] == target) {

                return true;
            } else if (matrix[0][i] > target) {

                col = i;
            }
        }

        if (row == 0 && col == 0) {

            return false;
        }

        for (int i = row; i >= 0; i--) {

            if (matrix[i][col - 1] > target && matrix[i][0] < target) {

                for (int j = 0; j < col; j++) {

                    if (matrix[i][j] == target) {

                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * teacher method
     *
     * @param matrix
     * @param K
     * @return
     */
    public static boolean isContains(int[][] matrix, int K) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {
            if (matrix[row][col] == K) {
                return true;
            } else if (matrix[row][col] > K) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
