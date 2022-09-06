package leetcode;

/**
 * @description: Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.
 * <p>
 * It is guaranteed that there will be a rectangle with a sum no larger than k.
 * @author: LISHUAI
 * @createDate: 2022/8/27 17:57
 * @version: 1.0
 */

public class LeetCode_363 {

    public static void main(String[] args) {
//        int[][] matrix = {{1, 0, 1}, {0, -2, 3}};
//        int k = 2;
//        int[][] matrix = {{2, 2, -1}};
//        int k = 3;
        int[][] matrix = {
                {05, -4, -3, 04},
                {-3, -4, 04, 05},
                {05, 01, 05, -4}
        };
        int k = 8;
        int i = maxSumSubmatrix(matrix, k);
        System.out.println(i);
    }

    public static int maxSumSubmatrix(int[][] matrix, int k) {

        int rLen = matrix.length, cLen = matrix[0].length;
        int[][] dp = new int[rLen + 1][cLen];
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < cLen; i++) {
            for (int j = 0; j < rLen; j++) {
                dp[j + 1][i] = dp[j][i] + matrix[j][i];
            }
        }

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                for (int l = i; l < rLen; l++) {
                    int c = 0;
                    for (int m = j; m < cLen; m++) {
                        c += (dp[l + 1][m] - dp[i][m]);
                        if (c == k) {
                            return k;
                        }
                        if (c < k) {
                            ans = Math.max(ans, c);
                        }
                    }
                }
            }
        }
        return ans;
    }
}
