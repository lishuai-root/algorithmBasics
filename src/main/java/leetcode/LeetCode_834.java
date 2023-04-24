package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 * <p>
 * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 * <p>
 * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
 * @author: LISHUAI
 * @createDate: 2022/5/24 22:35
 * @version: 1.0
 */

public class LeetCode_834 {

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        int n = 6;
        int[] ints = sumOfDistancesInTree(n, edges);
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[] ans = new int[n];
        int[] child = new int[n];
        boolean[] bls = new boolean[n];
        List<List<Integer>> graph = makeGraph(n, edges);
        computePath(graph, 0, ans, child, 0, bls);
        sumOfDistancesInTreeProcess(graph, ans, child, child[0], 0, 0, bls);
        return ans;
    }

    private static void sumOfDistancesInTreeProcess(List<List<Integer>> graph, int[] ans, int[] child, int all, int cur, int size, boolean[] bls) {
        if (bls[cur]) {
            return;
        }
        bls[cur] = true;
        List<Integer> list = graph.get(cur);
        if (!list.isEmpty()) {
            for (int next : list) {
                int ns = size + (ans[cur] - ans[next]) + (all - child[next]) - child[next];
                sumOfDistancesInTreeProcess(graph, ans, child, all, next, ns, bls);
            }
        }
        ans[cur] += size;
    }

    private static int computePath(List<List<Integer>> graph, int cur, int[] ans, int[] child, int size, boolean[] bls) {
        if (bls[cur]) {
            return ans[cur];
        }
        List<Integer> list = graph.get(cur);
        bls[cur] = true;
        int path = 0, ch = 0;
        for (int next : list) {
            path += computePath(graph, next, ans, child, size + 1, bls);
            ch += child[next];
        }
        bls[cur] = false;
        ans[cur] = path - (ch * size);
        child[cur] = ch + 1;
        return path + size;
    }

    private static List<List<Integer>> makeGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] ints : edges) {
            graph.get(ints[0]).add(ints[1]);
            graph.get(ints[1]).add(ints[0]);
        }
        return graph;
    }
}
