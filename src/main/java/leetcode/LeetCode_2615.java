package leetcode;

import java.util.Arrays;

/**
 * @description: You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.
 * <p>
 * Return the array arr.
 * @author: LISHUAI
 * @createDate: 2023/5/16 20:01
 * @version: 1.0
 */

public class LeetCode_2615 {


    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 1, 2};
        long[] distance = distance(nums);
    }

    public static long[] distance(int[] nums) {
        int len = nums.length;
        Integer[] reindex = new Integer[len];
        for (int i = 0; i < len; i++) {
            reindex[i] = i;
        }
        Arrays.sort(reindex, (a, b) -> nums[a] - nums[b]);
        int index = 0, pre = nums[reindex[0]];
        nums[reindex[0]] = 0;
        for (int i = 1; i < len; i++) {
            if (nums[reindex[i]] != pre) {
                index++;
            }
            pre = nums[reindex[i]];
            nums[reindex[i]] = index;
        }
        int[] ik = new int[index + 1];
        int[] ic = new int[index + 1];
        long[] right = new long[len];
        for (int i = len - 1; i >= 0; i--) {
            if (ic[nums[i]] != 0) {
                right[i] = (ik[nums[i]] - i) * (long) ic[nums[i]] + right[ik[nums[i]]];
            }
            ik[nums[i]] = i;
            ic[nums[i]]++;
        }
        Arrays.fill(ic, 0);
        long[] ans = new long[len];
        for (int i = 0; i < len; i++) {
            ans[i] = right[i];
            if (ic[nums[i]] != 0) {
                right[i] = (i - ik[nums[i]]) * (long) ic[nums[i]] + right[ik[nums[i]]];
            } else {
                right[i] = 0;
            }
            ans[i] += right[i];
            ik[nums[i]] = i;
            ic[nums[i]]++;
        }
        return ans;
    }
}
