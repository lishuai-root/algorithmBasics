package leetcode;

/**
 * @description: Given an array, rotate the array to the right by k steps, where k is non-negative.
 * @author: LISHUAI
 * @createDate: 2021/11/26 21:29
 * @version: 1.0
 */

public class LeetCode_189 {


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};

        rotate_02(nums, 3);

        for (int n : nums) {

            System.out.print(n + "   ");
        }
    }

    public static void rotate(int[] nums, int k) {

        int[] arr = new int[2 * nums.length];


        System.arraycopy(nums, 0, arr, 0, nums.length);

        System.arraycopy(nums, 0, arr, nums.length, nums.length);


        System.arraycopy(arr, nums.length - (k % nums.length), nums, 0, nums.length);
    }

    public static void rotate_02(int[] nums, int k) {

        int len = nums.length;

        k = k % len;

        reverse(nums, 0, len - k - 1);
        reverse(nums, len - k, len - 1);
        reverse(nums, 0, len - 1);
    }

    public static void reverse(int[] arr, int left, int right) {

        while (left < right) {

            arr[left] = arr[left] ^ arr[right];

            arr[right] = arr[left] ^ arr[right];

            arr[left] = arr[left] ^ arr[right];

            left++;

            right--;
        }
    }
}
