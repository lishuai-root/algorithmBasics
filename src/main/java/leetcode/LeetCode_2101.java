package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
 * This area is in the shape of a circle with the center as the location of the bomb.
 * <p>
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri].
 * xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
 * <p>
 * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range.
 * These bombs will further detonate the bombs that lie in their ranges.
 * <p>
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
 * @author: LISHUAI
 * @createDate: 2022/6/1 21:49
 * @version: 1.0
 */

public class LeetCode_2101 {

    public static void main(String[] args) {
//        int[][] bombs = {{2, 1, 3}, {6, 1, 4}};
//        int[][] bombs = {{1, 1, 5}, {10, 10, 5}};
//        int[][] bombs = {{1, 2, 3}, {2, 3, 1}, {3, 4, 2}, {4, 5, 3}, {5, 6, 4}};
        int[][] bombs = {{855, 82, 158}, {17, 719, 430}, {90, 756, 164}, {376, 17, 340}, {691, 636, 152}, {565, 776, 5}, {464, 154, 271}, {53, 361, 162}, {278, 609, 82}, {202, 927, 219}, {542, 865, 377}, {330, 402, 270}, {720, 199, 10}, {986, 697, 443}, {471, 296, 69}, {393, 81, 404}, {127, 405, 177}};
//        int[][] bombs = {{54, 95, 4}, {99, 46, 3}, {29, 21, 3}, {96, 72, 8}, {49, 43, 3}, {11, 20, 3}, {2, 57, 1}, {69, 51, 7}, {97, 1, 10}, {85, 45, 2}, {38, 47, 1}, {83, 75, 3}, {65, 59, 3}, {33, 4, 1}, {32, 10, 2}, {20, 97, 8}, {35, 37, 3}};
        int i = maximumDetonation(bombs);
        System.out.println(i);
    }

    public static int maximumDetonation_02(int[][] bombs) {
        int ans = 0, len = bombs.length, sum;
        int[] count = new int[len];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            queue.offer(i);
            sum = 0;
            Arrays.fill(count, 0);
            count[i] = 1;
            while (!queue.isEmpty()) {
                sum += queue.size();
                int size = queue.size();

                for (int j = 0; j < size; j++) {
                    int cur = queue.poll();
                    for (int k = 0; k < len; k++) {
                        if (count[k] == 0 && getLen(bombs, cur, k)) {
                            queue.offer(k);
                            count[k]++;
                        }
                    }
                }
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static int maximumDetonation(int[][] bombs) {
        int len = bombs.length, ans = 0;
        UF uf = new UF(len);
        int[] count = new int[len];

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (getLen(bombs, i, j)) {
                    uf.union(i, j);
                }
            }
        }
        for (int i : uf.count) {
            ans = Math.max(ans, i);
        }
        return ans;
    }

    private static boolean getLen(int[][] bombs, int p, int q) {
        long a = bombs[p][2];
        a = a * a;
        long x = Math.abs(bombs[q][0] - bombs[p][0]);
        long y = Math.abs(bombs[q][1] - bombs[p][1]);
        long b = x * x + y * y;
        return b <= a;
    }

    static class UF {
        int[] uf, count;
        int max;

        public UF(int size) {
            uf = new int[size];
            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }
            count = new int[size];
            Arrays.fill(count, 1);
            max = 1;
        }

        public void union(int p, int q) {
            p = find(p);
            q = find(q);
            if (p != q) {
                uf[q] = p;
                count[p] += count[q];
                max = Math.max(max, count[p]);
            }
        }

        public int find(int p) {
            return uf[p] == p ? p : (uf[p] = find(uf[p]));
        }

        public int size(int p) {
            return count[find(p)];
        }
    }
}



















