package leetcode;

import java.util.Arrays;

/**
 * @description: Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 * @author: LISHUAI
 * @createDate: 2022/5/28 21:35
 * @version: 1.0
 */

public class LeetCOde_268 {

    public static int missingNumber(int[] nums) {
        int ans = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                ans = i;
                break;
            }
        }
        return ans;
    }


    public static int missingNumber_02(int[] nums) {
        int ans = nums.length * (nums.length + 1) / 2;
        for (int i : nums) {
            ans -= i;
        }
        return ans;
    }

}




