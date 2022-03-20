package leetcode;

/**
 * @description: Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * @author: LISHUAI
 * @createDate: 2021/11/20 19:53
 * @version: 1.0
 */

public class LeetCode_079 {

    public static void main(String[] args) {


        char[][] chars = new char[][]{{'b', 'a', 'b', 'b', 'a'}};

        String word = "baa";

        boolean exist = exist(chars, word);

        System.out.println(exist);
    }

    public static boolean exist(char[][] board, String word) {

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[0].length; j++) {

                boolean[][] bbs = new boolean[board.length + 1][board[0].length + 1];

                bbs[i][j] = true;

                boolean process = process(board, i, j, word, 0, bbs);

                if (process) {

                    return true;
                }
            }

        }

        return false;
    }

    public static boolean process(char[][] board, int row, int col, String word, int index, boolean[][] bbs) {


        if (board[row][col] != word.charAt(index)) {

            return false;
        }

        index++;

        boolean bs = index == word.length();

        if (!bs && row + 1 < board.length && !bbs[row + 1][col]) {

            bbs[row + 1][col] = true;

            bs = bs || process(board, row + 1, col, word, index, bbs);

            bbs[row + 1][col] = false;
        }

        if (!bs && col + 1 < board[0].length && !bbs[row][col + 1]) {

            bbs[row][col + 1] = true;

            bs = bs || process(board, row, col + 1, word, index, bbs);

            bbs[row][col + 1] = false;
        }

        if (!bs && row - 1 >= 0 && !bbs[row - 1][col]) {

            bbs[row - 1][col] = true;

            bs = bs || process(board, row - 1, col, word, index, bbs);

            bbs[row - 1][col] = false;
        }

        if (!bs && col - 1 >= 0 && !bbs[row][col - 1]) {

            bbs[row][col - 1] = true;

            bs = bs || process(board, row, col - 1, word, index, bbs);

            bbs[row][col - 1] = false;
        }

        return bs;
    }


}
