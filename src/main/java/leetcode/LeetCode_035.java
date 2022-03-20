package leetcode;

/**
 * @description: Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * @author: LISHUAI
 * @createDate: 2021/11/16 21:27
 * @version: 1.0
 */

public class LeetCode_035 {


    public int searchInsert(int[] nums, int target) {

        int index = -1, i = 0;

        for (; i < nums.length && nums[i] <= target; i++) {

            if (nums[i] == target) {

                index = i;

                break;
            }
        }

        if (index == -1) {

            index = i;
        }

        return index;
    }
}
