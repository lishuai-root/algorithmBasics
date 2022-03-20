package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/2 21:10
 * @version: 1.0
 * <p>
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 */

public class LeetCode_15 {

    public static void main(String[] args) {

        int[] arr = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> lists = threeSum(arr);

        for (List<Integer> list : lists) {

            for (Integer i : list) {
                System.out.print(i + "   ");
            }

            System.out.println();
        }

    }

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();

        if (nums.length < 3)
            return lists;

        HashMap<Integer, Integer> map = new HashMap<>();

        HashMap<Integer, Integer> mapTemp = new HashMap<>();

        HashSet<String> set = new HashSet<>();

        List<Integer> l = null;

        StringBuffer buffer = null;

        int result, sum = 0, min, index, mn;

        for (int i = 0; i < nums.length; i++) {

            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 0; i < nums.length && map.size() > 0; i++) {

            if (mapTemp.get(nums[i]) != null)
                continue;

//            m = mapTemp;
//
//            mapTemp = map;
//
//            map = m;


            for (int j = i + 1; j < nums.length; j++) {


                sum = -nums[i] - nums[j];

                min = nums[j];

                result = nums[i];

                index = sum;

//                if (set.contains(nums[j]) || set.contains(sum))
//                    continue;


                if (map.get(sum) != null) {

                    buffer = new StringBuffer();

                    mn = map.get(sum);

                    if (min > index) {

                        min = min ^ index;

                        index = min ^ index;

                        min = min ^ index;
                    }

                    if (min > result) {

                        min = min ^ result;

                        result = min ^ result;

                        min = min ^ result;
                    }

                    if (result > index) {

                        result = result ^ index;

                        index = result ^ index;

                        result = result ^ index;
                    }

                    buffer.append(min).append(result).append(index);

                    if (set.contains(buffer.toString()))
                        continue;


                    set.add(buffer.toString());


//                    set.add(nums[j]);
//
//                    set.add(sum);
//
//                    set.add(nums[i]);

//                    map.remove(sum);
//
//                    map.remove(nums[j]);

//                    map.remove(nums[i]);

//                    map.put(nums[i], map.get(nums[i]) - 1);


                    mn = sum == nums[i] ? --mn : mn;

                    mn = sum == nums[j] ? --mn : mn;

                    if (mn > 0) {
                        l = new ArrayList<Integer>();

                        l.add(nums[i]);

                        l.add(sum);

                        l.add(nums[j]);

                        lists.add(l);
                    }


//                    for (int k = 0; k < result; k++) {
//
//                        l = new ArrayList<Integer>();
//
//                        l.add(nums[i]);
//
//                        l.add(sum);
//
//                        l.add(nums[j]);
//
//                        lists.add(l);
//                    }
                }


            }
            mapTemp.put(nums[i], map.remove(nums[i]));
        }

        return lists;
    }

}
