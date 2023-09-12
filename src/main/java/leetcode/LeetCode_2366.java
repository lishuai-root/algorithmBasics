package leetcode;

/**
 * @description: You are given a 0-indexed integer array nums. In one operation you can replace any element of the array with any two elements that sum to it.
 * <p>
 * For example, consider nums = [5,6,7]. In one operation, we can replace nums[1] with 2 and 4 and convert nums to [5,2,4,7].
 * Return the minimum number of operations to make an array that is sorted in non-decreasing order.
 * @author: LiShuai
 * @createDate: 2023/8/30 22:38
 * @version: 1.0
 */

public class LeetCode_2366 {

    public static long minimumReplacement(int[] nums) {
        int len = nums.length, index = len - 2, pre = nums[len - 1];
        long ans = 0;

        while (index >= 0) {
            int cur = nums[index--];
            if (cur > pre) {
                int k = (cur + pre - 1) / pre;
                pre = cur / k;
                ans += k - 1;
            } else {
                pre = cur;
            }
        }
        return ans;
    }
}
