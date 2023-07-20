package leetcode;

/**
 * @description: Given a binary array nums, you should delete one element from it.
 * <p>
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
 * @author: LiShuai
 * @createDate: 2023/7/5 22:25
 * @version: 1.0
 */

public class LeetCode_1493 {

    public static int longestSubarray(int[] nums) {
        int len = nums.length, pre = 0, ans = 0;
        int[] cache = new int[len + 1];

        for (int i = len - 1; i >= 0; --i) {
            cache[i] = (nums[i] == 1 ? cache[i + 1] + 1 : 0);
        }

        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, pre + cache[i + 1]);
            pre = (nums[i] == 1 ? pre + 1 : 0);
        }
        return ans;
    }
}
