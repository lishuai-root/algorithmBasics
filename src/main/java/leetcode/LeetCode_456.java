package leetcode;

import java.util.Random;

/**
 * @description: Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 * <p>
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 * @author: LISHUAI
 * @createDate: 2022/5/7 21:38
 * @version: 1.0
 */

public class LeetCode_456 {

    public static void main(String[] args) {
//        int[] nums = {3, 1, 4, 2};
//        int[] nums = {42, 43, 6, 12, 3, 4, 6, 11, 20};
//        int[] nums = {3, 5, 0, 3, 4};
//        int[] nums = {8, 10, 4, 6, 5};
        int[] nums = makeArr(500000);
        boolean pattern = find132pattern(nums);
        System.out.println(pattern);
    }

    private static int[] makeArr(int size) {
        int[] ans = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            ans[i] = random.nextInt(size * 2);
        }
        return ans;
    }

    public static boolean find132pattern(int[] nums) {
        int[] stack = new int[nums.length];
        int[] minNums = new int[nums.length];
        int index = -1, max, min = Integer.MAX_VALUE;

        for (int num : nums) {
            if (index != -1) {
                max = stack[index];
                while (index != -1 && num >= stack[index]) {
                    if (minNums[index] < stack[index] && minNums[index] < max && stack[index] > max) {
                        return true;
                    }
                    max = Math.max(max, stack[index--]);
                }
            }

            stack[++index] = num;
            minNums[index] = min;
            min = Math.min(min, num);
        }
        max = stack[index];
        while (index >= 0) {
            if (minNums[index] < stack[index] && minNums[index] < max && stack[index] > max) {
                return true;
            }
            max = Math.max(max, stack[index--]);
        }
        return false;
    }

    public static boolean find132pattern_02(int[] nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= min) {
                min = nums[i];
                continue;
            }
            int j = i;
            while (++j < nums.length && nums[i] > nums[j]) {
                if (min < nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
