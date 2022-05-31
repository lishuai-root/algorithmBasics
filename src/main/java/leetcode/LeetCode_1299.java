package leetcode;

/**
 * @description: Given an array arr, replace every element in that array with the greatest element among the elements to its right,
 * and replace the last element with -1.
 * <p>
 * After doing so, return the array.
 * @author: LISHUAI
 * @createDate: 2022/5/3 16:06
 * @version: 1.0
 */

public class LeetCode_1299 {

    public static int[] replaceElements(int[] arr) {
        int index = arr.length - 1, max = arr[index], tmp;
        arr[index] = -1;

        while (--index >= 0) {
            tmp = arr[index];
            arr[index] = max;
            max = Math.max(max, tmp);
        }
        return arr;
    }
}
