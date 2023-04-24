package leetcode;

import java.util.*;

/**
 * @description: You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled from 1 to n.
 * <p>
 * You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional edge between nodes ai and bi. Notice that the given graph may be disconnected.
 * <p>
 * Divide the nodes of the graph into m groups (1-indexed) such that:
 * <p>
 * Each node in the graph belongs to exactly one group.
 * For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with index x, and bi belongs to the group with index y, then |y - x| = 1.
 * Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is impossible to group the nodes with the given conditions.
 * @author: LISHUAI
 * @createDate: 2023/3/13 20:44
 * @version: 1.0
 */

public class LeetCode_2493 {

    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) {
//        int n = 6;
//        int[][] edges = {{1, 2}, {1, 4}, {1, 5}, {2, 6}, {2, 3}, {4, 6}};
        int n = 24;
        int[][] edges = {{2, 13}, {7, 3}, {5, 3}, {21, 1}, {5, 1}, {4, 13}, {21, 19}, {7, 13}, {15, 3}, {21, 22}, {17, 19}, {23, 22}, {14, 13}};
//        int n = 26;
//        int[][] edges = {{9, 16}, {8, 3}, {20, 21}, {12, 16}, {14, 3}, {7, 21}, {22, 3}, {22, 18}, {11, 16}, {25, 4}, {2, 4}, {14, 21}, {23, 3}, {17, 3}, {2, 16}, {24, 16}, {13, 4}, {10, 21}, {7, 4}, {9, 18}, {14, 18}, {14, 4}, {14, 16}, {1, 3}, {25, 18}, {17, 4}, {1, 16}, {23, 4}, {2, 21}, {5, 16}, {24, 18}, {20, 18}, {19, 16}, {24, 21}, {9, 3}, {24, 3}, {19, 18}, {25, 16}, {19, 21}, {6, 3}, {26, 18}, {5, 21}, {20, 16}, {2, 3}, {10, 18}, {26, 16}, {8, 4}, {11, 21}, {23, 16}, {13, 16}, {25, 3}, {7, 18}, {19, 3}, {20, 4}, {26, 3}, {23, 18}, {15, 18}, {17, 18}, {10, 16}, {26, 21}, {23, 21}, {7, 16}, {8, 18}, {10, 4}, {24, 4}, {7, 3}, {11, 18}, {9, 4}, {26, 4}, {13, 21}, {22, 16}, {22, 21}, {20, 3}, {6, 18}, {9, 21}, {10, 3}, {22, 4}, {1, 18}, {25, 21}, {11, 4}, {1, 21}, {15, 3}, {1, 4}, {15, 16}, {2, 18}, {13, 3}, {8, 21}, {13, 18}, {11, 3}, {15, 21}, {8, 16}, {17, 16}, {15, 4}, {12, 3}, {6, 4}, {17, 21}, {5, 18}, {6, 16}, {6, 21}, {12, 4}, {19, 4}, {5, 3}, {12, 21}, {5, 4}};
        int i = magnificentSets(n, edges);
        System.out.println(i);
        System.out.println(magnificentSets_02(n, edges));
    }

    public static int magnificentSets_02(int n, int[][] edges) {
        int[] graphSize = new int[n + 1];
        Map<Integer, List<Integer>> graph = makeGraph(edges, n, graphSize);
        Queue<Integer> handler = new PriorityQueue<>((a, b) -> (graphSize[a] - graphSize[b]));
        for (int i = 1; i <= n; i++) {
            handler.offer(i);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] bls = new boolean[n + 1];
        Set<Integer> set = new HashSet<>();
        Set<Integer> temp = new HashSet<>();
        int ans = 0;
        while (!handler.isEmpty()) {
            int cur = handler.poll();
            if (!bls[cur]) {
                bls[cur] = true;
                queue.addLast(cur);
                while (!queue.isEmpty()) {
                    ans++;
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        int next = queue.poll();
                        if (graph.containsKey(next)) {
                            List<Integer> list = graph.get(next);
                            for (int k : list) {
                                if (temp.contains(k)) {
                                    return -1;
                                }
                                if (!bls[k]) {
                                    queue.addLast(k);
                                    set.add(k);
                                    bls[k] = true;
                                }
                            }
                        }
                    }
                    Set<Integer> t = set;
                    set = temp;
                    temp = t;
                    set.clear();
                }
            }
        }
        return ans;
    }

    public static int magnificentSets(int n, int[][] edges) {
        int[] graphSize = new int[n + 1];
        Map<Integer, List<Integer>> graph = makeGraph(edges, n, graphSize);
        int[] exists = new int[n + 1];
        Arrays.fill(exists, -1);
        int ans = 0;

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 1; i <= n; i++) {
            if (exists[i] == -1) {
                set.add(1);
                exists[i] = 1;
                if (graph.containsKey(i)) {
                    List<Integer> list = graph.get(i);
                    for (int next : list) {
                        if (exists[next] == -1) {
                            int p = magnificentSetsProcess(graph, next, 1, exists);
                            if (p == -1) {
                                return -1;
                            }
                            queue.offer(p);
                        }
                    }
//                    if (queue.size() == 1) {
//                        ans += queue.peek();
//                    } else if (queue.size() >= 2) {
//                        ans += queue.poll() + queue.poll() - 1;
//                    }
//                    queue.clear();

                } else {
//                    ans++;
                }
                ans += set.size();
                set.clear();
            }
        }
        return ans;
    }

    private static int magnificentSetsProcess(Map<Integer, List<Integer>> graph, int cur, int index, int[] exists) {
        if (!graph.containsKey(cur)) {
            exists[cur] = index + 1;
            set.add(index + 1);
            return exists[cur];
        }
        List<Integer> list = graph.get(cur);
        List<Integer> next = new ArrayList<>(list.size());
        int curIndex = -1;
        for (int k : list) {
            if (exists[k] == -1) {
                next.add(k);
            } else if (exists[k] != index) {
                int p = Math.max(exists[k], index) - Math.min(exists[k], index);
                if (p != 2) {
                    return -1;
                }
                int q = Math.min(exists[k], index) + 1;
                if (curIndex == -1) {
                    curIndex = q;
                } else if (curIndex != q) {
                    return -1;
                }
            }
        }
        if (curIndex == -1) {
            curIndex = index + 1;
        }
        exists[cur] = curIndex;
        set.add(curIndex);
        int ans = curIndex;
        for (int n : next) {
            int ps = magnificentSetsProcess(graph, n, curIndex, exists);
            if (ps == -1) {
                return -1;
            }
            ans = Math.max(ans, ps);
        }
        return ans;
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] edges, int n, int[] graphSize) {
        Map<Integer, List<Integer>> graph = new HashMap<>(n);
        for (int[] edge : edges) {
            List<Integer> list = graph.computeIfAbsent(edge[0], o -> new ArrayList<>());
            list.add(edge[1]);
            graphSize[edge[0]]++;
            list = graph.computeIfAbsent(edge[1], o -> new ArrayList<>());
            list.add(edge[0]);
            graphSize[edge[1]]++;
        }
        return graph;
    }
}
