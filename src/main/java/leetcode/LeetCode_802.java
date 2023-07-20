package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
 * <p>
 * A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
 * <p>
 * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 * @author: LiShuai
 * @createDate: 2023/7/12 22:49
 * @version: 1.0
 */

public class LeetCode_802 {

    public static void main(String[] args) {
        int[][] graph = {{0}, {2, 3, 4}, {3, 4}, {0, 4}, {}};
        List<Integer> list = eventualSafeNodes(graph);
        System.out.println(list.toString());
    }

    public static List<Integer> eventualSafeNodes(int[][] graph) {
//        Map<Integer, List<Integer>> gh = makeGraph(graph);
        int len = graph.length;
        boolean[] exists = new boolean[len];
        int[] safaNodes = new int[len];
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int p = eventualSafeNodesProcess(graph, i, exists, safaNodes);
            safaNodes[i] = p;
            if (p == 2) {
                ans.add(i);
            }
        }
        return ans;
    }

    private static int eventualSafeNodesProcess(int[][] graph, int cur, boolean[] exists, int[] safeNodes) {
        if (graph[cur].length == 0) {
            return 2;
        }
        if (exists[cur]) {
            return 1;
        }
        if (safeNodes[cur] != 0) {
            return safeNodes[cur];
        }
        exists[cur] = true;
        int ans = 0;
        for (int next : graph[cur]) {
            ans |= eventualSafeNodesProcess(graph, next, exists, safeNodes);
            if ((ans & 1) != 0) {
                break;
            }
        }
        exists[cur] = false;
        safeNodes[cur] = ans;
        return ans;
    }

    private static int eventualSafeNodesProcess(Map<Integer, List<Integer>> graph, int cur, boolean[] exists, int[] safeNodes) {
        List<Integer> list = graph.get(cur);
        if (list.isEmpty()) {
            return 2;
        }
        if (exists[cur]) {
            return 1;
        }
        if (safeNodes[cur] != 0) {
            return safeNodes[cur];
        }
        exists[cur] = true;
        int ans = 0;

        for (int next : list) {
            if (cur == next) {
                ans = 1;
            } else {
                ans |= eventualSafeNodesProcess(graph, next, exists, safeNodes);
            }
            if ((ans & 1) != 0) {
                break;
            }
        }
        exists[cur] = false;
        safeNodes[cur] = ans;
        return ans;
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] graph) {
        Map<Integer, List<Integer>> gh = new HashMap<Integer, List<Integer>>();
        List<Integer> list;
        for (int i = 0; i < graph.length; i++) {
            int[] ints = graph[i];
            list = gh.computeIfAbsent(i, o -> new ArrayList<>());
            for (int next : ints) {
                list.add(next);
            }
        }
        return gh;
    }
}
