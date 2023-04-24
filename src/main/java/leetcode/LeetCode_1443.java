package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
 * <p>
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
 * @author: LISHUAI
 * @createDate: 2023/1/11 22:28
 * @version: 1.0
 */

public class LeetCode_1443 {

    public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> graph = makeGraph(edges);
        boolean[] bls = new boolean[n];
        return minTimeProcess(graph, 0, hasApple, bls);
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

    private static int minTimeProcess(Map<Integer, List<Integer>> graph, int cur, List<Boolean> hasApple, boolean[] bls) {
        if (bls[cur]) {
            return 0;
        }
        bls[cur] = true;
        int ans = 0;
        List<Integer> list = graph.get(cur);
        for (int next : list) {
            int p = 0;
            if (!bls[next] && hasApple.get(next)) {
                p = 2;
            }
            int q = minTimeProcess(graph, next, hasApple, bls);
            if (q != 0 || p != 0) {
                q += 2;
            }
            ans += q;
        }
        return ans;
    }
}
