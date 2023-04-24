package leetcode;

import java.util.*;

/**
 * @description: You are given a positive integer k. You are also given:
 * <p>
 * a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
 * a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
 * The two arrays contain integers from 1 to k.
 * <p>
 * You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once. The remaining cells should have the value 0.
 * <p>
 * The matrix should also satisfy the following conditions:
 * <p>
 * The number abovei should appear in a row that is strictly above the row at which the number belowi appears for all i from 0 to n - 1.
 * The number lefti should appear in a column that is strictly left of the column at which the number righti appears for all i from 0 to m - 1.
 * Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.
 * @author: LISHUAI
 * @createDate: 2023/4/11 22:57
 * @version: 1.0
 */

public class LeetCode_2392 {

    public static int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] rowReIndex = new int[k + 1];
        int[] colReIndex = new int[k + 1];
        boolean[] bls = new boolean[k + 1];
        Map<Integer, List<Integer>> rogGraph = makeGraph(rowConditions);
        int rowIndex = 0;
        for (int i = 1; i <= k; i++) {
            rowIndex = topoOrder(rogGraph, i, rowIndex, rowReIndex, bls);
            if (rowIndex == -1) {
                return new int[0][0];
            }
        }

        Arrays.fill(bls, false);
        Map<Integer, List<Integer>> colGraph = makeGraph(colConditions);
        int colIndex = 0;
        for (int i = 1; i <= k; i++) {
            colIndex = topoOrder(colGraph, i, colIndex, colReIndex, bls);
            if (colIndex == -1) {
                return new int[0][0];
            }
        }
        int[][] ans = new int[k][k];
        int index = 0;
        while (++index <= k) {
            ans[rowReIndex[index] - 1][colReIndex[index] - 1] = index;
        }
        return ans;
    }

    private static int topoOrder(Map<Integer, List<Integer>> graph, int cur, int index, int[] reIndex, boolean[] bls) {
        if (reIndex[cur] != 0) {
            return index;
        }
        if (bls[cur]) {
            return -1;
        }
        bls[cur] = true;
        if (graph.containsKey(cur)) {
            List<Integer> list = graph.get(cur);
            for (int next : list) {
                index = topoOrder(graph, next, index, reIndex, bls);
                if (index == -1) {
                    return -1;
                }
            }
        }

        reIndex[cur] = ++index;
        return index;
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> list;
        for (int[] edge : edges) {
            list = graph.computeIfAbsent(edge[1], o -> new ArrayList<>());
            list.add(edge[0]);
        }
        return graph;
    }
}
