package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 * <p>
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * <p>
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 * @author: LISHUAI
 * @createDate: 2021/11/16 21:31
 * @version: 1.0
 */

public class LeetCode_039 {
    public static void main(String[] args) {

        int[] arr = new int[]{1};

        int target = 1000000;

        List<List<Integer>> lists = combinationSum(arr, target);

//        for (List<Integer> integers : lists) {
//
//            for (Integer integer : integers) {
//
//                System.out.print(integer + "   ");
//            }
//
//            System.out.println();
//        }

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> list = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();

        process_02(candidates, target, 0, list, stack);

        return list;
    }

    public static void process(int[] arr, int beforeTarget, int index, List<List<Integer>> list, Stack<Integer> stack) {

        if (beforeTarget == 0) {

            List<Integer> integers = new ArrayList<>(stack);

            list.add(integers);

            return;
        }

        if (index >= arr.length) {

            return;
        }

        for (int i = 0; arr[index] * i <= beforeTarget; i++) {

            for (int j = 0; j < i; j++) {

                stack.push(arr[index]);
            }

            process(arr, beforeTarget - arr[index] * i, index + 1, list, stack);

            for (int j = 0; j < i && !stack.isEmpty(); j++) {

                stack.pop();
            }
        }
    }

    public static void process_02(int[] arr, int beforeTarget, int index, List<List<Integer>> list, Stack<Integer> stack) {

        if (beforeTarget == 0) {

            List<Integer> integers = new ArrayList<>(stack);

            list.add(integers);

            return;
        }

        if (index >= arr.length) {

            return;
        }

        int i = 0;

        for (; arr[index] * i <= beforeTarget; i++) {

            if (i > 0) {

                stack.push(arr[index]);
            }

            process(arr, beforeTarget - arr[index] * i, index + 1, list, stack);

        }

        for (int j = 1; j < i; j++) {

            stack.pop();
        }
    }
}
