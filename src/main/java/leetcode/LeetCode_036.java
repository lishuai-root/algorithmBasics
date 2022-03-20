package leetcode;

import java.util.Arrays;

/**
 * @description: Determine if a 9 x 9 Sudoku board is valid.
 * Only the filled cells need to be validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 * <p>
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * @author: LISHUAI
 * @createDate: 2021/12/2 21:49
 * @version: 1.0
 */

public class LeetCode_036 {
    public static void main(String[] args) {

//        System.out.println((int) '.');
//        System.out.println((int) '1');
//        System.out.println((int) '9');

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

        boolean validSudoku = isValidSudoku(board);

        System.out.println(validSudoku);
    }

    public static boolean isValidSudoku(char[][] board) {

        int[] nums = new int[9];

        int rowLen = board.length, colLen = board[0].length, index = 0;

        for (int i = 0; i < rowLen; i++) {

            for (int j = 0; j < colLen; j++) {

                if (board[i][j] <= '9' && board[i][j] >= '1') {

                    if (nums[board[i][j] - '1'] > 0) {

                        return false;
                    } else {

                        nums[board[i][j] - '1']++;
                    }
                }
            }

            Arrays.fill(nums, 0);
        }

        for (int i = 0; i < colLen; i++) {

            for (int j = 0; j < rowLen; j++) {

                if (board[j][i] <= '9' && board[j][i] >= '1') {

                    if (nums[board[j][i] - '1'] > 0) {

                        return false;
                    } else {

                        nums[board[j][i] - '1']++;
                    }
                }
            }

            Arrays.fill(nums, 0);
        }

        rowLen = 0;

        for (; rowLen < 9; rowLen = rowLen + 3) {

            colLen = 0;

            for (; colLen < 9; colLen = colLen + 3) {

                for (int i = 0; i < 3; i++) {

                    for (int j = 0; j < 3; j++) {

                        if (board[rowLen + i][colLen + j] <= '9' && board[rowLen + i][colLen + j] >= '1') {

                            if (nums[board[rowLen + i][colLen + j] - '1'] > 0) {

//                                System.out.println("+++++++++++++++++");

//                                System.out.println(rowLen + i);
//                                System.out.println(rowLen + j);

                                return false;
                            } else {

                                nums[board[rowLen + i][colLen + j] - '1']++;
                            }
                        }
                    }
                }

                Arrays.fill(nums, 0);
            }
        }


        return true;
    }
}
