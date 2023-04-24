package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 * <p>
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 * <p>
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 * @author: LISHUAI
 * @createDate: 2022/12/19 11:30
 * @version: 1.0
 */

public class LeetCode_1971 {

    private static Boolean[] cache;

    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = makeGraph(edges);
        boolean[] bls = new boolean[n];
        cache = new Boolean[n];
        return connected(graph, source, destination, bls);
    }

    private static boolean connected(Map<Integer, List<Integer>> graph, int cur, int destination, boolean[] bls) {

        if (cur == destination) {
            return true;
        }
        if (bls[cur]) {
            return false;
        }
        if (cache[cur] != null) {
            return cache[cur];
        }
        boolean ans = false;
        bls[cur] = true;
        List<Integer> list = graph.get(cur);
        for (int next : list) {
            ans = connected(graph, next, destination, bls);
            if (ans) {
                break;
            }
        }
        bls[cur] = false;
        cache[cur] = ans;
        return ans;
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> list;
        for (int[] edge : edges) {
            list = graph.computeIfAbsent(edge[0], o -> new ArrayList<>());
            list.add(edge[1]);
            list = graph.computeIfAbsent(edge[1], o -> new ArrayList<>());
            list.add(edge[0]);
        }
        return graph;
    }

    public static boolean validPath_bfs(int n, int[][] edges, int source, int destination) {
        if (source == destination) {
            return true;
        }
        Map<Integer, List<Integer>> graph = makeGraph(edges);
        int[] queue = new int[n];
        boolean[] visited = new boolean[n];
        queue[source] = source;
        int left = 0, right = 0;
        queue[right++] = source;
        visited[source] = true;

        while (left < right) {
            int pre = right;

            while (left < pre) {
                int cur = queue[left++];
                List<Integer> list = graph.get(cur);
                for (int next : list) {
                    if (!visited[next]) {
                        if (next == destination) {
                            return true;
                        }
                        visited[next] = true;
                        queue[right++] = next;
                    }
                }
            }
        }
        return false;
    }


    public static boolean validPath_uf(int n, int[][] edges, int source, int destination) {
        UF uf = new UF(n);
        for (int[] ints : edges) {
            uf.union(ints[0], ints[1]);
        }
        return uf.find(source) == uf.find(destination);
    }

    static class UF {
        int[] uf;

        public UF(int size) {
            uf = new int[size];
            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }
        }

        public void union(int p, int q) {
            p = find(p);
            q = find(q);
            if (p != q) {
                uf[q] = p;
            }
        }

        public int find(int p) {
            return uf[p] == p ? p : (uf[p] = find(uf[p]));
        }
    }
}
