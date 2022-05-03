package leetcode;

import java.util.*;

/**
 * @description: You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 * <p>
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 * <p>
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 * @author: LISHUAI
 * @createDate: 2022/4/27 19:58
 * @version: 1.0
 */

public class LeetCode_1202 {

    public static void main(String[] args) {
        UF uf = new UF(12);
        uf.union(1, 2);
    }

    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UF uf = new UF(s.length());
        Map<Integer, Queue<Character>> map = new HashMap<>();
        StringBuilder sbr = new StringBuilder();
        for (List<Integer> list : pairs) {
            uf.union(list.get(0), list.get(1));
        }

        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(uf.find(i), k -> new PriorityQueue<>()).offer(s.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            sbr.append(map.get(uf.find(i)).poll());
        }
        return sbr.toString();
    }
}


class UF {
    int[] uf, sz;

    public UF(int size) {
        uf = new int[size];
        sz = new int[size];
        Arrays.fill(sz, 1);

        for (int i = 0; i < size; i++) {
            uf[i] = i;
        }
    }

    public void union(int p, int q) {
        p = find(p);
        q = find(q);
        if (sz[p] > sz[q]) {
            uf[p] = q;
            sz[p] += sz[q];
        } else {
            uf[q] = p;
            sz[q] += sz[p];
        }
    }

    public int find(int p) {
        return uf[p] == p ? p : (uf[p] = find(uf[p]));
    }
}
