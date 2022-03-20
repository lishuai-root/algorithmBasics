package leetcode;

import java.util.*;

/**
 * @description: You are given an integer array nums. A number x is lonely when it appears only once,
 * and no adjacent numbers (i.e. x + 1 and x - 1) appear in the array.
 * <p>
 * Return all lonely numbers in nums. You may return the answer in any order.
 * <p>
 * @author: LISHUAI
 * @createDate: 2022/3/2 21:52
 * @version: 1.0
 */

public class LeetCode_2150 {

    public static void main(String[] args) {
        int[] nums = {61, 58, 4, 22, 28, 49, 77, 11, 76, 49, 67, 62, 91, 83, 24, 4, 1, 40, 91, 45, 36, 82, 35, 91, 29, 58, 96, 83, 49, 77, 22, 17, 2, 10};
        List<Integer> list = findLonely_03(nums);
        System.out.println(Integer.MIN_VALUE + 1);
        System.out.println(0xFFFFFFFE);
    }

    public static List<Integer> findLonely(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        if (nums.length == 1) {
            list.add(nums[0]);
            return list;
        }
        int len = nums.length;
        if (nums[0] != nums[1] && nums[0] + 1 != nums[1]) {
            list.add(nums[0]);
        }
        if (nums[len - 1] != nums[len - 2] && nums[len - 1] - 1 != nums[len - 2]) {
            list.add(nums[len - 1]);
        }

        for (int i = 1; i < len - 1; i++) {
            if (nums[i - 1] != nums[i] && nums[i] != nums[i + 1] && nums[i - 1] + 1 != nums[i] && nums[i] + 1 != nums[i + 1]) {
                list.add(nums[i]);
            }
        }

        return list;
    }

    public static List<Integer> findLonely_02(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i : nums) {
            if (map.get(i) == 1 && !map.containsKey(i + 1) && !map.containsKey(i - 1)) {
                list.add(i);
            }
        }
        return list;
    }


    public static List<Integer> findLonely_03(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        if (nums.length == 1) {
            list.add(nums[0]);
            return list;
        }
        int len = nums.length;

        for (int i = 0; i < len; ) {
            int cur = i + 1;
//            while (cur < len && (nums[cur] == nums[cur - 1] + 1 || nums[cur] == nums[cur - 1])) {
//                cur++;
//            }
            while (cur < len && (nums[cur] - nums[cur - 1] & -2) == 0) {
                cur++;
            }
            if (i + 1 == cur) {
                list.add(nums[i]);
            }
            i = cur;
        }

        return list;
    }
}
