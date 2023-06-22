package leetcode;

/**
 * @description: Given 2n balls of k distinct colors. You will be given an integer array balls of size k where balls[i] is the number of balls of color i.
 * <p>
 * All the balls will be shuffled uniformly at random, then we will distribute the first n balls to the first box and the remaining n balls to the other box (Please read the explanation of the second example carefully).
 * <p>
 * Please note that the two boxes are considered different. For example, if we have two balls of colors a and b, and two boxes [] and (), then the distribution [a] (b) is considered different than the distribution [b] (a) (Please read the explanation of the first example carefully).
 * <p>
 * Return the probability that the two boxes have the same number of distinct balls. Answers within 10-5 of the actual value will be accepted as correct.
 * @author: LISHUAI
 * @createDate: 2023/5/1 21:00
 * @version: 1.0
 */

public class LeetCode_1467 {

    private static int[] cache;

    private static int n;
    private static long total;

    public static void main(String[] args) {
//        int[] balls = {2, 1, 1};
        int[] balls = {6, 6, 6, 6, 6, 6};
//        int[] balls = {1, 2, 1, 2};
//        int[] balls = {3, 2, 1};
        double v = getProbability(balls);
        System.out.println(v);
    }

    public static double getProbability(int[] balls) {
        int len = balls.length;
        n = 0;
        for (int i : balls) {
            n += i;
        }
        long s = 1;
        for (int i = 2; i <= n; i++) {
            s *= i;
        }
        for (int i : balls) {
            if (i > 1) {
                s /= i;
            }
        }
        cache = new int[len];
        total = 0;
        long p = getProbabilityProcess(balls, 0, n >> 1, 0, len);

        return ((double) p) / (total + p);
    }

    private static long getProbabilityProcess(int[] balls, int index, int count, int k, int pre) {
        if (count == 0) {

            long s1 = 1, q1 = 0, s2 = 1, q2 = 0;
            for (int i = 2; i <= (n >> 1); i++) {
                s1 *= i;
                s2 *= i;
            }
            for (int i = 0; i < balls.length; i++) {
                q1 += cache[i];
                q2 += balls[i] - cache[i];
                if (cache[i] > 1) {
//                    s1 /= cache[i];
                    s1 /= (2L * (cache[i] - 1));
                }
                if (balls[i] - cache[i] > 1) {
                    s2 /= (2L * (balls[i] - cache[i] - 1));
                }
            }
//            if (q1 != q2) {
//                System.out.println(q1 + " : " + q2);
//            }
//            System.out.println(s1 + " : " + s2);

            if (k != pre) {
                total += (s1 * s2);
                return 0;
            }
            return s1 * s2;
        }

        if (count < 0 || index >= balls.length) {
            return 0;
        }

        long ans = getProbabilityProcess(balls, index + 1, count, k, pre);

        for (int i = 1; i <= balls[index]; i++) {
            cache[index] = i;
            if (i == balls[index]) {
                ans += getProbabilityProcess(balls, index + 1, count - i, k + 1, pre - 1);
            } else {
                ans += getProbabilityProcess(balls, index + 1, count - i, k + 1, pre);
            }
            cache[index] = 0;
        }
        return ans;
    }

    class Solution {
        double equals = 0;
        long[][] binom;

        public double getProbability(int[] balls) {
            equals = 0;
            int size = 0;
            for (int i = 0; i < balls.length; i++) {
                size += balls[i];
            }
            binom = getBinom(size, size / 2);
            choose(balls, 0, 0, size / 2, 0, new int[balls.length], new int[balls.length], 1);
            return equals / binom[size][size / 2];
        }

        void choose(int[] balls, int size1, int size2, int k, int i, int[] b1, int[] b2, double factor) {
            if (size1 > k || size2 > k) return;
            if (size1 == k) {
                count(b1, balls, factor);
                return;
            }
            if (size2 == k) {
                count(b2, balls, factor);
                return;
            }
            for (int j = 0; j <= Math.min(balls[i], k - size1); j++) {
                //pick [0-j) of ball i
                b1[i] += j;
                b2[i] += balls[i] - j;
                choose(balls, size1 + j, size2 + balls[i] - j, k, i + 1, b1, b2, factor * binom[balls[i]][j]);
                b1[i] -= j;
                b2[i] -= balls[i] - j;
            }
        }

        long[][] getBinom(int n, int k) {
            long[][] binom = new long[n + 1][k + 1];
            binom[0][0] = 1;
            for (int i = 1; i < n + 1; i++) {
                binom[i][0] = 1;
                for (int j = 1; j < k + 1; j++) {
                    binom[i][j] = binom[i - 1][j] + binom[i - 1][j - 1];
                }
            }
            return binom;
        }

        void count(int[] b1, int[] balls, double factor) {
            int c1 = 0, c2 = 0;
            for (int j = 0; j < balls.length; j++) {
                c1 += (b1[j] > 0) ? 1 : 0;
                c2 += (balls[j] - b1[j] > 0) ? 1 : 0;
            }

            if (c1 == c2) {
                equals += factor;
            }
            return;
        }
    }
}
