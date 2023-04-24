package leetcode;

import java.util.*;

/**
 * @description: You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
 * <p>
 * You are given two arrays redEdges and blueEdges where:
 * <p>
 * redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
 * blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
 * Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.
 * @author: LISHUAI
 * @createDate: 2023/2/11 18:09
 * @version: 1.0
 */

public class LeetCode_1129 {


    public static int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        Map<Integer, List<Integer>> redGraph = makeGraph(redEdges);
        Map<Integer, List<Integer>> blueGraph = makeGraph(blueEdges);
        int[] ans = new int[n];
        int[] redDP = new int[n];
        int[] blueDP = new int[n];
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.offer(new int[]{0, 0, -1});
        Arrays.fill(ans, -1);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curs = queue.poll();
                if (ans[curs[0]] == -1 || ans[curs[0]] > curs[1]) {
                    ans[curs[0]] = curs[1];
                } else {
                    continue;
                }
                if (curs[2] != 1) {
                    compute(blueGraph, curs, blueDP, queue, 1);
                }
                if (curs[2] != 2) {
                    compute(redGraph, curs, redDP, queue, 2);
                }
            }
        }
        return ans;
    }

    private static void compute(Map<Integer, List<Integer>> graph, int[] curs, int[] ansDP, Queue<int[]> queue, int flag) {
        if (ansDP[curs[0]] == 0 || ansDP[curs[0]] > curs[1]) {
            ansDP[curs[0]] = curs[1];
            if (graph.containsKey(curs[0])) {
                List<Integer> list = graph.get(curs[0]);
                for (int next : list) {
                    queue.offer(new int[]{next, curs[1] + 1, flag});
                }
            }
        }
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for (int[] redEdge : edges) {
            List<Integer> set = graph.computeIfAbsent(redEdge[0], o -> new ArrayList<>());
            set.add(redEdge[1]);
        }

        return graph;
    }
}
