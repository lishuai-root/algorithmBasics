package leetcode;

/**
 * @description: You are given a 0-indexed array of distinct integers nums.
 * <p>
 * There is an element in nums that has the lowest value and an element that has the highest value. We call them the minimum and maximum respectively. Your goal is to remove both these elements from the array.
 * <p>
 * A deletion is defined as either removing an element from the front of the array or removing an element from the back of the array.
 * <p>
 * Return the minimum number of deletions it would take to remove both the minimum and maximum element from the array.
 * @author: LiShuai
 * @createDate: 2023/9/12 21:51
 * @version: 1.0
 */

public class LeetCode_2091 {

    public static int minimumDeletions(int[] nums) {
        int min = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums[min]) {
                min = i;
            } else if (nums[i] > nums[max]) {
                max = i;
            }
        }
        int q1 = Math.min(min, max);
        int q2 = Math.max(max, min);
        int p1 = q1 + 1 + nums.length - q2;
        return Math.min(p1, Math.min(q2 + 1, nums.length - q1));
    }
}
