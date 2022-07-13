package leetcode;

/**
 * @description: A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 * <p>
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences. The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.
 * <p>
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 * @author: LISHUAI
 * @createDate: 2022/7/3 20:12
 * @version: 1.0
 */

public class LeetCode_376 {


    public static void main(String[] args) {
        int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int[] nums = {1, 7, 4, 9, 2, 5};
//        int[] nums = {0, 0};
        int i = wiggleMaxLength(nums);
        System.out.println(i);
        System.out.println((1 << 31));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }

    public static int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 1;
        }

        int[][] dp = new int[len][2];
        int ans = 0, cur;
        dp[0][1] = 1;
        for (int i = 1; i < len; i++) {
            cur = i;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] != nums[j]) {
                    int c = nums[i] - nums[j];
                    if (dp[j][0] == 0 || ((dp[j][0] ^ c) & (1 << 31)) != 0) {
                        if (dp[j][1] > dp[cur][1]) {
                            cur = j;
                        }
                    }
                }
            }
            dp[i][0] = nums[i] - nums[cur];
            dp[i][1] = dp[cur][1] + 1;
            ans = Math.max(ans, dp[i][1]);
        }
        return ans;
    }

}
