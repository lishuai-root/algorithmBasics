package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @description: Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 * @author: LISHUAI
 * @createDate: 2021/11/26 20:18
 * @version: 1.0
 */

public class LeetCode_169 {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 1, 2, 2, 2};

        int i = majorityElement_02(arr);

        System.out.println(i);
    }

    public static int majorityElement_02(int[] arr) {
        int cand = 0;
        int HP = 0;
        for (int k : arr) {
            if (HP == 0) {
                cand = k;
                HP = 1;
            } else if (k == cand) {
                HP++;
            } else {
                HP--;
            }
        }

        HP = 0;
        for (int j : arr) {
            if (j == cand) {
                HP++;
            }
        }
        return cand;
    }

    public int majorityElement(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();

        int key = 0;

        for (int n : nums) {

            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        Iterator<Integer> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {

            key = iterator.next();

            if (map.get(key) >= ((nums.length + 2 - 1) / 2)) {

                break;
            }
        }

        return key;
    }

    public int majorityElement_03(int[] nums) {

        int key = (nums.length + 2 - 1) / 2, sum = 0;

        for (int n : nums) {

            sum += n;
        }

        for (int n : nums) {

        }

        return 0;
    }
}
