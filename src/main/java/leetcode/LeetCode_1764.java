package leetcode;

/**
 * @description: You are given a 2D integer array groups of length n. You are also given an integer array nums.
 * <p>
 * You are asked if you can choose n disjoint subarrays from the array nums such that the ith subarray is equal to groups[i] (0-indexed), and if i > 0, the (i-1)th subarray appears before the ith subarray in nums (i.e. the subarrays must be in the same order as groups).
 * <p>
 * Return true if you can do this task, and false otherwise.
 * <p>
 * Note that the subarrays are disjoint if and only if there is no index k such that nums[k] belongs to more than one subarray. A subarray is a contiguous sequence of elements within an array.
 * @author: LISHUAI
 * @createDate: 2022/6/9 22:22
 * @version: 1.0
 */

public class LeetCode_1764 {

    public static void main(String[] args) {
        int[][] groups = {{1, -1, -1}, {3, -2, 0}};
        int[] nums = {1, -1, 0, 1, -1, -1, 3, -2, 0};
        System.out.println(canChoose(groups, nums));

    }

    public static boolean canChoose(int[][] groups, int[] nums) {
        int groupsIndex = 0, numsIndex = 0;
        while (groupsIndex < groups.length) {
            int[] cur = groups[groupsIndex];
            if (nums.length - numsIndex < cur.length) {
                return false;
            }
            do {
                if (cur[0] == nums[numsIndex]) {
                    int index = 1;
                    while (index < cur.length && numsIndex + index < nums.length && cur[index] == nums[numsIndex + index]) {
                        index++;
                    }
                    if (index == cur.length) {
                        groupsIndex++;
                        numsIndex += index;
                        break;
                    }
                }
                numsIndex++;
            } while (numsIndex + cur.length <= nums.length);

        }
        return groupsIndex == groups.length;
    }
}
