package leetcode;

import java.util.Random;

/**
 * @description: You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.
 * <p>
 * Return true if and only if we can do this so that the resulting number is a power of two.
 * @author: LISHUAI
 * @createDate: 2022/8/28 13:03
 * @version: 1.0
 */

public class LeetCode_869 {

    public static void main(String[] args) {
//        int n = 1;
        int n = 16;
        boolean b = reorderedPowerOf2(n);
        System.out.println(b);
        System.out.println(reorderedPowerOf2_02(n));
        Number x = 1;
        System.out.println(x);
    }

    private static void showB() {
        for (int i = 0; i <= 20; i++) {
            System.out.println(i + " : ");
            for (int j = 31; j >= 0; j--) {
                if ((i & (1 << j)) == 0) {
                    System.out.print('0');
                } else {
                    System.out.print('1');
                }
            }
            int k = i * 10;
            System.out.print("   :   ");
            for (int j = 31; j >= 0; j--) {
                if ((k & (1 << j)) == 0) {
                    System.out.print('0');
                } else {
                    System.out.print('1');
                }
            }
            System.out.println();
        }
    }

    private static void show(int size) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int n = random.nextInt();
            boolean b = reorderedPowerOf2(n);
            boolean b1 = reorderedPowerOf2_02(n);
            if (b != b1) {
                System.out.println(n + " - " + b + " - " + b1);
            }
        }
    }

    public static boolean reorderedPowerOf2(int n) {
        int[] bit = new int[10];
        int len = 0;
        while (n != 0) {
            bit[len++] = n % 10;
            n = n / 10;
        }
        return reorderedPowerOf2Process(bit, 0, len, 0);
    }

    private static boolean reorderedPowerOf2Process(int[] bit, int index, int len, int cur) {
        if (index >= len) {
            return ((-cur & cur) ^ cur) == 0;
        }

        for (int i = 0; i < len; i++) {
            if (bit[i] == -1) {
                continue;
            }
            if (bit[i] != 0 || cur != 0) {
                int c = bit[i];
                bit[i] = -1;
                boolean b = reorderedPowerOf2Process(bit, index + 1, len, cur * 10 + c);
                if (b) {
                    return true;
                }
                bit[i] = c;
            }
        }
        return false;
    }


    public static boolean reorderedPowerOf2_02(int n) {
        if (((-n & n) ^ n) == 0) {
            return true;
        }
        int[] bit = new int[10];
        int len = 0, b = 0;
        while (n != 0) {
            b ^= (1 << len);
            bit[len++] = n % 10;
            n = n / 10;
        }
        return reorderedPowerOf2Process(bit, 0, b);
    }

    private static boolean reorderedPowerOf2Process(int[] bit, int cur, int b) {
        if (b == 0) {
            return ((-cur & cur) ^ cur) == 0;
        }

        int tmp = b;
        while (tmp != 0) {
            int t = -tmp & tmp;
            int c = 31 - Integer.numberOfLeadingZeros(t);
            if (bit[c] != 0 || cur != 0) {
                boolean bl = reorderedPowerOf2Process(bit, cur * 10 + bit[c], b ^ t);
                if (bl) {
                    return true;
                }
            }
            tmp ^= t;
        }
        return false;
    }
}
