package leetcode;

import java.util.Random;

/**
 * @description: You are given an integer array nums.
 * <p>
 * You should move each element of nums into one of the two arrays A and B such that A and B are non-empty, and average(A) == average(B).
 * <p>
 * Return true if it is possible to achieve that and false otherwise.
 * <p>
 * Note that for an array arr, average(arr) is the sum of all the elements of arr over the length of arr.
 * @createDate: 2022/12/5 6:50
 * @author: LISHUAI
 * @version: 1.0
 */

public class LeetCode_805 {

    private static int allSum, count = 0;

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
//        int[] nums = {3, 1};
//        int[] nums = {18, 10, 5, 3};
        int[] nums = {3, 1, 2};
//        int[] nums = {60, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
        boolean b = splitArraySameAverage(nums);
        System.out.println(b);
//        boolean b1 = splitArraySameAverage_dp(nums);
//        System.out.println(b1);
//        System.out.println("------------------------------start----------------------------------");
//        for (int i = 0; i < 10; i++) {
//            int target = new Random().nextInt(10000);
//            int[] nums = makeArr(30, target);
//            boolean b = splitArraySameAverage(nums);
//            boolean b1 = splitArraySameAverage_dp(nums);
//
//            if (b != b1) {
//                System.out.println("error : b + - " + b + " , b1 - " + b1);
//                break;
//            }
//        }
//        System.out.println("------------------------------end----------------------------------");
    }

    private static int[] makeArr(int size, int target) {
        int[] nums = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt(target);
        }
        return nums;
    }

    public static boolean splitArraySameAverage(int[] nums) {
        allSum = 0;
        for (int i : nums) {
            allSum += i;
        }
        return splitArraySameAverageProcess(nums, 0, 0, 0);
    }

    private static boolean splitArraySameAverageProcess(int[] nums, int curSum, int k, int index) {
        if (index >= nums.length) {
            return false;
        }
        boolean ans = k != 0 && ((double) curSum) / k == ((double) allSum - curSum) / (nums.length - k);
        if (k > nums.length / 2) {
            return ans;
        }

        if (!ans) {
            ans = splitArraySameAverageProcess(nums, curSum + nums[index], k + 1, index + 1);
        }
        if (!ans) {
            ans = splitArraySameAverageProcess(nums, curSum, k, index + 1);
        }
        return ans;
    }

    private static boolean dfs(int[] nums, int curSum, int k, boolean[] bls) {
        if (k >= nums.length) {
            return false;
        }
        boolean ans = k != 0 && ((double) curSum) / k == ((double) allSum - curSum) / (nums.length - k);
        for (int i = 0; !ans && i < nums.length; i++) {
            if (!bls[i]) {
                bls[i] = true;
                ans = dfs(nums, curSum + nums[i], k + 1, bls);
                bls[i] = false;
            }
        }
        return ans;
    }

    public static boolean splitArraySameAverage_dp(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        int t = sum;

        for (int i = 0; i < nums.length; i++) {
            t -= nums[i];
            for (int j = 0; j <= nums.length - i; j++) {
                for (int k = 0; k <= t; k++) {
                    boolean ans = ((double) k) / j == ((double) sum - k) / (nums.length - j);
                    if (!ans) {
                        ans = dp[j + 1][k + nums[i]] || dp[j][k];
                    }

                    dp[j][k] = ans;
                }
            }
        }
        return dp[0][0];
    }
}
