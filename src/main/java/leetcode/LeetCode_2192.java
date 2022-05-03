package leetcode;

import java.util.*;

/**
 * @description: You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG).
 * The nodes are numbered from 0 to n - 1 (inclusive).
 * <p>
 * You are also given a 2D integer array edges,
 * where edges[i] = [fromi, toi] denotes that there is a unidirectional edge from fromi to toi in the graph.
 * <p>
 * Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.
 * <p>
 * A node u is an ancestor of another node v if u can reach v via a set of edges.
 * @author: LISHUAI
 * @createDate: 2022/3/30 23:00
 * @version: 1.0
 */

public class LeetCode_2192 {

    private static List<List<Integer>> graph;
    private static List<List<Integer>> ansList;

    public static List<List<Integer>> getAncestors(int n, int[][] edges) {

        graph = makeGrahp(n, edges);
        ansList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ansList.add(new ArrayList<>(n));
        }
        boolean[] bl;
        for (int i = 0; i < n; i++) {
            if (ansList.get(i).isEmpty()) {
                Set<Integer> l = getAncestorsProcess(i);
                ansList.get(i).addAll(l);
            }
            ansList.get(i).sort(Comparator.comparingInt(Integer::valueOf));
        }
        return ansList;
    }


    private static Set<Integer> getAncestorsProcess(int node) {

        List<Integer> list = graph.get(node);
        Set<Integer> ans = new HashSet<>();

        for (int i : list) {

            if (!ansList.get(i).isEmpty()) {
                ans.addAll(ansList.get(i));
            } else {
                Set<Integer> l = getAncestorsProcess(i);
                ansList.get(i).addAll(l);
                ans.addAll(l);
            }
            ans.add(i);
        }

        return ans;
    }

    private static List<List<Integer>> makeGrahp(int n, int[][] edges) {
        List<List<Integer>> ans = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            ans.add(new ArrayList<>());
        }

        for (int[] ints : edges) {
            ans.get(ints[1]).add(ints[0]);
        }
        return ans;
    }
}
