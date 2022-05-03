package leetcode;

import java.util.*;

/**
 * @description: A tree is an undirected graph in which any two vertices are connected by exactly one path.
 * In other words, any connected graph without simple cycles is a tree.
 * <p>
 * Given a tree of n nodes labelled from 0 to n - 1,
 * and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree,
 * you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h.
 * Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 * <p>
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * <p>
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 * @author: LISHUAI
 * @createDate: 2022/4/4 11:38
 * @version: 1.0
 */

public class LeetCode_310 {

    public static void main(String[] args) {
//        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
//        int n = 4;
//        int[][] edges = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
//        int n = 6;
//        int[][] edges = {};
//        int n = 1;
//        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}};
//        int n = 6;
//        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}};
//        int n = 7;
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {2, 4}, {0, 5}, {1, 6}, {2, 7}, {6, 8}, {3, 9}, {3, 10}, {8, 11}, {2, 12}};
        int n = 13;
        List<Integer> minHeightTrees = findMinHeightTrees_02(n, edges);
        for (int i : minHeightTrees) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("-----------");
        List<Integer> minHeightTrees_02 = findMinHeightTrees(n, edges);
        for (int i : minHeightTrees_02) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<List<Integer>> graph = makeGraph(edges, n);
//        for (int i = 0; i < n; i++) {
//            System.out.print(i + " : " + graph.get(i).size() + ", ");
//            for (int k : graph.get(i)) {
//                System.out.print(k + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------");
        List<Integer> ans = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE, pop, push, size, height, tmp, cur;
        int len = n + 1;
        int[] queue = new int[len];
        boolean[] bs = new boolean[n];


        for (int i = 0; i < n; i++) {

            Arrays.fill(bs, false);
            height = 0;
            push = -1;
            pop = -1;
            tmp = 1;
            queue[(++push) % len] = i;
            bs[i] = true;

            while (pop != push) {
                height++;
                size = 0;
                for (int j = 0; j < tmp; j++) {
                    cur = queue[(++pop) % len];
//                    pop = ++pop % len;
                    for (int k : graph.get(cur)) {
                        if (!bs[k]) {
                            queue[(++push) % len] = k;
//                            push = ++push % len;
                            bs[k] = true;
                            size++;
                        }
                    }
                }

                tmp = size;
            }
            if (minHeight > height) {
                minHeight = height;
                ans.clear();
                ans.add(i);
            } else if (minHeight == height) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static List<Integer> findMinHeightTrees_02(int n, int[][] edges) {
        if (n == 1) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            return list;
        }
        List<List<Integer>> graph = makeGraph(edges, n);
        for (int i = 0; i < n; i++) {
            System.out.print(i + " : " + graph.get(i).size() + ", ");
            for (int k : graph.get(i)) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
        return findMinHeightTreesProcess(graph);
    }

    public static List<Integer> findMinHeightTreesProcess(List<List<Integer>> graph) {

        int size, n = graph.size();
        Queue<Integer> queue = new ArrayDeque<>(n);
        int[] edgesSize = new int[n];
        for (int i = 0; i < graph.size(); i++) {
            edgesSize[i] = graph.get(i).size();
            if (graph.get(i).size() == 1) {
                queue.add(i);
                edgesSize[i]--;
            }

        }

        while (!queue.isEmpty() && n > 2) {

            size = queue.size();
            n -= size;
            for (int i = 0; i < size; i++) {
                int j = queue.poll();
                for (int k : graph.get(j)) {
                    edgesSize[k]--;
                    if (edgesSize[k] == 1) {
                        queue.add(k);
                    }
                }
            }

        }

        return new ArrayList<>(queue);
    }

    private static List<List<Integer>> makeGraph(int[][] edges, int n) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] ints : edges) {
            graph.get(ints[0]).add(ints[1]);
            graph.get(ints[1]).add(ints[0]);
        }
        return graph;
    }
}









