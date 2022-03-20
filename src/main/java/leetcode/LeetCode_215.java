package leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * @description: Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * @author: LISHUAI
 * @createDate: 2021/11/27 20:52
 * @version: 1.0
 */

public class LeetCode_215 {

    public static void main(String[] args) {

        int[] ints = makeArray(1000000);

        long start = System.currentTimeMillis();
        int kthLargest = findKthLargest(ints, 5);
        long end = System.currentTimeMillis();

        System.out.println(kthLargest);
        System.out.println(end - start);
        start = System.currentTimeMillis();
        kthLargest = findKthLargest_02(ints, 5);
        end = System.currentTimeMillis();
        System.out.println(kthLargest);
        System.out.println(end - start);
    }

    public static int[] makeArray(int length) {

        int[] arr = new int[length];

        Random r = new Random();

        for (int i = 0; i < length; i++) {

            arr[i] = r.nextInt(length * 3);
        }

        return arr;
    }

    public static int findKthLargest(int[] nums, int k) {

        Arrays.sort(nums);


        return nums[nums.length - k];
    }

    public static int findKthLargest_02(int[] nums, int k) {

        int max, result = 0;

        for (int i = 0; i < k; i++) {

            max = 0;

            for (int j = 1; j < nums.length - i; j++) {

                if (nums[max] < nums[j]) {

                    max = j;
                }
            }

            if (max != nums.length - i - 1) {

                nums[max] = nums[max] ^ nums[nums.length - i - 1];
                nums[nums.length - i - 1] = nums[max] ^ nums[nums.length - i - 1];
                nums[max] = nums[max] ^ nums[nums.length - i - 1];
            }
        }

        return nums[nums.length - k];
    }


}
