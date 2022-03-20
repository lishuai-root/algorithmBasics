package leetcode;

/**
 * @description: Given an array of integers arr, return true if and only if it is a valid mountain array.
 * <p>
 * Recall that arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * @author: LISHUAI
 * @createDate: 2022/1/25 19:44
 * @version: 1.0
 */

public class LeetCode_941 {

    public static void main(String[] args) {

        int[] arr = {3, 5, 5};

        boolean b = validMountainArray_02(arr);

        System.out.println(b);
    }

    public static boolean validMountainArray(int[] arr) {

        if (arr == null || arr.length < 3) {

            return false;
        }

        int i = 1, max = 0;

        while (i < arr.length && arr[i] > arr[i - 1]) {

            i++;
        }

        if (i == 1) {

            return false;
        }
        max = i - 1;

        while (i < arr.length && arr[i] < arr[i - 1]) {

            i++;
        }

        return max != 0 && max != arr.length - 1 && i == arr.length;
    }


    public static boolean validMountainArray_02(int[] arr) {

        if (arr == null || arr.length < 3) {

            return false;
        }

        int left = 0, right = arr.length - 1;

        while (left < arr.length - 2 && arr[left] < arr[left + 1]) {

            left++;
        }

        if (left == 0) {

            return false;
        }

        while (right > 0 && arr[right] > arr[right - 1]) {

            right--;
        }

        return left == right && right != arr.length - 1;
    }
}
