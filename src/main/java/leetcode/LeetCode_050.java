package leetcode;

/**
 * @description: Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * @author: LISHUAI
 * @createDate: 2021/12/2 22:50
 * @version: 1.0
 */

public class LeetCode_050 {
    private static int size = 0;

    public static void main(String[] args) {
        double x = 2.00000;
        int n = 10;
        double v = myPow(0.000001, Integer.MAX_VALUE);
//        double v = myPow(x, n);

        System.out.println(v);
        System.out.println(size);
        size = 0;
        for (int i = 0; i < 31; i++) {
            size += i;
        }
        System.out.println(size);
    }


    public static double myPow(double x, int n) {

        if (n == 0 || x == 1) {
            return 1;
        }

        if (x == -1) {
            if ((n & 1) == 1) {
                return -1;
            } else {
                return 1;
            }
        }

        if (n == Integer.MIN_VALUE) {

            return 0;
        }

        double result = 1, curResult = x;

        int index = 1, cur = Math.abs(n);

        while (cur >= 1) {
            curResult = x;

            index = 1;
            while ((index << 1) < cur && (index << 1) > 0) {
                index <<= 1;
                curResult *= curResult;
            }
            cur -= index;

            result *= curResult;
//            if (result == 0) {
//                return 0D;
//            } else if (result == 1 || result == -1) {
//                return (cur & 1) == 1 ? result : 1;
//            }
        }


        if (n < 0) {
            result = 1 / result;
        }

        return result;
    }
}
