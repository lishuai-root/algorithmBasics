package leetcode;

/**
 * @description: You are given an integer array nums and an integer target.
 * <p>
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 * <p>
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 * @author: LISHUAI
 * @createDate: 2021/11/28 19:45
 * @version: 1.0
 */

public class LeetCode_494 {

    int ans = 0;

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1, 1};

        int target = 3;

        int targetSumWays = findTargetSumWays(arr, target);

        System.out.println(targetSumWays);
    }

    public static int findTargetSumWays(int[] nums, int target) {

        return process(nums, target, 0, 0);
    }

    private static int process(int[] nums, int target, int index, int sum) {

        if (index == nums.length) {

            if (target == sum) {

                return 1;
            }

            return 0;
        }

        return process(nums, target, index + 1, sum + nums[index])
                + process(nums, target, index + 1, sum - nums[index]);
    }

    /**
     * other people method
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays_02(int[] nums, int target) {
        int total = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            total += nums[i];
        }
        if ((total - target) % 2 == 1 || total < target) {
            return 0;
        }
        int sum = (total - target) / 2;
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = sum - nums[i]; j >= 0; j--) {
                dp[j + nums[i]] += dp[j];
            }
        }
        return dp[sum];
    }

}
