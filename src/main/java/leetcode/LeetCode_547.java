package leetcode;

/**
 * @description: There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 * <p>
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * <p>
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 * <p>
 * Return the total number of provinces.
 * @author: LiShuai
 * @createDate: 2023/6/2 21:53
 * @version: 1.0
 */

public class LeetCode_547 {

    public static int findCircleNum(int[][] isConnected) {
        int rLen = isConnected.length, cLen = isConnected[0].length;
        UF uf = new UF(rLen);

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.size;
    }

    static class UF {
        private int[] uf;

        private int size;

        public UF(int size) {
            uf = new int[size];
            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }
            this.size = size;
        }

        public void union(int p, int q) {
            p = find(p);
            q = find(q);
            if (p != q) {
                uf[q] = p;
                --size;
            }
        }

        public int find(int p) {
            return uf[p] == p ? p : (uf[p] = find(uf[p]));
        }
    }
}
