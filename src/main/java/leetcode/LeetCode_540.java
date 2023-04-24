package leetcode;

/**
 * @description: You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * <p>
 * Return the single element that appears only once.
 * <p>
 * Your solution must run in O(log n) time and O(1) space.
 * @author: LISHUAI
 * @createDate: 2023/2/21 20:09
 * @version: 1.0
 */

public class LeetCode_540 {

    public static int singleNonDuplicate(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            ans ^= i;
        }
        return ans;
    }
}
