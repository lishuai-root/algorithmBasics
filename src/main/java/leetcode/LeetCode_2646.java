package leetcode;

import java.util.*;

/**
 * @description: There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 * <p>
 * Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith node.
 * <p>
 * The price sum of a given path is the sum of the prices of all nodes lying on that path.
 * <p>
 * Additionally, you are given a 2D integer array trips, where trips[i] = [starti, endi] indicates that you start the ith trip from the node starti and travel to the node endi by any path you like.
 * <p>
 * Before performing your first trip, you can choose some non-adjacent nodes and halve the prices.
 * <p>
 * Return the minimum total price sum to perform all the given trips.
 * @author: LISHUAI
 * @createDate: 2023/4/20 20:06
 * @version: 1.0
 */

public class LeetCode_2646 {

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{2, 0}, {3, 1}, {1, 0}, {0, 4}};
        int[] price = {2, 16, 4, 16, 6};
        int[][] trips = {{4, 3}};
        int i = minimumTotalPrice(n, edges, price, trips);
        System.out.println(i);
    }

    public static int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        List<Set<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] priceA = new int[n];
        int[] priceB = new int[n];
        int[] exists = new int[n];
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(0);
        exists[0] = 1;
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int cur = list.removeFirst();
                priceA[cur] = price[cur];
                priceB[cur] = price[cur] >>> 1;
                Set<Integer> set = graph.get(cur);
                for (int next : set) {
                    if (exists[next] == 0) {
                        list.addLast(next);
                        exists[next] = 1;
                    }
                }
            }
            int[] t = priceA;
            priceA = priceB;
            priceB = t;
        }

        int ansA = 0, ansB = 0;
        for (int[] trip : trips) {
            int start = trip[0];
            int end = trip[1];
            ansA += query(graph, priceA, start, end);
            ansB += query(graph, priceB, start, end);
        }
        return Math.min(ansA, ansB);
    }

    private static int query(List<Set<Integer>> graph, int[] price, int start, int end) {
        int ans = 0;
        int[] ex = new int[price.length];
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        ex[start] = 1;
        queue.offer(new int[]{start, price[start]});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == end) {
                ans = cur[1];
                break;
            }
            Set<Integer> set = graph.get(cur[0]);
            for (int next : set) {
                if (ex[next] == 0) {
                    ex[next] = 1;
                    queue.offer(new int[]{next, cur[1] + price[next]});
                }
            }
        }
        return ans;
    }

    public int minimumTotalPrice_02(int n, int[][] edges, int[] price, int[][] trips) {
        List<Integer>[] tree = buildTree(n, edges);

        int[] visitCount = new int[n];
        for (int[] trip : trips) {
            travel(trip[0], -1, trip[1], tree, visitCount);
        }

        int[] cost = new int[n];
        int[][] calcualted = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(calcualted[i], -1);
            cost[i] = price[i] * visitCount[i];
        }

        return minPrice(trips[0][0], -1, tree, 1, cost, calcualted);
    }

    private int minPrice(int node, int parent, List<Integer>[] tree, int canHalf, int[] cost, int[][] calculated) {
        if (calculated[node][canHalf] != -1) {
            return calculated[node][canHalf];
        }

        int minPrice;

        int noHalfCurrentNodePrice = cost[node];
        for (int next : tree[node]) {
            if (next != parent) {
                noHalfCurrentNodePrice += minPrice(next, node, tree, 1, cost, calculated);
            }
        }

        int halfCurrentNodePrice = Integer.MAX_VALUE;
        if (canHalf == 1) {
            halfCurrentNodePrice = cost[node] / 2;

            for (int next : tree[node]) {
                if (next != parent) {
                    halfCurrentNodePrice += minPrice(next, node, tree, 0, cost, calculated);
                }
            }
        }

        calculated[node][canHalf] = Math.min(noHalfCurrentNodePrice, halfCurrentNodePrice);

        return calculated[node][canHalf];
    }

    private boolean travel(int node, int parent, int target, List<Integer>[] tree, int[] visitCount) {
        if (node == target) {
            visitCount[node]++;
            return true;
        }

        boolean found = false;
        for (int next : tree[node]) {
            if (parent != next) {
                found |= travel(next, node, target, tree, visitCount);
            }

            if (found) {
                break;
            }
        }

        if (found) {
            visitCount[node]++;
        }

        return found;
    }

    private List<Integer>[] buildTree(int n, int[][] edges) {
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        return tree;
    }
}
