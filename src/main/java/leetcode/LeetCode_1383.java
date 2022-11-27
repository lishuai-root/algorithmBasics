package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.
 * <p>
 * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
 * <p>
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
 * <p>
 * Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2022/9/11 22:03
 * @version: 1.0
 */

public class LeetCode_1383 {

    public static void main(String[] args) {
        int[] speed = {2, 10, 3, 1, 5, 8}, efficiency = {5, 4, 3, 9, 7, 2};
        int n = 6, k = 2;
        int i = maxPerformance(n, speed, efficiency, k);
        System.out.println(i);
        int i1 = maxPerformance_dp(n, speed, efficiency, k);
        System.out.println(i1);
        System.out.println(1000000007 * 1000000007);
    }

    public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        return maxPerformanceProcess(speed, efficiency, n, k, 0, 0, Integer.MAX_VALUE);
    }

    public static int maxPerformance_dp(int n, int[] speed, int[] efficiency, int k) {
        long ans = 0, sum = 0;
        Integer[] dp = new Integer[n];
        for (int i = 0; i < n; i++) {
            dp[i] = i;
        }
        Arrays.sort(dp, (a, b) -> (efficiency[b] - efficiency[a]));
        Queue<Integer> queue = new PriorityQueue<>(k, (a, b) -> (speed[a] - speed[b]));

        for (int i = 0; i < n; i++) {
            int c = dp[i];
            if (queue.size() >= k) {
                sum -= speed[queue.poll()];
            }
            queue.offer(c);
            sum += speed[c];
//            sum %= 1000000007;
            ans = Math.max(ans, sum * efficiency[c]);

        }
        return (int) (ans % 1000000007);
    }

    private static int maxPerformanceProcess(int[] speed, int[] efficiency, int n, int k, int index, int sum, int min) {
        int ans = (sum * min) % 100000009;
        if (k == 0 || index >= n) {
            return ans;
        }

        int p1 = maxPerformanceProcess(speed, efficiency, n, k - 1, index + 1, (sum + speed[index]) % 100000009, Math.min(min, efficiency[index]));
        int p2 = maxPerformanceProcess(speed, efficiency, n, k, index + 1, sum, min);
        return Math.max(ans, Math.max(p1, p2)) % 100000009;
    }

    public int maxPerformance_other(int n, int[] speed, int[] efficiency, int k) {
        int[][] players = new int[n][2];
        for (int i = 0; i < n; i++) {
            players[i][0] = efficiency[i];
            players[i][1] = speed[i];
        }
        // Sort the players based on efficiency in decreasing order, as for each iteration, we'll consider only players with higher efficiency.
        Arrays.sort(players, (p1, p2) -> (p2[0] - p1[0]));

        // Priority-Queue to maintain players with highest relative speeds with efficiencies greater than the one under iteration.
        PriorityQueue<Integer> speedQueue = new PriorityQueue<>(k);
        long teamPerformance = 0, teamSpeed = 0;

        for (int i = 0; i < n; i++) {
            // This is because a team can have atmost `k` players.
            if (speedQueue.size() >= k) {
                teamSpeed -= speedQueue.remove();
            }
            speedQueue.add(players[i][1]);
            teamSpeed += players[i][1];

            teamPerformance = Math.max(teamPerformance, teamSpeed * players[i][0]);
        }
        return (int) (teamPerformance % 1000000007);
    }
}
