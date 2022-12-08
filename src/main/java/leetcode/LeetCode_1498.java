package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array of integers nums and an integer target.
 * <p>
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target.
 * Since the answer may be too large, return it modulo 109 + 7.
 * @author: LISHUAI
 * @createDate: 2022/12/3 4:40
 * @version: 1.0
 */

public class LeetCode_1498 {

    public static void main(String[] args) {
        int[] nums = {3, 5, 6, 7};
        int target = 10;
//        int[] nums = {3, 3, 3, 6, 8};
//        int target = 10;
//        int[] nums = {2, 3, 3, 4, 6, 7, 2};
//        int target = 12;
//        int[] nums = {7, 10, 7, 3, 7, 5, 4};
//        int target = 12;
        int i = numSubseq(nums, target);
        System.out.println(i);
        System.out.println(numSubseq_dp(nums, target));
//        System.out.println(subArr(nums, 0, 0));
    }

    private static int subArr(int[] nums, int c, int index) {
        if (index >= nums.length) {
            return c > 0 ? 1 : 0;
        }
        return subArr(nums, c + 1, index + 1) + subArr(nums, c, index + 1);
    }

    public static int numSubseq(int[] nums, int target) {
        return numSubseqProcess(nums, target, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
    }


    public static int numSubseqProcess(int[] nums, int target, int min, int max, int index) {
        if (index >= nums.length) {
            return 0;
        }
        int p1 = numSubseqProcess(nums, target, min, max, index + 1);
        int n = Math.min(min, nums[index]), x = Math.max(max, nums[index]);
        int p2 = numSubseqProcess(nums, target, n, x, index + 1);
        if (target - n >= x) {
            p2 += 1;
        }
        return (p1 + p2) % 100000007;
    }


    public static int numSubseq_dp(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0, n = nums.length, l = 0, r = n - 1, mod = (int) 1e9 + 7;
        int[] pows = new int[n];
        pows[0] = 1;
        for (int i = 1; i < n; ++i)
            pows[i] = pows[i - 1] * 2 % mod;
        while (l <= r) {
            if (nums[l] + nums[r] > target) {
                r--;
            } else {
                res = (res + pows[r - l++]) % mod;
            }
        }
        return res;
    }
}
