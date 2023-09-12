package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given a 0-indexed m x n binary matrix grid.
 * <p>
 * Let us call a non-empty subset of rows good if the sum of each column of the subset is at most half of the length of the subset.
 * <p>
 * More formally, if the length of the chosen subset of rows is k, then the sum of each column should be at most floor(k / 2).
 * <p>
 * Return an integer array that contains row indices of a good subset sorted in ascending order.
 * <p>
 * If there are multiple good subsets, you can return any of them. If there are no good subsets, return an empty array.
 * <p>
 * A subset of rows of the matrix grid is any matrix that can be obtained by deleting some (possibly none or all) rows from grid.
 * @author: LiShuai
 * @createDate: 2023/9/5 20:08
 * @version: 1.0
 */

public class LeetCode_2732 {

    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        List<Integer> res = new ArrayList<>();
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            boolean allZero = true;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero) {
                res.add(i);
                return res;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int ii = i + 1; ii < m; ii++) {
                boolean allZero = true;
                for (int j = 0; j < n; j++) {
                    if ((grid[i][j] & grid[ii][j]) > 0) {
                        allZero = false;
                        break;
                    }
                }
                if (allZero) {
                    res.add(i);
                    res.add(ii);
                    return res;
                }
            }
        }
        return res;
    }
}
