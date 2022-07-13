package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * <p>
 * In one move, you can increment n - 1 elements of the array by 1.
 * @author: LISHUAI
 * @createDate: 2022/6/15 20:11
 * @version: 1.0
 */

public class LeetCode_453 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int i = minMoves_02(nums);
        System.out.println(i);
    }

    public static int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int[] base = new int[1];
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> {
            return (b + base[0]) - (a + base[0]);
        });
        for (int i : nums) {
            min = Math.min(min, i);
            queue.offer(i);
        }

        while (min != queue.peek() + base[0]) {
            int max = queue.poll() + base[0];
            base[0] += max - min;
            queue.offer(max - base[0]);
            min = max;
        }
        return base[0];
    }

    public static int minMoves_02(int[] nums) {
        int base = 0, len = nums.length, min = 0, max = len - 1;
        Arrays.sort(nums);

        while (nums[min] != nums[max]) {
            base += nums[max] - nums[min];
            nums[max] = nums[min];
            min = max;
            max = max > 0 ? max - 1 : len - 1;
        }
        return base;
    }
}
