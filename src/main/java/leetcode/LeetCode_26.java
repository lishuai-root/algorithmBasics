package leetcode;

/**
 * @description: Given an integer array nums sorted in non-decreasing order,
 * remove the duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages,
 * you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 * @author: LISHUAI
 * @createDate: 2021/12/2 21:14
 * @version: 1.0
 */

public class LeetCode_26 {

    public static void main(String[] args) {

        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        int i = removeDuplicates(nums);

        for (int j = 0; j < i; j++) {

            System.out.print(nums[j] + "  ");
        }
    }

    public static int removeDuplicates(int[] nums) {

        int skip = 0;

        for (int i = 1; i < nums.length; i++) {

            while (i < nums.length && nums[i] == nums[i - 1]) {

                skip++;

                i++;
            }

            if (i < nums.length && skip > 0) {

                nums[i - skip] = nums[i];
            }
        }

        return nums.length - skip;
    }
}























