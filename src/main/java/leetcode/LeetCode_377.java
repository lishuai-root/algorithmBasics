package leetcode;

/**
 * @description: Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
 * <p>
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 * @author: LISHUAI
 * @createDate: 2022/8/5 21:48
 * @version: 1.0
 */

public class LeetCode_377 {
    private static int[] arr;
    private static int arrIndex;

    private static Integer[][] cache;

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 32;
        int i = combinationSum4(nums, target);
        System.out.println(i);
    }

    public static int combinationSum4(int[] nums, int target) {
        int ans = 0;
        cache = new Integer[nums.length][target];
        for (int i = 0; i < nums.length; i++) {
            ans += combinationSum4Process(nums, nums[i], target, i);
        }
        return ans;
    }

    private static int combinationSum4Process(int[] nums, int curSum, int target, int cur) {
        if (curSum == target) {
            return 1;
        }

        if (curSum > target) {
            return 0;
        }
        if (cache[cur][curSum] != null) {
            return cache[cur][curSum];
        }

        int ans = 0;
        for (int num : nums) {
            ans += combinationSum4Process(nums, curSum + num, target, cur);
        }
        cache[cur][curSum] = ans;
        return ans;
    }

    public static int combinationSum4_02(int[] nums, int target) {
        arr = new int[target * 10];
        arrIndex = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += combinationSum4Process(nums, i, 0, target);
        }
        return ans;
    }

    private static int combinationSum4Process_02(int[] nums, int index, int curSum, int target) {
        if (curSum == target) {
            for (int i = 0; i < arrIndex; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return 1;
        }

        if (index >= nums.length || curSum > target) {
            return 0;
        }
        int p1 = 0, p2 = 0;
        arr[arrIndex++] = nums[index];
        p1 = combinationSum4Process_02(nums, index, curSum + nums[index], target);
        arrIndex--;
        p2 = combinationSum4Process_02(nums, index + 1, curSum, target);
        return p1 + p2;
    }

    public static int combinationSum4_other(int[] nums, int target) {
        Integer[] memo = new Integer[target + 1];
        return recurse(nums, target, memo);
    }

    public static int recurse(int[] nums, int remain, Integer[] memo) {

        if (remain < 0) return 0;
        if (memo[remain] != null) return memo[remain];
        if (remain == 0) return 1;

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += recurse(nums, remain - nums[i], memo);
        }

        memo[remain] = ans;
        return memo[remain];
    }
}
