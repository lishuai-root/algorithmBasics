package leetcode;

/**
 * @description: Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * @author: LISHUAI
 * @createDate: 2021/12/2 22:50
 * @version: 1.0
 */

public class LeetCode_50 {

    public static void main(String[] args) {

        double v = myPow(0.000001, Integer.MAX_VALUE);

        System.out.println(v);
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
//            System.out.println(cur);


            curResult = x;


            index = 1;
            while (index * 2 < cur && index * 2 > 0) {

//                System.out.println(cur);
                index *= 2;

                curResult *= curResult;
            }
            cur -= index;

            result *= curResult;
        }


//        for (int i = index; i < Math.abs(n); i++) {
//
//            result *= x;
//        }

//        System.out.println(result);

//        if ((n & 1) == 1) {
//
//            result *= x;
//        }

        if (n < 0) {

            result = 1 / result;
        }

        return result;
    }
}
