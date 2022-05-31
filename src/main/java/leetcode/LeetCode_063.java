package leetcode;

/**
 * @description: You are given an m x n integer array grid.
 * There is a robot initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m-1][n-1]).
 * The robot can only move either down or right at any point in time.
 * <p>
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
 * <p>
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * <p>
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 * @author: LISHUAI
 * @createDate: 2022/5/20 21:10
 * @version: 1.0
 */

public class LeetCode_063 {

    private static final int TMP = 2000000000;
    private static Integer[][] cache;

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
//        int[][] obstacleGrid = {{0, 1}, {0, 0}};
        int i = uniquePathsWithObstacles_dp(obstacleGrid);
        System.out.println(i);
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        cache = new Integer[obstacleGrid.length][obstacleGrid[0].length];
        return uniquePathsWithObstaclesProcess(obstacleGrid, 0, 0);
    }

    private static int uniquePathsWithObstaclesProcess(int[][] obstacleGrid, int row, int col) {
        if (obstacleGrid[row][col] == 1) {
            return 0;
        }
        if (row == obstacleGrid.length - 1 && col == obstacleGrid[0].length - 1) {
            return 1;
        }
        if (cache[row][col] != null) {
            return cache[row][col];
        }
        int ans = 0;
        if (row + 1 < obstacleGrid.length) {
            ans += (uniquePathsWithObstaclesProcess(obstacleGrid, row + 1, col) % TMP);
            ans %= TMP;
        }

        if (col + 1 < obstacleGrid[0].length) {
            ans += (uniquePathsWithObstaclesProcess(obstacleGrid, row, col + 1) % TMP);
            ans %= TMP;
        }
        cache[row][col] = ans;
        return ans;
    }

    public static int uniquePathsWithObstacles_dp(int[][] obstacleGrid) {
        int rowLen = obstacleGrid.length, colLen = obstacleGrid[0].length;
        int[] dp = new int[colLen + 1];
        dp[colLen - 1] = obstacleGrid[rowLen - 1][colLen - 1] ^ 1;

        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 0) {
                    dp[j] = (dp[j] + dp[j + 1]) % TMP;
                } else {
                    dp[j] = 0;
                }
            }
        }
        return dp[0];
    }
}
