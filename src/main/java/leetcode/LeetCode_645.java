package leetcode;

/**
 * @description: You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 * <p>
 * You are given an integer array nums representing the data status of this set after the error.
 * <p>
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 * @author: LISHUAI
 * @createDate: 2022/10/23 21:18
 * @version: 1.0
 */

public class LeetCode_645 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 4};
//        int[] nums = {2, 3, 2};
//        int[] nums = {3, 2, 2};
//        int[] nums = {1, 1};
        int[] errorNums = findErrorNums(nums);
        for (int i : errorNums) {
            System.out.println(i + " ");
        }
        System.out.println();
    }

    public static int[] findErrorNums(int[] nums) {
        int[] arr = new int[nums.length + 1];
        int p = 0, q = 0;
        for (int num : nums) {
            if (arr[num] == num) {
                p = arr[num];
            } else {
                arr[num] = num;
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (arr[i] == 0) {
                q = i;
            }
        }
        return new int[]{p, q};
    }
}
