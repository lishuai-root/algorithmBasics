package leetcode;

import java.util.*;

/**
 * @description: Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
 * <p>
 * answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
 * answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
 * Note that the integers in the lists may be returned in any order.
 * @author: LISHUAI
 * @createDate: 2023/5/6 14:50
 * @version: 1.0
 */

public class LeetCode_2215 {

    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<List<Integer>> ans = new ArrayList<>(2);
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        ans.add(list1);
        ans.add(list2);
        int len1 = nums1.length, len2 = nums2.length;
        int index1 = 0, index2 = 0;

        while (index1 < len1) {
            if (index1 > 0 && nums1[index1] == nums1[index1 - 1]) {
                index1++;
                continue;
            }
            while (index2 < len2 && nums1[index1] > nums2[index2]) {
                index2++;
            }
            if (index2 >= len2 || nums1[index1] != nums2[index2]) {
                list1.add(nums1[index1]);
            }
            index1++;
        }

        index1 = 0;
        index2 = 0;

        while (index2 < len2) {
            if (index2 > 0 && nums2[index2] == nums2[index2 - 1]) {
                index2++;
                continue;
            }
            while (index1 < len1 && nums2[index2] > nums1[index1]) {
                index1++;
            }
            if (index1 >= len1 || nums1[index1] != nums2[index2]) {
                list2.add(nums2[index2]);
            }
            index2++;
        }
        return ans;
    }

    public static List<List<Integer>> findDifference_02(int[] nums1, int[] nums2) {
        Set<Integer> exists = new HashSet<>(nums1.length);
        Set<Integer> ans = new HashSet<>(nums1.length);
        for (int i : nums2) {
            exists.add(i);
        }
        for (int i : nums1) {
            if (!exists.contains(i)) {
                ans.add(i);
            }
        }
        List<List<Integer>> ll = new ArrayList<>();
        List<Integer> list = new ArrayList<>(ans);
        ll.add(list);
        exists.clear();
        ans.clear();
        for (int i : nums1) {
            exists.add(i);
        }
        for (int i : nums2) {
            if (!exists.contains(i)) {
                ans.add(i);
            }
        }
        list = new ArrayList<>(ans);
        ll.add(list);
        return ll;
    }
}
