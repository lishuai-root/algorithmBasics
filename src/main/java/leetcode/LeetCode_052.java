package leetcode;

/**
 * @description: The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 * @author: LISHUAI
 * @createDate: 2022/6/4 14:57
 * @version: 1.0
 */

public class LeetCode_052 {

    private static boolean[] AA, BB;

    public static void main(String[] args) {
        int n = 4;
        int i = totalNQueens(n);
        System.out.println(i);

    }

    public static int totalNQueens(int n) {
        boolean[] rowExists = new boolean[n];
        AA = new boolean[n << 1];
        BB = new boolean[n << 1];
        return totalNQueensProcess(n, rowExists, 0, n);
    }

    private static int totalNQueensProcess(int len, boolean[] colExists, int row, int n) {
        if (n == 0) {
            return 1;
        }
        if (row >= len) {
            return 0;
        }
        int ans = 0;

        for (int i = 0; i < len; i++) {
            int a = checkAA(row, i);
            int b = checkBB(row, i);
            if (!colExists[i] && !AA[a] && !BB[b]) {
                colExists[i] = true;
                AA[a] = true;
                BB[b] = true;
                ans += totalNQueensProcess(len, colExists, row + 1, n - 1);
                colExists[i] = false;
                AA[a] = false;
                BB[b] = false;
            }
        }
        return ans;
    }

    private static int checkAA(int row, int col) {
        int min = Math.min(row, col);
        row -= min;
        col -= min;
        if (col == 0) {
            return row;
        }
        return AA.length / 2 + col - 1;
    }

    private static int checkBB(int row, int col) {
        int len = BB.length >>> 1;
        int min = Math.min(len - col - 1, row);
        row -= min;
        col += min;
        if (col == len - 1) {
            return row;
        }
        return BB.length / 2 + col - 1;
    }
}
