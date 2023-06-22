package leetcode;

import java.util.Arrays;

/**
 * @description: You are given a 0-indexed array nums of n integers, and an integer k.
 * <p>
 * The k-radius average for a subarray of nums centered at some index i with the radius k is the average of all elements in nums between the indices i - k and i + k (inclusive). If there are less than k elements before or after the index i, then the k-radius average is -1.
 * <p>
 * Build and return an array avgs of length n where avgs[i] is the k-radius average for the subarray centered at index i.
 * <p>
 * The average of x elements is the sum of the x elements divided by x, using integer division. The integer division truncates toward zero, which means losing its fractional part.
 * <p>
 * For example, the average of four elements 2, 3, 1, and 5 is (2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75, which truncates to 2.
 * @author: LiShuai
 * @createDate: 2023/6/20 22:42
 * @version: 1.0
 */

public class LeetCode_2090 {

    public static int[] getAverages(int[] nums, int k) {
        int len = nums.length;
        long sum = 0;
        int[] ans = new int[len];
        if (k * 2 >= len) {
            Arrays.fill(ans, -1);
            return ans;
        }
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        for (int i = 0; i < len; i++) {
            if (i + k >= len) {
                ans[i] = -1;
                continue;
            }
            sum += nums[i + k];
            if (i - k < 0) {
                ans[i] = -1;
            } else {
                ans[i] = (int) (sum / ((k << 1) + 1));
                sum -= nums[i - k];
            }
        }
        return ans;
    }
}
