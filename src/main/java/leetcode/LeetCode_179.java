package leetcode;

import java.util.Arrays;

/**
 * @description: Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * <p>
 * Since the result may be very large, so you need to return a string instead of an integer.
 * @author: LiShuai
 * @createDate: 2023/8/12 19:33
 * @version: 1.0
 */

public class LeetCode_179 {

    public String largestNumber(int[] nums) {
        String s[] = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            s[i] = String.valueOf(nums[i]);
        }
        StringBuilder sb = new StringBuilder("");
        Arrays.sort(s, (a, b) -> (b + a).compareTo(a + b));
        for (String str : s) {
            sb.append(str);
        }
        String result = sb.toString();
        return result.startsWith("0") ? "0" : result;

    }
}
