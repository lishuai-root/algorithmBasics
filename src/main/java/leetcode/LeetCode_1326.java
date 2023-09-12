package leetcode;

import java.util.Arrays;

/**
 * @description: There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
 * <p>
 * There are n + 1 taps located at points [0, 1, ..., n] in the garden.
 * <p>
 * Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.
 * <p>
 * Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.
 * @author: LiShuai
 * @createDate: 2023/8/31 21:15
 * @version: 1.0
 */

public class LeetCode_1326 {

    public static void main(String[] args) {
//        int n = 7;
//        int[] ranges = {1, 2, 1, 0, 2, 1, 0, 1};
        int n = 97;
        int[] ranges = {1, 5, 3, 1, 4, 5, 5, 1, 2, 0, 2, 2, 4, 3, 0, 0, 1, 4, 5, 5, 0, 3, 5, 1, 1, 0, 0, 0, 4, 1, 1, 1, 0, 4, 4, 1, 0, 0, 2, 5, 5, 4, 4, 4, 2, 4, 3, 4, 4, 2, 3, 4, 0, 2, 0, 1, 0, 4, 2, 3, 0, 0, 0, 1, 5, 2, 0, 2, 4, 4, 3, 3, 0, 0, 3, 1, 1, 1, 4, 2, 5, 2, 3, 1, 0, 1, 0, 2, 4, 3, 4, 0, 2, 4, 1, 1, 2, 5};
        int i = minTaps_02(n, ranges);
        System.out.println(i);
    }

    public static int minTaps(int n, int[] ranges) {
        int ans = minTapsProcess(n, ranges, 0, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int minTapsProcess(int n, int[] ranges, int pre, int index) {
        if (pre >= n) {
            return 0;
        }
        if (index > n) {
            return Integer.MAX_VALUE;
        }

        int p1 = minTapsProcess(n, ranges, pre, index + 1);
        int p2 = Integer.MAX_VALUE;
        if (index - ranges[index] <= pre) {
            p2 = minTapsProcess(n, ranges, Math.max(index + ranges[index], pre), index + 1);
            if (p2 != Integer.MAX_VALUE) {
                ++p2;
            }
        }
        return Math.min(p1, p2);
    }

    public static int minTaps_dp(int n, int[] ranges) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0;
        for (int i = n; i >= 0; --i) {
            for (int j = 0; j < n; j++) {
                int p = i - ranges[i] <= j ? dp[Math.min(n, Math.max(i + ranges[i], j))] + 1 : Integer.MAX_VALUE;
                dp[j] = Math.min(dp[j], (p == Integer.MAX_VALUE ? p : p + 1));
            }
        }
        return dp[0] == Integer.MAX_VALUE ? -1 : dp[0];
    }

    public static int minTaps_02(int n, int[] ranges) {
        Integer[] nums = new Integer[n + 1];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        Arrays.sort(nums, (a, b) -> a - ranges[a] - (b - ranges[b]));
        int ans = 0, pre = 0, max = -1;

        for (int i = 0; i <= n; i++) {
            int cur = nums[i];
            if (cur - ranges[cur] <= pre) {
                max = Math.max(max, cur + ranges[cur]);
            } else {
                if (max == -1) {
                    return -1;
                }
                ++ans;
                pre = max;

                if (pre >= n) {
                    break;
                }
                max = -1;
            }
//            System.out.println(pre);
        }
        if (max > pre && pre < n) {
            pre = max;
            ++ans;
        }

        return pre >= n ? ans : -1;
    }
}
