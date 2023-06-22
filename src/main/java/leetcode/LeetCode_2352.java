package leetcode;

/**
 * @description: Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
 * <p>
 * A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
 * @author: LiShuai
 * @createDate: 2023/6/13 22:12
 * @version: 1.0
 */

public class LeetCode_2352 {

    public static int equalPairs(int[][] grid) {
        int len = grid.length, ans = 0;

        for (int[] ints : grid) {
            for (int j = 0; j < len; j++) {
                int k = 0;
                while (k < len) {
                    if (ints[k] != grid[k][j]) {
                        break;
                    }
                    ++k;
                }
                if (k >= len) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
