package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.
 * <p>
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
 * @author: LISHUAI
 * @createDate: 2022/12/21 21:29
 * @version: 1.0
 */

public class LeetCode_886 {

    public static boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = makeGraph(dislikes);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (dp[i] == 0) {
                boolean b = possibleBipartitionProcess(graph, dp, i, 1);
                if (!b) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean possibleBipartitionProcess(Map<Integer, List<Integer>> graph, int[] dp, int cur, int tag) {
        dp[cur] = tag;
        List<Integer> list = graph.get(cur);
        if (list != null) {
            for (int next : list) {
                if (dp[next] == 0 && !possibleBipartitionProcess(graph, dp, next, tag + 1)) {
                    return false;
                } else if ((dp[next] & 1) == (tag & 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> list;
        for (int[] ints : dislikes) {
            list = graph.computeIfAbsent(ints[0], o -> new ArrayList<>());
            list.add(ints[1]);
            list = graph.computeIfAbsent(ints[1], o -> new ArrayList<>());
            list.add(ints[0]);
        }
        return graph;
    }
}
