package leetcode;

import java.util.List;

/**
 * @description: Given a triangle array, return the minimum path sum from top to bottom.
 * <p>
 * For each step, you may move to an adjacent number of the row below.
 * More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 * <p>
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 * @author: LISHUAI
 * @createDate: 2022/6/13 23:11
 * @version: 1.0
 */

public class LeetCode_120 {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] dp = new int[len + 1];
        dp[len] = Integer.MAX_VALUE;
        for (int i = len - 1; i >= 0; i--) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j + 1], dp[j]) + list.get(j);
            }
        }
        return dp[0];
    }
}
