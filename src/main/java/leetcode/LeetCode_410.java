package leetcode;

import java.util.Arrays;

/**
 * @description: Given an array nums which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays.
 * <p>
 * Write an algorithm to minimize the largest sum among these m subarrays.
 * @author: LISHUAI
 * @createDate: 2022/3/31 21:37
 * @version: 1.0
 */

public class LeetCode_410 {

    private static Integer[][] dp;

    public static void main(String[] args) {
//        int[] nums = {7, 2, 5, 10, 8};
//        int m = 2;
//        int[] nums = {1, 2, 3, 4, 5};
//        int m = 2;
        int[] nums = {1, 4, 4};
        int m = 3;
//        int[] nums = {10, 5, 13, 4, 8, 4, 5, 11, 14, 9, 16, 10, 20, 8};
//        int m = 8;
//        int i = splitArray_03(nums, m);
//        System.out.println(i);

        int i = Solution.splitArray(nums, m);
    }

    public static int splitArray(int[] nums, int m) {
        int sum = 0, mid, left = 0, right = 0, ans = 0;
        int[][] dp = new int[m][2];
        for (int i : nums) {
            sum += i;
        }
        System.out.println("sum : " + sum);
        if (m == 1) {
            return sum;
        }
        mid = (sum + m - 1) / m;
//        mid = sum / m;
        System.out.println("mid : " + mid);
        int n = 0;

        while (right < nums.length) {
            while (right < nums.length && n + nums[right] <= mid) {
                n += nums[right++];
            }

            if (n < mid && right < nums.length) {
                n += nums[right++];
            }
            ans = Math.max(ans, n);
//            while (left < right && n >= mid) {
//                ans = Math.max(ans, n);
//                n -= nums[left++];
//            }

            n -= nums[left++];
            while (right > left && n > mid) {
                n -= nums[--right];
            }

        }

        return ans;
    }

    public static int splitArray_02(int[] nums, int m) {
        dp = new Integer[nums.length][m];
        return splitArrayProcess(nums, m - 1, 0);
    }

    private static int splitArrayProcess(int[] nums, int m, int index) {
        if (m < 0) {
            return 0;
        }
        int sum = 0, ans = Integer.MAX_VALUE;

        if (dp[index][m] != null) {
            return dp[index][m];
        }
        if (m == 0) {
            for (int i = index; i < nums.length; i++) {
                sum += nums[i];
            }
            dp[index][m] = sum;
            return sum;
        }

        for (int i = index; i < nums.length - m; i++) {
            sum += nums[i];
            if (sum > ans) {
                break;
            }
            ans = Math.min(ans, Math.max(sum, splitArrayProcess(nums, m - 1, i + 1)));
        }
        dp[index][m] = ans;
        return ans;
    }

    public static int splitArray_03(int[] nums, int m) {
        int[][] dp = new int[m][nums.length];
        int[] sums = new int[nums.length];

        sums[0] = nums[0];
        dp[0][0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
            dp[0][i] = sums[i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < nums.length; j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = 0; k < j; k++) {
                    ans = Math.min(ans, Math.max(sums[j] - sums[k], dp[i - 1][k]));
                }
                dp[i][j] = ans;
            }
        }

        return dp[m - 1][nums.length - 1];
    }

    /**
     * other people method
     *
     * @param nums
     * @param m
     * @return
     */
    public int splitArray_04(int[] nums, int m) {
        /**
         dp[i][j] = ans of sub-problem, splitting nums[0] ~ nums[j] into i groups
         goal: get dp[m][nums.length - 1]
         dp[1][j] = sum(0, j)
         dp[i][j] = min(dp[i][j], max(dp[i - 1][k], sum(k + 1, j)))  0 <=k < j
         sum(k + 1, j) = sum(0, j) - sum(0, k)
         */
        int[] sums = new int[nums.length];
        int[][] dp = new int[m + 1][nums.length];
        for (int[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE);
        sums[0] = nums[0];
        dp[1][0] = sums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = nums[i] + sums[i - 1];
            dp[1][i] = sums[i];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k], (sums[j] - sums[k])));
                }
            }
        }
        return dp[m][nums.length - 1];
    }

    /**
     * other people method
     */
    static class Solution {
        public static int splitArray(int[] nums, int m) {
            int low = 0, high = 0;
            for (int i : nums) {
                low = Math.max(low, i);
                high += i;
            }

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (isPossible(nums, m, mid)) {
                    high = mid - 1;
                } else low = mid + 1;
            }
            return low;
        }

        static boolean isPossible(int[] nums, int m, int limit) {
            int count = 1, sum = 0;

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (sum > limit) {
                    count++;
                    sum = nums[i];
                    if (count > m)
                        return false;
                }
            }
            return true;
        }
    }
}
