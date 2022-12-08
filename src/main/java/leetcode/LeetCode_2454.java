package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: You are given a 0-indexed array of non-negative integers nums. For each integer in nums, you must find its respective second greater integer.
 * <p>
 * The second greater integer of nums[i] is nums[j] such that:
 * <p>
 * j > i
 * nums[j] > nums[i]
 * There exists exactly one index k such that nums[k] > nums[i] and i < k < j.
 * If there is no such nums[j], the second greater integer is considered to be -1.
 * <p>
 * For example, in the array [1, 2, 4, 3], the second greater integer of 1 is 4, 2 is 3, and that of 3 and 4 is -1.
 * Return an integer array answer, where answer[i] is the second greater integer of nums[i].
 * @author: LISHUAI
 * @createDate: 2022/12/6 3:32
 * @version: 1.0
 */

public class LeetCode_2454 {

    public static void main(String[] args) {
//        int[] nums = {2, 4, 0, 9, 6};
        int[] nums = {3, 3};
        int[] ints = secondGreaterElement(nums);
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
        ints = secondGreaterElement_02(nums);
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] secondGreaterElement(int[] nums) {
        int[] stack = new int[nums.length];
        int[] ans = new int[nums.length];
        int[] dp = new int[nums.length];
        int index = -1;

        for (int i = nums.length - 1; i >= 0; i--) {
            while (index != -1 && nums[i] >= nums[stack[index]]) {
                index--;
            }
            dp[i] = (index != -1 ? stack[index] : -1);
            stack[++index] = i;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            int c = dp[i];
            if (c == -1) {
                ans[i] = -1;
                continue;
            }

            int k = -1;
            for (int j = c + 1; j <= nums.length - 1; j++) {
                if (nums[j] > nums[i]) {
                    k = nums[j];
                    break;
                }
            }
            ans[i] = k;
        }
        return ans;
    }

    public static int[] secondGreaterElement_02(int[] nums) {
        int[] stack = new int[nums.length];
        int[] ans = new int[nums.length];
        Map<Integer, List<Integer>> map = new HashMap<>();
        int index = -1;

        for (int i = nums.length - 1; i >= 0; i--) {
            while (index != -1 && nums[i] >= nums[stack[index]]) {
                index--;
            }
            if (index != -1) {
                map.computeIfAbsent(stack[index], o -> new ArrayList<>()).add(i);
            } else {
                ans[i] = -1;
            }
            stack[++index] = i;
        }

        index = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (map.containsKey(i)) {
                List<Integer> list = map.get(i);
                list.sort((a, b) -> {
                    return nums[a] - nums[b];
                });
                int k = index, p = 0;
                while (k >= 0 && p < list.size()) {
                    while (p < list.size() && nums[list.get(p)] < nums[stack[k]]) {
                        ans[list.get(p++)] = nums[stack[k]];
                    }
                    k--;
                }
                while (p < list.size()) {
                    ans[list.get(p++)] = -1;
                }
            }
            while (index != -1 && nums[i] >= nums[stack[index]]) {
                index--;
            }
            stack[++index] = i;
        }
        return ans;
    }
}
