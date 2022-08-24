package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.
 * <p>
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 * @author: LISHUAI
 * @createDate: 2022/8/18 20:01
 * @version: 1.0
 */

public class LeetCode_1338 {

    public int minSetSize(int[] arr) {
        int[] sum = new int[100001];
        int ans = 0, k = 0;
        for (int i : arr) {
            sum[i]++;
        }
        Arrays.sort(sum);
        for (int i = sum.length - 1; i >= 0; i--) {
            ans++;
            k += sum[i];
            if (k >= arr.length / 2) {
                break;
            }
        }
        return ans;
    }
}
