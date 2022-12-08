package leetcode;

import java.util.Random;

/**
 * @description: You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the top and bottom sides.
 * <p>
 * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right or to the left.
 * <p>
 * A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and is represented in the grid as 1.
 * A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and is represented in the grid as -1.
 * We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out of the bottom. A ball gets stuck if it hits a "V" shaped pattern between two boards or if a board redirects the ball into either wall of the box.
 * <p>
 * Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom after dropping the ball from the ith column at the top, or -1 if the ball gets stuck in the box.
 * @author: LISHUAI
 * @createDate: 2022/12/1 0:32
 * @version: 1.0
 */

public class LeetCode_1706 {

    public static void main(String[] args) {
//        int[][] grid = {{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, -1}};
//        int[][] grid = {{-1}};
//        int[][] grid = {{1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}, {1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}};
        int[][] grid = makeArray(10000, 100000);
        System.out.println("----------------------------------start------------------------------");
        long start = System.currentTimeMillis();
        int[] ball = findBall(grid);
        long end = System.currentTimeMillis();
        System.out.println("findBall - " + (end - start));

//        for (int i : ball) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
        start = System.currentTimeMillis();
        ball = findBall_dp(grid);
        end = System.currentTimeMillis();
        System.out.println("findBall_dp - " + (end - start));
//        for (int i : ball) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
    }

    private static int[][] makeArray(int row, int col) {
        int[][] arr = new int[row][col];
        Random rand = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int nextInt = rand.nextInt(11);
                if (nextInt < 5) {
                    arr[i][j] = -1;
                } else {
                    arr[i][j] = 1;
                }
            }
        }
        return arr;
    }

    public static int[] findBall(int[][] grid) {
        int len = grid[0].length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            int j = findBallProcess(grid, 0, i);
            dp[i] = j;
        }
        return dp;
    }

    private static int findBallProcess(int[][] grid, int row, int col) {
        if (row >= grid.length) {
            return col;
        }
        int cur = grid[row][col];
        int c = col + cur;
        if (c < 0 || c >= grid[row].length || grid[row][col] + grid[row][c] == 0) {
            return -1;
        }
        return findBallProcess(grid, row + 1, c);
    }


    public static int[] findBall_dp(int[][] grid) {
        int rowLen = grid.length, colLen = grid[rowLen - 1].length;
        int[][] dp = new int[rowLen + 1][colLen];
        for (int i = 0; i < colLen; i++) {
            dp[rowLen][i] = i;
        }

        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = 0; j < colLen; j++) {
                int cur = grid[i][j];
                int c = j + cur;
                if (c < 0 || c >= colLen || grid[i][j] + grid[i][c] == 0) {
                    dp[i][j] = -1;
                } else {
                    dp[i][j] = dp[i + 1][c];
                }
            }
        }
        return dp[0];
    }
}
