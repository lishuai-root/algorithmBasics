package leetcode;

import java.util.*;

/**
 * @description: You are given a network of n nodes, labeled from 1 to n. You are also given times,
 * a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node,
 * and wi is the time it takes for a signal to travel from source to target.
 * <p>
 * We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal.
 * If it is impossible for all the n nodes to receive the signal, return -1.
 * @author: LISHUAI
 * @createDate: 2022/5/14 23:28
 * @version: 1.0
 */

public class LeetCode_743 {

    public static int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = makeGraph(times, n);
        return networkDelayTimeProcess(graph, k);
    }

    private static int networkDelayTimeProcess(List<List<int[]>> graph, int cur) {
        int len = graph.size();
        int[] times = new int[len];
        int[] bits = new int[((len + 31) >>> 5)];
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        Arrays.fill(times, Integer.MAX_VALUE);
        queue.offer(new int[]{cur - 1, 0});
        times[cur - 1] = 0;
        int ans = 0, size = 0;

        while (!queue.isEmpty() && size < len) {
            int[] curs = queue.poll();
            int c = curs[0];
            if ((bits[c >>> 5] & (1 << (c & 31))) == 0) {
                ans = Math.max(ans, curs[1]);
                size++;
                bits[c >>> 5] = bits[c >>> 5] | (1 << (c & 31));
            }

            List<int[]> list = graph.get(c);
            for (int[] ints : list) {
                if (ints[1] + curs[1] < times[ints[0]]) {
                    times[ints[0]] = ints[1] + curs[1];
                    queue.offer(new int[]{ints[0], ints[1] + curs[1]});
                }
            }
        }
        if (size != len) {
            return -1;
        }

        return ans;
    }

    private static List<List<int[]>> makeGraph(int[][] times, int n) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] ints : times) {
            graph.get(ints[0] - 1).add(new int[]{ints[1] - 1, ints[2]});
        }
        return graph;
    }
}
