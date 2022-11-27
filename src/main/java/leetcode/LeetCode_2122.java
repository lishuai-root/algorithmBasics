package leetcode;

import java.util.Arrays;

/**
 * @description: Alice had a 0-indexed array arr consisting of n positive integers. She chose an arbitrary positive integer k and created two new 0-indexed integer arrays lower and higher in the following manner:
 * <p>
 * lower[i] = arr[i] - k, for every index i where 0 <= i < n
 * higher[i] = arr[i] + k, for every index i where 0 <= i < n
 * Unfortunately, Alice lost all three arrays. However, she remembers the integers that were present in the arrays lower and higher, but not the array each integer belonged to. Help Alice and recover the original array.
 * <p>
 * Given an array nums consisting of 2n integers, where exactly n of the integers were present in lower and the remaining in higher, return the original array arr. In case the answer is not unique, return any valid array.
 * <p>
 * Note: The test cases are generated such that there exists at least one valid array arr.
 * @author: LISHUAI
 * @createDate: 2022/9/15 21:21
 * @version: 1.0
 */

public class LeetCode_2122 {
    public static void main(String[] args) {
//        int[] nums = {2, 10, 6, 4, 8, 12};
        int[] nums = {1, 1, 3, 3};
        int[] ints = recoverArray(nums);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    public static int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        int k = 0;
        for (int i : nums) {
            if (i != nums[0]) {
                k = i - nums[0] >>> 1;
                break;
            }
        }
        int n = nums.length >>> 1;
        int[] ans = new int[n];

        for (int i = 0; i < nums.length; i += 2) {
            ans[i >>> 1] = nums[i] + k;
        }
        return ans;
    }
}