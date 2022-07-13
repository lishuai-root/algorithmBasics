package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 * <p>
 * A subsequence is a sequence that can be derived from arr by deleting some or no elements without changing the order of the remaining elements.
 * @author: LISHUAI
 * @createDate: 2022/6/6 19:04
 * @version: 1.0
 */

public class LeetCode_1218 {

    public static int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i : arr) {
            map.put(i, map.getOrDefault(i - difference, 0) + 1);
            ans = Math.max(ans, map.get(i));
        }
        return ans;
    }

}
