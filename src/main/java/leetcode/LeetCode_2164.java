package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given a 0-indexed integer array nums. Rearrange the values of nums according to the following rules:
 * <p>
 * Sort the values at odd indices of nums in non-increasing order.
 * For example, if nums = [4,1,2,3] before this step, it becomes [4,3,2,1] after. The values at odd indices 1 and 3 are sorted in non-increasing order.
 * Sort the values at even indices of nums in non-decreasing order.
 * For example, if nums = [4,1,2,3] before this step, it becomes [2,1,4,3] after. The values at even indices 0 and 2 are sorted in non-decreasing order.
 * Return the array formed after rearranging the values of nums.
 * @author: LISHUAI
 * @createDate: 2022/5/2 15:42
 * @version: 1.0
 */

public class LeetCode_2164 {

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 3, 5};
        int[] ints = sortEvenOdd_02(nums);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    public static int[] sortEvenOdd(int[] nums) {
        int[] j = new int[nums.length >>> 1];
        int[] o;
        if ((nums.length & 1) == 1) {
            o = new int[(nums.length >>> 1) + 1];
            o[o.length - 1] = nums[nums.length - 1];
        } else {
            o = new int[nums.length >>> 1];
        }

        for (int i = 0; i < nums.length >>> 1; i++) {
            o[i] = nums[i << 1];
            j[i] = nums[(i << 1) + 1];
        }

        Arrays.sort(o);
        Arrays.sort(j);
        if ((nums.length & 1) == 1) {
            nums[nums.length - 1] = o[o.length - 1];
        }
        int jLen = j.length;
        for (int i = 0; i < j.length; i++) {
            nums[i << 1] = o[i];
            nums[(i << 1) + 1] = j[--jLen];
        }
        return nums;
    }

    public static int[] sortEvenOdd_02(int[] nums) {
        Queue<Integer> min = new PriorityQueue<>();
        Queue<Integer> max = new PriorityQueue<>((a, b) -> {
            return b - a;
        });

        for (int i = 0; i < nums.length >>> 1; i++) {
            min.offer(nums[i << 1]);
            max.offer(nums[(i << 1) + 1]);
        }
        if ((nums.length & 1) == 1) {
            min.offer(nums[nums.length - 1]);
        }
        int len = nums.length >>> 1;
        for (int i = 0; i < len; i++) {
            nums[i << 1] = min.poll();
            nums[(i << 1) + 1] = max.poll();
        }
        if (!min.isEmpty()) {
            nums[nums.length - 1] = min.poll();
        }
        return nums;
    }
}



