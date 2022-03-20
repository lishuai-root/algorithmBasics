package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description: You have an undirected, connected graph of n nodes labeled from 0 to n - 1.
 * You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.
 * <p>
 * Return the length of the shortest path that visits every node. You may start and stop at any node,
 * you may revisit nodes multiple times, and you may reuse edges.
 * @author: LISHUAI
 * @createDate: 2022/2/15 20:55
 * @version: 1.0
 */

public class LeetCode_847 {

    private static Set<Integer> set;

    public static void main(String[] args) {

//        int[][] graph = {{1, 2, 3, 4, 5, 6, 7, 8, 9}, {0, 2, 3, 4, 5, 6, 7, 8, 9}, {0, 1, 3, 4, 5, 6, 7, 8, 9}, {0, 1, 2, 4, 5, 6, 7, 8, 9}, {0, 1, 2, 3, 5, 6, 7, 8, 9}, {0, 1, 2, 3, 4, 6, 7, 8, 9}, {0, 1, 2, 3, 4, 5, 7, 8, 9}, {0, 1, 2, 3, 4, 5, 6, 8, 9}, {0, 1, 2, 3, 4, 5, 6, 7, 9, 10}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 11}, {8}, {9}};
//        int[][] graph = {{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}};
        int[][] graph = {{1, 2, 3}, {0}, {0}, {0}};
        int i = shortestPathLength(graph);

        System.out.println(i);
    }

    public static int shortestPathLength(int[][] graph) {
        return shortestPathLengthProcess(graph);
    }

    private static int shortestPathLengthProcess(int[][] graph) {

        int start = Integer.MAX_VALUE, ansPath = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] g : graph) {
            start = Math.min(start, g.length);
        }
        boolean[][] bl = new boolean[graph.length][graph.length];
//        for (int i = 0; i < graph.length; i++) {
//            if (graph[i].length == start) {
//                set = new HashSet<>();
//                map.put(i, 1);
//                ansPath = Math.min(ansPath, doShortestPathLengthProcess(graph, bl, i, 0, map));
//                map.remove(i);
//            }
//        }
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].length == start) {

                ansPath = Math.min(ansPath, doShortestPathLengthProcess_02(graph, i));
            }
        }

        return ansPath;
    }

    private static int doShortestPathLengthProcess(int[][] graph, boolean[][] bl, int cur, int rows, Map<Integer, Integer> map) {

        if (map.size() == graph.length) {
            return rows;
        }

        int minPath = Integer.MAX_VALUE;
        int[] curRows = graph[cur];

        for (int row : curRows) {

            if (cur != row && !bl[cur][row] && !set.contains(row)) {
                bl[cur][row] = true;
                map.put(cur, map.getOrDefault(cur, 0) + 1);
                minPath = Math.min(minPath, doShortestPathLengthProcess(graph, bl, row, rows + 1, map));
                bl[cur][row] = false;
                map.put(cur, map.get(cur) - 1);
                if (map.get(cur) == 0) {
                    map.remove(cur);
                }

            }
        }

        return minPath;
    }

    private static int doShortestPathLengthProcess_02(int[][] graph, int start) {

        int[] stack = new int[graph.length];
        boolean[] bl = new boolean[graph.length];
        int index = -1;
        stack[++index] = start;
        bl[start] = true;
        int cur, rows = -1, size = 0;

        while (index != -1) {

            cur = stack[index--];
            size++;
            rows++;


            int[] curRows = graph[cur];

            for (int c : curRows) {
                if (!bl[c] || curRows.length == 1) {
                    stack[++index] = c;
                    bl[c] = true;
                }
            }
        }

        return rows + graph.length - size;
    }
}



















