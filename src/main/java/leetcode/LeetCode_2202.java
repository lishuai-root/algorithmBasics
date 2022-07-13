package leetcode;

/**
 * @description: You are given a 0-indexed integer array nums representing the contents of a pile, where nums[0] is the topmost element of the pile.
 * <p>
 * In one move, you can perform either of the following:
 * <p>
 * If the pile is not empty, remove the topmost element of the pile.
 * If there are one or more removed elements, add any one of them back onto the pile. This element becomes the new topmost element.
 * You are also given an integer k, which denotes the total number of moves to be made.
 * <p>
 * Return the maximum value of the topmost element of the pile possible after exactly k moves. In case it is not possible to obtain a non-empty pile after k moves, return -1.
 * @author: LISHUAI
 * @createDate: 2022/6/12 13:55
 * @version: 1.0
 */

public class LeetCode_2202 {

    public static void main(String[] args) {
//        int[] nums = {5, 2, 2, 4, 0, 6};
//        int k = 4;
        int[] nums = {2};
        int k = 1;
        int i = maximumTop(nums, k);
        System.out.println(i);
    }

    public static int maximumTop(int[] nums, int k) {
        if (k == 0) {
            return nums[0];
        }
        if (nums.length == 1) {
            if ((k & 1) == 0) {
                return nums[0];
            } else {
                return -1;
            }
        }

        int ans = -1, len = Math.min(nums.length, k - 1);
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, nums[i]);
        }
        if (k < nums.length) {
            ans = Math.max(ans, nums[k]);
        }
        return ans;
    }
}
