package leetcode;

/**
 * @description: You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * @author: LISHUAI
 * @createDate: 2021/11/21 0:40
 * @version: 1.0
 */

public class LeetCode_070 {

    public static void main(String[] args) {
        int i = climbStairs_03(44);

        int i1 = climbStairs_02(44);

        System.out.println(i);

        System.out.println(i1);
    }


    public static int climbStairs(int n) {

        return process(n, 0);
    }

    public static int process(int n, int index) {

        if (index == n) {

            return 1;
        }

        if (index > n) {

            return 0;
        }

        return process(n, index + 1) + process(n, index + 2);
    }

    public static int climbStairs_02(int n) {

        if (n == 1) {

            return 1;
        }
        if (n == 2) {

            return 2;
        }

        int[] ints = new int[n + 1];

        ints[1] = 1;

        ints[2] = 2;

        for (int i = 3; i <= n; i++) {

            ints[i] = ints[i - 1] + ints[i - 2];
        }

        return ints[n];
    }


    public static int climbStairs_03(int n) {

        if (n == 1) {

            return 1;
        }
        if (n == 2) {

            return 2;
        }

        int left = 1, right = 2;

        n -= 1;

        while (--n > 0) {

            if (left < right) {

                left = left + right;
            } else {

                right = left + right;
            }
        }

        return Math.max(left, right);
    }
}
