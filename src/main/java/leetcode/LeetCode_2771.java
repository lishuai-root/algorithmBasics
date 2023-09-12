package leetcode;

/**
 * @description: You are given two 0-indexed integer arrays nums1 and nums2 of length n.
 * <p>
 * Let's define another 0-indexed integer array, nums3, of length n. For each index i in the range [0, n - 1], you can assign either nums1[i] or nums2[i] to nums3[i].
 * <p>
 * Your task is to maximize the length of the longest non-decreasing subarray in nums3 by choosing its values optimally.
 * <p>
 * Return an integer representing the length of the longest non-decreasing subarray in nums3.
 * <p>
 * Note: A subarray is a contiguous non-empty sequence of elements within an array.
 * @author: LiShuai
 * @createDate: 2023/8/27 15:52
 * @version: 1.0
 */

public class LeetCode_2771 {

    public static void main(String[] args) {
//        int[] nums1 = {1, 3, 2, 1};
//        int[] nums2 = {2, 2, 3, 4};
        int[] nums1 = {7, 4, 4};
        int[] nums2 = {8, 7, 20};
        int i = maxNonDecreasingLength(nums1, nums2);
        System.out.println(i);
        System.out.println(maxNonDecreasingLength_02(nums1, nums2));
    }


    public static int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int ans = 1, len = nums1.length;
        int[][] dp = new int[len][2];
        dp[0][0] = Math.min(nums1[0], nums2[0]);
        dp[0][1] = 1;
        for (int i = 1; i < len; i++) {
            ans = Math.max(ans, compute(nums1, nums2, i, dp));
        }
        return ans;
    }

    private static int compute(int[] nums1, int[] nums2, int end, int[][] dp) {
        int len = nums1.length, pre = Integer.MAX_VALUE, ans = 0;
        int cur = Math.min(nums1[end], nums2[end]);
        if (cur < dp[end - 1][0]) {
            cur = Math.max(nums1[end], nums2[end]);
        }
        if (cur >= dp[end - 1][0]) {
            dp[end][0] = cur;
            dp[end][1] = dp[end - 1][1] + 1;
            return dp[end][1];
        }
        for (int i = end; i >= 0; --i) {
            cur = Math.max(nums1[i], nums2[i]);
            if (cur > pre) {
                cur = Math.min(nums1[i], nums2[i]);
            }
            if (cur <= pre) {
                pre = cur;
                ++ans;
            } else {
                break;
            }
            if (i == end) {
                dp[end][0] = cur;
            }
        }
        dp[end][1] = ans;
        return ans;
    }

    public static int maxNonDecreasingLength_02(int[] nums1, int[] nums2) {
        int len = nums1.length, ans = 1;
        int[][] dp = new int[len][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < len; i++) {
            int min = Math.min(nums1[i], nums2[i]);
            int preMax = Math.max(nums1[i - 1], nums2[i - 1]);
            int preMin = Math.min(nums1[i - 1], nums2[i - 1]);
            dp[i][0] = Math.max((min >= preMin ? dp[i - 1][0] + 1 : 1), (min >= preMax ? dp[i - 1][1] + 1 : 1));
            int max = Math.max(nums1[i], nums2[i]);
            dp[i][1] = Math.max((max >= preMin ? dp[i - 1][0] + 1 : 1), (max >= preMax ? dp[i - 1][1] + 1 : 1));
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        return ans;
    }
}
