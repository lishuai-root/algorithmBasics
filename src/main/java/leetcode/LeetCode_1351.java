package leetcode;

/**
 * @description: Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.
 * @author: LiShuai
 * @createDate: 2023/6/8 21:24
 * @version: 1.0
 */

public class LeetCode_1351 {

    public static int countNegatives(int[][] grid) {
        int ans = 0;
        for (int[] ints : grid) {
            for (int i : ints) {
                if (i < 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
