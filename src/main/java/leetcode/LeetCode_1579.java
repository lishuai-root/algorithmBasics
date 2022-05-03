package leetcode;

import java.util.*;

/**
 * @description: Alice and Bob have an undirected graph of n nodes and 3 types of edges:
 * <p>
 * Type 1: Can be traversed by Alice only.
 * Type 2: Can be traversed by Bob only.
 * Type 3: Can by traversed by both Alice and Bob.
 * Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi,
 * find the maximum number of edges you can remove so that after removing the edges,
 * the graph can still be fully traversed by both Alice and Bob.
 * The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.
 * <p>
 * Return the maximum number of edges you can remove, or return -1 if it's impossible for the graph to be fully traversed by Alice and Bob.
 * @author: LISHUAI
 * @createDate: 2022/4/26 20:29
 * @version: 1.0
 */

public class LeetCode_1579 {
    private static int allSize, BobSize, AliceSize, allEdgeSize, N;
    private static Map<Integer, List<int[]>> allEdges, BobEdges, AliceEdges;
    private static int allMax, BobMax, AliceMax;
    private static boolean[] allBs, BobBs, AliceBs;
    private static Set<Integer> allSet, BobSet, AliceSet;
    private static Set<String> allEdgesSet;

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}};
//        int n = 4;
//        int[][] edges = {{3, 1, 2}, {3, 2, 3}, {1, 1, 4}, {2, 1, 4}};
//        int n = 4;
//        int[][] edges = {{3, 2, 3}, {1, 1, 2}, {2, 3, 4}};
//        int n = 2;
//        int[][] edges = {{1, 1, 2}, {2, 1, 2}, {3, 1, 2}};
//        int n = 2;
//        int[][] edges = {{1, 1, 2}, {2, 1, 2}};
//        int n = 4;
//        int[][] edges = {{3, 1, 2}, {3, 3, 4}, {1, 1, 3}, {2, 2, 4}};
//        int i = maxNumEdgesToRemove_T(n, edges);
//        System.out.println(i);

        Solution solution = new Solution();
        int i1 = solution.maxNumEdgesToRemove(n, edges);
        System.out.println(i1);

    }

    public static int maxNumEdgesToRemove(int n, int[][] edges) {
        allBs = new boolean[n + 1];
        BobBs = new boolean[n + 1];
        AliceBs = new boolean[n + 1];
        makeGraph(edges);
        allSet = new HashSet<>();
        BobSet = new HashSet<>();
        AliceSet = new HashSet<>();
        allEdgesSet = new HashSet<>();

        for (int i : allEdges.keySet()) {
            System.out.print(i + " : ");
            for (int[] j : allEdges.get(i)) {
                System.out.print(j[0] + " ");
            }
            System.out.println();
        }

//        allBs[allMax] = true;
        for (int curNode : allEdges.keySet()) {
            if (!allBs[curNode]) {
                allBs[curNode] = true;
                allSize += dfs(allEdges, allMax, allBs, allSet);
            }
        }


        BobBs[BobMax] = true;
        BobSize = dfs(BobEdges, BobMax, BobBs, BobSet);
        AliceBs[AliceMax] = true;
        AliceSize = dfs(AliceEdges, AliceMax, AliceBs, AliceSet);
        if (BobSet.size() != n || AliceSet.size() != n) {
            return -1;
        }
        return edges.length - allEdgeSize - BobSize - AliceSize;
    }

    public static int maxNumEdgesToRemove_T(int n, int[][] edges) {
        allBs = new boolean[n + 1];
        BobBs = new boolean[n + 1];
        AliceBs = new boolean[n + 1];
        makeGraph(edges);
        allSet = new HashSet<>();
        BobSet = new HashSet<>();
        AliceSet = new HashSet<>();
        allEdgesSet = new HashSet<>();
        BobSize = Integer.MAX_VALUE;
        AliceSize = Integer.MAX_VALUE;
        N = n;

        BobSet.add(BobMax);
        BobBs[BobMax] = true;
        for (int[] ints : BobEdges.get(BobMax)) {
            BobBs[ints[0]] = true;
            allSize = 0;
            dfs_T(BobEdges, ints[0], BobBs, BobSet, 1);
            BobBs[ints[0]] = false;
            BobSize = Math.min(BobSize, allSize);
//            if (ints[1] != 3) {
//                BobSize++;
//            }
        }
        AliceSet.add(AliceMax);
        AliceBs[AliceMax] = true;
        for (int[] ints : AliceEdges.get(AliceMax)) {
            AliceBs[ints[0]] = true;
            allSize = 0;
            dfs_T(AliceEdges, ints[0], AliceBs, AliceSet, 1);
            AliceBs[ints[0]] = false;
            AliceSize = Math.min(BobSize, allSize);
//            if (ints[1] != 3) {
//                AliceSize++;
//            }
        }
//        BobBs[BobMax] = true;
//        BobSize = dfs_T(BobEdges, BobMax, BobBs, BobSet);
//        AliceBs[AliceMax] = true;
//        AliceSize = dfs_T(AliceEdges, AliceMax, AliceBs, AliceSet);
        if (BobSet.size() != n || AliceSet.size() != n) {
            return -1;
        }
        return edges.length - allEdgeSize - BobSize - AliceSize;
    }

    private static int dfs_T(Map<Integer, List<int[]>> graph, int cur, boolean[] bs, Set<Integer> set, int size) {
        if (!graph.containsKey(cur)) {
            if (size != N) {
                allSize = Integer.MAX_VALUE;
            }
        }
        set.add(cur);
        List<int[]> list = graph.get(cur);
        int ans = Integer.MAX_VALUE;

        for (int[] ints : list) {
            if (!bs[ints[0]]) {
                bs[ints[0]] = true;
                if (ints[1] != 3) {
                    ++allSize;
                }
                ans = Math.min(ans, dfs_T(graph, ints[0], bs, set, size + 1));
                bs[ints[0]] = false;
            }
        }
        return ans;
    }

    private static int dfs(Map<Integer, List<int[]>> graph, int cur, boolean[] bs, Set<Integer> set) {
        if (!graph.containsKey(cur)) {
            return 0;
        }
        set.add(cur);
        List<int[]> list = graph.get(cur);
        int ans = 0;
        for (int[] ints : list) {
            if (!bs[ints[0]]) {
                if (!allBs[ints[0]]) {
                    ans++;
                }
                bs[ints[0]] = true;
//                if (ints[1] == 3) {
//                    allEdgesSet.add(ints[0] + "-" + cur);
//                    allEdgesSet.add(cur + "-" + ints[0]);
//                }
                ans += dfs(graph, ints[0], bs, set);
            }
        }
        return ans;
    }

    private static void makeGraph(int[][] edges) {
        allEdges = new HashMap<>();
        BobEdges = new HashMap<>();
        AliceEdges = new HashMap<>();
        allMax = -1;
        BobMax = -1;
        AliceMax = -1;
        allEdgeSize = 0;

        for (int[] ints : edges) {
            List<int[]> list;
            if (ints[0] == 3) {
                ++allEdgeSize;
                if ((list = allEdges.get(ints[1])) == null) {
                    list = new ArrayList<>();
                    allEdges.put(ints[1], list);
                }
                list.add(new int[]{ints[2], ints[0]});
                if (allMax == -1 || allEdges.get(allMax).size() < allEdges.get(ints[1]).size()) {
                    allMax = ints[1];
                }

                if ((list = allEdges.get(ints[2])) == null) {
                    list = new ArrayList<>();
                    allEdges.put(ints[2], list);
                }
                list.add(new int[]{ints[1], ints[0]});
                if (allMax == -1 || allEdges.get(allMax).size() < allEdges.get(ints[2]).size()) {
                    allMax = ints[2];
                }
            }
            if (ints[0] == 2 || ints[0] == 3) {
                if ((list = BobEdges.get(ints[1])) == null) {
                    list = new ArrayList<>();
                    BobEdges.put(ints[1], list);
                }
                list.add(new int[]{ints[2], ints[0]});
                if (BobMax == -1 || BobEdges.get(BobMax).size() < BobEdges.get(ints[1]).size()) {
                    BobMax = ints[1];
                }
                if ((list = BobEdges.get(ints[2])) == null) {
                    list = new ArrayList<>();
                    BobEdges.put(ints[2], list);
                }
                list.add(new int[]{ints[1], ints[0]});

                if (BobMax == -1 || BobEdges.get(BobMax).size() < BobEdges.get(ints[2]).size()) {
                    BobMax = ints[2];
                }

            }
            if (ints[0] == 1 || ints[0] == 3) {
                if ((list = AliceEdges.get(ints[1])) == null) {
                    list = new ArrayList<>();
                    AliceEdges.put(ints[1], list);
                }
                list.add(new int[]{ints[2], ints[0]});
                if (AliceMax == -1 || AliceEdges.get(AliceMax).size() < AliceEdges.get(ints[1]).size()) {
                    AliceMax = ints[1];
                }
                if ((list = AliceEdges.get(ints[2])) == null) {
                    list = new ArrayList<>();
                    AliceEdges.put(ints[2], list);
                }
                list.add(new int[]{ints[1], ints[0]});
                if (AliceMax == -1 || AliceEdges.get(AliceMax).size() < AliceEdges.get(ints[2]).size()) {
                    AliceMax = ints[2];
                }
            }
        }
    }
}

class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] counts = new int[3];
        for (int i = 0; i < n; i++) parent[i] = i;
        handleType(edges, counts, parent, 3, n);
        if (counts[2] == n - 1) return edges.length - counts[2];
        counts[0] = counts[2];
        counts[1] = counts[2];
        int[] copy = parent.clone();

        // handle type 1
        handleType(edges, counts, parent, 1, n);
        if (counts[0] < n - 1) return -1;

        // handle type 2
        handleType(edges, counts, copy, 2, n);
        if (counts[1] < n - 1) return -1;

        // return edges.length - counts[2] - (counts[0] - counts[2]) - (counts[1] - counts[2]);
        return edges.length + counts[2] - counts[0] - counts[1];
    }

    private void handleType(int[][] edges, int[] counts, int[] parent, int type, int n) {
        for (int[] edge : edges) {
            if (type == edge[0]) {
                int a = edge[1] - 1;
                int b = edge[2] - 1;
                int parentA = find(a, parent);
                int parentB = find(b, parent);
                if (parentA == parentB) continue;
                parent[parentA] = parentB;
                counts[type - 1]++;
                if (counts[type - 1] == n - 1) return;
            }
        }
    }

    private int find(int cur, int[] parent) {
        if (parent[cur] == cur) return cur;
        return parent[cur] = find(parent[cur], parent);
    }
}