package leetcode;

import java.util.Arrays;

/**
 * @description: You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.
 * <p>
 * In the ith operation (1-indexed), you will:
 * <p>
 * Choose two elements, x and y.
 * Receive a score of i * gcd(x, y).
 * Remove x and y from nums.
 * Return the maximum score you can receive after performing n operations.
 * <p>
 * The function gcd(x, y) is the greatest common divisor of x and y.
 * @author: LISHUAI
 * @createDate: 2022/6/5 14:44
 * @version: 1.0
 */

public class LeetCode_1799 {
    private static int maxSum;
    private static int[] maxDP;
    private static int ans;

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5, 6};
//        int[] nums = {896, 72, 970, 78, 753, 457, 729, 417, 903, 431, 50, 969, 688, 948};
        int[] nums = {415, 230, 471, 705, 902, 87};
        int i = maxScore(nums);
        System.out.println(i);
    }

    public static int maxScore(int[] nums) {
        int n = nums.length >>> 1;
        int[] dp = new int[n];
        maxDP = new int[n];
        maxSum = 0;
        ans = 0;
        maxScoreProcess(nums, dp, 0, 0);

        return ans;
    }


    private static void maxScoreProcess(int[] nums, int[] dp, int index, int sum) {
        if (index >= dp.length) {
//            if (sum > maxSum) {
            System.arraycopy(dp, 0, maxDP, 0, dp.length);
            maxSum = sum;
//            }
            int max = maxAns(maxDP);
            ans = Math.max(max, ans);
            return;
        }

        int curIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                curIndex = i;
                break;
            }
        }
        if (curIndex != -1) {
            int cur = nums[curIndex];
            nums[curIndex] = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != -1) {
                    int c = nums[i];
                    int g = gcd(cur, c);
                    nums[i] = -1;
                    dp[index] = g;
                    maxScoreProcess(nums, dp, index + 1, sum + g);
                    nums[i] = c;
                }
            }
            nums[curIndex] = cur;
        }
    }

    private static int maxAns(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 1; i <= nums.length; i++) {
            ans += i * nums[i - 1];
        }
        return ans;
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

}
