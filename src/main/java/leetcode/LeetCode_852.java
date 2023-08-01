package leetcode;

/**
 * @description: An array arr a mountain if the following properties hold:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array arr, return the index i such that arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 * <p>
 * You must solve it in O(log(arr.length)) time complexity.
 * @author: LiShuai
 * @createDate: 2023/7/25 19:31
 * @version: 1.0
 */

public class LeetCode_852 {

    public static int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        if (left == right) {
            return 0;
        }

        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
