package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given an integer array nums of unique elements, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * @author: LISHUAI
 * @createDate: 2021/11/20 19:31
 * @version: 1.0
 */

public class LeetCode_078 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};

        List<List<Integer>> subsets = subsets(nums);

        for (List<Integer> subset : subsets) {

            for (Integer integer : subset) {

                System.out.print(integer + "   ");
            }

            System.out.println();
        }
    }


    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();

        process(nums, 0, list, new ArrayList<>());

        return list;
    }

    public static void process(int[] nums, int index, List<List<Integer>> list, List<Integer> childList) {

        if (index == nums.length) {

            list.add(new ArrayList<>(childList));

            return;
        }

        process(nums, index + 1, list, childList);

        childList.add(nums[index]);

        process(nums, index + 1, list, childList);

        childList.remove(childList.size() - 1);
    }


}
