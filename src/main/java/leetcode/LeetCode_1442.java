package leetcode;

/**
 * @description: Given an array of integers arr.
 * <p>
 * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
 * <p>
 * Let's define a and b as follows:
 * <p>
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * Note that ^ denotes the bitwise-xor operation.
 * <p>
 * Return the number of triplets (i, j and k) Where a == b.
 * @author: LISHUAI
 * @createDate: 2022/8/15 21:02
 * @version: 1.0
 */

public class LeetCode_1442 {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 6, 7};
//        int[] arr = {1, 1, 1, 1, 1};
        int i = countTriplets(arr);
        System.out.println(i);
    }

    public static int countTriplets(int[] arr) {
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                int b = arr[j];
                if (a == b) {
                    ans++;
                }
                for (int k = j + 1; k < arr.length; k++) {
                    b ^= arr[k];
                    if (a == b) {
                        ans++;
                    }
                }
                a ^= arr[j];
            }
        }
        return ans;
    }
}
