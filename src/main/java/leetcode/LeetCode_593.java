package leetcode;

import java.util.Arrays;

/**
 * @description: Given the coordinates of four points in 2D space p1, p2, p3 and p4,
 * return true if the four points construct a square.
 * <p>
 * The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
 * <p>
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 * @author: LISHUAI
 * @createDate: 2022/3/22 21:50
 * @version: 1.0
 */

public class LeetCode_593 {

    public static void main(String[] args) {
//        int[] p1 = {1, 0}, p2 = {-1, 0}, p3 = {0, 1}, p4 = {0, -1};
//        int[] p1 = {1134, -2539}, p2 = {492, -1255}, p3 = {-792, -1897}, p4 = {-150, -3181};

//        int[] p1 = {0, 0}, p2 = {5, 0}, p3 = {5, 4}, p4 = {0, 4};
        int[] p1 = {0, 0}, p2 = {0, 0}, p3 = {0, 0}, p4 = {0, 0};

//        int[] p1 = {2, 1}, p2 = {1, 2}, p3 = {0, 0}, p4 = {2, 0};

//        int[] p1 = {1, 1}, p2 = {5, 3}, p3 = {3, 5}, p4 = {7, 7};
        boolean b = validSquare_02(p1, p2, p3, p4);
        System.out.println(b);

    }

    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] ans = {p1, p2, p3, p4};
        Arrays.sort(ans, (a, b) -> {
            int c = a[0] - b[0];
            if (c == 0) {
                c = a[1] - b[1];
            }
            return c;
        });

        int com = 0;
        for (; com < ans.length; com++) {
            if (ans[com][0] != ans[0][0] || ans[com][1] != ans[0][0]) {
                break;
            }
        }
        if (com >= ans.length) {
            return false;
        }
        int n1 = Math.abs(ans[3][0] - ans[0][0]);
        int n2 = Math.abs(ans[3][1] - ans[0][1]);

        int n3 = Math.abs(ans[2][0] - ans[1][0]);
        int n4 = Math.abs(ans[2][1] - ans[1][1]);

        String x1, x2;
        int t1, t2, t3, t4;
        int m1, m2, m3, m4;
        if (n2 == 0 || n1 == 0) {
            x1 = n2 == 0 ? Math.abs(n1) + "" : Math.abs(n2) + "";
            m1 = n1;
            m2 = n2;
        } else {
            int gcd = gcd(n1, n2);
            m1 = Math.abs(n1 / gcd);
            m2 = Math.abs(n2 / gcd);

//            x1 = Math.min(t1, t2) + "-" + Math.max(t1, t2);
//            m1 = Math.max(t1, t2);
//            m2 = Math.min(t1, t2);
        }

        if (n3 == 0 || n4 == 0) {
            x2 = n3 == 0 ? Math.abs(n4) + "" : Math.abs(n3) + "";
            m3 = n3;
            m4 = n4;
        } else {
            int gcd = gcd(n3, n4);
            m3 = Math.abs(n3 / gcd);
            m4 = Math.abs(n4 / gcd);

//            x2 = Math.min(t1, t2) + "-" + Math.max(t1, t2);
//            m3 = Math.max(t1, t2);
//            m4 = Math.min(t1, t2);
        }

//        System.out.println(x1);
//        System.out.println(x2);
        System.out.println("n1 : " + n1 + ", n2 : " + n2);
        System.out.println("n3 : " + n3 + ", n4 : " + n4);
        System.out.println("m1 : " + m1 + ", m2 : " + m2);
        System.out.println("m3 : " + m3 + ", m4 : " + m4);
//        return n1 == n2 && n2 == n3 && n3 == n4 && m1 == m4 && m2 == m3;
        return n1 * n1 + n2 * n2 == n3 * n3 + n4 * n4 &&
                Math.abs(ans[2][0] - ans[0][0]) + Math.abs(ans[2][1] - ans[0][1]) ==
                        Math.abs(ans[1][0] - ans[0][0]) + Math.abs(ans[1][1] - ans[0][1]) && m1 == m4 && m2 == m3;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    public static boolean validSquare_02(int[] p1, int[] p2, int[] p3, int[] p4) {

//        if (p1[0] == p1[1] && p1[1] == p2[0] && p2[0] == p2[1] && p2[1] == p3[0] && p3[0] == p3[1] && p3[1] == p4[0] && p4[0] == p4[1]) {
//            return false;
//        }
        int[][] ans = {p1, p2, p3, p4};
        Arrays.sort(ans, (a, b) -> {
            int c = a[0] - b[0];
            if (c == 0) {
                c = a[1] - b[1];
            }
            return c;
        });

        int com = 0;
        for (; com < ans.length; com++) {
            if (ans[com][0] != ans[0][0] || ans[com][1] != ans[0][0]) {
                break;
            }
        }
        if (com >= ans.length) {
            return false;
        }
        int n1 = Math.abs(ans[3][0] - ans[0][0]);
        int n2 = Math.abs(ans[3][1] - ans[0][1]);

        int n3 = Math.abs(ans[2][0] - ans[1][0]);
        int n4 = Math.abs(ans[2][1] - ans[1][1]);

        return n1 * n1 + n2 * n2 == n3 * n3 + n4 * n4 &&
                Math.abs(ans[2][0] - ans[0][0]) + Math.abs(ans[2][1] - ans[0][1]) ==
                        Math.abs(ans[1][0] - ans[0][0]) + Math.abs(ans[1][1] - ans[0][1]);
    }
}
