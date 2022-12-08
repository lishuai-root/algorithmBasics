package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 * <p>
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 * <p>
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 * <p>
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 * @author: LISHUAI
 * @createDate: 2022/12/6 3:18
 * @version: 1.0
 */

public class LeetCode_496 {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] stack = new int[nums2.length];
        int[] ans = new int[nums1.length];
        int index = -1;

        for (int i = nums2.length - 1; i >= 0; i--) {
            while (index != -1 && nums2[i] >= nums2[stack[index]]) {
                index--;
            }
            map.put(nums2[i], index == -1 ? -1 : nums2[stack[index]]);
            stack[++index] = i;
        }
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}
