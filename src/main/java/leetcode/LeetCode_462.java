package leetcode;

import java.util.Arrays;

/**
 * @description: Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * <p>
 * In one move, you can increment or decrement an element of the array by 1.
 * <p>
 * Test cases are designed so that the answer will fit in a 32-bit integer.
 * @author: LISHUAI
 * @createDate: 2022/6/30 21:06
 * @version: 1.0
 */

public class LeetCode_462 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
        int[] nums = {1, 1, 2};
//        int[] nums = {1, 10, 2, 9};
        int i = minMoves2(nums);
        System.out.println(i);
    }

    public static int minMoves2(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        long preSum = 0, ans = Integer.MAX_VALUE;
        Arrays.sort(nums);
        long[] sum = new long[len];
        sum[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            sum[i] += nums[i] + sum[i + 1];
        }

        int left = 0, right = 0;
        while (right < len) {
            if (nums[left] != nums[right]) {
                long cur = ((long) left * nums[left] - preSum) + (sum[right] - (long) (len - right) * nums[left]);
                ans = (int) Math.min(ans, cur);
                preSum += (right - left) * (long) nums[left];
                left = right;
            }
            right++;
        }
        ans = Math.min(ans, (long) left * nums[left] - preSum);
        return (int) ans;
    }
}
