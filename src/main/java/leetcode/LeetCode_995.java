package leetcode;

/**
 * @description: You are given a binary array nums and an integer k.
 * <p>
 * A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.
 * <p>
 * Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.
 * <p>
 * A subarray is a contiguous part of an array.
 * @author: LiShuai
 * @createDate: 2023/6/5 22:20
 * @version: 1.0
 */

public class LeetCode_995 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 1, 0, 1, 1, 0};
        int k = 3;
        int i = minKBitFlips(nums, k);
        System.out.println(i);
    }

    public static int minKBitFlips(int[] nums, int k) {
        int len = nums.length, left = 0, ans = 0, b = 0;
        int[] bits = new int[len + 1];

        while (left < len) {
            b ^= bits[left];
            if ((nums[left] ^ b) == 0) {
                if (left + k > len) {
                    return -1;
                }
                bits[left + k] = 1;
                b ^= 1;
                ++ans;
            }
            ++left;
        }
        return ans;
    }
}
