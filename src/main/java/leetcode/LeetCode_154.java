package leetcode;

/**
 * @description: Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 * <p>
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * <p>
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 * <p>
 * You must decrease the overall operation steps as much as possible.
 * @author: LISHUAI
 * @createDate: 2022/12/17 21:51
 * @version: 1.0
 */

public class LeetCode_154 {

    public static int findMin(int[] nums) {
        int len = nums.length;
        if (nums[0] < nums[len - 1]) {
            return nums[0];
        }

        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i - 1]) {
                return nums[i];
            }
        }
        return 0;
    }
}
