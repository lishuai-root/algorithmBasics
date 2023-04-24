package leetcode;

import java.util.*;

/**
 * @description: You are given an undirected graph (the "original graph") with n nodes labeled from 0 to n - 1. You decide to subdivide each edge in the graph into a chain of nodes, with the number of new nodes varying between each edge.
 * <p>
 * The graph is given as a 2D array of edges where edges[i] = [ui, vi, cnti] indicates that there is an edge between nodes ui and vi in the original graph, and cnti is the total number of new nodes that you will subdivide the edge into. Note that cnti == 0 means you will not subdivide the edge.
 * <p>
 * To subdivide the edge [ui, vi], replace it with (cnti + 1) new edges and cnti new nodes. The new nodes are x1, x2, ..., xcnti, and the new edges are [ui, x1], [x1, x2], [x2, x3], ..., [xcnti-1, xcnti], [xcnti, vi].
 * <p>
 * In this new graph, you want to know how many nodes are reachable from the node 0, where a node is reachable if the distance is maxMoves or less.
 * <p>
 * Given the original graph and maxMoves, return the number of nodes that are reachable from node 0 in the new graph.
 * @author: LISHUAI
 * @createDate: 2023/4/3 23:07
 * @version: 1.0
 */

public class LeetCode_882 {

    public static int reachableNodes(int[][] edges, int maxMoves, int n) {

        int[][] nodes = new int[n][n];
        int[][] cache = new int[n][n];
        boolean[] bls = new boolean[n];
        int[] path = new int[n];
        int ans = 1;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        Map<Integer, List<int[]>> graph = makeGraph(edges, cache);
        queue.offer(new int[]{0, maxMoves});
        bls[0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] == 0) {
                break;
            }
            if (graph.containsKey(cur[0])) {
                List<int[]> list = graph.get(cur[0]);
                for (int[] next : list) {
                    int sum = nodes[cur[0]][next[0]] + nodes[next[0]][cur[0]];
                    int ca = 0;
                    if (sum < cache[cur[0]][next[0]] && cur[1] > nodes[cur[0]][next[0]]) {
                        ca = Math.min(cur[1] - nodes[cur[0]][next[0]], cache[cur[0]][next[0]] - sum);
                        nodes[cur[0]][next[0]] = Math.min(cur[1], cache[cur[0]][next[0]] - nodes[next[0]][cur[0]]);
                    }
                    if (cur[1] >= cache[cur[0]][next[0]] + 1) {
                        int c = cur[1] - next[1] - 1;
                        if (c > path[next[0]]) {
                            queue.offer(new int[]{next[0], c});
                            path[next[0]] = c;
                        }
                        if (!bls[next[0]]) {
                            ca++;
                            bls[next[0]] = true;
                        }
                    }
                    ans += ca;
                }
            }
        }
        return ans;
    }

    private static Map<Integer, List<int[]>> makeGraph(int[][] edges, int[][] cache) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        List<int[]> list;
        for (int[] curs : edges) {
            list = graph.computeIfAbsent(curs[0], o -> new ArrayList<>());
            list.add(new int[]{curs[1], curs[2]});
            list = graph.computeIfAbsent(curs[1], o -> new ArrayList<>());
            list.add(new int[]{curs[0], curs[2]});
            cache[curs[0]][curs[1]] = curs[2];
            cache[curs[1]][curs[0]] = curs[2];
        }
        return graph;
    }
}
