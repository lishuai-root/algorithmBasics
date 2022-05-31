package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an integer array nums and an integer k.
 * <p>
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 * <p>
 * Return the maximum number of operations you can perform on the array.
 * @author: LISHUAI
 * @createDate: 2022/5/4 17:01
 * @version: 1.0
 */

public class LeetCode_1679 {

    public static void main(String[] args) {
//        int[] nums = {3, 1, 3, 4, 3};
//        int k = 6;
        int[] nums = {1, 2, 3, 4};
        int k = 5;
        int i = maxOperations(nums, k);
        System.out.println(i);
    }

    public static int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums.length, ans = 0;
        while (--right >= 0 && nums[right] >= k) {
            ;
        }
        while (left < right) {
            while (right > left && nums[left] + nums[right] > k) {
                right--;
            }
            if (right > left && nums[right] + nums[left] == k) {
                ans++;
                right--;
            }
            left++;
        }
        return ans;
    }
}


















