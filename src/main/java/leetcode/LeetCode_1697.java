package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.
 * <p>
 * Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .
 * <p>
 * Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.
 * @author: LISHUAI
 * @createDate: 2023/4/29 20:18
 * @version: 1.0
 */

public class LeetCode_1697 {

    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> queries[a][2] - queries[b][2]);
        for (int i = 0; i < queries.length; i++) {
            queue.offer(i);
        }
        boolean[] ans = new boolean[queries.length];
        UF uf = new UF(n);

        int index = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int[] ints = queries[cur];
            while (index < edgeList.length && edgeList[index][2] < ints[2]) {
                uf.union(edgeList[index][0], edgeList[index][1]);
                index++;
            }
            ans[cur] = uf.find(ints[0]) == uf.find(ints[1]);
        }
        return ans;
    }

    public static boolean[] distanceLimitedPathsExist_02(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        int[][] tmp = new int[queries.length][4];
        for (int i = 0; i < queries.length; i++) {
            tmp[i][0] = queries[i][0];
            tmp[i][1] = queries[i][1];
            tmp[i][2] = queries[i][2];
            tmp[i][3] = i;
        }
        Arrays.sort(tmp, (a, b) -> a[2] - b[2]);
        boolean[] ans = new boolean[queries.length];
        UF uf = new UF(n);

        int index = 0;
        for (int[] ints : tmp) {
            while (index < edgeList.length && edgeList[index][2] < ints[2]) {
                uf.union(edgeList[index][0], edgeList[index][1]);
                index++;
            }
            ans[ints[3]] = uf.find(ints[0]) == uf.find(ints[1]);
        }
        return ans;
    }

    static class UF {
        int[] uf;

        public UF(int size) {
            uf = new int[size];
            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }
        }

        public void union(int p, int q) {
            p = find(p);
            q = find(q);
            if (p != q) {
                uf[q] = p;
            }
        }

        public int find(int p) {
            return (uf[p] == p ? p : (uf[p] = find(uf[p])));
        }
    }
}
