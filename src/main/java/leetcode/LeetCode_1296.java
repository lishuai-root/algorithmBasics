package leetcode;

import java.util.*;

/**
 * @description: Given an array of integers nums and a positive integer k,
 * check whether it is possible to divide this array into sets of k consecutive numbers.
 * <p>
 * Return true if it is possible. Otherwise, return false.
 * @author: LISHUAI
 * @createDate: 2022/5/23 23:05
 * @version: 1.0
 */

public class LeetCode_1296 {

    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i : nums) {
            if (!map.containsKey(i)) {
                queue.offer(i);
            }
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        while (!queue.isEmpty()) {
            int cur = queue.peek();
            while (!map.containsKey(cur) || map.get(cur) == 0) {
                queue.poll();
                if (queue.isEmpty()) {
                    return true;
                }
                cur = queue.peek();
            }

            for (int i = 0; i < k; i++, cur++) {
                if (!map.containsKey(cur) || map.get(cur) == 0) {
                    return false;
                }
                if (map.get(cur) > 1) {
                    map.put(cur, map.get(cur) - 1);
                } else {
                    map.remove(cur);
                }
            }
        }
        return true;
    }

    public boolean isPossibleDivide_02(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        Arrays.sort(nums);

        for (int i : nums) {
            if (map.get(i) > 0) {
                int cur = i;
                int size = map.get(i);
                for (int j = 0; j < k; j++, cur++) {
                    if (!map.containsKey(cur) || map.get(cur) < size) {
                        return false;
                    }
                    map.put(cur, map.get(cur) - size);
                }
            }
        }
        return true;
    }

}
