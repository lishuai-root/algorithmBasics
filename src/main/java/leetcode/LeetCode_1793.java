package leetcode;

/**
 * @description: You are given an array of integers nums (0-indexed) and an integer k.
 * <p>
 * The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.
 * <p>
 * Return the maximum possible score of a good subarray.
 * @author: LISHUAI
 * @createDate: 2022/6/5 18:53
 * @version: 1.0
 */

public class LeetCode_1793 {

    public static void main(String[] args) {
//        int[] nums = {1, 4, 3, 7, 4, 5};
//        int k = 3;
//        int[] nums = {8182, 1273, 9847, 6230, 52, 1467, 6062, 726, 4852, 4507, 2460, 2041, 500, 1025, 5524};
//        int k = 8;
        int[] nums = {5534, 378, 6709, 3383, 2247, 1491, 5119, 4055, 8735, 7227, 3322, 3788};
        int k = 6;
//        int[] nums = {5, 5, 4, 5, 4, 1, 1, 1};
//        int k = 0;
        int i = maximumScore(nums, k);
        System.out.println(i);
    }

    public static int maximumScore(int[] nums, int k) {
        int ans = 0, min = Integer.MAX_VALUE, sum;
        for (int i = k; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            int m = min;
            for (int j = k; j >= 0; j--) {
                m = Math.min(m, nums[j]);
                ans = Math.max(ans, m * (i - j + 1));
            }
        }
        return ans;
    }

    public int[] get(int[] nums) {
        int n = nums.length;
        int[] st = new int[n];
        int[] ans = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            while (top != -1 && nums[st[top]] >= nums[i]) {
                --top;
            }
            if (top != -1) ans[i] = st[top] + 1;
            else ans[i] = 0;
            st[++top] = i;
        }
        return ans;
    }

    public void reverse(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[n - 1 - i];
            nums[n - 1 - i] = temp;
        }
    }

    public int maximumScore_02(int[] nums, int k) {
        int n = nums.length;
        int[] left = get(nums);
        reverse(nums);
        int[] right = get(nums);
        reverse(nums);
        int sol = 0;
        for (int i = 0; i < n; i++) {
            int l = left[i];
            int r = n - 1 - right[n - 1 - i];
            int len = r - l + 1;
            if (k >= l && k <= r) {
                sol = Math.max(sol, len * nums[i]);
            }
        }
        return sol;
    }
}
