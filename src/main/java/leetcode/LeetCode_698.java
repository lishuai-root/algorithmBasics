package leetcode;

import java.util.Arrays;

/**
 * @description: Given an integer array nums and an integer k,
 * return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 * @author: LISHUAI
 * @createDate: 2022/2/22 23:22
 * @version: 1.0
 */

public class LeetCode_698 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4};
//        int k = 3;
//        int[] nums = {1, 1, 1, 1, 2, 2, 2, 2};
//        int k = 3;

//        int[] nums = {4, 4, 6, 2, 3, 8, 10, 2, 10, 7};
//        int k = 4;

//        int[] nums = {3522, 181, 521, 515, 304, 123, 2512, 312, 922, 407, 146, 1932, 4037, 2646, 3871, 269};
//        int k = 5;
//        int[] nums = {7628, 3147, 7137, 2578, 7742, 2746, 4264, 7704, 9532, 9679, 8963, 3223, 2133, 7792, 5911, 3979};
//        int k = 6;

//        int[] nums = {2, 2, 2, 2, 3, 4, 5};
//        int k = 4;
//        int[] nums = {4, 3, 2, 3, 5, 2, 1};
//        int k = 4;
//        int[] nums = {3, 3, 10, 2, 6, 5, 10, 6, 8, 3, 2, 1, 6, 10, 7, 2};
//        int k = 6;
        int[] nums = {7628, 3147, 7137, 2578, 7742, 2746, 4264, 7704, 9532, 9679, 8963, 3223, 2133, 7792, 5911, 3979};
        int k = 6;
        boolean b = canPartitionKSubsets(nums, k);
        System.out.println(b);
        System.out.println(canPartitionKSubsets_dp(nums, k));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length < k) {
            return false;
        }
        int[] arr = new int[k];
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
//        return canPartitionKSubsetsProcess(nums, arr, (int) (sum / k), 0, 0);
        return f(nums, sum / k) == k;
    }

    private static int f(int[] nums, int mid) {
        Integer[][] dp = new Integer[nums.length][mid];
        return canPartitionKSubsetsProcess(nums, mid, 0, 0, dp);
    }


    private static int canPartitionKSubsetsProcess(int[] nums, int mid, int sum, int index, Integer[][] dp) {
        if (sum == mid) {
            return 1 + f(nums, mid);
        }
        if (index >= nums.length || sum > mid || sum + nums[index] > mid) {
            return 0;
        }
        if (dp[index][sum] != null) {
            return dp[index][sum];
        }

        int p1 = canPartitionKSubsetsProcess(nums, mid, sum, index + 1, dp);

        int k = nums[index];
        nums[index] = 0;
        int p2 = canPartitionKSubsetsProcess(nums, mid, sum + k, index + 1, dp);
        nums[index] = k;
        dp[index][sum] = Math.max(p1, p2);
        return dp[index][sum];
    }


    private static boolean canPartitionKSubsetsProcess(int[] nums, int[] arr, int mid, int index, int k) {
        if (index >= nums.length) {
            return true;
        }
        for (int i : arr) {
            if (mid != i && mid - i < nums[index]) {
                return false;
            }
        }
        boolean ans = false;
        int len = arr.length, p = (k + 1) % len;
        while (!ans) {
            if (arr[p] + nums[index] <= mid) {
                arr[p] += nums[index];
                ans = canPartitionKSubsetsProcess(nums, arr, mid, index + 1, p);
                arr[p] -= nums[index];
            }
            if (p == k) {
                break;
            }
            p = (++p) % len;
        }

//        for (int i = 0; i < arr.length && !ans; i++) {
//            if (arr[i] + nums[index] <= mid) {
//                arr[i] += nums[index];
//                ans = canPartitionKSubsetsProcess(nums, arr, mid, index + 1, i);
//                arr[i] -= nums[index];
//            }
//        }
        return ans;
    }

    private static boolean canPartitionKSubsetsProcess_02(int[] nums, int[] arr, int mid, int index, int m) {
        if (index >= nums.length) {
            return true;
        }

        if (m >= arr.length) {
            return false;
        }

        boolean ans = canPartitionKSubsetsProcess(nums, arr, mid, index, m + 1);
        if (!ans && arr[m] + nums[index] <= mid) {
            arr[m] += nums[index];
            ans = canPartitionKSubsetsProcess(nums, arr, mid, index + 1, 0);
            arr[m] -= nums[index];
        }

        return ans;
    }

    public static boolean canPartitionKSubsets_dp(int[] nums, int k) {
        if (nums.length < k) {
            return false;
        }
        long sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % k != 0) {
            return false;
        }
        int mid = (int) (sum / k);
        int len = nums.length;
        boolean[][] bls = new boolean[len + 1][k + 1];
        int[][] dp = new int[len + 1][k + 1];
        for (int i = 0; i <= k; i++) {
            bls[len][i] = true;
            dp[len][i] = mid;
        }
//        for (int i = 0; i <= len; i++) {
//            bls[i][k] = true;
//            dp[i][k] = mid;
//        }

        for (int i = len - 1; i >= 0; i--) {
            for (int j = k - 1; j >= 0; j--) {
                boolean ans = bls[i][j + 1];
                if (ans) {
                    dp[i][j] = dp[i][j + 1];
                }
                if (!ans && dp[i + 1][0] - nums[i] >= 0 && bls[i + 1][0]) {
                    ans = bls[i + 1][0];
                    dp[i][j] = dp[i + 1][0] - nums[i];
                }
                bls[i][j] = ans;
            }
        }
//        for (boolean[] bs : bls) {
//            for (boolean b : bs) {
//                System.out.print(b + " ");
//            }
//            System.out.println();
//        }
        return bls[0][0];
    }
}
