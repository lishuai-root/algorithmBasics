package leetcode;

import java.util.Random;

/**
 * @description: An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * <p>
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 * <p>
 * A subarray is a contiguous subsequence of the array.
 * @author: LISHUAI
 * @createDate: 2021/8/10 20:57
 * @version: 1.0
 */

public class LeetCode_413 {

    public static void main(String[] args) {

//        int[] arr = {1, 3, 5, 7, 9, 10, 11, 12, 13};
//
//        int i = numberOfArithmeticSlices(arr);
//
//        int i1 = numberOfArithmeticSlices_02(arr);
//
//        System.out.println(i);
//
//        System.out.println(i1);

        int[] arr = new int[100000000];
//            System.out.println(i);

        Random r = new Random();

        for (int j = 0; j < 100000000; j++) {
//                System.out.println(j);

//            arr[j] = r.nextInt(100000000 * 10);

            arr[j] = r.nextInt(100000000 * 10);
        }

//        for (int i = 0; i < 100; i++) {


        long start = System.currentTimeMillis();
        int i1 = numberOfArithmeticSlices(arr);
        long end = System.currentTimeMillis();

        System.out.println("time1 : " + (end - start));

        start = System.currentTimeMillis();
        int i2 = numberOfArithmeticSlices_03(arr);
        end = System.currentTimeMillis();

        System.out.println("time2 : " + (end - start));

        System.out.println("i : " + "   " + i1 + "   " + i2);
//        }
    }

    public static int numberOfArithmeticSlices(int[] nums) {

        if (nums.length < 3) {

            return 0;
        }

        int result = 0, index = 0, arit = nums[1] - nums[0];

        for (int i = 2; i < nums.length; i++) {

            if (nums[i] - nums[i - 1] == arit) {

                result += Math.max(i - index - 1, 0);
            } else {

                index = i - 1;

                arit = nums[i] - nums[i - 1];
            }
        }

        return result;
    }


    public static int numberOfArithmeticSlices_02(int[] nums) {

        if (nums.length < 3) {

            return 0;
        }

        int result = 0, index = 0;

        nums[0] = nums[1] - nums[0];

        for (int i = 2; i < nums.length; i++) {

            if (nums[i] - nums[i - 1] == nums[0]) {

                result += Math.max(i - index - 1, 0);
            } else {

                index = i - 1;

                nums[0] = nums[i] - nums[i - 1];
            }
        }

        return result;
    }

    public static int numberOfArithmeticSlices_03(int[] nums) {

        if (nums.length < 3) {

            return 0;
        }

        int result = 0, index = 0, i = 2;

        nums[0] = nums[1] - nums[0];

        for (; i < nums.length; i++) {

            if (nums[i] - nums[i - 1] != nums[0]) {

//                if (i - index >= 2)

                result += Math.max(((i - index - 2) >> 1) * (i - index - 1), 0);

                index = i - 1;

                nums[0] = nums[i] - nums[i - 1];
            }
        }

        if (i - index >= 2)

            result += ((i - index - 2) >> 1) * (i - index - 1);

        return result;
    }
}
