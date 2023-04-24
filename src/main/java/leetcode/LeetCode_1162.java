package leetcode;

/**
 * @description: Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.
 * <p>
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 * @author: LISHUAI
 * @createDate: 2023/2/10 22:46
 * @version: 1.0
 */

public class LeetCode_1162 {

    public static int maxDistance(int[][] grid) {
        int rowLen = grid.length, colLen = grid[0].length;
        int[][] cache = new int[rowLen * colLen][2];
        int preIndex = 0, index = cache.length;
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (grid[i][j] == 0) {
                    --index;
                    cache[index][0] = i;
                    cache[index][1] = j;
                } else {
                    cache[preIndex][0] = i;
                    cache[preIndex][1] = j;
                    preIndex++;
                }
            }
        }

        if (preIndex == 0 || index == cache.length) {
            return -1;
        }

        for (int i = index; i < cache.length; i++) {
            int cur = Integer.MAX_VALUE;
            for (int j = 0; j < index; j++) {
                cur = Math.min(cur, Math.abs(cache[i][0] - cache[j][0]) + Math.abs(cache[i][1] - cache[j][1]));
                if (cur == 1) {
                    break;
                }
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }

}
