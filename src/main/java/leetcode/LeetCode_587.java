package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @description: You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
 * <p>
 * You are asked to fence the entire garden using the minimum length of rope as it is expensive.
 * The garden is well fenced only if all the trees are enclosed.
 * <p>
 * Return the coordinates of trees that are exactly located on the fence perimeter.
 * @author: LISHUAI
 * @createDate: 2022/3/29 21:48
 * @version: 1.0
 */

public class LeetCode_587 {

    public static void main(String[] args) {
        int[][] trees = {{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}};
//        int[][] trees = {{1, 2}, {2, 2}, {4, 2}};
        int[][] ints = outerTrees(trees);
        for (int[] ii : ints) {
            for (int i : ii) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static int[][] outerTrees(int[][] trees) {
        int maxRow = 0, maxR = Integer.MIN_VALUE;
        boolean[] bls = new boolean[trees.length];
        for (int i = 0; i < trees.length; i++) {
            int[] ints = trees[i];
            if (ints[0] > maxR) {
                maxR = ints[0];
                maxRow = i;
            }
        }

        Set<Integer> exists = new HashSet<>();
//        bls[maxRow] = true;
//        exists.add(maxRow);
        int[][] cache = new int[trees.length][5];
        int cur = maxRow;

        while (true) {
            int index = 0;
            for (int i = 0; i < trees.length; i++) {
                if (!bls[i] && cur != i) {
                    int[] ints = trees[i];
                    int x = trees[cur][0] - ints[0];
                    int y = trees[cur][1] - ints[1];
                    if (y >= 0 && x < 0) {
                        cache[index][4] = 4;
                    } else if (y > 0 && x >= 0) {
                        cache[index][4] = 3;
                    } else if (y <= 0 && x > 0) {
                        cache[index][4] = 2;
                    } else {
                        cache[index][4] = 1;
                    }

                    cache[index][3] = x * x + y * y;
                    int gcd = gcd(x, y);
                    x /= gcd;
                    y /= gcd;
                    cache[index][0] = i;
                    cache[index][1] = y;
                    cache[index][2] = x;
                    index++;
                }
            }

            Arrays.sort(cache, 0, index, (a, b) -> {
                int c = b[4] - a[4];
                if (c == 0) {
                    if (a[1] * a[2] == 0) {
                        c = Integer.MAX_VALUE;
                    } else {
                        c = compareK(a, b);
                    }
                    if ((a[4] & 1) != 0) {
                        c = -c;
                    }
                }
                if (c == 0) {
                    c = a[3] - b[3];
                }
                return c;
            });
            if (index == 0 || exists.contains(cache[0][0])) {
                break;
            }
            exists.add(cache[0][0]);
            bls[cache[0][0]] = true;
            cur = cache[0][0];
        }
        int[][] ans = new int[exists.size()][2];
        int q = 0;
        for (int i : exists) {
            ans[q++] = trees[i];
        }
        return ans;
    }


    private static int compareK(int[] a, int[] b) {
        return a[1] * b[2] - b[1] * a[2];
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }


    private int orientation(int[] p, int[] q, int[] r) {
        return (r[1] - q[1]) * (q[0] - p[0]) - ((q[1] - p[1]) * (r[0] - q[0]));
    }

    public int[][] outerTrees_other(int[][] trees) {
        Stack<int[]> upper = new Stack<>();
        Stack<int[]> lower = new Stack<>();
        Arrays.sort(trees, (p, q) ->
                q[0] - p[0] == 0 ? q[1] - p[1] : q[0] - p[0]);
        for (int i = 0; i < trees.length; i++) {
            while (lower.size() >= 2 && orientation(lower.get(lower.size() - 2), lower.get(lower.size() - 1), trees[i]) > 0) {
                lower.pop();
            }
            while (upper.size() >= 2 && orientation(upper.get(upper.size() - 2), upper.get(upper.size() - 1), trees[i]) < 0) {
                upper.pop();
            }
            lower.push(trees[i]);
            upper.push(trees[i]);

        }
        Set<int[]> res = new HashSet<>(lower);
        res.addAll(upper);
        return res.toArray(new int[res.size()][]);

    }
}
