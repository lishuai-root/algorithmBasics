package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 * <p>
 * A subarray is a contiguous part of an array.
 * @author: LISHUAI
 * @createDate: 2023/1/19 21:37
 * @version: 1.0
 */

public class LeetCode_974 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
//        int k = 2;
        int[] nums = {-1, 2, 9};
        int k = 2;
        int i = subarraysDivByK(nums, k);
        System.out.println(i);
    }

    public static int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, remainder; i < nums.length; i++) {
            if (i > 0) nums[i] += nums[i - 1];
            remainder = (nums[i] % k + k) % k;
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }
        int result = map.getOrDefault(0, 0);
        for (int frequency : map.values())
            result += frequency * (frequency - 1) / 2;
        return result;
    }
}
