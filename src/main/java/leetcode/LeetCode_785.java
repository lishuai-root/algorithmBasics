package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
 * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
 * More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
 * <p>
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
 * <p>
 * Return true if and only if it is bipartite.
 * @author: LISHUAI
 * @createDate: 2022/4/29 21:20
 * @version: 1.0
 */

public class LeetCode_785 {

    private static int allSize, excuteSize = 0;


    public static void main(String[] args) {
//        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
//        int[][] graph = {{4, 1}, {0, 2}, {1, 3}, {2, 4}, {3, 0}};
//        int[][] graph = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        int[][] graph = {{1, 7, 43, 45, 48, 57, 59, 61, 63, 84, 85, 87, 93, 95, 98}, {0, 17, 30, 33, 45, 51, 52, 66, 71, 74, 78, 87, 89, 96, 98}, {7, 18, 19, 25, 30, 40, 46, 55, 59, 71, 81, 82, 85}, {19, 29, 38, 39, 43, 46, 51, 64, 67, 77, 84, 92}, {7, 15, 18, 20, 29, 38, 49, 61, 83, 92, 98}, {16, 20, 32, 36, 40, 52, 68, 70, 97}, {9, 12, 13, 18, 24, 26, 33, 41, 54, 69, 71, 72, 79}, {0, 2, 4, 13, 15, 20, 28, 46, 56, 61, 69, 70, 79, 82, 89, 91}, {13, 25, 32, 42, 44, 47, 50, 52, 55, 59, 61, 73, 79, 87, 93}, {6, 11, 25, 29, 30, 36, 60, 74, 75, 79, 84, 85, 99}, {13, 28, 39, 50, 51, 55, 56, 78, 82, 90, 92}, {9, 17, 31, 37, 40, 62, 74, 81, 86, 88}, {6, 22, 32, 33, 41, 56, 61, 66, 68, 71, 72, 78, 87, 91}, {6, 7, 8, 10, 19, 21, 29, 32, 39, 58, 60, 65, 73, 80, 98}, {20, 34, 36, 37, 41, 52, 56, 57, 60, 71, 81, 83, 91, 94}, {4, 7, 17, 18, 28, 36, 54, 56, 59, 63, 81, 95, 98}, {5, 23, 49, 55, 58, 80, 82, 91, 99}, {1, 11, 15, 20, 24, 40, 64, 73, 92}, {2, 4, 6, 15, 20, 29, 53, 54, 71, 76, 85, 86, 88}, {2, 3, 13, 27, 34, 50, 66, 69, 70, 72, 88, 97, 98}, {4, 5, 7, 14, 17, 18, 21, 33, 35, 48, 54, 59, 62, 63, 69, 72, 81, 83, 89, 94, 99}, {13, 20, 29, 30, 33, 37, 45, 51, 58, 89, 93}, {12, 23, 34, 45, 47, 60, 64, 74, 83, 93, 95}, {16, 22, 36, 37, 56, 60, 62, 63, 68, 77, 78, 91, 99}, {6, 17, 31, 34, 51, 65, 66, 72, 81, 85, 99}, {2, 8, 9, 27, 38, 45, 53, 62, 64, 69, 76, 77, 86, 91, 94, 99}, {6, 28, 33, 39, 44, 45, 58, 67, 68, 83, 85, 86}, {19, 25, 28, 41, 49, 63, 66, 73, 81, 83, 86, 91}, {7, 10, 15, 26, 27, 33, 40, 73, 74, 77, 95}, {3, 4, 9, 13, 18, 21, 60, 64, 76, 81, 85}, {1, 2, 9, 21, 34, 38, 57, 59, 63, 69, 75, 76, 84, 92, 98}, {11, 24, 33, 41, 43, 50, 64, 79, 83}, {5, 8, 12, 13, 50, 56, 67, 78, 81, 82, 83, 94}, {1, 6, 12, 20, 21, 26, 28, 31, 36, 44, 46, 63, 64, 66, 76, 82, 91, 93, 94, 95, 98}, {14, 19, 22, 24, 30, 37, 51, 53, 55, 61, 89}, {20, 36, 51, 52, 64, 72, 74, 77, 78, 91}, {5, 9, 14, 15, 23, 33, 35, 45, 49, 53, 60, 79, 81}, {11, 14, 21, 23, 34, 44, 45, 51, 52, 62, 64, 84}, {3, 4, 25, 30, 57, 79, 80, 86, 87, 90, 91, 98, 99}, {3, 10, 13, 26, 43, 46, 56, 61, 66, 69, 75, 77, 81, 82, 96}, {2, 5, 11, 17, 28, 47, 52, 60, 67, 71, 72, 80, 84, 90, 91}, {6, 12, 14, 27, 31, 42, 46, 47, 48, 55, 59, 71, 75, 78, 93, 95, 96}, {8, 41, 48, 65, 66, 67, 74, 83, 93}, {0, 3, 31, 39, 49, 50, 58, 69, 73, 86, 88, 93, 97}, {8, 26, 33, 37, 45, 49, 53, 54, 57, 62, 77, 84, 86, 92}, {0, 1, 21, 22, 25, 26, 36, 37, 44, 50, 58, 65, 68, 75, 77, 82, 84, 92}, {2, 3, 7, 33, 39, 41, 54, 56, 71, 73, 75, 79, 81, 83, 84, 88, 99}, {8, 22, 40, 41, 49, 55, 57, 58, 61, 65, 66, 73, 80, 81, 82, 91, 97}, {0, 20, 41, 42, 60, 67, 78, 92, 93}, {4, 16, 27, 36, 43, 44, 47, 57, 59, 72}, {8, 10, 19, 31, 32, 43, 45, 56, 64, 72, 89, 90, 91, 92, 93, 95}, {1, 3, 10, 21, 24, 34, 35, 37, 76, 79, 85, 93, 97}, {1, 5, 8, 14, 35, 37, 40, 53, 61, 62, 71, 73, 83, 86, 99}, {18, 25, 34, 36, 44, 52, 54, 56, 61, 74, 76, 77, 84, 95}, {6, 15, 18, 20, 44, 46, 53, 56, 61, 63, 67, 72, 90, 91}, {2, 8, 10, 16, 34, 41, 47, 67, 73, 95}, {7, 10, 12, 14, 15, 23, 32, 39, 46, 50, 53, 54, 61, 63, 71, 72, 75, 76, 77, 82, 84}, {0, 14, 30, 38, 44, 47, 49, 60, 63, 66, 80, 81, 89}, {13, 16, 21, 26, 43, 45, 47, 84, 85}, {0, 2, 8, 15, 20, 30, 41, 49, 69, 75, 76, 81, 99}, {9, 13, 14, 22, 23, 29, 36, 40, 48, 57, 68, 71, 74, 76, 92, 94}, {0, 4, 7, 8, 12, 34, 39, 47, 52, 53, 54, 56, 71, 93}, {11, 20, 23, 25, 37, 44, 52, 64, 73, 78, 82, 83, 87, 91}, {0, 15, 20, 23, 27, 30, 33, 54, 56, 57, 87}, {3, 17, 22, 25, 29, 31, 33, 35, 37, 50, 62, 68, 77, 88}, {13, 24, 42, 45, 47, 68, 86}, {1, 12, 19, 24, 27, 33, 39, 42, 47, 57, 68, 69, 71, 76, 77, 87, 88, 92, 95}, {3, 26, 32, 40, 42, 48, 54, 55, 73, 77, 82, 84, 98}, {5, 12, 23, 26, 45, 60, 64, 65, 66, 70, 80, 87, 88, 97}, {6, 7, 19, 20, 25, 30, 39, 43, 59, 66, 71, 76, 81, 84}, {5, 7, 19, 68, 75, 81, 86, 88, 92, 93, 96}, {1, 2, 6, 12, 14, 18, 40, 41, 46, 52, 56, 60, 61, 66, 69, 74, 75, 84, 85, 86, 90}, {6, 12, 19, 20, 24, 35, 40, 49, 50, 54, 56, 76, 82, 92}, {8, 13, 17, 27, 28, 43, 46, 47, 52, 55, 62, 67, 76, 88, 92, 98}, {1, 9, 11, 22, 28, 35, 42, 53, 60, 71, 80, 87, 90, 96, 99}, {9, 30, 39, 41, 45, 46, 56, 59, 70, 71, 79, 81, 99}, {18, 25, 29, 30, 33, 51, 53, 56, 59, 60, 66, 69, 72, 73, 80, 87}, {3, 23, 25, 28, 35, 39, 44, 45, 53, 56, 64, 66, 67, 95, 97}, {1, 10, 12, 23, 32, 35, 41, 48, 62, 86, 90}, {6, 7, 8, 9, 31, 36, 38, 46, 51, 75}, {13, 16, 38, 40, 47, 57, 68, 74, 76, 96, 97}, {2, 11, 14, 15, 20, 24, 27, 29, 32, 36, 39, 46, 47, 57, 59, 69, 70, 75, 91}, {2, 7, 10, 16, 32, 33, 39, 45, 47, 56, 62, 67, 72, 96}, {4, 14, 20, 22, 26, 27, 31, 32, 42, 46, 52, 62, 84, 92, 95}, {0, 3, 9, 30, 37, 40, 44, 45, 46, 53, 56, 58, 67, 69, 71, 83, 91, 93}, {0, 2, 9, 18, 24, 26, 29, 51, 58, 71, 88}, {11, 18, 25, 26, 27, 38, 43, 44, 52, 65, 70, 71, 78, 90, 92, 99}, {0, 1, 8, 12, 38, 62, 63, 66, 68, 74, 76}, {11, 18, 19, 43, 46, 64, 66, 68, 70, 73, 85, 93}, {1, 7, 20, 21, 34, 50, 57}, {10, 38, 40, 50, 54, 71, 74, 78, 86, 92, 99}, {7, 12, 14, 16, 23, 25, 27, 33, 35, 38, 40, 47, 50, 54, 62, 81, 84, 96}, {3, 4, 10, 17, 30, 44, 45, 48, 50, 60, 66, 70, 72, 73, 83, 86, 90, 94, 99}, {0, 8, 21, 22, 33, 41, 42, 43, 48, 50, 51, 61, 70, 84, 88}, {14, 20, 25, 32, 33, 60, 92}, {0, 15, 22, 28, 33, 41, 50, 53, 55, 66, 77, 83}, {1, 39, 41, 70, 74, 80, 82, 91}, {5, 19, 43, 47, 51, 68, 77, 80}, {0, 1, 4, 13, 15, 19, 30, 33, 38, 67, 73, 99}, {9, 16, 20, 23, 24, 25, 38, 46, 52, 59, 74, 75, 86, 90, 92, 98}};

//        boolean b1 = isBipartite(graph);
//        System.out.println(b1);
        boolean b = isBipartite_03(graph);
        System.out.println(b);
        System.out.println(excuteSize);
        boolean b2 = Solution.isBipartite(graph);
        System.out.println(b2);
        System.out.println(Solution.size);
    }

    public static boolean isBipartite(int[][] graph) {
        if (graph.length == 1) {
            return false;
        }
        Set<String> set = new HashSet<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j : graph[i]) {
                set.add(Math.min(i, j) + "-" + Math.max(i, j));
            }
        }

        allSize = set.size();
//        System.out.println(allSize);
        int[] bls = new int[graph.length];
        bls[0]++;
        return isBipartiteProcess(graph, bls, 0, graph[0].length);
    }

    private static boolean isBipartiteProcess(int[][] graph, int[] bls, int node, int size) {
        if (size == allSize) {
            return true;
        }

        boolean ans = false;
        int[] cur = graph[node];
        for (int i : cur) {
            bls[i]++;
        }

        for (int i = 0; i < bls.length; i++) {
            if (bls[i] == 0 && !ans) {
                bls[i]++;
                ans = isBipartiteProcess(graph, bls, i, size + graph[i].length);
                bls[i]--;
            }
        }

        for (int i : cur) {
            bls[i]--;
        }

        return ans;
    }


    public static boolean isBipartite_02(int[][] graph) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j : graph[i]) {
                if (j > i) {
                    list.add(new int[]{i, j});
                }
            }
        }
        System.out.println("list size : " + list.size());
//        for (int[] ints : list) {
//            System.out.println(ints[0] + " - " + ints[1]);
//        }
        int[] left = new int[graph.length], right = new int[graph.length];
        return isBipartiteProcess(list, 0, left, right);
    }

    public static boolean isBipartite_03(int[][] graph) {
        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < graph.length; i++) {
//
//        }

        int[] left = new int[graph.length];
        int[] right = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (left[i] == 0 && right[i] == 0) {
                list.add(i);
                if (isBipartiteProcess(list, graph, left, right)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isBipartiteProcess(List<Integer> list, int[][] graph, int[] left, int[] right) {
        System.out.println(list.size());
        if (list.isEmpty()) {
            return true;
        }
        List<Integer> l = new ArrayList<>();
        for (int i : list) {
            left[i]++;
            for (int j : graph[i]) {
                excuteSize++;
                if (left[j] != 0) {
                    return false;
                }
                if (right[j] == 0) {
                    l.add(j);
                }
            }
        }
        return isBipartiteProcess(l, graph, right, left);
    }

    private static boolean isBipartiteProcess(List<int[]> graph, int index, int[] left, int[] right) {
//        System.out.println(index);
        excuteSize++;
        if (index >= graph.size()) {
            return true;
        }

        int[] cur = graph.get(index);
        boolean ans = false;

//        if ((left[cur[0]] > 0 && left[cur[1]] > 0) || (right[cur[0]] > 0 && right[cur[1]] > 0)) {
//            return ans;
//        }


        if (left[cur[1]] == 0 && right[cur[0]] == 0) {
            left[cur[0]]++;
            right[cur[1]]++;
            ans = isBipartiteProcess(graph, index + 1, left, right);
            left[cur[0]]--;
            right[cur[1]]--;
        }


        if (!ans) {

            if (left[cur[0]] == 0 && right[cur[1]] == 0) {
                right[cur[0]]++;
                left[cur[1]]++;
                ans = isBipartiteProcess(graph, index + 1, left, right);
                right[cur[0]]--;
                left[cur[1]]--;
            }

        }
        return ans;
    }

    static class Solution {
        public static int size = 0;

        public static boolean isBipartite(int[][] graph) {
            int len = graph.length;
            boolean[] set1 = new boolean[len];
            boolean[] set2 = new boolean[len];

            for (int i = 0; i < len; ++i) {
                if (set1[i] || set2[i]) {
                    continue;
                }
                if (!dfs(i, set1, set2, graph)) {
                    return false;
                }
            }
            return true;
        }

        private static boolean dfs(int index, boolean[] set1, boolean[] set2, int[][] graph) {
            size++;
            if (set1[index]) {
                return !set2[index];
            }
            set1[index] = true;
            for (int n : graph[index]) {
                if (!dfs(n, set2, set1, graph)) {
                    return false;
                }
            }
            return true;
        }
    }

}



