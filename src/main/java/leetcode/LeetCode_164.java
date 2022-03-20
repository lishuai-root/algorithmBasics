package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description: Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
 * <p>
 * You must write an algorithm that runs in linear time and uses linear extra space.
 * @author: LISHUAI
 * @createDate: 2021/6/24 19:02
 * @version: 1.0
 */

public class LeetCode_164 {

    public static void main(String[] args) {
        int[] arr = {3, 6, 9, 1};

        int i = maximumGap(arr);

        System.out.println(i);
    }


    public static int maximumGap(int[] nums) {
        if (nums.length < 2)
            return 0;

        Arrays.sort(nums);

        int max = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }

        return max;
    }

    public static int maximumGap_002(int[] nums) {
        if (nums.length < 2)
            return 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }

        int max = Integer.MIN_VALUE;

        int prve = queue.poll(), next = 0;

        while (!queue.isEmpty()) {
            next = queue.poll();

            max = Math.max(max, next - prve);

            prve = next;
        }

        return max;
    }


}

