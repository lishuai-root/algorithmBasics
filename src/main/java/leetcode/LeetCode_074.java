package leetcode;

import java.util.Random;

/**
 * @description: Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 * <p>
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * @author: LISHUAI
 * @createDate: 2022/2/24 22:43
 * @version: 1.0
 */

public class LeetCode_074 {

    public static void main(String[] args) {
//        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
//        int target = 3;
//        boolean b = searchMatrix_03(matrix, target);
//        System.out.println(b);

        int row = 10000, col = 100000;
        int[][] ints = makeArray(row, col);
        int target = new Random().nextInt(row * col);

        long start = System.currentTimeMillis();
        boolean b = searchMatrix(ints, target);
        long end = System.currentTimeMillis();

        System.out.println("02 : " + (end - start));

        start = System.currentTimeMillis();
        boolean b1 = searchMatrix_03(ints, target);

        end = System.currentTimeMillis();

        System.out.println("03 : " + (end - start));

    }

    private static void test() {
        int row = 10, col = 10;
        while (true) {
            int[][] ints = makeArray(row, col);
            int target = new Random().nextInt(row * col);

            boolean b = searchMatrix(ints, target);
            boolean b1 = searchMatrix_03(ints, target);

            if (b != b1) {

                for (int[] ints1 : ints) {
                    for (int i : ints1) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                }
                System.out.println(b + " " + b1);
                System.out.println(target);
                break;
            }
        }

    }

    private static int[][] makeArray(int row, int col) {
        int[][] ans = new int[row][col];
        int num = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[i][j] = num++;
            }
        }
        return ans;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            for (int i : ints) {
                if (i == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean searchMatrix_02(int[][] matrix, int target) {
        int len = matrix[0].length;

        for (int[] ints : matrix) {
            if (ints[len - 1] >= target) {
                for (int i : ints) {
                    if (i == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static boolean searchMatrix_03(int[][] matrix, int target) {

        int left = 0, right = matrix.length - 1, cur, len = matrix[0].length;
        while (right >= left) {
            if (left == right) {
                cur = left;
                left = 0;
                right = len - 1;
                while (left <= right) {
                    len = (right + left) >>> 1;
                    if (matrix[cur][len] == target) {
                        return true;
                    }
                    if (matrix[cur][len] < target) {
                        left = len + 1;
                    } else {
                        right = len - 1;
                    }
                }
                return false;
            }
            cur = (right + left) >>> 1;
            if (matrix[cur][len - 1] < target) {
                left = cur + 1;
            } else if (matrix[cur][len - 1] > target) {
                right = cur;
            } else {
                return true;
            }
        }
        return false;
    }
}
