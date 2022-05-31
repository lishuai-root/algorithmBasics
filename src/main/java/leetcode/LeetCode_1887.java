package leetcode;

import java.util.*;

/**
 * @description: Given an integer array nums,
 * your goal is to make all elements in nums equal. To complete one operation, follow these steps:
 * <p>
 * Find the largest value in nums. Let its index be i (0-indexed) and its value be largest.
 * If there are multiple elements with the largest value, pick the smallest i.
 * Find the next largest value in nums strictly smaller than largest. Let its value be nextLargest.
 * Reduce nums[i] to nextLargest.
 * Return the number of operations to make all elements in nums equal.
 * @author: LISHUAI
 * @createDate: 2022/3/24 21:04
 * @version: 1.0
 */

public class LeetCode_1887 {

    public static void main(String[] args) {
        int[] nums = {5, 1, 3};
        int i = reductionOperations_04(nums);
        System.out.println(i);
    }

    public static int reductionOperations(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int ans = 0;

        for (int i : nums) {
            if (!map.containsKey(i)) {
                queue.offer(i);
            }
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        while (queue.size() > 1) {
            int cur = queue.poll();
            ans += map.get(cur);
            map.put(queue.peek(), map.get(queue.peek()) + map.get(cur));
        }
        return ans;
    }


    public static int reductionOperations_02(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int ans = 0, pre = 0;

        for (int i : nums) {
            if (!map.containsKey(i)) {
                queue.offer(i);
            }
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        while (queue.size() > 1) {
            int cur = queue.poll();
            pre += map.get(cur);
            ans += pre;
        }
        return ans;
    }

    public static int reductionOperations_03(int[] nums) {
        Arrays.sort(nums);
        int left = nums.length - 2, ans = 0;

        while (left >= 0) {

            while (left >= 0 && nums[left] == nums[left + 1]) {
                left--;
            }
            if (left < 0) {
                break;
            }
            ans += nums.length - left - 1;

            left--;
        }

        return ans;
    }

    public static int reductionOperations_04(int[] nums) {
        Arrays.sort(nums);
        int left = 0, ans = 0;

        while (++left < nums.length && nums[left] == nums[left - 1]) {
            ;
        }

        while (left < nums.length) {
            ans += nums.length - left;
            while (++left < nums.length && nums[left] == nums[left - 1]) {
                ;
            }
        }

        return ans;
    }
}
