package leetcode;

/**
 * @description: You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
 * Check if these points make a straight line in the XY plane.
 * @author: LISHUAI
 * @createDate: 2021/12/22 19:56
 * @version: 1.0
 */

public class LeetCode_1232 {

    public static void main(String[] args) {

        int a = 6, b = 5;

        System.out.println(gcd(5, 6));
    }

    public static boolean checkStraightLine(int[][] coordinates) {

        if (coordinates.length <= 2) {

            return true;
        }

        int m, n, x, y, v;

        x = coordinates[1][0] - coordinates[0][0];

        y = coordinates[1][1] - coordinates[0][1];

        v = gcd(x, y);

        m = x / v;

        n = y / v;

        for (int i = 2; i < coordinates.length; i++) {

            x = coordinates[i][0] - coordinates[0][0];

            y = coordinates[i][1] - coordinates[0][1];

            v = gcd(Math.max(x, y), Math.min(x, y));

            if (m != (x / v) || n != (y / v)) {

                return false;
            }
        }

        return true;
    }

    public static int gcd(int x, int y) {
        if (y == 0)
            return x;
        else
            return gcd(y, x % y);
    }
}
