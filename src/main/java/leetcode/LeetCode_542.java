package leetcode;

/**
 * @description: Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 * @author: LiShuai
 * @createDate: 2023/8/17 21:51
 * @version: 1.0
 */

public class LeetCode_542 {

    public static int[][] updateMatrix(int[][] mat) {
        int rowLen = mat.length, colLen = mat[0].length;
        int[][] ans = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (mat[i][j] == 1) {
                    int s = Math.min((i - 1 >= 0 ? ans[i - 1][j] : Integer.MAX_VALUE),
                            (j - 1 >= 0 ? ans[i][j - 1] : Integer.MAX_VALUE));
                    ans[i][j] = (s == Integer.MAX_VALUE ? s : s + 1);
                }
            }
        }

        for (int i = rowLen - 1; i >= 0; --i) {
            for (int j = colLen - 1; j >= 0; --j) {
                if (mat[i][j] == 1) {
                    int s = Math.min((i + 1 < rowLen ? ans[i + 1][j] : Integer.MAX_VALUE),
                            (j + 1 < colLen ? ans[i][j + 1] : Integer.MAX_VALUE));
                    ans[i][j] = Math.min(ans[i][j], (s == Integer.MAX_VALUE ? s : s + 1));
                }
            }
        }
        return ans;
    }
}
