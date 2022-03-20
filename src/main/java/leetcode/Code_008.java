package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: https://leetcode.com/problems/largest-component-size-by-common-factor/
 * <p>
 * Given a non-empty array of unique positive integers nums, consider the following graph:
 * <p>
 * There are nums.length nodes, labelled nums[0] to nums[nums.length - 1];
 * There is an edge between nums[i] and nums[j] if and only if nums[i] and nums[j] share a common factor greater than 1.
 * Return the size of the largest connected component in the graph.
 * @author: LISHUAI
 * @createDate: 2021/6/29 20:43
 * @version: 1.0
 */

public class Code_008 {

    public static void main(String[] args) {

    }

    /**
     * 求最大公约数
     *
     * @param m
     * @param n
     * @return
     */
    private static int fn_001(int m, int n) {
        return n == 0 ? m : fn_001(n, m % n);
    }

    private static int process(int[] nums) {

        int max = Integer.MIN_VALUE, index = 0, n = 0;

        Map<Integer, Integer> map = new HashMap<>(16);

        Map<Integer, Integer> result = new HashMap<>(16);

        Map<Integer, Integer> parent = new HashMap<>(16);

        for (int i = 0; i < nums.length; i++) {

            index = nums[i];

            for (int j = 2; j <= index; j++) {

                n = index % j;
                if (n == 0) {
                    if (map.containsKey(j)) {

                        if (parent.containsKey(i)) {
                            result.put(parent.get(i), result.get(parent.get(i)) + result.get(j));

                        }

                        if (result.containsKey(j)) {
                            result.put(j, result.get(j) + 1);
                        } else {
                            result.put(j, 2);
                        }

                        max = Math.max(max, result.get(j));
                    } else {
                        map.put(j, i);
                    }

                }
            }
        }

        return max;
    }

    static class Code {
        public static int largestComponentSize1(int[] arr) {
            int N = arr.length;
            UnionFind set = new UnionFind(N);
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (gcd(arr[i], arr[j]) != 1) {
                        set.union(i, j);
                    }
                }
            }
            return set.maxSize();
        }

        public static int largestComponentSize2(int[] arr) {
            int N = arr.length;
            UnionFind unionFind = new UnionFind(N);
            HashMap<Integer, Integer> fatorsMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                int num = arr[i];
                int limit = (int) Math.sqrt(num);
                for (int j = 1; j <= limit; j++) {
                    if (num % j == 0) {
                        if (j != 1) {
                            if (!fatorsMap.containsKey(j)) {
                                fatorsMap.put(j, i);
                            } else {
                                unionFind.union(fatorsMap.get(j), i);
                            }
                        }
                        int other = num / j;
                        if (other != 1) {
                            if (!fatorsMap.containsKey(other)) {
                                fatorsMap.put(other, i);
                            } else {
                                unionFind.union(fatorsMap.get(other), i);
                            }
                        }
                    }
                }
            }
            return unionFind.maxSize();
        }

        public static int gcd(int m, int n) {
            return n == 0 ? m : gcd(n, m % n);
        }

        public static class UnionFind {
            private int[] parents;
            private int[] sizes;
            private int[] help;

            public UnionFind(int N) {
                parents = new int[N];
                sizes = new int[N];
                help = new int[N];
                for (int i = 0; i < N; i++) {
                    parents[i] = i;
                    sizes[i] = 1;
                }
            }

            public int maxSize() {
                int ans = 0;
                for (int size : sizes) {
                    ans = Math.max(ans, size);
                }
                return ans;
            }

            private int find(int i) {
                int hi = 0;
                while (i != parents[i]) {
                    help[hi++] = i;
                    i = parents[i];
                }
                for (hi--; hi >= 0; hi--) {
                    parents[help[hi]] = i;
                }
                return i;
            }

            public void union(int i, int j) {
                int f1 = find(i);
                int f2 = find(j);
                if (f1 != f2) {
                    int big = sizes[f1] >= sizes[f2] ? f1 : f1;
                    int small = big == f1 ? f2 : f1;
                    parents[small] = big;
                    sizes[big] = sizes[f1] + sizes[f2];
                }
            }
        }
    }
}
