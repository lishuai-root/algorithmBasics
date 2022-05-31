package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: You are given a network of n nodes represented as an n x n adjacency matrix graph, where the ith node is directly connected to the jth node if graph[i][j] == 1.
 * <p>
 * Some nodes initial are initially infected by malware. Whenever two nodes are directly connected, and at least one of those two nodes is infected by malware,
 * both nodes will be infected by malware. This spread of malware will continue until no more nodes can be infected in this manner.
 * <p>
 * Suppose M(initial) is the final number of nodes infected with malware in the entire network after the spread of malware stops.
 * <p>
 * We will remove exactly one node from initial, completely removing it and any connections from this node to any other node.
 * <p>
 * Return the node that, if removed, would minimize M(initial). If multiple nodes could be removed to minimize M(initial), return such a node with the smallest index.
 * @author: LISHUAI
 * @createDate: 2022/5/9 20:50
 * @version: 1.0
 */

public class LeetCode_928 {

    public static void main(String[] args) {
//        int[][] graph = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
//        int[] initial = {0, 1};
        int[][] graph = {{1, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 1, 1}, {0, 0, 1, 1}};
        int[] initial = {0, 1};
//        int[][] graph = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
//        int[] initial = {0, 1};
        int i = minMalwareSpread(graph, initial);
        System.out.println(i);
    }

    public static int minMalwareSpread(int[][] graph, int[] initial) {
        int size = graph.length;
        UF uf = new UF(size, initial);
        int[] connected = new int[size];

        for (int i = 0; i < graph.length; i++) {
            int[] ints = graph[i];
            for (int j = 0; j < i; j++) {
                if (ints[j] == 1) {
                    uf.union(i, j);
                    connected[j]++;
                    connected[i]++;
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : initial) {
            map.put(i, uf.find(i));
        }
        Arrays.sort(initial);
        int ans = 0, max = 0, maxAll = 0;
        for (int i : initial) {
            int j = uf.find(map.get(i));
            int all = uf.getAllSize(j);
            j = uf.getSize(j);
            System.out.println(i + " : " + j);
            if (j > max) {
                max = j;
                ans = i;
                maxAll = connected[i];
                continue;
            }
            if (j == max && connected[i] > maxAll) {
                ans = i;
                maxAll = connected[i];
            }

        }
        return ans;
    }

    static class UF {
        int[] count, uf, allCount;

        public UF(int size, int[] initial) {
            count = new int[size];
            uf = new int[size];
            allCount = new int[size];
            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }

            for (int i : initial) {
                count[i]++;
            }
            Arrays.fill(allCount, 1);
        }

        public int find(int p) {
            return uf[p] == p ? p : (uf[p] = find(uf[p]));
        }

        public void union(int p, int q) {
            p = find(p);
            q = find(q);
            if (p != q) {
                uf[q] = p;
                count[p] += count[q];
                allCount[p] += allCount[q];
            }
        }

        public int getSize(int p) {
            return count[find(p)];
        }

        public int getAllSize(int p) {
            return allCount[find(p)];
        }
    }
}
