package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations in any order.
 * @author: LISHUAI
 * @createDate: 2022/2/14 20:17
 * @version: 1.0
 */

public class LeetCode_047 {

    private static final int MIN_NUM = 0xffffffff;
    private static List<List<Integer>> resultList;

    public static void main(String[] args) {

        int[] nums = {1, 1, 2};
        List<List<Integer>> lists = permuteUnique(nums);

        for (List<Integer> l : lists) {
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        resultList = new ArrayList<>();
        boolean[] bl = new boolean[nums.length];
        process(nums, new ArrayList<>(), bl);
        return resultList;
    }

    private static void process(int[] nums, List<Integer> list, boolean[] bl) {

        if (list.size() == nums.length) {
//            if (!resultList.contains(list)) {
            resultList.add(new ArrayList<>(list));
//            }
            return;
        }

        int[] ans = new int[21];
        for (int i = 0; i < nums.length; i++) {

            if (!bl[i] && ans[nums[i] + 10] == 0) {
                bl[i] = true;
                list.add(nums[i]);
                process(nums, list, bl);
                list.remove(list.size() - 1);
                bl[i] = false;
                ans[nums[i] + 10]++;
            }
        }
    }


//    public static List<List<Integer>> permuteUnique_02(int[] nums) {
//
//        int sum = 1, len = nums.length;
//
//        for (int i = len; i > 1; i--) {
//            sum *= i;
//        }
//
//        List<List<Integer>> ansList = new ArrayList<>(sum);
//    }
}











