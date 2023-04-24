package leetcode;

/**
 * @description: Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * <p>
 * Return the kth positive integer that is missing from this array.
 * @author: LISHUAI
 * @createDate: 2023/3/6 18:35
 * @version: 1.0
 */

public class LeetCode_1539 {

    public static int findKthPositive(int[] arr, int k) {
        int ans = 0, index = 0, num = 1;

        for (int i = 0; i < k; i++) {
            while (index < arr.length && arr[index] == num) {
                index++;
                num++;
            }
            ans = num++;
        }
        return ans;
    }
}
