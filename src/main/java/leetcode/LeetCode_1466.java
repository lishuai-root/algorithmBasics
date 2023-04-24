package leetcode;

import java.util.*;

/**
 * @description: There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 * <p>
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 * <p>
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 * <p>
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 * <p>
 * It's guaranteed that each city can reach city 0 after reorder.
 * @author: LISHUAI
 * @createDate: 2023/3/24 22:07
 * @version: 1.0
 */

public class LeetCode_1466 {

    public static void main(String[] args) {
        int n = 6;
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
//        int n = 3;
//        int[][] connections = {{1, 0}, {2, 0}};
//        int n = 5;
//        int[][] connections = {{1, 0}, {1, 2}, {3, 2}, {3, 4}};
        int i = minReorder(n, connections);
        System.out.println(i);
    }

    public static int minReorder(int n, int[][] connections) {
        Map<Integer, List<Integer>> graph = makeGraph(connections);
        int[] exists = new int[n];
        Arrays.fill(exists, -1);
        exists[0] = 1;
        for (int i = 0; i < n; i++) {
            minReorderProcess(graph, i, exists);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (isConnected(graph, i, exists)) {
                ans += flagAns(graph, i, exists);
            }
        }
        return ans;
    }

    private static int flagAns(Map<Integer, List<Integer>> graph, int cur, int[] exists) {
        if (!graph.containsKey(cur)) {
            if (exists[cur] == 0) {
                exists[cur] = 2;
                return 1;
            }
            return 0;
        }
        List<Integer> list = graph.get(cur);
        int ans = exists[cur] == 0 ? 1 : 0;
        for (int next : list) {
            if (exists[next] == 0) {
                ans += flagAns(graph, next, exists);
            }
        }
        if (exists[cur] == 0) {
            exists[cur] = 2;
        }
        return ans;
    }

    private static boolean isConnected(Map<Integer, List<Integer>> graph, int cur, int[] exists) {
        if (exists[cur] != 0) {
            return true;
        }

        if (!graph.containsKey(cur)) {
            return false;
        }
        List<Integer> list = graph.get(cur);
        for (int next : list) {
            if (isConnected(graph, next, exists)) {
                exists[cur] = 2;
                return true;
            }
        }
        return false;
    }

    private static int minReorderProcess(Map<Integer, List<Integer>> graph, int cur, int[] exists) {
        if (cur == 0) {
            return 1;
        }
        if (exists[cur] != -1) {
            return exists[cur];
        }
        if (!graph.containsKey(cur)) {
            exists[cur] = 0;
            return 0;
        }
        List<Integer> list = graph.get(cur);
        int ans = 0;
        for (int next : list) {
            ans |= minReorderProcess(graph, next, exists);
        }
        exists[cur] = ans;
        return ans;
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> list;
        for (int[] ints : connections) {
            list = graph.computeIfAbsent(ints[0], o -> new ArrayList<>());
            list.add(ints[1]);
        }
        return graph;
    }

    public int minReorder_02(int n, int[][] connections) {
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < connections.length; i++) {
            adj.get(connections[i][0]).add(connections[i][1]);
            adj.get(connections[i][1]).add(-connections[i][0]);
        }
        boolean vis[] = new boolean[n];
        int cnt = 0;
        Queue<Integer> qu = new LinkedList<>();
        qu.add(0);
        vis[0] = true;
        while (!qu.isEmpty()) {
            int curr = qu.poll();
            for (int it : adj.get(Math.abs(curr))) {
                if (!vis[Math.abs(it)]) {
                    qu.add(it);
                    vis[Math.abs(it)] = true;
                    if (it > 0)
                        cnt++;
                }
            }
        }
        return cnt;
    }
}
