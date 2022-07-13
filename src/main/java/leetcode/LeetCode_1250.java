package leetcode;

/**
 * @description: Given an array nums of positive integers. Your task is to select some subset of nums,
 * multiply each element by an integer and add all these numbers. The array is said to be good if you can obtain a sum of 1 from the array by any possible subset and multiplicand.
 * <p>
 * Return True if the array is good otherwise return False.
 * @author: LISHUAI
 * @createDate: 2022/6/5 16:46
 * @version: 1.0
 */

public class LeetCode_1250 {

    public static void main(String[] args) {
        System.out.println(12 * 3 + 5 * -7);
        show(10);
        show(-10);
    }

    public static boolean isGoodArray(int[] nums) {
        return false;
    }

    private static void show(int num) {
        for (int i = 31; i >= 0; i--) {
            if ((num & 1 << i) == 0) {
                System.out.print(0);
            } else {
                System.out.print(1);
            }
        }
        System.out.println();
    }
}
