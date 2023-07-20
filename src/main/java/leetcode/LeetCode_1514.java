package leetcode;

import java.util.*;

/**
 * @description: You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 * <p>
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 * <p>
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 * @author: LiShuai
 * @createDate: 2023/6/29 22:27
 * @version: 1.0
 */

public class LeetCode_1514 {

    public static void main(String[] args) {
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb = {0.5, 0.5, 0.2};
        int start = 0, end = 2;
        double v = maxProbability_02(n, edges, succProb, start, end);
        System.out.println(v);
    }

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        LinkedList<double[]> queue = new LinkedList<>();
        double[] cache = new double[n];
        Map<Integer, List<double[]>> graph = makeGraph(edges, succProb);
        queue.addLast(new double[]{start, 1});
        cache[start] = Double.MAX_VALUE;

        while (!queue.isEmpty()) {
            double[] cur = queue.removeFirst();
            List<double[]> doubles = graph.get((int) cur[0]);
            if (doubles == null) {
                continue;
            }
            for (double[] next : doubles) {
                if (next[1] * cur[1] > cache[(int) next[0]]) {
                    cache[(int) next[0]] = next[1] * cur[1];
                    queue.addLast(new double[]{next[0], cache[(int) next[0]]});
                }
            }
        }
        return cache[end];
    }


    public static double maxProbability_02(int n, int[][] edges, double[] succProb, int start, int end) {
        Queue<double[]> queue = new PriorityQueue<>(n, (a, b) -> b[1] >= a[1] ? 1 : -1);
        double[] cache = new double[n];
        Map<Integer, List<double[]>> graph = makeGraph(edges, succProb);
        queue.offer(new double[]{start, 1});
        cache[start] = Double.MAX_VALUE;

        while (!queue.isEmpty()) {
            double[] cur = queue.poll();
            if (cur[0] == end) {
                return cur[1];
            }
            List<double[]> doubles = graph.get((int) cur[0]);
            if (doubles == null) {
                continue;
            }
            for (double[] next : doubles) {
                if (next[1] * cur[1] > cache[(int) next[0]]) {
                    cache[(int) next[0]] = next[1] * cur[1];
                    queue.offer(new double[]{next[0], cache[(int) next[0]]});
                }
            }
        }
        return 0;
    }

    private static Map<Integer, List<double[]>> makeGraph(int[][] edges, double[] succProb) {
        Map<Integer, List<double[]>> graph = new HashMap<>();
        List<double[]> doubles;
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            doubles = graph.computeIfAbsent(edge[0], o -> new ArrayList<>());
            doubles.add(new double[]{edge[1], succProb[i]});
            doubles = graph.computeIfAbsent(edge[1], o -> new ArrayList<>());
            doubles.add(new double[]{edge[0], succProb[i]});
        }
        return graph;
    }
}
