package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        int[] nums = {1, 2, 3, 4, 4, 5};
        int[] nums = {1, 2, 3, 4, 5, 5, 6, 7};
        boolean possible = isPossible(nums);
        System.out.println(possible);
    }

    public static boolean isPossible(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> result = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i : nums) {
            if (map.containsKey(i) && map.get(i) > 0) {
                int cur = i;
                int size = Integer.MAX_VALUE;

                for (int j = 0; j < 3; j++, cur++) {
                    if (!map.containsKey(cur)) {
                        break;
                    }
                    size = Math.min(size, map.get(cur));
                }
                if (cur - i == 3) {
                    cur = i;
                    for (int j = 0; j < 3; j++, cur++) {
                        map.put(cur, map.get(cur) - size);
                        if (map.get(cur) == 0) {
                            map.remove(cur);
                        }
                    }
                    if (!map.containsKey(cur - 1) || map.getOrDefault(cur, 0) == map.get(cur - 1) + size) {
                        while (map.containsKey(cur) && map.get(cur) == size) {
                            map.put(cur, map.get(cur) - size);
                            if (map.get(cur) == 0) {
                                map.remove(cur);
                            }
                            cur++;
                        }
                    }

                    result.put(cur - 1, result.getOrDefault(cur, 0) + size);
                }
            }
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort(Integer::compare);
        for (int i : list) {
            if (!result.containsKey(i - 1) || !result.get(i - 1).equals(map.get(i))) {
                return false;
            }
            result.put(i, result.getOrDefault(i, 0) + 1);
        }
        return true;
    }

}
