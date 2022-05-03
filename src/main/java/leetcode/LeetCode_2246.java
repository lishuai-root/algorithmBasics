package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1.
 * The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
 * <p>
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 * <p>
 * Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.
 * @author: LISHUAI
 * @createDate: 2022/4/17 17:52
 * @version: 1.0
 */

public class LeetCode_2246 {
    private static int ans;

    public static void main(String[] args) {
//        int[] parent = {-1, 0, 0, 1, 1, 2};
//        String s = "abacbe";
//        int[] parent = {-1, 0, 0, 0};
//        String s = "aabc";
        int[] parent = {-1};
        String s = "z";
        int i = longestPath(parent, s);
        System.out.println(i);
    }

    public static int longestPath(int[] parent, String s) {
        ans = 1;
        List<List<Integer>> edges = makeEdges(parent);
        longestPathProcess(edges, 0, s);
        return ans;
    }


    private static int longestPathProcess(List<List<Integer>> edges, int index, String s) {
        if (index >= edges.size()) {
            return 1;
        }
        List<Integer> list = edges.get(index);
        int min = 0, max = 0;

        for (int i : list) {
            int info = longestPathProcess(edges, i, s);
            if (s.charAt(index) != s.charAt(i)) {
                if (info > max) {
                    min = max;
                    max = info;
                } else if (info > min) {
                    min = info;
                }
            }
        }
        ans = Math.max(ans, max + min + 1);
        return max + 1;
    }

    private static List<List<Integer>> makeEdges(int[] parent) {
        List<List<Integer>> edges = new ArrayList<>();

        for (int i = 0; i < parent.length; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i < parent.length; i++) {
            edges.get(parent[i]).add(i);
        }
        return edges;
    }

}
