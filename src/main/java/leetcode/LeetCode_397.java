package leetcode;

/**
 * @description: Given a positive integer n, you can apply one of the following operations:
 * <p>
 * If n is even, replace n with n / 2.
 * If n is odd, replace n with either n + 1 or n - 1.
 * <p>
 * Return the minimum number of operations needed for n to become 1.
 * @author: LISHUAI
 * @createDate: 2021/12/18 19:44
 * @version: 1.0
 */

public class LeetCode_397 {

    public static void main(String[] args) {

        int a = 65535;

        int i = integerReplacement(a);

        System.out.println(i);
    }


    public static int integerReplacement(int n) {

        if (n == 1) {

            return 0;
        }

        int size = 0, m = 0, x = 0;

        if ((n & 1) == 1) {

            n++;

            size++;
        }

        for (int i = 0; i < 32; i++) {

            if (n >>> i == 1) {

                m = i;

                x++;
            }
        }

        return size + Math.max(m, x);
    }

    private static int process(int n, int sum) {

        if (n == 1) {

            return sum;
        }

        int m;

        if ((n & 1) == 1) {

            m = process(--n >>> 1, sum + 2);

            m = Math.min(m, process(++n >>> 1, sum + 2));
        } else {

            m = process(n >>> 1, sum + 1);
        }

        return m;
    }
}
