package leetcode;

import java.util.Arrays;

/**
 * @description: A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.
 * <p>
 * Given an array of numbers arr, return true if the array can be rearranged to form an arithmetic progression. Otherwise, return false.
 * @author: LiShuai
 * @createDate: 2023/6/6 23:00
 * @version: 1.0
 */

public class LeetCode_1502 {

    public boolean canMakeArithmeticProgression(int[] arr) {
        int len = arr.length;
        if (len <= 2) {
            return true;
        }
        Arrays.sort(arr);
        int c = arr[1] - arr[0];
        for (int i = 2; i < len; i++) {
            if (c != arr[i] - arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
