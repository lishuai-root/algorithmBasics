package leetcode;

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
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        boolean b = canPartitionKSubsets(nums, k);
        System.out.println(b);
        System.out.println(canPartitionKSubsets_dp(nums, k));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length < k) {
            return false;
        }
        int[] arr = new int[k];
        long sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % k != 0) {
            return false;
        }
        return canPartitionKSubsetsProcess(nums, arr, (int) (sum / k), 0);
    }

    private static boolean canPartitionKSubsetsProcess(int[] nums, int[] arr, int mid, int index) {
        if (index >= nums.length) {
            return true;
        }

        boolean ans = false;
        for (int i = 0; i < arr.length && !ans; i++) {
            if (arr[i] + nums[index] <= mid) {
                arr[i] += nums[index];
                ans = canPartitionKSubsetsProcess(nums, arr, mid, index + 1);
                arr[i] -= nums[index];
            }
        }
        return ans;
    }

    private static boolean canPartitionKSubsetsProcess(int[] nums, int[] arr, int mid, int index, int m) {
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
        for (boolean[] bs : bls) {
            for (boolean b : bs) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        return bls[0][0];
    }
}
