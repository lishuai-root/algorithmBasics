package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 * @author: LISHUAI
 * @createDate: 2022/6/4 14:04
 * @version: 1.0
 */

public class LeetCode_051 {
    private static boolean[] AA, BB;

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> list = solveNQueens(n);
        for (List<String> l : list) {
            for (String s : l) {
                System.out.println(s);
            }
            System.out.println("--------------");
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        char[][] chars = new char[n][n];
        boolean[] rowExists = new boolean[n];
        AA = new boolean[n << 1];
        BB = new boolean[n << 1];
        List<List<String>> list = new ArrayList<>();
        for (char[] c : chars) {
            Arrays.fill(c, '.');
        }
        solveNQueensProcess(list, chars, rowExists, 0, n);
        return list;
    }

    private static void solveNQueensProcess(List<List<String>> list, char[][] chars,
                                            boolean[] colExists, int row, int n) {
        if (n == 0) {
            List<String> l = new ArrayList<>();
            for (char[] rowChar : chars) {
                l.add(String.valueOf(rowChar));
            }
            list.add(l);
        }
        if (row >= chars.length) {
            return;
        }
        int rolLen = chars[row].length;

        for (int i = 0; i < rolLen; i++) {
            int a = checkAA(row, i);
            int b = checkBB(row, i);
            if (!colExists[i] && !AA[a] && !BB[b]) {
                colExists[i] = true;
                chars[row][i] = 'Q';
                AA[a] = true;
                BB[b] = true;
                solveNQueensProcess(list, chars, colExists, row + 1, n - 1);
                chars[row][i] = '.';
                colExists[i] = false;
                AA[a] = false;
                BB[b] = false;
            }
        }
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
