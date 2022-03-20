package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * @author: LISHUAI
 * @createDate: 2021/12/13 21:00
 * @version: 1.0
 */

public class LeetCode_229 {

    public static List<Integer> majorityElement(int[] nums) {

        int size = nums.length / 3;

        List<Integer> list = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {

            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i : map.keySet()) {

            if (map.get(i) > size) {

                list.add(i);
            }
        }

        return list;
    }
}
