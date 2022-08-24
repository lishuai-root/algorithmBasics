package leetcode;

import java.util.Arrays;

/**
 * @description: Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <p>
 * You must find a solution with a memory complexity better than O(n2).
 * <p>
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * @author: LISHUAI
 * @createDate: 2022/8/2 20:43
 * @version: 1.0
 */

public class LeetCode_378 {


    public static void main(String[] args) {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;
        int i = kthSmallest(matrix, k);
        System.out.println(i);
    }

    private static void show(int r, int c) {
        int[][] ints = new int[r][c];
        int index = 1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ints[i][j] = index++;
            }
        }
        for (int[] arr : ints) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static int kthSmallest(int[][] matrix, int k) {
        int rowLen = matrix.length;
        int[] list = new int[rowLen * rowLen];
        for (int i = 0; i < rowLen; i++) {
            System.arraycopy(matrix[i], 0, list, rowLen * i, rowLen);
        }
        Arrays.sort(list);
        return list[k - 1];
    }
}
