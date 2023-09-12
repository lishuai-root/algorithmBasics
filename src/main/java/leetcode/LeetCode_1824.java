package leetcode;

/**
 * @description: There is a 3 lane road of length n that consists of n + 1 points labeled from 0 to n. A frog starts at point 0 in the second lane and wants to jump to point n. However, there could be obstacles along the way.
 * <p>
 * You are given an array obstacles of length n + 1 where each obstacles[i] (ranging from 0 to 3) describes an obstacle on the lane obstacles[i] at point i. If obstacles[i] == 0, there are no obstacles at point i. There will be at most one obstacle in the 3 lanes at each point.
 * <p>
 * For example, if obstacles[2] == 1, then there is an obstacle on lane 1 at point 2.
 * The frog can only travel from point i to point i + 1 on the same lane if there is not an obstacle on the lane at point i + 1. To avoid obstacles, the frog can also perform a side jump to jump to another lane (even if they are not adjacent) at the same point if there is no obstacle on the new lane.
 * <p>
 * For example, the frog can jump from lane 3 at point 3 to lane 1 at point 3.
 * Return the minimum number of side jumps the frog needs to reach any lane at point n starting from lane 2 at point 0.
 * <p>
 * Note: There will be no obstacles on points 0 and n.
 * @author: LiShuai
 * @createDate: 2023/8/27 17:28
 * @version: 1.0
 */

public class LeetCode_1824 {

    public static int minSideJumps(int[] obstacles) {
        int len = obstacles.length;
        int[] dp = new int[4];

        for (int i = len - 2; i >= 0; --i) {
            int pre = dp[0];
            dp[0] = Integer.MAX_VALUE;
            for (int j = 1; j < 4; j++) {
                if (obstacles[i] == j) {
                    dp[j] = Integer.MAX_VALUE;
                    continue;
                }
                if (dp[j] == Integer.MAX_VALUE) {
                    dp[j] = pre + 1;
                }
                if (i != 0 && obstacles[i - 1] != j) {
                    dp[0] = Math.min(dp[0], dp[j]);
                }
            }
        }
        return dp[2];
    }


}
