package leetcode;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @description: You are given a directed graph with n nodes labeled from 0 to n - 1, where each node has exactly one outgoing edge.
 * <p>
 * The graph is represented by a given 0-indexed integer array edges of length n, where edges[i] indicates that there is a directed edge from node i to node edges[i].
 * <p>
 * The edge score of a node i is defined as the sum of the labels of all the nodes that have an edge pointing to i.
 * <p>
 * Return the node with the highest edge score. If multiple nodes have the same edge score, return the node with the smallest index.
 * @author: LISHUAI
 * @createDate: 2022/12/4 1:24
 * @version: 1.0
 */

public class LeetCode_2374 {

    public static void main(String[] args) throws Exception {
//        int[] edges = {1, 0, 0, 0, 0, 7, 7, 5};
//        int[] edges = {2, 0, 0, 2};
        int[] edges = readArr();
        int i = edgeScore(edges);
        System.out.println(i);
    }

    private static int[] readArr() throws Exception {
        String path = "E:\\All_workspace\\IDEA_workspace\\algorithmBasics\\src\\main\\resources\\leetCode_2371.txt";

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String s = reader.readLine();
        String[] split = s.split(",");
        int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        return arr;
    }

    public static int edgeScore(int[] edges) {
        long[] sum = new long[edges.length];
        for (int i = 0; i < edges.length; i++) {
            sum[edges[i]] += i;
        }
        int ans = -1;
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] > max) {
                ans = i;
                max = sum[i];
            }
        }
        return ans;
    }
}
