package leetcode;

/**
 * @description: Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 * <p>
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 * @author: LISHUAI
 * @createDate: 2022/12/13 10:12
 * @version: 1.0
 */

public class LeetCode_931 {

    public static void main(String[] args) {
        int[][] matrix = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        int i = minFallingPathSum(matrix);
        System.out.println(i);
    }

    public static int minFallingPathSum(int[][] matrix) {
        int rowLen = matrix.length, colLen = matrix[rowLen - 1].length;
        int[][] dp = new int[rowLen + 1][colLen];

        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                int pre = (j - 1 >= 0 ? dp[i + 1][j - 1] : Integer.MAX_VALUE);
                int next = (j + 1 < colLen ? dp[i + 1][j + 1] : Integer.MAX_VALUE);
                dp[i][j] = Math.min(pre, Math.min(dp[i + 1][j], next)) + matrix[i][j];
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i : dp[0]) {
            ans = Math.min(ans, i);
        }
        return ans;
    }
}
