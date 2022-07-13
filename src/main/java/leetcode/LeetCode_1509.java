package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an integer array nums. In one move, you can choose one element of nums and change it by any value.
 * <p>
 * Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
 * @author: LISHUAI
 * @createDate: 2022/6/9 23:11
 * @version: 1.0
 */

public class LeetCode_1509 {

    public static void main(String[] args) {
//        int[] nums = {6, 6, 0, 1, 1, 4, 6};
        int[] nums = {82, 81, 95, 75, 20};
        int i = minDifference_02(nums);
        System.out.println(i);
    }

    public static int minDifference(int[] nums) {
        if (nums.length <= 3) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i <= 3; i++) {
            ans = Math.min(ans, nums[nums.length - 4 + i] - nums[i]);
        }
        return ans;
    }

    public static int minDifference_02(int[] nums) {
        if (nums.length <= 3) {
            return 0;
        }
        int[] arr = new int[4];
        int[] minArr = new int[4];
        int index = 0, ans = Integer.MAX_VALUE;
        for (int num : nums) {
            if (index < arr.length) {
                arr[index] = num;
                minArr[index++] = num;
            } else {
                Arrays.sort(arr);
                if (arr[0] < num) {
                    arr[0] = num;
                }
                Arrays.sort(minArr);
                if (minArr[3] > num) {
                    minArr[3] = num;
                }
            }
        }
        Arrays.sort(arr);
        Arrays.sort(minArr);
        for (int i = 0; i < arr.length; i++) {
            ans = Math.min(ans, arr[i] - minArr[i]);
        }
        return ans;
    }
}
