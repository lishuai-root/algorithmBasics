package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note: The solution set must not contain duplicate combinations.
 * @author: LISHUAI
 * @createDate: 2021/12/4 13:50
 * @version: 1.0
 */

public class LeetCode_040 {

    private static List<List<Integer>> result;

    public static void main(String[] args) {

        int[] arr = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        List<List<Integer>> lists = combinationSum2(arr, target);

        for (List<Integer> list : lists) {

            for (Integer i : list) {

                System.out.print(i + "  ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        result = new ArrayList<>();

        Arrays.sort(candidates);


        List<Integer> list = new ArrayList<>();

        process(candidates, target, 0, 0, list);

        return result;
    }

    private static void process(int[] candidates, int target, int curSum, int index, List<Integer> list) {


        if (curSum == target) {

            if (!result.contains(list)) {

                result.add(new ArrayList<>(list));
            }

            return;
        }

        if (index >= candidates.length) {

            return;
        }

        int cur = 0;

        for (int i = index; i < candidates.length; i++) {

            if (curSum + candidates[i] > target) {

                return;
            }

            if (candidates[i] != cur) {

                cur = candidates[i];

                list.add(candidates[i]);

                process(candidates, target, curSum + candidates[i], i + 1, list);

                list.remove(list.size() - 1);
            }

        }
    }


}
