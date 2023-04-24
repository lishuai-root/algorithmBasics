package leetcode;

import java.util.Arrays;

/**
 * @description: You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.
 * <p>
 * The score of a path between two cities is defined as the minimum distance of a road in this path.
 * <p>
 * Return the minimum possible score of a path between cities 1 and n.
 * <p>
 * Note:
 * <p>
 * A path is a sequence of roads between two cities.
 * It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
 * The test cases are generated such that there is at least one path between 1 and n.
 * @author: LISHUAI
 * @createDate: 2023/3/22 20:17
 * @version: 1.0
 */

public class LeetCode_2492 {

    public static int minScore(int n, int[][] roads) {
        int[] paths = new int[n + 1];
        UF uf = new UF(n + 1);
        Arrays.fill(paths, Integer.MAX_VALUE);
        for (int[] ints : roads) {
            paths[ints[0]] = Math.min(paths[ints[0]], ints[2]);
//            paths[ints[0]] = paths[ints[0]] == 0 ? ints[2] : Math.min(paths[ints[0]], ints[2]);
//            paths[ints[1]] = paths[ints[1]] == 0 ? ints[2] : Math.min(paths[ints[1]], ints[2]);
            uf.union(ints[0], ints[1]);
        }
        int i = uf.find(1);
        int ans = paths[1];
        for (int j = 2; j <= n; j++) {
            if (uf.find(j) == i) {
                ans = Math.min(ans, paths[j]);
            }
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
                uf[p] = q;
            }
        }

        public int find(int p) {
            return uf[p] == p ? p : (uf[p] = find(uf[p]));
        }
    }
}
