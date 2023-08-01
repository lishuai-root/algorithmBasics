package leetcode;

/**
 * @description: There is a regular convex polygon with n vertices. The vertices are labeled from 0 to n - 1 in a clockwise direction, and each vertex has exactly one monkey. The following figure shows a convex polygon of 6 vertices.
 * <p>
 * <p>
 * Each monkey moves simultaneously to a neighboring vertex. A neighboring vertex for a vertex i can be:
 * <p>
 * the vertex (i + 1) % n in the clockwise direction, or
 * the vertex (i - 1 + n) % n in the counter-clockwise direction.
 * A collision happens if at least two monkeys reside on the same vertex after the movement or intersect on an edge.
 * <p>
 * Return the number of ways the monkeys can move so that at least one collision happens. Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * Note that each monkey can only move once.
 * @author: LiShuai
 * @createDate: 2023/7/24 18:34
 * @version: 1.0
 */

public class LeetCode_2550 {

    public static void main(String[] args) {
        int n = 500000003;
        int i = monkeyMove(n);
        System.out.println(i);
        System.out.println((Math.pow(2, n) % 1000000007) - 2);
    }

    public static int monkeyMove(int n) {
        long ans = 1, m = 2;
        int k = n;
        while (k > 0) {
            if ((k & 1) != 0) {
                ans *= m;
                ans %= 1000000007;
            }
            m *= m;
            m %= 1000000007;
            k >>= 1;
        }

        return (int) ((ans + 1000000005) % 1000000007);
    }
}
