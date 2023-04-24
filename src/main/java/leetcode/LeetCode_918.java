package leetcode;

/**
 * @description: Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 * <p>
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 * <p>
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 * @author: LISHUAI
 * @createDate: 2023/1/18 21:24
 * @version: 1.0
 */

public class LeetCode_918 {

    public static void main(String[] args) {
        int[] nums = {-3, -2, -3};
        int i = maxSubarraySumCircular(nums);
        System.out.println(i);
    }

    public static int maxSubarraySumCircular(int[] nums) {

        int len = nums.length;
        int[] aftSum = new int[len];
        aftSum[len - 1] = nums[len - 1];
        int sum = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            aftSum[i] = Math.max(aftSum[i + 1] + nums[i], nums[i]);
            sum += nums[i];
        }

        int preSum = nums[0], ans = aftSum[0], max = preSum;
        for (int i = 1; i < len; i++) {
            ans = Math.max(ans, Math.max(aftSum[i], sum - preSum + max));
            preSum += nums[i];
            max = Math.max(max, preSum);
        }
        return ans;
    }
}
