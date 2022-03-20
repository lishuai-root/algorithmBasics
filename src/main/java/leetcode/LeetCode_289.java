package leetcode;

/**
 * @description: According to Wikipedia's article: "The Game of Life, also known simply as Life,
 * is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * The board is made up of an m x n grid of cells, where each cell has an initial state:
 * live (represented by a 1) or dead (represented by a 0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state,
 * where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 * <p>
 * Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite,
 * which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border).
 * How would you address these problems?
 * @author: LISHUAI
 * @createDate: 2022/3/9 23:40
 * @version: 1.0
 */

public class LeetCode_289 {

    private static final int[][] row = {
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, 1}
    };

    public static void gameOfLife_02(int[][] board) {
        int rLen = board.length, cLen = board[0].length, sum;
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                sum = 0;
                for (int[] ints : row) {
                    int r = i + ints[0];
                    int c = j + ints[1];
                    if (r >= 0 && r < rLen && c >= 0 && c < cLen) {
                        sum += (board[r][c] & 1);
                    }
                }
                if (sum == 3) {
                    board[i][j] += 4;
                } else if (sum != 2) {
                    board[i][j] += 2;
                }
            }
        }

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (board[i][j] >= 4) {
                    board[i][j] = 1;
                } else if (board[i][j] >= 2) {
                    board[i][j] = 0;
                }
            }
        }
    }


    public static void gameOfLife(int[][] board) {
        int rLen = board.length, cLen = board[0].length, sum;
        int[][] ans = new int[rLen][cLen];

        for (int i = 0; i < rLen; i++) {
            System.arraycopy(board[i], 0, ans[i], 0, cLen);
        }

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                sum = 0;
                for (int[] ints : row) {
                    int r = i + ints[0];
                    int c = j + ints[1];
                    if (r >= 0 && r < rLen && c >= 0 && c < cLen) {
                        sum += ans[r][c];
                    }
                }
                if (sum < 2) {
                    board[i][j] = 0;
                } else if (sum == 2) {
                    board[i][j] = ans[i][j] & 1;
                } else if (sum == 3) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}
