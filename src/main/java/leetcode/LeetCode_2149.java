package leetcode;

/**
 * @description: You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.
 * <p>
 * You should rearrange the elements of nums such that the modified array follows the given conditions:
 * <p>
 * Every consecutive pair of integers have opposite signs.
 * For all integers with the same sign, the order in which they were present in nums is preserved.
 * The rearranged array begins with a positive integer.
 * Return the modified array after rearranging the elements to satisfy the aforementioned conditions.
 * <p>
 * nums consists of equal number of positive and negative integers.
 * <p>
 * @author: LISHUAI
 * @createDate: 2022/3/2 21:31
 * @version: 1.0
 */

public class LeetCode_2149 {

    public static void main(String[] args) {
        int[] nums = {3, 1, -2, -5, 2, -4};
        int[] ints = rearrangeArray(nums);
    }

    public static int[] rearrangeArray(int[] nums) {
        int leftIndex = -1, rightIndex = -1, ans = 1 << 31;
        int[] leftNums = new int[nums.length / 2], rightNums = new int[nums.length / 2];
        for (int i : nums) {
            if ((i & ans) == 0) {
                rightNums[++rightIndex] = i;
            } else {
                leftNums[++leftIndex] = i;
            }
        }
        leftIndex = -1;
        for (int i = 0; i < leftNums.length; i++) {
            nums[++leftIndex] = rightNums[i];
            nums[++leftIndex] = leftNums[i];
        }
        return nums;
    }

    public static int[] rearrangeArray_02(int[] nums) {
        int[] ans = new int[nums.length];
        int leftIndex = 0, rightIndex = 1;
        for (int i : nums) {
            if (i > 0) {
                ans[leftIndex] = i;
                leftIndex += 2;
            } else {
                ans[rightIndex] = i;
                rightIndex += 2;
            }
        }
        return ans;
    }
}
