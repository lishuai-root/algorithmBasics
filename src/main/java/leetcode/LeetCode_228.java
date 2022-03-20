package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given a sorted unique integer array nums.
 * <p>
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
 * That is, each element of nums is covered by exactly one of the ranges,
 * and there is no integer x such that x is in one of the ranges but not in nums.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * @author: LISHUAI
 * @createDate: 2022/2/28 18:10
 * @version: 1.0
 */

public class LeetCode_228 {

    public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return list;
        }

        StringBuilder sb = new StringBuilder();
        int cur;

        for (int i = 0; i < nums.length; i++) {
            cur = i;
            i++;
            sb.append(nums[cur]);
            while (i < nums.length && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            i--;
            if (i != cur) {
                sb.append("->")
                        .append(nums[i]);
            }
            list.add(sb.toString());
            sb.delete(0, sb.length());
        }

        return list;
    }


}























