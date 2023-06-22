package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements at index ai and index bi (0-indexed) of array source. Note that you can swap elements at a specific pair of indices multiple times and in any order.
 * <p>
 * The Hamming distance of two arrays of the same length, source and target, is the number of positions where the elements are different. Formally, it is the number of indices i for 0 <= i <= n-1 where source[i] != target[i] (0-indexed).
 * <p>
 * Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.
 * @author: LISHUAI
 * @createDate: 2023/5/15 21:17
 * @version: 1.0
 */

public class LeetCode_1722 {

    public static void main(String[] args) throws Exception {
        int[] source = {1, 2, 3, 4}, target = {2, 1, 4, 5};
        int[][] allowedSwaps = {{0, 1}, {2, 3}};
        int i = minimumHammingDistance(source, target, allowedSwaps);
        System.out.println(i);
    }

    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int len = source.length;
        UF uf = new UF(len);
        for (int[] ints : allowedSwaps) {
            uf.union(ints[0], ints[1]);
        }
        Map<Integer, Map<Integer, Integer>> mm = new HashMap<Integer, Map<Integer, Integer>>();
        for (int i = 0; i < len; i++) {
            int p = uf.find(i);
            Map<Integer, Integer> m = mm.computeIfAbsent(p, o -> new HashMap<>());
            m.put(source[i], m.getOrDefault(source[i], 0) + 1);
            m.put(target[i], m.getOrDefault(target[i], 0) - 1);
        }
        int ans = 0;
        for (Map<Integer, Integer> m : mm.values()) {
            for (int v : m.values()) {
                if (v > 0) {
                    ans += v;
                }
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
            if (q != p) {
                uf[q] = p;
            }
        }

        public int find(int p) {
            return uf[p] == p ? p : (uf[p] = find(uf[p]));
        }
    }
}
