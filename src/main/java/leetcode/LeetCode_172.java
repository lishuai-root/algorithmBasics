package leetcode;

/**
 * @description: Given an integer n, return the number of trailing zeroes in n!.
 * <p>
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 * @author: LISHUAI
 * @createDate: 2022/12/16 19:59
 * @version: 1.0
 */

public class LeetCode_172 {

    public static void main(String[] args) {
        long a = 1;
        for (int i = 1; i <= 30; i++) {
            a *= i;
            System.out.println(a + " - " + i);
        }
        System.out.println(a);
        long b = 664;
        System.out.println(b);
        System.out.println((b *= 21));
        System.out.println((b *= 22));
        System.out.println((b *= 23));
        System.out.println((b *= 24));
        System.out.println(b * 20 + "-" + b * 5);
        System.out.println((b *= 25));
    }

    public static int trailingZeroes(int n) {
        int ans = 0, k = 5;
        while (k <= n) {
            ans += (n / k);
            k *= 5;
        }
        return ans;
    }
}
