package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: Given a m * n matrix seats  that represent seats distributions in a classroom. If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.
 * <p>
 * Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting directly in front or behind him. Return the maximum number of students that can take the exam together without any cheating being possible..
 * <p>
 * Students must be placed in seats in good condition.
 * @author: LISHUAI
 * @createDate: 2022/12/18 11:23
 * @version: 1.0
 */

public class LeetCode_1349 {

    private static Integer[][][] cache;

    public static void main(String[] args) {
        char[][] seats = {
                {'#', '.', '#', '#', '.', '#'},
                {'.', '#', '#', '#', '#', '.'},
                {'#', '.', '#', '#', '.', '#'}
        };
//        char[][] seats = {
//                {'.', '.', '.', '.', '#', '.', '.', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', '.', '.', '.', '#', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '#', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', '#', '.', '.', '#', '.'}
//        };
//        char[][] seats = {
//                {'.', '#'},
//                {'#', '#'},
//                {'#', '.'},
//                {'#', '#'},
//                {'.', '#'}
//        };
        int i = maxStudents(seats);
        System.out.println(i);
    }

    public static int maxStudents(char[][] seats) {
        int len = seats.length;
        cache = new Integer[2][len][seats[len - 1].length];
        return maxStudentsProcess_02(seats, 0, 0);
    }

    private static int maxStudentsProcess(char[][] seats, int row, int col) {
        if (row < 0 || row >= seats.length || col < 0 || col >= seats[row].length) {
            return 0;
        }

        int c = (col == seats[row].length - 1 ? 0 : col + 1);
        int r = (c == 0 ? row + 1 : row);
        int p1 = maxStudentsProcess(seats, r, c);
        int p2 = 0;
        if (seats[row][col] == '.') {
            if (row - 1 < 0 || col - 1 < 0 || seats[row - 1][col - 1] != '-') {
                if (row - 1 < 0 || col + 1 >= seats[row].length || seats[row - 1][col + 1] != '-') {
                    if (col - 1 < 0 || seats[row][col - 1] != '-') {
                        char cr = seats[row][col];
                        seats[row][col] = '-';
                        p2 = maxStudentsProcess(seats, r, c) + 1;
                        seats[row][col] = cr;
                    }
                }
            }
        }

        return Math.max(p1, p2);
    }

    private static int maxStudentsProcess_02(char[][] seats, int row, int col) {
        if (row < 0 || row >= seats.length || col < 0 || col >= seats[row].length) {
            return 0;
        }
        int c = (col == seats[row].length - 1 ? 0 : col + 1);
        int r = (c == 0 ? row + 1 : row);
        int p1 = maxStudentsProcess_02(seats, r, c);
        int p2 = 0;

        if (seats[row][col] == '.') {
            setState(seats, row, col, 1);
            p2 = maxStudentsProcess_02(seats, r, c) + 1;
            setState(seats, row, col, -1);
        }

        return Math.max(p1, p2);
    }

    private static void setState(char[][] seats, int row, int col, int target) {
        if (col + 1 < seats[row].length) {
            seats[row][col + 1] += target;
        }
        if (row + 1 < seats.length && col - 1 >= 0) {
            seats[row + 1][col - 1] += target;
        }
        if (row + 1 < seats.length && col + 1 < seats[row].length) {
            seats[row + 1][col + 1] += target;
        }
    }

    class Solution {

        int r, c;
        int[][] memo;
        List<Integer> masks;

        public int maxStudents(char[][] seats) {
            r = seats.length;
            c = seats[0].length;
            memo = new int[r][1 << c];
            for (int i = 0; i < r; i++) {
                Arrays.fill(memo[i], -1);
            }
            return getMax(seats, 0, 0);
        }

        private int getMax(char[][] seats, int curRow, int prevRowMask) {
            if (curRow == r) {
                return 0;
            }
            if (memo[curRow][prevRowMask] != -1) {
                return memo[curRow][prevRowMask];
            }
            masks = new LinkedList<>(); // reset the masks list for backtrack
            backtrack(seats[curRow], 0, prevRowMask, 0); // backtrack results store in masks list
            int res = 0;
            for (int m : masks) {
                res = Math.max(res, Integer.bitCount(m) + getMax(seats, curRow + 1, m));
            }
            memo[curRow][prevRowMask] = res;
            return res;
        }

        // this returns all combination of legal seat assignment for a given row based on prevous row's mask
        private void backtrack(char[] seats, int cur, int prevRowMask, int curRowMask) {
            if (cur == c) {
                masks.add(curRowMask);
                return;
            }
            // cur seat is not taken
            backtrack(seats, cur + 1, prevRowMask, curRowMask);

            // cur seat is taken, check if left, upper left and upper right positions are empty
            if (seats[cur] != '#'
                    && (cur == 0 || (((curRowMask & (1 << (cur - 1))) == 0) && (prevRowMask & (1 << (cur - 1))) == 0))
                    && (cur == c - 1 || ((prevRowMask & (1 << (cur + 1))) == 0))) {
                curRowMask |= (1 << (cur));
                backtrack(seats, cur + 1, prevRowMask, curRowMask);
                curRowMask ^= (1 << (cur));
            }
        }
    }
}
