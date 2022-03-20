package leetcode;

/**
 * @description: Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * @author: LISHUAI
 * @createDate: 2021/11/28 17:40
 * @version: 1.0
 */

public class LeetCode_416 {

    public static void main(String[] args) {

        int[] arr = new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97};

        boolean b = canPartition_02(arr);

        System.out.println(b);
    }


    public static boolean canPartition(int[] nums) {

        int max = 0;

        for (int i : nums) {

            max += i;
        }

        if ((max & 1) == 1) {

            return false;
        }

        max = max / 2;

        return process(nums, 0, 0, max);
    }

    public static boolean process(int[] nums, int index, int curSum, int sum) {

        if (curSum > sum) {

            return false;
        }

        if (curSum == sum) {

            return true;
        }

        if (index >= nums.length) {

            return false;
        }

        boolean bl = false;

        bl = process(nums, index + 1, curSum + nums[index], sum);

        if (!bl) {

            bl = bl | process(nums, index + 1, curSum, sum);
        }

        return bl;
    }

    public static boolean canPartition_02(int[] nums) {

        int max = 0, len = nums.length;

        for (int i : nums) {

            max += i;
        }

        if ((max & 1) == 1) {

            return false;
        }

        max = max >> 1;

        boolean[][] dp = new boolean[len][max + 1];

        for (int i = 0; i < len; i++) {

            dp[i][0] = true;
        }

        if (nums[0] <= max) {

            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < len; i++) {

            for (int j = 1; j <= max; j++) {

                dp[i][j] = dp[i - 1][j];

                if (j - nums[i] >= 0) {

                    dp[i][j] |= dp[i - 1][j - nums[i]];
                }
            }

            if (dp[i][max]) {

                return true;
            }
        }

        return false;
    }
}
