package leetcode;

/**
 * @description: Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
 * <p>
 * Return the length of the shortest subarray to remove.
 * <p>
 * A subarray is a contiguous subsequence of the array.
 * @author: LISHUAI
 * @createDate: 2023/5/8 22:23
 * @version: 1.0
 */

public class LeetCode_1574 {

    public static void main(String[] args) {
//        int[] arr = {5, 4, 3, 2, 1};
//        int[] arr = {10, 13, 17, 21, 15, 15, 9, 17, 22, 22, 13};
//        int[] arr = {2, 2, 2, 1, 1, 1};
//        int[] arr = {1, 2, 3, 10, 4, 2, 3, 5};
        int[] arr = {2, 1};
        int lengthOfShortestSubarray = findLengthOfShortestSubarray(arr);
        System.out.println(lengthOfShortestSubarray);
    }

    public static int findLengthOfShortestSubarray(int[] arr) {
        int len = arr.length;
        int index = len;
        while (--index > 0 && arr[index] >= arr[index - 1]) ;
        if (index == 0) {
            return 0;
        }

        int ans = index;
        for (int i = 0; i < len; i++) {
            if (i != 0 && arr[i] < arr[i - 1]) {
                break;
            }
            while (index < len && arr[i] > arr[index]) {
                index++;
            }
            ans = Math.min(ans, index - i - 1);
        }
        return ans;
    }
}
