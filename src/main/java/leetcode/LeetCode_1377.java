package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from vertex 1. In one second, the frog jumps from its current vertex to another unvisited vertex if they are directly connected. The frog can not jump back to a visited vertex. In case the frog can jump to several vertices, it jumps randomly to one of them with the same probability. Otherwise, when the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.
 * <p>
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
 * <p>
 * Return the probability that after t seconds the frog is on the vertex target. Answers within 10-5 of the actual answer will be accepted.
 * @author: LISHUAI
 * @createDate: 2022/12/11 18:12
 * @version: 1.0
 */

public class LeetCode_1377 {

    public static void main(String[] args) {
        int n = 9, t = 6, target = 8;
        int[][] edges = {{2, 1}, {3, 1}, {4, 2}, {5, 1}, {6, 2}, {7, 5}, {8, 7}, {9, 7}};
//        int n = 7, t = 2, target = 4;
//        int[][] edges = {{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5}};
//        int n = 3, t = 1, target = 2;
//        int[][] edges = {{2, 1}, {3, 2}};
        System.out.println(frogPosition(n, edges, t, target));
    }


    public static double frogPosition(int n, int[][] edges, int t, int target) {
        Map<Integer, List<Integer>> graph = makeGraph(edges);
        boolean[] bls = new boolean[n + 1];
        bls[1] = true;
        return dfs(graph, 1, target, t, 1, bls);
    }

    private static double dfs(Map<Integer, List<Integer>> graph, int cur, int target, int k, double g, boolean[] bls) {
        if (cur == target) {
            if (k == 0) {
                return g;
            } else {
                List<Integer> ll = graph.computeIfAbsent(cur, o -> new ArrayList<>());
                for (int i : ll) {
                    if (!bls[i]) {
                        return 0;
                    }
                }
                return g;
            }
        }
        if (k == 0) {
            return 0;
        }

        List<Integer> list = graph.get(cur);
        int size = 0;
        for (int i : list) {
            if (!bls[i]) {
                size++;
            }
        }
        if (size == 0) {
            return 0;
        }
        double next = g / size;
        double ans = 0D;
        for (int i : list) {
            if (!bls[i]) {
                bls[i] = true;
                ans += dfs(graph, i, target, k - 1, next, bls);
            }
        }
        return ans;
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        for (int[] ints : edges) {
            List<Integer> list = graph.computeIfAbsent(ints[0], o -> new ArrayList<>());
            list.add(ints[1]);
            list = graph.computeIfAbsent(ints[1], o -> new ArrayList<>());
            list.add(ints[0]);
        }
        return graph;
    }
}
