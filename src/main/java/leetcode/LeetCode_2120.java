package leetcode;

import java.util.Arrays;

/**
 * @description: There is an n x n grid, with the top-left cell at (0, 0) and the bottom-right cell at (n - 1, n - 1). You are given the integer n and an integer array startPos where startPos = [startrow, startcol] indicates that a robot is initially at cell (startrow, startcol).
 * <p>
 * You are also given a 0-indexed string s of length m where s[i] is the ith instruction for the robot: 'L' (move left), 'R' (move right), 'U' (move up), and 'D' (move down).
 * <p>
 * The robot can begin executing from any ith instruction in s. It executes the instructions one by one towards the end of s but it stops if either of these conditions is met:
 * <p>
 * The next instruction will move the robot off the grid.
 * There are no more instructions left to execute.
 * Return an array answer of length m where answer[i] is the number of instructions the robot can execute if the robot begins executing from the ith instruction in s.
 * @author: LiShuai
 * @createDate: 2023/6/4 21:49
 * @version: 1.0
 */

public class LeetCode_2120 {

    private static final int[][] tmp = new int[4][2];
    private static final int[] reindex = new int[26];
    private static int[][][] dp;
    private static int[] cache;

    private static int[] totalStartPos;

    private static int[][] end;

    static {
        tmp[0] = new int[]{0, -1};
        tmp[1] = new int[]{0, 1};
        tmp[2] = new int[]{-1, 0};
        tmp[3] = new int[]{1, 0};
        reindex['L' - 'A'] = 0;
        reindex['R' - 'A'] = 1;
        reindex['U' - 'A'] = 2;
        reindex['D' - 'A'] = 3;
    }

    public static void main(String[] args) {
//        int n = 3;
//        int[] startPos = {0, 1};
//        String s = "RRDDLU";
        int n = 2;
        int[] startPos = {1, 1};
        String s = "LURD";
        int[] ints = executeInstructions(n, startPos, s);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(executeInstructions_02(n, startPos, s)));
    }

    public static int[] executeInstructions(int n, int[] startPos, String s) {
        int[] ans = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ans[i] = executeInstructionsProcess(n, s, i, startPos[0], startPos[1]);
        }
        return ans;
    }

    private static int executeInstructionsProcess(int n, String s, int index, int row, int col) {
        if (index >= s.length()) {
            return 0;
        }
        char c = s.charAt(index);
        int k = reindex[c - 'A'];

        int[] t = tmp[k];
        int nr = row + t[0], nc = col + t[1];
        if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
            return 0;
        }
        return executeInstructionsProcess(n, s, index + 1, nr, nc) + 1;
    }

    public static int[] executeInstructions_02(int n, int[] startPos, String s) {
        int len = s.length();
        dp = new int[len][4][3];
        cache = new int[len];
        end = new int[len][2];
        int[] ans = new int[len];
        totalStartPos = startPos;

        for (int i = len - 1; i >= 0; i--) {
            int[][] ss = dp[i];
            ss[0] = new int[]{Integer.MAX_VALUE};
            ss[1] = new int[]{Integer.MIN_VALUE};
            ss[2] = new int[]{0, Integer.MAX_VALUE};
            ss[3] = new int[]{0, Integer.MIN_VALUE};
            int p = executeInstructionsProcess_02(n, s, i, i, startPos[0], startPos[1]);

            cache[i] = p;
            ans[i] = p;
        }
        return ans;
    }


    private static int executeInstructionsProcess_02(int n, String s, int start, int index, int row, int col) {
        int[][] ss = dp[start];
        int[] mom = {row, col, index};
        if (ss[0][0] > row) {
            ss[0] = mom;
        }
        if (ss[1][0] < row) {
            ss[1] = mom;
        }
        if (ss[2][1] > col) {
            ss[2] = mom;
        }
        if (ss[3][1] < col) {
            ss[3] = mom;
        }
        end[start][0] = row;
        end[start][1] = col;
        if (index >= s.length()) {
            return 0;
        }
        char c = s.charAt(index);
        int k = reindex[c - 'A'];

        int[] t = tmp[k];
        int cr = row - totalStartPos[0], cl = col - totalStartPos[1];
        int[][] cur = dp[index];
        int ni = index + 1;
        int nr = row + t[0], nc = col + t[1];
        if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
            return 0;
        }
        if (cache[index] != 0) {
            ni = index + cache[index];
            nr = end[index][0] + cr;
            nc = end[index][1] + cl;
            for (int[] cni : cur) {
                int ncr = cni[0] + cr;
                int ncc = cni[1] + cl;
                if (ncr < 0 || ncr >= n || ncc < 0 || ncc >= n) {
                    if (ni > cni[2]) {
                        ni = cni[2];
                        nr = ncr;
                        nc = ncc;
                    }
                }
            }
        }

        if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
            return ni - index;
        }


        return executeInstructionsProcess_02(n, s, start, ni, nr, nc) + ni - index;
    }
}
