package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: There is an m x n grid, where (0, 0) is the top-left cell and (m - 1, n - 1) is the bottom-right cell. You are given an integer array startPos where startPos = [startrow, startcol] indicates that initially, a robot is at the cell (startrow, startcol). You are also given an integer array homePos where homePos = [homerow, homecol] indicates that its home is at the cell (homerow, homecol).
 * <p>
 * The robot needs to go to its home. It can move one cell in four directions: left, right, up, or down, and it can not move outside the boundary. Every move incurs some cost. You are further given two 0-indexed integer arrays: rowCosts of length m and colCosts of length n.
 * <p>
 * If the robot moves up or down into a cell whose row is r, then this move costs rowCosts[r].
 * If the robot moves left or right into a cell whose column is c, then this move costs colCosts[c].
 * Return the minimum total cost for this robot to return home.
 * @author: LiShuai
 * @createDate: 2023/9/3 20:17
 * @version: 1.0
 */

public class LeetCode_2087 {


    public static int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int rLen = rowCosts.length;
        int cLen = colCosts.length;
        int[][] costs = new int[rLen][cLen];
        for (int[] cs : costs) {
            Arrays.fill(cs, Integer.MAX_VALUE);
        }
        costs[startPos[0]][startPos[1]] = 0;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{startPos[0], startPos[1], 0});

        while (!queue.isEmpty()) {
            int[] curs = queue.poll();
            if (curs[0] == homePos[0] && curs[1] == homePos[1]) {
                return curs[2];
            }
            int r = curs[0] + 1;
            if (r >= 0 && r < rLen && curs[2] + rowCosts[r] < costs[r][curs[1]]) {
                costs[r][curs[1]] = curs[2] + rowCosts[r];
                queue.offer(new int[]{r, curs[1], curs[2] + rowCosts[r]});
            }
            r = curs[0] - 1;
            if (r >= 0 && r < rLen && curs[2] + rowCosts[r] < costs[r][curs[1]]) {
                costs[r][curs[1]] = curs[2] + rowCosts[r];
                queue.offer(new int[]{r, curs[1], curs[2] + rowCosts[r]});
            }
            int c = curs[1] + 1;
            if (c >= 0 && c < cLen && curs[2] + colCosts[c] < costs[curs[0]][c]) {
                costs[curs[0]][c] = curs[2] + colCosts[c];
                queue.offer(new int[]{curs[0], c, curs[2] + colCosts[c]});
            }
            c = curs[1] - 1;
            if (c >= 0 && c < cLen && curs[2] + colCosts[c] < costs[curs[0]][c]) {
                costs[curs[0]][c] = curs[2] + colCosts[c];
                queue.offer(new int[]{curs[0], c, curs[2] + colCosts[c]});
            }
        }
        return -1;
    }

    public static int minCost_02(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int ans = 0;
        int start = startPos[0];
        int end = homePos[0];
        int k = startPos[0] <= homePos[0] ? -1 : 1;
        while (start != end) {
            ans += rowCosts[end];
            end += k;
        }

        start = startPos[1];
        end = homePos[1];
        k = startPos[1] <= homePos[1] ? -1 : 1;
        while (start != end) {
            ans += colCosts[end];
            end += k;
        }
        return ans;
    }
}
