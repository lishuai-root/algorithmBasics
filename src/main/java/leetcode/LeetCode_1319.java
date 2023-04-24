package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.
 * <p>
 * You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.
 * <p>
 * Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.
 * @author: LISHUAI
 * @createDate: 2023/3/23 21:30
 * @version: 1.0
 */

public class LeetCode_1319 {

    public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        int ans = 0;
        boolean[] bls = new boolean[n];
        Map<Integer, List<Integer>> graph = makeGraph(connections);
        for (int i = 0; i < n; i++) {
            if (!bls[i]) {
                makeConnectedProcess(graph, i, bls);
                ans++;
            }
        }
        return ans - 1;
    }

    private static void makeConnectedProcess(Map<Integer, List<Integer>> graph, int cur, boolean[] bls) {
        if (bls[cur]) {
            return;
        }
        bls[cur] = true;
        List<Integer> list = graph.get(cur);
        if (list != null) {
            for (int next : list) {
                makeConnectedProcess(graph, next, bls);
            }
        }
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> list;
        for (int[] ints : connections) {
            list = graph.computeIfAbsent(ints[0], o -> new ArrayList<>());
            list.add(ints[1]);
            list = graph.computeIfAbsent(ints[1], o -> new ArrayList<>());
            list.add(ints[0]);
        }
        return graph;
    }

    public static int makeConnected_02(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        UF uf = new UF(n);
        for (int[] ints : connections) {
            uf.union(ints[0], ints[1]);
        }
        return uf.s - 1;
    }

    static class UF {
        int[] uf;
        int s;

        public UF(int size) {
            uf = new int[size];
            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }
            s = size;
        }

        public void union(int p, int q) {
            p = find(p);
            q = find(q);
            if (p != q) {
                uf[q] = p;
                s--;
            }
        }

        public int find(int p) {
            return uf[p] == p ? p : (uf[p] = find(uf[p]));
        }
    }
}
