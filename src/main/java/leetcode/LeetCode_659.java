package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @description: You are given an integer array nums that is sorted in non-decreasing order.
 * <p>
 * Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:
 * <p>
 * Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
 * All subsequences have a length of 3 or more.
 * Return true if you can split nums according to the above conditions, or false otherwise.
 * <p>
 * A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).
 * @author: LISHUAI
 * @createDate: 2022/5/23 23:28
 * @version: 1.0
 */

public class LeetCode_659 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 3, 4, 4, 5, 5};
//        int[] nums = {1, 2, 3, 3, 4, 5};
        int[] nums = {1, 2, 3, 4, 4, 5};
//        int[] nums = {1, 2, 3, 4, 5, 5, 6, 7};
//        int[] nums = {9, 10, 11, 12, 13, 14, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 41, 42, 42, 43, 44, 45, 46, 47, 47, 48, 48, 49, 49, 50, 50, 51, 51, 51, 52, 52, 52, 53, 53, 53, 54, 54, 54, 55, 55, 55, 56, 56, 56, 57, 57, 57, 58, 58, 58, 59, 59, 59, 59, 60, 60, 60, 60, 61, 61, 61, 61, 62, 62, 62, 62, 63, 63, 63, 63, 64, 64, 64, 64, 65, 65, 65, 65, 66, 66, 66, 66, 67, 67, 67, 67, 68, 68, 68, 68, 69, 69, 69, 69, 70, 70, 70, 70, 71, 71, 71, 71, 72, 72, 72, 72, 73, 73, 73, 73, 74, 74, 74, 74, 75, 75, 75, 75, 76, 76, 76, 76, 76, 77, 77, 77, 77, 77, 78, 78, 78, 78, 78, 79, 79, 79, 79, 80, 80, 80, 80, 81, 81, 81, 81, 82, 82, 82, 82, 83, 83, 83, 83, 84, 84, 84, 84, 85, 85, 85, 85, 85, 86, 86, 86, 86, 86, 86, 87, 87, 87, 87, 87, 87, 88, 88, 88, 88, 88, 88, 89, 89, 89, 89, 89, 89, 90, 90, 90, 90, 90, 90, 91, 91, 91, 91, 91, 91, 92, 92, 92, 92, 92, 92, 93, 93, 93, 93, 93, 93, 94, 94, 94, 94, 94, 94, 95, 95, 95, 95, 95, 95, 96, 96, 96, 96, 96, 96, 96, 97, 97, 97, 97, 97, 97, 97, 98, 98, 98, 98, 98, 98, 98, 99, 99, 99, 99, 99, 99, 99, 100, 100, 100, 100, 100, 100, 100, 101, 101, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 102, 102, 103, 103, 103, 103, 103, 103, 103, 104, 104, 104, 104, 104, 104, 104, 105, 105, 105, 105, 105, 105, 106, 106, 106, 106, 106, 106, 107, 107, 107, 107, 107, 107, 108, 108, 108, 108, 108, 108, 109, 109, 109, 109, 109, 109, 110, 110, 110, 110, 110, 111, 111, 111, 111, 111, 112, 112, 112, 112, 112, 113, 113, 113, 113, 113, 114, 114, 114, 114, 114, 115, 115, 115, 115, 115, 116, 116, 116, 116, 116, 117, 117, 117, 117, 118, 118, 118, 118, 119, 119, 119, 119, 120, 120, 120, 120, 121, 121, 121, 122, 122, 122, 123, 123, 123, 124, 124, 124, 125, 125, 125, 126, 126, 126, 127, 127, 127, 128, 128, 128, 129, 129, 129, 130, 130, 130, 131, 131, 132, 132, 133, 133, 134, 134, 135, 135, 136, 136, 137, 137, 138, 138, 139, 139, 140, 140, 141, 141, 142, 142, 143, 143, 144, 144, 145, 145, 146, 146, 147};
        boolean possible = isPossible(nums);
        System.out.println(possible);
    }

    public static boolean isPossible(int[] nums) {
        boolean[] bs = new boolean[nums.length];
        int[] stack = new int[3];
        int index = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (bs[i]) {
                continue;
            }
            index = 0;
            stack[index++] = i;

            for (int j = i + 1; index < stack.length && j < nums.length; j++) {
                if (bs[j]) {
                    continue;
                }
                if (nums[j] == nums[stack[index - 1]] + 1) {
                    stack[index++] = j;
                }
            }
            if (index >= stack.length) {
                for (int j : stack) {
                    bs[j] = true;
                }
                map.put(nums[stack[index - 1]], map.getOrDefault(nums[stack[index - 1]], 0) + 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (bs[i]) {
                continue;
            }
            if (map.getOrDefault(nums[i] - 1, -1) <= 0) {
                return false;
            }
            bs[i] = true;
            map.put(nums[i] - 1, map.getOrDefault(nums[i] - 1, 0) - 1);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return true;
    }

    public static boolean isPossible_other(int[] nums) {
        if (nums.length < 3) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(map.keySet().size());
        queue.addAll(map.keySet());

        while (!queue.isEmpty()) {
            int min = queue.peek();
            int count = 0;
            while (true) {
                if (!map.containsKey(min)) {
                    // no consecutive number, if count is not 3 at least, return false immediately
                    if (count < 3) return false;
                    // otherwise break because we cannot add more number to the current group since it would not be consecutive
                    break;
                }
                map.put(min, map.get(min) - 1);
                count++;
                if (map.get(min) == 0) {
                    // if the current minimum doesn't not match with the min in the heap, it means that the min value is gonna become isolated and we will never be able to add it to any group, so return false immediately
                    if (min != queue.peek()) return false;
                    // no more occurrences in the map, so remove it also from the queue
                    queue.poll();
                }
                // if the occurrences of current value (min) (+1 because we just removed it from the map) are bigger than the next one, we cannot add numbers anymore to the current group because otherwise the current value would become isolated
                if (map.containsKey(min + 1) && map.get(min) + 1 > map.get(min + 1)) {
                    // I need to break, cannot add more numbers to the current group anymore
                    if (count < 3)
                        return false; // when I break if, count is less than 3, again return immediately false
                    break;
                }

                min += 1; // update our minimum in search for another value to append to the current group
            }
        }
        // if false was never returned, we were able to build up the groups and can return true
        return true;
    }
}
