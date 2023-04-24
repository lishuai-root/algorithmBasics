package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: You are given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label which is a lower-case character given in the string labels (i.e. The node with the number i has the label labels[i]).
 * <p>
 * The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi in the tree.
 * <p>
 * Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same label as node i.
 * <p>
 * A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
 * @author: LISHUAI
 * @createDate: 2023/1/12 22:37
 * @version: 1.0
 */

public class LeetCode_1519 {

    public static void main(String[] args) {
        System.out.println(Integer.numberOfLeadingZeros(2));
    }

    public static int[] countSubTrees(int n, int[][] edges, String labels) {
        Map<Integer, List<Integer>> graph = makeGraph(edges);
        int[] ans = new int[n];
        boolean[] bls = new boolean[n];
        countSubTreesProcess(graph, labels, 0, ans, bls);
        return ans;
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> list;
        for (int[] ints : edges) {
            list = graph.computeIfAbsent(ints[0], o -> new ArrayList<>());
            list.add(ints[1]);
            list = graph.computeIfAbsent(ints[1], o -> new ArrayList<>());
            list.add(ints[0]);
        }
        return graph;
    }

    private static int[] countSubTreesProcess(Map<Integer, List<Integer>> graph, String labels, int cur, int[] ans, boolean[] bls) {
        if (bls[cur]) {
            return null;
        }
        bls[cur] = true;
        int[] count = new int[26];
        List<Integer> list = graph.get(cur);
        for (int next : list) {
            int[] child = countSubTreesProcess(graph, labels, next, ans, bls);
            if (child != null) {
                for (int i = 0; i < count.length; i++) {
                    count[i] += child[i];
                }
            }
        }
        int c = labels.charAt(cur) - 'a';
        count[c]++;
        ans[cur] = count[c];
        return count;
    }
}
