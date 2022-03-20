package leetcode;

import java.util.*;

/**
 * @description: Given an array nums of n integers,
 * return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * <p>
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * @author: LISHUAI
 * @createDate: 2022/2/13 22:41
 * @version: 1.0
 */

public class LeetCode_018 {

    private static List<List<Integer>> resultList;

    private static Map<Integer, Integer> map;

    public static void main(String[] args) {

        int[] nums = {1, 0, -1, 0, -2, 2};
//        int[] nums = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90, 90};
//        int target = 200;
        int target = 0;
        List<List<Integer>> lists = fourSum(nums, target);
        System.out.println("size : " + lists.size());
        for (List<Integer> l : lists) {

            for (int i : l) {

                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        resultList = new ArrayList<>();
        map = new HashMap<>();

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        process(nums, target, new ArrayList<Integer>(), 0, 0);
        return resultList;
    }

    private static void process(int[] nums, int target, List<Integer> child, int index, int sum) {

        if (child.size() == 4) {

            if (sum == target) {
                List<Integer> l = new ArrayList<>(child);

                if (!resultList.contains(l)) {
                    resultList.add(l);
                }

            }
            return;
        }

        if (index == nums.length) {
            return;
        }

        if (child.size() == 3) {
            if (map.getOrDefault(target - sum, 0) > 0) {
                List<Integer> l = new ArrayList<>(child);
                l.add(target - sum);
                l.sort((a, b) -> a - b);
                if (!resultList.contains(l)) {
                    resultList.add(l);
                }
            }
            return;
        }
        process(nums, target, child, index + 1, sum);

        child.add(nums[index]);
        map.put(nums[index], map.get(nums[index]) - 1);
        process(nums, target, child, index + 1, sum + nums[index]);
        map.put(nums[index], map.get(nums[index]) + 1);
        child.remove(child.size() - 1);
    }
}
