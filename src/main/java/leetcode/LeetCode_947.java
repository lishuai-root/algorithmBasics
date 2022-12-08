package leetcode;

import java.util.*;

/**
 * @description: On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 * <p>
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 * <p>
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
 * @author: LISHUAI
 * @createDate: 2022/12/4 3:01
 * @version: 1.0
 */

public class LeetCode_947 {

    private static int[] cache;
    private static int index;

    public static void main(String[] args) {
//        int[][] stones = {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};
//        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
//        int[][] stones = {{0, 1}, {1, 0}, {1, 1}};
        int[][] stones = {{0, 1}, {1, 1}};
        int i = removeStones(stones);
        System.out.println(i);
        System.out.println(removeStones_02(stones));
        System.out.println(removeStones_dp(stones));
    }

    public static int removeStones(int[][] stones) {
        Map<Integer, Integer> rows = new HashMap<>(), cols = new HashMap<>();
        for (int[] stone : stones) {
            rows.put(stone[0], rows.getOrDefault(stone[0], 0) + 1);
            cols.put(stone[1], cols.getOrDefault(stone[1], 0) + 1);
        }
        boolean[] bls = new boolean[stones.length];
//        cache = new int[stones.length];
//        index = 0;
        return removeStonesProcess(stones, rows, cols, bls, stones.length);
    }

    private static int removeStonesProcess(int[][] stones, Map<Integer, Integer> rows, Map<Integer, Integer> cols, boolean[] bls, int k) {
        if (k <= 1) {
//            for (int i = 0; i < index; i++) {
//                System.out.print(cache[index] + " ");
//            }
//            System.out.println();
            return 0;
        }

        int ans = 0;
        for (int i = 0; i < stones.length; i++) {
            int r = stones[i][0];
            int c = stones[i][1];
            if (!bls[i] && (rows.get(r) > 1 || cols.get(c) > 1)) {
                bls[i] = true;
                rows.put(r, rows.get(r) - 1);
                cols.put(c, cols.get(c) - 1);
//                cache[index++] = i;
                ans = Math.max(ans, removeStonesProcess(stones, rows, cols, bls, k - 1) + 1);
                bls[i] = false;
                rows.put(r, rows.get(r) + 1);
                cols.put(c, cols.get(c) + 1);
//                index--;
            }
        }
        return ans;
    }


    public static int removeStones_02(int[][] stones) {
        int ans = 0;
        boolean[] bls = new boolean[stones.length];
        for (int i = 0; i < stones.length; i++) {
            if (!bls[i]) {
                ans++;
                bls[i] = true;
                removeStonesProcess(stones, i, bls);
            }
        }
        return stones.length - ans;
    }

    private static void removeStonesProcess(int[][] stones, int index, boolean[] bls) {
        if (index >= bls.length) {
            return;
        }
        for (int i = 0; i < stones.length; i++) {
            if (!bls[i]) {
                if (stones[index][0] == stones[i][0] || stones[index][1] == stones[i][1]) {
                    bls[i] = true;
                    removeStonesProcess(stones, i, bls);
                }
            }
        }
    }

    public static int removeStones_dp(int[][] stones) {
        stones = hashArray(stones);
        int len = stones.length;
        UF uf = new UF(len << 1);
        for (int[] ints : stones) {
            uf.union(ints[0], len + ints[1]);
            uf.union(len + ints[1], ints[0]);
        }
        Set<Integer> set = new HashSet<Integer>();
        for (int[] ints : stones) {
            set.add(uf.find(ints[0]));
            set.add(uf.find(len + ints[1]));
        }
        return stones.length - set.size();
    }

    private static int[][] hashArray(int[][] stones) {
        Arrays.sort(stones, (a, b) -> (a[0] - b[0]));
//        int[][] ans = new int[stones.length][stones[0].length];
        int index = 0, pre = stones[0][0];
        stones[0][0] = index;
        for (int i = 1; i < stones.length; i++) {
            if (stones[i][0] != pre) {
                index++;
            }
            pre = stones[i][0];
            stones[i][0] = index;
        }
        Arrays.sort(stones, (a, b) -> (a[1] - b[1]));
        index = 0;
        pre = stones[0][1];
        stones[0][1] = index;
        for (int i = 1; i < stones.length; i++) {
            if (stones[i][1] != pre) {
                index++;
            }
            pre = stones[i][1];
            stones[i][1] = index;
        }
        return stones;
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
