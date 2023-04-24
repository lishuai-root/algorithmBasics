package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @description: There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
 * <p>
 * You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
 * <p>
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
 * <p>
 * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
 * @author: LISHUAI
 * @createDate: 2023/4/9 17:23
 * @version: 1.0
 */

public class LeetCode_1857 {

    public static int largestPathValue(String colors, int[][] edges) {
        if (edges == null || edges.length == 0) {
            return 1;
        }
        Map<Integer, Node> graph = makeGraph(edges);
        int ans = 0;
        for (int i : graph.keySet()) {
            Node node = largestPathValueProcess(graph.get(i), colors);
            if (node.maxColor == -1) {
                return -1;
            }
            ans = Math.max(ans, node.maxColor);
        }
        return ans;
    }

    private static Node largestPathValueProcess(Node node, String colors) {
        if (node.road) {
            node.maxColor = -1;
            return node;
        }
        if (node.maxColor != 0) {
            return node;
        }
        node.road = true;
        int c = colors.charAt(node.val) - 'a';
        for (Node child : node.children) {
            Node n = largestPathValueProcess(child, colors);
            if (n.maxColor == -1) {
                node.maxColor = -1;
                return node;
            }
            for (int i = 0; i < node.color.length; i++) {
                node.color[i] = Math.max(node.color[i], n.color[i]);
                node.maxColor = Math.max(node.maxColor, node.color[i]);
            }
        }
        node.road = false;
        node.color[c]++;
        node.maxColor = Math.max(node.maxColor, node.color[c]);
        return node;
    }

    public static Map<Integer, Node> makeGraph(int[][] edges) {
        Map<Integer, Node> graph = new HashMap<>();
        Node node, child;
        for (int[] edge : edges) {
            node = graph.computeIfAbsent(edge[0], o -> new Node(edge[0]));
            child = graph.computeIfAbsent(edge[1], o -> new Node(edge[1]));
            node.children.add(child);
        }
        return graph;
    }


    static class Node {
        int val, maxColor;

        boolean road;
        int[] color;
        LinkedList<Node> children;

        public Node(int val) {
            this.val = val;
            this.road = false;
            this.color = new int[26];
            this.children = new LinkedList<Node>();
        }
    }
}
