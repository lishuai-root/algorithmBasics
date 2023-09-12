package leetcode;

import java.util.*;

/**
 * @description: There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
 * <p>
 * Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
 * <p>
 * Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
 * @author: LiShuai
 * @createDate: 2023/8/20 22:55
 * @version: 1.0
 */

public class LeetCode_1334 {

    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<List<int[]>> graph = new ArrayList<List<int[]>>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            if (edge[2] <= distanceThreshold) {
                graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
                graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
            }
        }
        int ans = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int p = findTheCityProcess(graph, n, i, distanceThreshold);
            if (p <= min) {
                min = p;
                ans = i;
            }
        }
        return ans;
    }

    private static int findTheCityProcess(List<List<int[]>> graph, int n, int start, int distanceThreshold) {
        int[] weights = new int[n];
        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[start] = 0;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int ans = 0;
        for (int[] edge : graph.get(start)) {
            queue.offer(new int[]{edge[0], edge[1]});
        }

        while (!queue.isEmpty()) {
            int[] curs = queue.poll();
            if (curs[1] > distanceThreshold) {
                break;
            }
            if (weights[curs[0]] == Integer.MAX_VALUE) {
                ++ans;
            }

            if (curs[1] >= weights[curs[0]]) {
                continue;
            }
            weights[curs[0]] = curs[1];
            List<int[]> list = graph.get(curs[0]);
            for (int[] edge : list) {
                if (curs[1] + edge[1] <= distanceThreshold) {
                    queue.offer(new int[]{edge[0], curs[1] + edge[1]});
                }
            }
        }
        return ans;
    }

}
