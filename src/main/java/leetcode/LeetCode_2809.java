package leetcode;

import java.util.*;

/**
 * @description: You are given two 0-indexed integer arrays nums1 and nums2 of equal length. Every second, for all indices 0 <= i < nums1.length, value of nums1[i] is incremented by nums2[i]. After this is done, you can do the following operation:
 * <p>
 * Choose an index 0 <= i < nums1.length and make nums1[i] = 0.
 * You are also given an integer x.
 * <p>
 * Return the minimum time in which you can make the sum of all elements of nums1 to be less than or equal to x, or -1 if this is not possible.
 * @author: LiShuai
 * @createDate: 2023/8/8 20:52
 * @version: 1.0
 */

public class LeetCode_2809 {

    private static int total1, total2;
    private static Map<Integer, Integer>[][] cache;

    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 3}, nums2 = {1, 2, 3};
//        int[] nums1 = {4, 5, 3, 2, 3, 9, 5, 7, 10, 4}, nums2 = {4, 4, 0, 4, 1, 2, 4, 0, 4, 0};
//        int[] nums1 = {4, 7, 2, 3, 4, 3, 10, 8}, nums2 = {3, 4, 0, 1, 1, 0, 2, 2};
        int[] nums1 = {1, 7, 9, 4, 8, 8, 1}, nums2 = {2, 2, 3, 2, 0, 1, 0};
//        int[] nums1 = {37, 87, 5, 33, 90, 37, 87, 86, 55, 68, 52, 41, 84, 74, 73, 28, 9, 61, 49, 6, 30, 31, 25, 45, 70, 80, 66, 18, 84, 60, 18, 26, 54, 17, 40, 85, 72, 56, 36, 15, 37, 93, 12, 42, 84, 38, 45, 46, 60, 5, 90, 36, 25, 79, 83, 2, 42, 1, 83, 76, 8, 10, 24, 30, 60, 32, 35, 36, 58, 63, 84, 62, 48, 70, 14, 97, 9, 100, 4, 95, 12, 15, 56, 15, 88},
//                nums2 = {0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1};
        List<Integer> n1 = new ArrayList<>();
        List<Integer> n2 = new ArrayList<>();
        for (int i : nums1) {
            n1.add(i);
        }
        for (int i : nums2) {
            n2.add(i);
        }
//        int x = 4;
//        int x = 1471;
//        int x = 47;
//        int x = 36;
        int x = 20;
        int i = minimumTime(n1, n2, x);
        System.out.println(i);
        System.out.println(minimumTime_dp(n1, n2, x));
        System.out.println(minimumTime_02(n1, n2, x));
    }


    public static int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int len = nums1.size(), ns1 = 0, ns2 = 0;
        List<Integer> is = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            is.add(i);
            ns1 += nums1.get(i);
            ns2 += nums2.get(i);
        }
        is.sort((a, b) -> nums2.get(a) - nums2.get(b));
        total1 = ns1;
        total2 = ns2;
        cache = new Map[len][len + 1];
        int i = minimumTimeProcess(nums1, nums2, x, is, 0, 0, ns1);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private static int minimumTimeProcess(List<Integer> nums1, List<Integer> nums2, int x,
                                          List<Integer> is, int index, int n, int sum) {
        if (sum <= x) {
            return n;
        }
        if (index >= is.size()) {
            return Integer.MAX_VALUE;
        }
        Map<Integer, Integer> cc = cache[index][n];
        if (cc == null) {
            cc = new HashMap<>();
            cache[index][n] = cc;
        }
        if (cc.containsKey(sum)) {
            return cc.get(sum);
        }
        int p1 = minimumTimeProcess(nums1, nums2, x, is, index + 1, n, sum);
        int ci = is.get(index);
        int q = sum + total2 - nums1.get(ci) - (nums2.get(ci) * (n + 1));
        int p2 = minimumTimeProcess(nums1, nums2, x, is, index + 1, n + 1, q);
        cc.put(sum, Math.min(p1, p2));
        return Math.min(p1, p2);
    }

    public static int minimumTime_dp(List<Integer> nums1, List<Integer> nums2, int x) {
        int len = nums1.size(), ns1 = 0, ns2 = 0;
        Integer[] is = new Integer[len];
        for (int i = 0; i < len; i++) {
            is[i] = i;
            ns1 += nums1.get(i);
            ns2 += nums2.get(i);
        }
        if (ns1 <= x) {
            return 0;
        }
        Arrays.sort(is, (a, b) -> nums2.get(a) - nums2.get(b));
        int[] dp = new int[len];
        Arrays.fill(dp, ns1);

        for (int i = 1; i <= len; i++) {
            int q = i - 2 >= 0 ? dp[i - 2] : ns1;
            for (int j = i - 1; j < len; ++j) {
                int index = is[j];
                int t = dp[j];
                dp[j] = q + ns2 - i * nums2.get(index) - nums1.get(index);
                if (dp[j] <= x) {
                    return i;
                }
                q = Math.min(q, t);
            }
        }
        return -1;
    }

    /**
     * 错误的
     *
     * @param nums1
     * @param nums2
     * @param x
     * @return
     */
    public static int minimumTime_02(List<Integer> nums1, List<Integer> nums2, int x) {
        int len = nums1.size(), ns1 = 0, ns2 = 0;
        Integer[] is = new Integer[len];
        for (int i = 0; i < len; i++) {
            is[i] = i;
            ns1 += nums1.get(i);
            ns2 += nums2.get(i);
        }
        if (ns1 <= x) {
            return 0;
        }
        Arrays.sort(is, (a, b) -> {
            int c = nums2.get(a) - nums2.get(b);
            if (c == 0) {
                c = nums1.get(a) - nums1.get(b);
            }
            return c;
        });
        int[] cache = new int[len];
        cache[0] = nums1.get(is[0]) + nums2.get(is[0]);

        for (int i = 1; i < len; i++) {
            cache[i] = Math.max(cache[i - 1], nums1.get(is[i]) + nums2.get(is[i]));
        }

        for (int i = len - 1; i >= 0; --i) {
            if (ns1 + ns2 - cache[i] <= x) {
                return len - i;
            }
            int index = is[i];
            ns2 -= nums2.get(index);
            ns1 += ns2 - nums1.get(index);
            if (ns1 <= x) {
                return len - i;
            }
        }
        return -1;
    }
}
