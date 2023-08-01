package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @description: On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves. The rows and columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).
 * <p>
 * A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.
 * <p>
 * <p>
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
 * <p>
 * The knight continues moving until it has made exactly k moves or has moved off the chessboard.
 * <p>
 * Return the probability that the knight remains on the board after it has stopped moving.
 * @author: LiShuai
 * @createDate: 2023/7/22 19:44
 * @version: 1.0
 */

public class LeetCode_688 {

    private static final int[][] TEMP = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

    private static Double[][][] cache;

    public static void main(String[] args) {
//        int n = 3, k = 2, row = 0, column = 0;
//        int n = 3, k = 2, row = 1, column = 1;
        int n = 8, k = 30, row = 6, column = 4;
//        double v = knightProbability(n, k, row, column);
//        System.out.println(v);
        System.out.println(knightProbability_02(n, k, row, column));
        System.out.println(knightProbability_dp(n, k, row, column));
    }

    public static double knightProbability(int n, int k, int row, int column) {
        if (k == 0) {
            return 1.0D;
        }
        long p = 1, q = 1;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{row, column});

        for (int i = 0; i < k; i++) {
            int size = queue.size();
            long m = 0;
            for (int j = 0; j < size; j++) {
                int[] cur = queue.removeFirst();
                for (int[] ints : TEMP) {
                    int r = cur[0] + ints[0];
                    int c = cur[1] + ints[1];
                    if (r >= 0 && r < n && c >= 0 && c < n) {
                        ++m;
                        queue.addLast(new int[]{r, c});
                    }
                }
            }
            if (m == 0) {
                return 0;
            }
            p *= m;
            q *= (size * 8L);
        }
        return (double) p / q;
    }


    public static double knightProbability_02(int n, int k, int row, int column) {
        if (k == 0) {
            return 1.0D;
        }
        cache = new Double[k + 1][n][n];
        return knightProbabilityProcess(n, k, row, column);
    }

    private static double knightProbabilityProcess(int n, int k, int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        if (cache[k][row][col] != null) {
            return cache[k][row][col];
        }
        double ans = 0;
        for (int[] ints : TEMP) {
            int r = row + ints[0];
            int c = col + ints[1];
            double ps = knightProbabilityProcess(n, k - 1, r, c);
            ans += ps;
        }
        ans = ans / 8;
        cache[k][row][col] = ans;
        return ans;
    }

    public static double knightProbability_dp(int n, int k, int row, int column) {
        if (k == 0) {
            return 1.0D;
        }
        double[][][] dp = new double[k + 1][n][n];
        for (double[] ds : dp[0]) {
            Arrays.fill(ds, 1D);
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    double p = 0;
                    for (int[] ints : TEMP) {
                        int r = j + ints[0];
                        int c = l + ints[1];
                        if (r >= 0 && r < n && c >= 0 && c < n) {
                            dp[i][j][l] += dp[i - 1][r][c];
                        }
                    }
                    dp[i][j][l] = p;
                }
            }
        }
        double q = Math.pow(8, k);
        return dp[k][row][column] / q;
    }
}
