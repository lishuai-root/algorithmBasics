package leetcode;

import java.util.Map;

/**
 * @description: Given n points on a 1-D plane, where the ith point (from 0 to n-1) is at x = i,
 * find the number of ways we can draw exactly k non-overlapping line segments such that each segment covers two or more points.
 * The endpoints of each segment must have integral coordinates. The k line segments do not have to cover all n points,
 * and they are allowed to share endpoints.
 * <p>
 * Return the number of ways we can draw k non-overlapping line segments. Since this number can be huge, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2021/12/20 20:36
 * @version: 1.0
 */

public class LeetCode_1621 {

    private static int PREFIX = 1000000007;

    private static Map<String, Long> map;
    private static Long[][][] dps;

    static {

        PREFIX = 10;

        for (int i = 0; i < 8; i++) {

            PREFIX = PREFIX * 10;
        }

        PREFIX += 7;
    }

    public static void main(String[] args) {

//        int i = numberOfSets_02(108, 6);
        int i = numberOfSets(921, 753);
//        int i = numberOfSets(251, 234);
//        int i = numberOfSets(315, 81);
//        int i = numberOfSets(751, 201);

        System.out.println(i);
        int i2 = numberOfSets_03(921, 753);

        System.out.println(i2);
        int i3 = numberOfSets_04(921, 753);

        System.out.println(i3);
    }

    public static int numberOfSets(int n, int k) {

        long sum = 0;

//        map = new HashMap<>();
//
//        for (int i = 0; i < n; i++) {
//
//            for (int j = i + 1; j < n; j++) {
//
//                sum += process(j, n - 1, k - 1);
//            }
//        }
//
//        System.out.println(sum % PREFIX);
//
//        sum = 0;
        dps = new Long[n + 1][n + 1][k + 1];
        Long[][] dp = new Long[n + 1][k + 1];


//        for (int i = 0; i < n; i++) {
//
//            for (int j = i + 1; j < n; j++) {

        sum += process_02(dp, 0, n - 1, k);
//            }
//        }


        return (int) (sum % PREFIX);
    }

    private static long process(int start, int end, int k) {

        if (k == 0) {

//            System.out.println("start : " + start);
//            System.out.println("end : " + end);
//
//            System.out.println("----------------");
            return 1;
        }


        if (start == end) {

            return 0;
        }


        long sum = 0;

        for (int i = start; i < end; i++) {

            for (int j = i + 1; j <= end; j++) {

                String line = (end - j) + "-" + (k - 1);

                if (map.containsKey(line)) {

                    sum += map.get(line);

                    continue;
                }

                long s = process(j, end, k - 1);

                map.put(line, s % PREFIX);

                sum += (s % PREFIX);
            }
        }

        return sum;
    }


    private static long process_02(Long[][] dp, int start, int end, int k) {

        if (k == 0) {
            return 1;
        }

        if (start == end) {
            return 0;
        }

        if (dp[end - start][k] != null) {
            return dp[end - start][k];
        }
//        if (dps[start][end][k] != null) {
//            return dps[start][end][k];
//        }
        long sum = 0;

//        for (int i = start; i <= end - k && i == start; i++) {
////            long s = process_02(dp, i + 1, end, k - 1);
////            sum += (s % PREFIX);
//            for (int j = i + 1; j <= end - k + 1; j++) {
//                long s = process_02(dp, j, end, k - 1);
//                s = s * (j - i);
//                sum += (s % PREFIX);
//            }
//        }

        for (int i = start + 1; i <= end - k + 1; i++) {
            long s = process_02(dp, i, end, k - 1);
            s = s * (i - start);
            sum += (s % PREFIX);
        }

//        dps[start][end][k] = sum;
        dp[end - start][k] = sum;
        return sum;
    }

    public static int numberOfSets_03(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        long[][] cache = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            cache[i][0] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {
//                for (int l = i + 1; l < n - j + 1; l++) {
//                    dp[i][j] = (dp[i][j] + dp[l][j - 1] * (l - i)) % PREFIX;
//                }
//                cache[i + 1][j - 1] = cache[i + 2][j - 1] + dp[i + 1][j - 1];
                dp[i][j] = (dp[i + 1][j] + cache[i + 1][j - 1]) % PREFIX;
                cache[i][j - 1] = cache[i + 1][j - 1] + dp[i][j - 1];
            }
        }
//        for (long[] ls : dp) {
//            for (long l : ls) {
//                System.out.print(l + " ");
//            }
//            System.out.println();
//        }
        return (int) dp[1][k];
    }

    public static int numberOfSets_04(int n, int k) {
        long[] dp = new long[k + 1];
        long[] cache = new long[k + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[j] = (dp[j] + cache[j - 1]) % PREFIX;
                cache[j - 1] = cache[j - 1] + dp[j - 1];
            }
        }
        return (int) dp[k];
    }


    public int numberOfSets_other(int n, int k) {
        if (n - 1 >= k) {
            /* dp[i][j] = the number of ways to divide string of length i into (j+1) non-overlapping segments
               i is ALWAYS larger than or equal to j+1.
               dp[i][j] = dp[i-1][j] + (dp[i-1][j-1] + dp[i-2][j-1] + dp[i-3][j-1] + ... + dp[j][j-1])
             */
            int[] dp = new int[k];
            int[] sums = new int[k];
            int mod = (int) (1e9 + 7);

            /* diff is defined as (length - segments), whereas 'length' is the length of the line.
               each time, we start with dp[length][0] and goes diagonally to dp[length+1][1],...,dp[length+k-1][k-1]
             */
            for (int diff = 1; diff < n - k + 1; diff++) {
                dp[0] = ((diff + 1) * diff) >> 1;
                sums[0] = (sums[0] + dp[0]) % mod;
                for (int segments = 2; segments <= k; segments++) {
                    dp[segments - 1] = (sums[segments - 2] + dp[segments - 1]) % mod;
                    sums[segments - 1] = (sums[segments - 1] + dp[segments - 1]) % mod;
                }
            }

            return dp[k - 1];
        } else {
            return 0;
        }
    }

}
