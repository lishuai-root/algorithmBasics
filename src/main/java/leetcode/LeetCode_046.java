package leetcode;

import java.util.*;

/**
 * @description: Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 * @author: LISHUAI
 * @createDate: 2021/11/17 20:26
 * @version: 1.0
 */

public class LeetCode_046 {

    private static boolean[] bl;

    public static void main(String[] args) {

        int[] nums = new int[]{1, 2, 3, 4};

        List<List<Integer>> permute = permute_01(nums);

        System.out.println(permute.size());

        for (List<Integer> list : permute) {

            for (int i : list) {

                System.out.print(i + "   ");
            }
            System.out.println();
        }

    }

    public static List<List<Integer>> permute(int[] nums) {

        bl = new boolean[nums.length];

        Set<Integer> set = new HashSet<>();

        Stack<Integer> stack = new Stack<>();

        List<List<Integer>> list = new ArrayList<>();

        process_02(nums, list, stack);

        return list;
    }

    public static void process(int[] nums, List<List<Integer>> list, Set<Integer> set, Stack<Integer> stack) {

        if (stack.size() == nums.length) {

            ArrayList<Integer> integers = new ArrayList<>(stack);

            list.add(integers);
        }

        for (int i = 0; i < nums.length; i++) {

            if (set.contains(nums[i])) {
                continue;
            }

            set.add(nums[i]);

            stack.push(nums[i]);

            process(nums, list, set, stack);

            stack.pop();

            set.remove(nums[i]);
        }
    }

    public static void process_02(int[] nums, List<List<Integer>> list, List<Integer> ls) {

        if (ls.size() == nums.length) {

            ArrayList<Integer> integers = new ArrayList<>(ls);

            list.add(integers);
        }

        for (int i = 0; i < nums.length; i++) {

            if (!bl[i]) {

                bl[i] = true;

                ls.add(nums[i]);

                process_02(nums, list, ls);

                ls.remove(ls.size() - 1);

                bl[i] = false;
            }
        }
    }


    public static List<List<Integer>> permute_01(int[] nums) {

        bl = new boolean[nums.length];

        int[] stack = new int[nums.length];

        List<List<Integer>> list = new ArrayList<>();

        process_03(nums, list, -1, stack);

        return list;
    }

    public static void process_03(int[] nums, List<List<Integer>> list, int index, int[] stack) {

        if (index == nums.length - 1) {

            ArrayList<Integer> integers = new ArrayList<>(index);

            for (int i = 0; i <= index; i++) {

                integers.add(stack[i]);
            }

            list.add(integers);
        }

        for (int i = 0; i < nums.length; i++) {

            if (!bl[i]) {

                bl[i] = true;

                stack[++index] = nums[i];

                process_03(nums, list, index, stack);

                index--;

                bl[i] = false;
            }
        }
    }
}
