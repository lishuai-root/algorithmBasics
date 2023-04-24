package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2022/12/13 10:25
 * @version: 1.0
 */

public class LeetCode_1289 {

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int i = minFallingPathSum_02(arr);
        System.out.println(i);
    }

    public static int minFallingPathSum(int[][] grid) {
        int rowLen = grid.length, colLen = grid[rowLen - 1].length;
        int[][] dp = new int[rowLen + 1][colLen];

        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < colLen; k++) {
                    if (k != j) {
                        min = Math.min(min, dp[i + 1][k]);
                    }
                }
                dp[i][j] = (min == Integer.MAX_VALUE ? 0 : min) + grid[i][j];
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i : dp[0]) {
            ans = Math.min(ans, i);
        }
        return ans;
    }

    public static int minFallingPathSum_02(int[][] grid) {
        int rowLen = grid.length, colLen = grid[rowLen - 1].length;
        if (colLen == 1) {
            return grid[0][0];
        }
        int[][] dp = new int[rowLen + 1][colLen];
        int[] minIndex = {0, 1};
        int index;

        for (int i = rowLen - 1; i >= 0; i--) {
            int mn = minIndex[0], mm = minIndex[1];
            index = -1;
            for (int j = colLen - 1; j >= 0; j--) {
                int min = (mn == j ? dp[i + 1][mm] : dp[i + 1][mn]);
                dp[i][j] = min + grid[i][j];
                if (index == -1) {
                    minIndex[++index] = j;
                } else if (dp[i][j] < dp[i][minIndex[0]]) {
                    minIndex[1] = minIndex[0];
                    minIndex[0] = j;
                    index = 1;
                } else {
                    if (index == 0 || dp[i][j] < dp[i][minIndex[1]]) {
                        minIndex[1] = j;
                        index = 1;
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i : dp[0]) {
            ans = Math.min(ans, i);
        }
        return ans;
    }
}
