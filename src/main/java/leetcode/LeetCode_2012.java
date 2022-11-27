package leetcode;

/**
 * @description: You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:
 * <p>
 * 2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
 * 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
 * 0, if none of the previous conditions holds.
 * Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
 * @author: LISHUAI
 * @createDate: 2022/9/21 22:49
 * @version: 1.0
 */

public class LeetCode_2012 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
        int[] nums = {2, 4, 6, 4};
        int i = sumOfBeauties(nums);
        System.out.println(i);
    }

    public static int sumOfBeauties(int[] nums) {
        int[] dp = new int[nums.length];
        int max = nums[0], ans = 0;
        dp[nums.length - 1] = Integer.MAX_VALUE;
        for (int i = nums.length - 2; i >= 0; i--) {
            dp[i] = Math.min(dp[i + 1], nums[i + 1]);
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > max && nums[i] < dp[i]) {
                ans += 2;
            } else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
            max = Math.max(max, nums[i]);
        }
        return ans;
    }
}
