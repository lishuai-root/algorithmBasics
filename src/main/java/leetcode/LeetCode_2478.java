package leetcode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description: You are given a string s that consists of the digits '1' to '9' and two integers k and minLength.
 * <p>
 * A partition of s is called beautiful if:
 * <p>
 * s is partitioned into k non-intersecting substrings.
 * Each substring has a length of at least minLength.
 * Each substring starts with a prime digit and ends with a non-prime digit. Prime digits are '2', '3', '5', and '7', and the rest of the digits are non-prime.
 * Return the number of beautiful partitions of s. Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * @author: LISHUAI
 * @createDate: 2023/2/12 14:15
 * @version: 1.0
 */

public class LeetCode_2478 {

    private static final Set<Character> set;
    private static final int T;

    private static int TEMP = 1000000007;

    static {
        set = new HashSet<Character>(4);
        set.add('2');
        set.add('3');
        set.add('5');
        set.add('7');
        int m = 0;
        m |= (1 << 2);
        m |= (1 << 3);
        m |= (1 << 5);
        m |= (1 << 7);
        T = m;
    }


    public static void main(String[] args) {
//        String s = "23542185131";
//        int k = 3;
//        int minLength = 2;
        String s = "44";
        int k = 1;
        int minLength = 1;
//        String s = "242538614532395749146912679859";
//        int k = 1;
//        int minLength = 6;
//        int i = beautifulPartitions(s, k, minLength);
//        System.out.println(i);
//        System.out.println(beautifulPartitions_dp(s, k, minLength));
        Map<String, String> getenv = System.getenv();
    }

    public static int beautifulPartitions(String s, int k, int minLength) {
        int len = s.length();
        if (k * minLength > s.length() || !set.contains(s.charAt(0)) || set.contains(s.charAt(len - 1))) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return beautifulPartitionsProcess(s, k, minLength, minLength - 1);
    }

    private static int beautifulPartitionsProcess(String s, int k, int minLength, int index) {

        if (index >= s.length()) {
            return 0;
        }
        if (k == 1) {
            return index < s.length() ? 1 : 0;
        }

        int ans = beautifulPartitionsProcess(s, k, minLength, index + 1);
        if (!set.contains(s.charAt(index))) {
            if (index + 1 < s.length() && set.contains(s.charAt(index + 1)) && index + minLength < s.length()) {
                ans += beautifulPartitionsProcess(s, k - 1, minLength, index + minLength);
            }
        }
        return ans % TEMP;
    }

    public static int beautifulPartitions_dp(String s, int k, int minLength) {
        int len = s.length();
        if (k * minLength > s.length() || !set.contains(s.charAt(0)) || set.contains(s.charAt(len - 1))) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        int[][] dp = new int[k][len + 1];
        boolean[] bls = new boolean[len];
        for (int i = 0; i < len; i++) {
            bls[i] = set.contains(s.charAt(i));
            dp[0][i] = 1;
        }
        for (int i = 0; i < len - 1; i++) {
            bls[i] = bls[i + 1] && !set.contains(s.charAt(i));
        }
        int p = (k - 1) * (minLength - 1);
        for (int i = 1; i < k; i++, p -= (minLength - 1)) {
            int q = minLength * i;
            for (int j = len - q; j >= p; j--) {
                dp[i][j] = bls[j] ? (dp[i][j + 1] + dp[i - 1][j + minLength]) % TEMP : dp[i][j + 1];
            }
        }
        return dp[k - 1][minLength - 1];
    }
}
