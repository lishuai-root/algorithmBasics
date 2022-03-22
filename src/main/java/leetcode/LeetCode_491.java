package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: Given an integer array nums,
 * return all the different possible increasing subsequences of the given array with at least two elements.
 * You may return the answer in any order.
 * <p>
 * The given array may contain duplicates, and two equal integers should also be considered a special case of increasing sequence.
 * @author: LISHUAI
 * @createDate: 2022/3/22 20:58
 * @version: 1.0
 */

public class LeetCode_491 {

    private static List<List<Integer>> ans;

    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> list = findSubsequences(nums);

        for (List<Integer> l : list) {
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {
        ans = new ArrayList<>();
        findSubsequencesProcess(nums, 0, new ArrayList<>());
        return ans;
    }

    private static void findSubsequencesProcess(int[] nums, int index, List<Integer> list) {

        if (list.size() >= 2) {
            ans.add(new ArrayList<>(list));
        }
        if (index >= nums.length) {
            return;
        }

        Set<Integer> set = new HashSet<>();

        for (int i = index; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            if (list.isEmpty() || nums[i] >= list.get(list.size() - 1)) {
                list.add(nums[i]);
                set.add(nums[i]);
                findSubsequencesProcess(nums, i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }
}
