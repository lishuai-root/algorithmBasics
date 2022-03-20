package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Given an integer array nums, return the number of all the arithmetic subsequences of nums.
 * <p>
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * <p>
 * For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
 * For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
 * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
 * <p>
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 * The test cases are generated so that the answer fits in 32-bit integer.
 * @author: LISHUAI
 * @createDate: 2022/2/21 23:02
 * @version: 1.0
 */

public class LeetCode_446 {

    public static int numberOfArithmeticSlices(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.get(nums[i]) - 1);
            int curAns = 1;
            for (int j = 0; j < nums.length; j++) {
                map.put(nums[j], map.get(nums[j]) - 1);
                int max = nums[j] - nums[i];
                int cur = map.getOrDefault(nums[j] + max, 0);
                while (cur != 0) {
                    curAns *= cur;
                }
            }
        }
        return 0;
    }

    private static int process(int[] nums, Map<Integer, Integer> map, int cur, int max) {
        return 0;
    }
}
