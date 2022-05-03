package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: There is a tree (i.e., a connected,
 * undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.
 * Each node has a value associated with it, and the root of the tree is node 0.
 * <p>
 * To represent this tree, you are given an integer array nums and a 2D array edges. Each nums[i] represents the ith node's value,
 * and each edges[j] = [uj, vj] represents an edge between nodes uj and vj in the tree.
 * <p>
 * Two values x and y are coprime if gcd(x, y) == 1 where gcd(x, y) is the greatest common divisor of x and y.
 * <p>
 * An ancestor of a node i is any other node on the shortest path from node i to the root. A node is not considered an ancestor of itself.
 * <p>
 * Return an array ans of size n, where ans[i] is the closest ancestor to node i such that nums[i] and nums[ans[i]] are coprime,
 * or -1 if there is no such ancestor.
 * <p>
 * In the above figure, each node's value is in parentheses.
 * - Node 0 has no coprime ancestors.
 * - Node 1 has only one ancestor, node 0. Their values are coprime (gcd(2,3) == 1).
 * - Node 2 has two ancestors, nodes 1 and 0. Node 1's value is not coprime (gcd(3,3) == 3), but node 0's
 * value is (gcd(2,3) == 1), so node 0 is the closest valid ancestor.
 * - Node 3 has two ancestors, nodes 1 and 0. It is coprime with node 1 (gcd(3,2) == 1), so node 1 is its
 * closest valid ancestor.
 * @author: LISHUAI
 * @createDate: 2022/1/19 19:25
 * @version: 1.0
 */

public class LeetCode_1766 {

    private static List<List<Integer>> cache;

    public static void main(String[] args) {

//        int[] nums = {5, 6, 10, 2, 3, 6, 15};
        int[] nums = {9, 16, 30, 23, 33, 35, 9, 47, 39, 46, 16, 38, 5, 49, 21, 44, 17, 1, 6, 37, 49, 15, 23, 46, 38, 9, 27, 3, 24, 1, 14, 17, 12, 23, 43, 38, 12, 4, 8, 17, 11, 18, 26, 22, 49, 14, 9};

//        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
        int[][] edges = {{17, 0}, {30, 17}, {41, 30}, {10, 30}, {13, 10}, {7, 13}, {6, 7}, {45, 10}, {2, 10}, {14, 2}, {40, 14}, {28, 40}, {29, 40}, {8, 29}, {15, 29}, {26, 15}, {23, 40}, {19, 23}, {34, 19}, {18, 23}, {42, 18}, {5, 42}, {32, 5}, {16, 32}, {35, 14}, {25, 35}, {43, 25}, {3, 43}, {36, 25}, {38, 36}, {27, 38}, {24, 36}, {31, 24}, {11, 31}, {39, 24}, {12, 39}, {20, 12}, {22, 12}, {21, 39}, {1, 21}, {33, 1}, {37, 1}, {44, 37}, {9, 44}, {46, 2}, {4, 46}};

        int[] coprimes = getCoprimes(nums, edges);

        for (int i : coprimes) {

            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(gcd(3, 3));
    }

    public static int[] getCoprimes(int[] nums, int[][] edges) {
        List<List<Integer>> graph = getGraph(edges, nums, nums.length);
        cache = new ArrayList<>(nums.length);
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            cache.add(new ArrayList<>());
        }
        for (int i = 0; i < nums.length; i++) {
//            List<Integer> list = getCoprimesProcess(i, graph);
//            int m = -1;
//            for (int j : list) {
//                if (gcd(nums[i], nums[j]) == 1) {
//                    m = j;
//                    break;
//                }
//            }
//            ans[i] = m;
            if (gcd(nums[i], nums[graph.get(i).get(0)]) == 1) {
                ans[i] = graph.get(i).get(0);
            } else {
                ans[i] = -1;
            }
        }
        return ans;
    }

    private static List<Integer> getCoprimesProcess(int node, List<List<Integer>> graph) {
        if (!cache.get(node).isEmpty()) {
            return cache.get(node);
        }

        List<Integer> list = new ArrayList<>();
        for (int i : graph.get(node)) {
            list.add(i);
            list.addAll(getCoprimesProcess(i, graph));
        }
        return list;
    }

    private static List<List<Integer>> getGraph(int[][] edges, int[] nums, int n) {
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> l = new ArrayList<>();
//            l.add(Integer.MAX_VALUE);
            graph.add(l);
        }

        for (int[] ints : edges) {

            if (graph.get(ints[1]).isEmpty()) {
                graph.get(ints[1]).add(ints[0]);
            } else {
                if (nums[graph.get(ints[1]).get(0)] > nums[ints[0]]) {
                    graph.get(ints[1]).add(0, ints[0]);
                }
            }

            if (graph.get(ints[0]).isEmpty()) {
                graph.get(ints[0]).add(ints[1]);
            } else {
                if (nums[graph.get(ints[0]).get(0)] > nums[ints[1]]) {
                    graph.get(ints[0]).add(0, ints[1]);
                }
            }


//            graph.get(ints[1]).add(0, Math.min(graph.get(ints[1]).get(0), ints[0]));
//            graph.get(ints[0]).add(0, Math.min(graph.get(ints[0]).get(0), ints[1]));
//            graph.get(ints[1]).add(ints[0]);
        }
        return graph;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
