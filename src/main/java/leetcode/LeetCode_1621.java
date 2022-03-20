package leetcode;

import java.util.Arrays;
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

    private static int PREFIX;

    private static Map<String, Long> map;

    static {

        PREFIX = 10;

        for (int i = 0; i < 8; i++) {

            PREFIX = PREFIX * 10;
        }

        PREFIX += 7;
    }

    public static void main(String[] args) {

        int i = numberOfSets_02(108, 6);

        System.out.println(i);
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

        long[][] dp = new long[n + 1][k + 1];

        for (long[] l : dp) {

            Arrays.fill(l, Integer.MIN_VALUE);
        }

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                sum += process_02(dp, j, n - 1, k - 1);
            }
        }


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


    private static long process_02(long[][] dp, int start, int end, int k) {

        if (k == 0) {

            return 1;
        }


        if (start == end) {

            return 0;
        }


        long sum = 0;

        for (int i = start; i < end; i++) {

            for (int j = i + 1; j <= end; j++) {

                if (dp[end - j][k - 1] != Integer.MIN_VALUE) {

                    sum += dp[end - j][k - 1];

                    continue;
                }

                long s = process_02(dp, j, end, k - 1);

                dp[end - j][k - 1] = s % PREFIX;

                sum += (s % PREFIX);
            }
        }

        return sum;
    }


    public static int numberOfSets_02(int n, int k) {

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i < n + 1; i++) {

            dp[i][0] = 1;
        }

        for (int i = 0; i < k + 1; i++) {

            dp[0][i] = 1;
        }


        for (int i = 1; i < n + 1; i++) {

            for (int j = 1; j < k + 1; j++) {

                dp[i][j] += dp[i - 1][j - 1] * 2 % PREFIX;
            }
        }

        for (long[] l : dp) {

            for (long i : l) {

                System.out.print(i + "  ");
            }

            System.out.println();
        }

        return (int) (dp[n][k] % PREFIX);
    }
}
