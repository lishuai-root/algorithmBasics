package leetcode;

/**
 * @description: Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.
 * <p>
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 * @author: LISHUAI
 * @createDate: 2022/6/25 18:50
 * @version: 1.0
 */

public class LeetCode_665 {

    public static void main(String[] args) {
//        int[] nums = {3, 4, 2, 3};
        int[] nums = {4, 2, 1};
        boolean b = checkPossibility(nums);
        System.out.println(b);
    }

    public static boolean checkPossibility(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }
        boolean l = true;
        int pre = Integer.MIN_VALUE;
        boolean[] bls = new boolean[nums.length];
        bls[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            bls[i] = bls[i + 1] && (nums[i] <= nums[i + 1]);
        }
        if (bls[1]) {
            return bls[0];
        }
        for (int i = 0; i < nums.length - 1 && l; i++) {

            if (l && nums[i + 1] >= pre && bls[i + 1]) {
                return true;
            }
            l &= (nums[i] >= pre);
            pre = nums[i];

        }
        return l;
    }

    private static boolean isNon(int[] nums, int left, int right) {
        for (int i = left + 1; i < right; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
