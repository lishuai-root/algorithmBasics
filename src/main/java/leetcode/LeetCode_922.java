package leetcode;

/**
 * @description: Given an array of integers nums, half of the integers in nums are odd, and the other half are even.
 * <p>
 * Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.
 * <p>
 * Return any answer array that satisfies this condition.
 * @author: LISHUAI
 * @createDate: 2022/5/2 16:14
 * @version: 1.0
 */

public class LeetCode_922 {

    public static void main(String[] args) {
        int[] nums = {3, 2};
        int[] ints = sortArrayByParityII(nums);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    public static int[] sortArrayByParityII(int[] nums) {

        int i = 0, j = 1;
        while (i < nums.length) {
            while (i < nums.length && (nums[i] & 1) == 0) {
                i += 2;
            }
            while (j < nums.length && (nums[j] & 1) == 1) {
                j += 2;
            }
            if (i < nums.length && j < nums.length) {
                nums[i] = nums[i] ^ nums[j];
                nums[j] = nums[i] ^ nums[j];
                nums[i] = nums[i] ^ nums[j];
            }
        }
        return nums;
    }
}
