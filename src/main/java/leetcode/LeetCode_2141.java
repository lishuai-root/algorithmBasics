package leetcode;

import java.util.Arrays;

/**
 * @description: You have n computers. You are given the integer n and a 0-indexed integer array batteries where the ith battery can run a computer for batteries[i] minutes. You are interested in running all n computers simultaneously using the given batteries.
 * <p>
 * Initially, you can insert at most one battery into each computer. After that and at any integer time moment, you can remove a battery from a computer and insert another battery any number of times. The inserted battery can be a totally new battery or a battery from another computer. You may assume that the removing and inserting processes take no time.
 * <p>
 * Note that the batteries cannot be recharged.
 * <p>
 * Return the maximum number of minutes you can run all the n computers simultaneously.
 * @author: LISHUAI
 * @createDate: 2022/6/15 22:08
 * @version: 1.0
 */

public class LeetCode_2141 {


    /**
     * teacher method
     *
     * @param n
     * @param batteries
     * @return
     */
    public static long maxRunTime(int n, int[] batteries) {
        Arrays.sort(batteries);
        int size = batteries.length;
        long[] sums = new long[size];
        sums[0] = batteries[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + batteries[i];
        }
        long l = 0;
        long m = 0;
        long r = sums[size - 1] / n;
        long ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (ok(batteries, sums, m, n)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static boolean ok(int[] arr, long[] sum, long time, int num) {
        int left = mostLeft(arr, time);
        num -= arr.length - left;
        long rest = left == 0 ? 0 : sum[left - 1];
        return time * (long) num <= rest;
    }

    // >= time 最左
    public static int mostLeft(int[] arr, long time) {
        int l = 0;
        int m = 0;
        int r = arr.length - 1;
        int ans = arr.length;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] >= time) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
