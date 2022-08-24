package leetcode;

/**
 * @description: There is a special square room with mirrors on each of the four walls. Except for the southwest corner, there are receptors on each of the remaining corners, numbered 0, 1, and 2.
 * <p>
 * The square room has walls of length p and a laser ray from the southwest corner first meets the east wall at a distance q from the 0th receptor.
 * <p>
 * Given the two integers p and q, return the number of the receptor that the ray meets first.
 * <p>
 * The test cases are guaranteed so that the ray will meet a receptor eventually.
 * @author: LISHUAI
 * @createDate: 2022/8/4 21:37
 * @version: 1.0
 */

public class LeetCode_858 {

    public static void main(String[] args) {
//        int p = 2, q = 1;
        int p = 3, q = 1;
        int i = mirrorReflection(p, q);
        System.out.println(i);
    }

    public static int mirrorReflection(int p, int q) {
        int gcd = gcd(p, q);
        int k = q / gcd;
        int m = p / gcd;
        if ((k & 1) == 0) {
            if ((m & 1) == 0) {
                return 3;
            } else {
                return 0;
            }
        } else {
            if ((m & 1) == 0) {
                return 2;
            } else {
                return 1;
            }
        }
    }

    public static int gcd(int x, int y) {
        if (y == 0)
            return x;
        else
            return gcd(y, x % y);
    }
}
