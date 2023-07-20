package leetcode;

/**
 * @description: You are given two integer arrays nums1 and nums2 of length n.
 * <p>
 * The XOR sum of the two integer arrays is (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) (0-indexed).
 * <p>
 * For example, the XOR sum of [1,2,3] and [3,2,1] is equal to (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4.
 * Rearrange the elements of nums2 such that the resulting XOR sum is minimized.
 * <p>
 * Return the XOR sum after the rearrangement.
 * @author: LiShuai
 * @createDate: 2023/7/1 16:40
 * @version: 1.0
 */

public class LeetCode_1879 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {2, 3};
        int i = minimumXORSum(nums1, nums2);
        System.out.println(i);
        System.out.println(minimumXORSum_02(nums1, nums2));
    }

    public static int minimumXORSum(int[] nums1, int[] nums2) {
        int len = nums1.length;
        return minimumXORSumProcess(nums1, nums2, 0, 0);
    }

    private static int minimumXORSumProcess(int[] nums1, int[] nums2, int index, int bit) {
        if (index >= nums1.length) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums2.length; i++) {
            if ((bit & (1 << i)) == 0) {
                bit |= (1 << i);
                ans = Math.min(ans, minimumXORSumProcess(nums1, nums2, index + 1, bit) + (nums1[index] ^ nums2[i]));
                bit ^= (1 << i);
            }
        }
        return ans;
    }

    public static int minimumXORSum_02(int[] nums1, int[] nums2) {
        int len = nums1.length, bit = (1 << len);
        int[] dp = new int[bit];
        for (int i = 0; i < len; i++) {
            dp[(bit - 1) ^ (1 << i)] = nums1[len - 1] ^ nums2[i];
        }

        for (int i = len - 2; i >= 0; --i) {
            for (int j = 0; j < bit; j++) {
                int p = Integer.MAX_VALUE;
                for (int k = 0; k < len; k++) {
                    if ((j & (1 << k)) == 0) {
                        p = Math.min(p, dp[j | (1 << k)] + (nums1[i] ^ nums2[k]));
                    }
                }
                dp[j] = p;
            }
        }
        return dp[0];
    }
}
