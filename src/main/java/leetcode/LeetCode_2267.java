package leetcode;

/**
 * @description: A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:
 * <p>
 * It is ().
 * It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
 * It can be written as (A), where A is a valid parentheses string.
 * You are given an m x n matrix of parentheses grid. A valid parentheses string path in the grid is a path satisfying all of the following conditions:
 * <p>
 * The path starts from the upper left cell (0, 0).
 * The path ends at the bottom-right cell (m - 1, n - 1).
 * The path only ever moves down or right.
 * The resulting parentheses string formed by the path is valid.
 * Return true if there exists a valid parentheses string path in the grid. Otherwise, return false.
 * @author: LISHUAI
 * @createDate: 2022/12/31 15:11
 * @version: 1.0
 */

public class LeetCode_2267 {

    private static final int[][] TEMP = {{0, 1}, {1, 0}};

    public static void main(String[] args) {
//        char[][] grid = {
//                {'(', '(', '(', '(', '('},
//                {'(', '(', ')', ')', ')'},
//                {')', '(', ')', ')', '('},
//                {'(', '(', ')', ')', ')'}
//        };
        char[][] grid = {{'(', '(', ')', '(', ')', '(', '(', ')', '(', '(', ')', ')', ')', ')', ')', '(', ')', '(', '(', ')', '(', '(', ')', ')', ')', ')', ')', '(', '(', '(', '('}, {')', '(', '(', '(', ')', '(', ')', '(', '(', ')', ')', ')', ')', '(', ')', ')', '(', '(', ')', ')', '(', ')', '(', ')', '(', '(', ')', '(', ')', '(', '('}, {')', ')', '(', '(', ')', '(', '(', ')', ')', ')', ')', '(', '(', ')', ')', '(', ')', '(', ')', ')', '(', '(', '(', ')', ')', ')', '(', ')', ')', '(', ')'}, {'(', '(', ')', '(', ')', '(', '(', ')', '(', '(', '(', ')', ')', '(', ')', '(', ')', ')', ')', ')', ')', ')', '(', '(', ')', '(', ')', '(', ')', '(', '('}, {')', ')', '(', ')', ')', '(', '(', '(', ')', ')', '(', ')', '(', ')', ')', ')', '(', '(', '(', ')', ')', '(', ')', '(', ')', ')', '(', '(', '(', '(', ')'}, {')', ')', '(', '(', ')', '(', ')', '(', ')', '(', ')', '(', ')', ')', '(', ')', '(', ')', ')', '(', ')', '(', '(', '(', ')', '(', ')', ')', ')', '(', '('}, {')', '(', '(', '(', '(', '(', '(', ')', ')', '(', '(', ')', '(', ')', ')', '(', ')', ')', ')', '(', '(', '(', ')', '(', '(', ')', ')', '(', ')', '(', ')'}, {')', ')', '(', '(', '(', '(', '(', '(', '(', ')', ')', '(', '(', '(', '(', '(', '(', '(', '(', '(', '(', '(', '(', ')', ')', '(', '(', ')', ')', '(', ')'}, {'(', ')', ')', ')', '(', '(', ')', ')', ')', ')', '(', ')', ')', '(', ')', ')', '(', '(', '(', '(', '(', '(', '(', ')', ')', '(', '(', ')', ')', '(', '('}, {'(', '(', ')', '(', ')', ')', ')', ')', '(', '(', '(', ')', ')', ')', '(', ')', '(', '(', ')', '(', '(', '(', ')', '(', '(', '(', '(', '(', ')', ')', ')'}, {'(', ')', '(', '(', '(', '(', ')', '(', '(', ')', ')', '(', '(', ')', '(', '(', '(', ')', '(', '(', '(', ')', ')', '(', ')', ')', '(', ')', '(', '(', ')'}, {')', ')', '(', '(', '(', '(', ')', '(', '(', ')', ')', '(', ')', ')', '(', ')', '(', '(', '(', '(', '(', '(', '(', ')', '(', '(', ')', ')', '(', '(', '('}, {'(', ')', ')', ')', '(', ')', '(', '(', '(', ')', ')', ')', '(', ')', '(', ')', ')', '(', '(', '(', '(', ')', '(', ')', ')', ')', ')', ')', ')', '(', '('}, {'(', '(', '(', '(', '(', '(', ')', ')', '(', ')', '(', '(', '(', ')', ')', '(', ')', '(', ')', '(', ')', '(', '(', '(', ')', ')', ')', '(', ')', '(', '('}, {'(', ')', ')', ')', ')', '(', '(', ')', ')', ')', ')', ')', ')', '(', '(', ')', '(', ')', ')', '(', ')', '(', ')', ')', ')', '(', '(', ')', '(', '(', '('}, {'(', ')', ')', '(', '(', ')', ')', '(', ')', ')', '(', '(', '(', ')', ')', ')', ')', '(', '(', '(', ')', ')', '(', ')', '(', '(', '(', '(', ')', ')', ')'}, {')', '(', '(', ')', '(', '(', ')', ')', ')', '(', '(', '(', '(', ')', '(', ')', ')', ')', '(', ')', '(', ')', '(', '(', ')', '(', '(', '(', '(', '(', '('}, {'(', ')', '(', ')', '(', '(', ')', ')', ')', ')', ')', '(', '(', ')', ')', '(', ')', '(', ')', ')', ')', ')', '(', '(', '(', ')', '(', ')', '(', ')', ')'}, {'(', ')', '(', ')', ')', ')', '(', '(', '(', ')', '(', ')', '(', '(', ')', ')', '(', ')', '(', ')', '(', ')', '(', '(', '(', '(', '(', ')', '(', ')', '('}, {')', ')', ')', ')', ')', '(', ')', ')', '(', '(', ')', '(', ')', ')', '(', ')', ')', '(', '(', '(', '(', ')', '(', '(', '(', '(', ')', ')', ')', ')', '('}, {')', '(', '(', '(', '(', '(', ')', '(', ')', ')', ')', ')', '(', '(', '(', ')', ')', '(', ')', ')', '(', '(', '(', '(', '(', '(', ')', ')', '(', '(', '('}, {'(', '(', '(', ')', ')', '(', ')', '(', ')', ')', ')', ')', '(', ')', ')', ')', ')', '(', ')', '(', '(', '(', '(', ')', '(', '(', '(', '(', '(', '(', ')'}};
        boolean b = hasValidPath(grid);
        System.out.println(b);
    }

    public static boolean hasValidPath(char[][] grid) {
        int rLen = grid.length, cLen = grid[rLen - 1].length;
        Boolean[][][] bls = new Boolean[rLen][cLen][((rLen + cLen) >>> 1) + 1];
        return hasValidPathProcess(grid, 0, 0, 0, bls);
    }

    private static boolean hasValidPathProcess(char[][] grid, int prefix, int row, int col, Boolean[][][] bls) {
        if ((grid[row][col] == ')' && prefix == 0)) {
            return false;
        }
        int rLen = grid.length, cLen = grid[rLen - 1].length;
        int c = (grid[row][col] == '(' ? prefix + 1 : prefix - 1);
        if (c > ((rLen + cLen) >>> 1)) {
            return false;
        }
        if (row == rLen - 1 && col == cLen - 1) {
            return c == 0;
        }

        if (bls[row][col][c] != null) {
            return bls[row][col][c];
        }
        boolean ans = false;
        if (row + 1 < rLen) {
            ans = hasValidPathProcess(grid, c, row + 1, col, bls);
        }
        if (!ans && col + 1 < cLen) {
            ans = hasValidPathProcess(grid, c, row, col + 1, bls);
        }
        bls[row][col][c] = ans;
        return ans;
    }
}
