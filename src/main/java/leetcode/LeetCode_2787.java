package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given two positive integers n and x.
 * <p>
 * Return the number of ways n can be expressed as the sum of the xth power of unique positive integers, in other words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.
 * <p>
 * Since the result can be very large, return it modulo 109 + 7.
 * <p>
 * For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.
 * @author: LiShuai
 * @createDate: 2023/9/9 21:06
 * @version: 1.0
 */

public class LeetCode_2787 {

    public static void main(String[] args) {
//        int n = 10, x = 2;
        int n = 8, x = 3;
        int i = numberOfWays(n, x);
        System.out.println(i);
    }

    public static int numberOfWays(int n, int x) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; true; i++) {
            int c = i;
            for (int j = 1; j < x; j++) {
                c *= i;
            }
            if (c > n) {
                break;
            }
            list.add(c);
        }
        int len = list.size();
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int i = len - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                int p = j + list.get(i);
                if (p <= n) {
                    dp[j] += dp[p];
                    dp[j] %= 1000000007;
                }
            }
        }
        return dp[0];
    }

    public static int numberOfWays_02(int n, int x) {
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int i = 1; true; i++) {
            int c = i;
            for (int j = 1; j < x; j++) {
                c *= i;
            }
            if (c > n) {
                break;
            }
            for (int j = 0; j < n; ++j) {
                int p = j + c;
                if (p <= n) {
                    dp[j] += dp[p];
                    dp[j] %= 1000000007;
                }
            }
        }
        return dp[0];
    }
}
