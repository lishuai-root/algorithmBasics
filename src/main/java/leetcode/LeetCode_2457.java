package leetcode;

/**
 * @description: You are given two positive integers n and target.
 * <p>
 * An integer is considered beautiful if the sum of its digits is less than or equal to target.
 * <p>
 * Return the minimum non-negative integer x such that n + x is beautiful. The input will be generated such that it is always possible to make n beautiful.
 * @author: LiShuai
 * @createDate: 2023/9/4 21:55
 * @version: 1.0
 */

public class LeetCode_2457 {

    public static void main(String[] args) {
//        long n = 16;
//        int target = 6;
        long n = 6068060761L;
        int target = 3;
        long l = makeIntegerBeautiful(n, target);
        System.out.println(l);
    }

    public static long makeIntegerBeautiful(long n, int target) {
        long ans = 0;
        int t = 0;
        long q = 10;
        long mid = n;
        while (mid > 0) {
            int k = (int) (mid % 10);
            t += k;
            mid /= 10;
        }
        mid = n;
        while (t > target) {
            long p = mid % q;
            mid -= p;
            mid += q;
            q *= 10;
            long m = mid;
            t = 0;
            while (m > 0) {
                int k = (int) (m % 10);
                t += k;
                m /= 10;
            }
        }
        return mid - n;
    }
}
