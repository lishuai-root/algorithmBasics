package leetcode;

/**
 * @description: Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product,
 * and return the product.
 * <p>
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * A subarray is a contiguous subsequence of the array.
 * @author: LISHUAI
 * @createDate: 2021/8/10 22:49
 * @version: 1.0
 */

public class LeetCode_152 {

    public static void main(String[] args) {

//        int[] arr = {2, 3, -2, 4};
//        int[] arr = {-2, 0, -1};
        int[] arr = {-2, 3, -4};
        int i = maxProduct_03(arr);

        System.out.println(i);
    }

    public static int maxProduct(int[] nums) {

        int ans = nums[0], min, max;
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                ans = Math.max(ans, nums[i]);
                dp[i][0] = 0;
                dp[i][1] = 0;
                continue;
            }
            min = nums[i] * dp[i - 1][0];
            max = nums[i] * dp[i - 1][1];
            dp[i][0] = Math.min(nums[i], Math.min(min, max));
            dp[i][1] = Math.max(nums[i], Math.max(min, max));
            ans = Math.max(ans, dp[i][1]);
        }

        return ans;
    }

    public static int maxProduct_03(int[] nums) {

        int ans = nums[0], min = nums[0], max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                ans = Math.max(ans, nums[i]);
                min = 0;
                max = 0;
                continue;
            }
            int emp = nums[i] * min;
            max = nums[i] * max;
            min = Math.min(nums[i], Math.min(emp, max));
            max = Math.max(nums[i], Math.max(emp, max));
            ans = Math.max(ans, max);
        }

        return ans;
    }

    // other people method
    public int maxProduct_02(int[] nums) {

        int result = Integer.MIN_VALUE;
        int size = nums.length;
        int current_max = 1, current_min = 1;

        for (int i = 0; i < size; i++) {
            if (nums[i] > result) {
                result = nums[i];
            }
        }

        for (int i = 0; i < size; i++) {
            if (nums[i] == 0) {
                current_min = 1;
                current_max = 1;
                continue;
            }
            int temp = current_max * nums[i];
            current_max = Math.max(Math.max(nums[i] * current_max, nums[i] * current_min), nums[i]);
            current_min = Math.min(Math.min(temp, nums[i] * current_min), nums[i]);
            result = Math.max(result, current_max);
        }
        return result;
    }
}
