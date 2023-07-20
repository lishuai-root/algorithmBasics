package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given an array nums consisting of positive integers.
 * <p>
 * Starting with score = 0, apply the following algorithm:
 * <p>
 * Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
 * Add the value of the chosen integer to score.
 * Mark the chosen element and its two adjacent elements if they exist.
 * Repeat until all the array elements are marked.
 * Return the score you get after applying the above algorithm.
 * @author: LiShuai
 * @createDate: 2023/7/18 21:37
 * @version: 1.0
 */

public class LeetCode_2593 {

    public static void main(String[] args) {
        int[] nums = {2, 1, 3, 4, 5, 2};
        long score02 = findScore_02(nums);
    }

    public static long findScore(int[] nums) {
        int len = nums.length;
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> {
            if (nums[a] != nums[b]) {
                return nums[a] - nums[b];
            }
            return a - b;
        });
        for (int i = 0; i < len; i++) {
            queue.offer(i);
        }
        long ans = 0;
        int[] cache = new int[len];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cache[cur] == 0) {
                ans += nums[cur];
                cache[cur] = 1;
                if (cur > 0) {
                    cache[cur - 1] = 1;
                }
                if (cur < len - 1) {
                    cache[cur + 1] = 1;
                }
            }
        }
        return ans;
    }

    public static long findScore_02(int[] nums) {
        int len = nums.length;
        Integer[] cache = new Integer[len];
        for (int i = 0; i < len; i++) {
            cache[i] = i;
        }
        Arrays.sort(cache, (a, b) -> {
            if (nums[a] != nums[b]) {
                return nums[a] - nums[b];
            }
            return a - b;
        });
        long ans = 0;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            int k = cache[i];
            if (arr[k] == 0) {
                arr[k] = 1;
                ans += nums[k];
                if (k > 0) {
                    arr[k - 1] = 1;
                }
                if (k < len - 1) {
                    arr[k + 1] = 1;
                }
            }
        }
        return ans;
    }
}
