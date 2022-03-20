package leetcode;

import java.util.Arrays;

/**
 * @description: There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn].
 * You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary).
 * You can apply at most maxMove moves to the ball.
 * <p>
 * Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary.
 * Since the answer can be very large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2022/2/22 20:39
 * @version: 1.0
 */

public class LeetCode_576 {

    private static final int MVN = 1000000007;
    private static final int[][] MOVE = {{-1, 0,}, {0, -1}, {1, 0}, {0, 1}};
    private static int[][][] ROADS;

    public static void main(String[] args) {
//        int m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0;
//        int m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1;
//        int m = 8, n = 7, maxMove = 16, startRow = 1, startColumn = 5;
        int m = 8, n = 50, maxMove = 23, startRow = 5, startColumn = 26;    //914783380
        int paths = findPaths(m, n, maxMove, startRow, startColumn);
        System.out.println(paths);
    }

    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        ROADS = new int[m][n][maxMove + 1];
        for (int[][] road : ROADS) {
            for (int[] ints : road) {
                Arrays.fill(ints, -1);
            }
        }
        return findPathsProcess(m, n, maxMove, startRow, startColumn) % MVN;
    }

    private static int findPathsProcess(int m, int n, int maxMove, int curRow, int curColumn) {
        if (curRow >= m || curRow < 0 || curColumn >= n || curColumn < 0) {
            return 1;
        }
        if (maxMove == 0) {
            return 0;
        }
        if (ROADS[curRow][curColumn][maxMove] != -1) {
            return ROADS[curRow][curColumn][maxMove];
        }
        if (curRow > maxMove && curColumn > maxMove && m - curRow > maxMove && n - curColumn > maxMove) {
            ROADS[curRow][curColumn][maxMove] = 0;
            return 0;
        }
        int paths = 0;

        for (int[] ints : MOVE) {
            paths += findPathsProcess(m, n, maxMove - 1, curRow + ints[0], curColumn + ints[1]);
            paths %= MVN;
        }
        paths %= MVN;
        ROADS[curRow][curColumn][maxMove] = paths;
        return paths;
    }
}
