package leetcode;

/**
 * @description: Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 * <p>
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * @author: LISHUAI
 * @createDate: 2021/11/30 18:05
 * @version: 1.0
 */

public class LeetCode_007 {

    public static void main(String[] args) {

        int a = Integer.MAX_VALUE;


//
//        int[] stack = new int[32];
//
//        for (int i = 0; i < 32; i++) {
//
//            stack[i] = (~a >>> i) & 1;
//        }
//
//        for (int i = 15; i >= 0; i--) {
//
//            System.out.print(stack[i] + " ");
//        }

        int reverse = reverse(a);
//
        System.out.println(reverse);

    }

    /**
     * 123 : 0 0 0 0 0 0 0 0 0 1 1 1 1 0 1 1
     * ~123: 1 1 1 1 1 1 1 1 1 0 0 0 0 1 0 0
     * 321 : 0 0 0 0 0 0 0 1 0 1 0 0 0 0 0 1
     * ~321: 1 1 1 1 1 1 1 0 1 0 1 1 1 1 1 0
     * <p>
     * 120 : 0 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0
     * ~120: 1 1 1 1 1 1 1 1 1 0 0 0 0 1 1 1
     * 21 :  0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 1
     * ~21:  1 1 1 1 1 1 1 1 1 1 1 0 1 0 1 0
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {

        int m = 10, n = x;

        StringBuilder sb = new StringBuilder();

        while (x != 0) {

            sb.append(Math.abs(x % m));

            x /= m;
        }

        try {

            m = Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {

            return 0;
        }

        return n > 0 ? m : -m;
    }
}
