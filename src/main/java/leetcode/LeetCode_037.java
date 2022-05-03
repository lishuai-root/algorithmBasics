package leetcode;

/**
 * @description: Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 * <p>
 * It is guaranteed that the input board has only one solution.
 * @author: LISHUAI
 * @createDate: 2022/4/11 6:40
 * @version: 1.0
 */

public class LeetCode_037 {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solveSudoku(board);
        for (char[] chars : board) {
            for (char c : chars) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public static void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[board.length][10];
        boolean[][] col = new boolean[board[0].length][10];
        boolean[][] cur = new boolean[9][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int c = board[i][j] - '0';
                    row[i][c] = true;
                    col[j][c] = true;
                    cur[i / 3 * 3 + j / 3][c] = true;
                }
            }
        }
        solveSudokuProcess(board, row, col, cur, 0, 0);
    }

    private static boolean solveSudokuProcess(char[][] board, boolean[][] row, boolean[][] col, boolean[][] cur, int r, int c) {
        a:
        while (r < board.length) {
            while (c < board[0].length) {
                if (board[r][c] == '.') {
                    break a;
                }
                ++c;
            }
            ++r;
            c = 0;
        }

        if (r >= board.length) {
            return true;
        }

        int cr = r / 3 * 3 + c / 3;
        for (int i = 1; i <= 9; i++) {
            if (!row[r][i] && !col[c][i] && !cur[cr][i]) {
                row[r][i] = true;
                col[c][i] = true;
                cur[cr][i] = true;
                board[r][c] = (char) (i + '0');
//                if (solveSudokuProcess(board, row, col, cur, c + 1 >= board[0].length ? r + 1 : r, c + 1 >= board[0].length ? 0 : c + 1)) {
//                    return true;
//                }
                if (solveSudokuProcess(board, row, col, cur, r, c)) {
                    return true;
                }
                row[r][i] = false;
                col[c][i] = false;
                cur[cr][i] = false;
                board[r][c] = '.';
            }
        }
        return false;
    }
}











