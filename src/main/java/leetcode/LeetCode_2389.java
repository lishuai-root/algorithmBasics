package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an integer array nums of length n, and an integer array queries of length m.
 * <p>
 * Return an array answer of length m where answer[i] is the maximum size of a subsequence that you can take from nums such that the sum of its elements is less than or equal to queries[i].
 * <p>
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * @author: LISHUAI
 * @createDate: 2022/12/25 13:20
 * @version: 1.0
 */

public class LeetCode_2389 {

    public static int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = query(nums, queries[i]);
        }
        return ans;
    }

    private static int query(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = -1, pre = -1;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] < target) {
                left = mid + 1;
                pre = mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid + 1;
            }
        }
        return pre + 1;
    }
}
