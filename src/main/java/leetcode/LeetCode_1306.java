package leetcode;

/**
 * @description: Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * @author: LISHUAI
 * @createDate: 2022/12/26 15:12
 * @version: 1.0
 */

public class LeetCode_1306 {

    public static boolean canReach(int[] arr, int start) {
        return canReachProcess(arr, start, new boolean[arr.length]);
    }

    private static boolean canReachProcess(int[] arr, int index, boolean[] bls) {
        if (index < 0 || index >= arr.length || bls[index]) {
            return false;
        }
        if (arr[index] == 0) {
            return true;
        }
        bls[index] = true;
        return canReachProcess(arr, index + arr[index], bls) || canReachProcess(arr, index - arr[index], bls);
    }
}
