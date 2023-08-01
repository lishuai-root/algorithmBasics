package leetcode;

import java.util.LinkedList;

/**
 * @description: Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
 * <p>
 * A subarray is a contiguous part of an array.
 * @author: LiShuai
 * @createDate: 2023/7/31 22:42
 * @version: 1.0
 */

public class LeetCode_862 {

    public static void main(String[] args) {
//        int[] nums = {56, -21, 56, 35, -9};
//        int k = 61;
//        int[] nums = {84, -37, 32, 40, 95};
//        int k = 167;
        int[] nums = {1};
        int k = 1;
        int i = shortestSubarray(nums, k);
        System.out.println(i);
    }

    public static int shortestSubarray(int[] nums, int k) {
        int len = nums.length, ans = Integer.MAX_VALUE;
        long sum = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        long[] total = new long[len];

        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (sum >= k) {
                ans = Math.min(ans, i + 1);
            }
            long p = sum - k;
            while (!queue.isEmpty() && total[queue.getFirst()] <= p) {
                ans = Math.min(ans, i - queue.removeFirst());
            }
            while (!queue.isEmpty() && total[queue.getLast()] >= sum) {
                queue.removeLast();
            }
            queue.addLast(i);
            total[i] = sum;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
