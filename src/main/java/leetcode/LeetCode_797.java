package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
 * <p>
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 * @author: LISHUAI
 * @createDate: 2022/5/11 22:49
 * @version: 1.0
 */

public class LeetCode_797 {

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int[] ways = new int[graph.length];
        list.add(0);
        allPathsSourceTargetProcess(graph, list, ans, ways, 0, graph.length - 1);
        return ans;
    }

    private static void allPathsSourceTargetProcess(int[][] graph, List<Integer> list,
                                                    List<List<Integer>> ans, int[] ways, int cur, int end) {
        if (cur == end) {
            ans.add(new ArrayList<>(list));
            return;
        }

        int[] curs = graph[cur];
        for (int c : curs) {
            if ((ways[cur] & (1 << c)) == 0) {
                ways[cur] |= (1 << c);
                list.add(c);
                allPathsSourceTargetProcess(graph, list, ans, ways, c, end);
                list.remove(list.size() - 1);
                ways[cur] ^= (1 << c);
            }
        }
    }
}
