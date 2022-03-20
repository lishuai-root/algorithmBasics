package leetcode;

/**
 * @description: Given an integer array nums, return the number of reverse pairs in the array.
 * <p>
 * A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].
 * @author: LISHUAI
 * @createDate: 2022/2/22 19:12
 * @version: 1.0
 */

public class LeetCode_493 {

    public static void main(String[] args) {
        int[] nums = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        int i = reversePairs(nums);

        System.out.println(i);
        System.out.println(0xffffffff >>> 2);
        System.out.println(0x7fffffff);
        System.out.println(Integer.MAX_VALUE);
    }

    public static int reversePairs(int[] nums) {

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < (0xffffffff >>> 2) && nums[i] > (nums[j] << 1)) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
