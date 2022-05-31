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
        List<List<Integer>> graph = makeGraph(n, edges);
        boolean[] bls = new boolean[n];
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            bls[i] = true;
            ans[i] = sumOfDistancesInTreeProcess(graph, i, 0, bls);
            bls[i] = false;
        }
        return ans;
    }

    private static int sumOfDistancesInTreeProcess(List<List<Integer>> graph, int cur, int size, boolean[] bls) {
        List<Integer> list = graph.get(cur);
        if (list == null || list.isEmpty()) {
            return size;
        }
        int ans = size;
        for (int next : list) {
            if (!bls[next]) {
                bls[next] = true;
                ans += sumOfDistancesInTreeProcess(graph, next, size + 1, bls);
                bls[next] = false;
            }
        }
        return ans;
    }

    private static List<List<Integer>> makeGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
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
