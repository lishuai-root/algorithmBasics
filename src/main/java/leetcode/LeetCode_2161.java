package leetcode;

/**
 * @description: You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that the following conditions are satisfied:
 * <p>
 * Every element less than pivot appears before every element greater than pivot.
 * Every element equal to pivot appears in between the elements less than and greater than pivot.
 * The relative order of the elements less than pivot and the elements greater than pivot is maintained.
 * More formally, consider every pi, pj where pi is the new position of the ith element and pj is the new position of the jth element. For elements less than pivot, if i < j and nums[i] < pivot and nums[j] < pivot, then pi < pj. Similarly for elements greater than pivot, if i < j and nums[i] > pivot and nums[j] > pivot, then pi < pj.
 * Return nums after the rearrangement.
 * @author: LISHUAI
 * @createDate: 2022/7/22 21:15
 * @version: 1.0
 */

public class LeetCode_2161 {

    public static int[] pivotArray(int[] nums, int pivot) {
        int less = 0, ge = 0, eq = 0;
        for (int i : nums) {
            if (i < pivot) {
                ++less;
            } else if (i == pivot) {
                ++eq;
            }
        }
        ge = less + eq;
        eq = less;
        less = 0;
        int[] ans = new int[nums.length];
        for (int num : nums) {
            if (num < pivot) {
                ans[less++] = num;
            } else if (num == pivot) {
                ans[eq++] = num;
            } else {
                ans[ge++] = num;
            }
        }
        return ans;
    }
}
