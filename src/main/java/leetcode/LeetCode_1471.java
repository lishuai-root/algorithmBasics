package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Given an array of integers arr and an integer k.
 * <p>
 * A value arr[i] is said to be stronger than a value arr[j] if |arr[i] - m| > |arr[j] - m| where m is the median of the array.
 * If |arr[i] - m| == |arr[j] - m|, then arr[i] is said to be stronger than arr[j] if arr[i] > arr[j].
 * <p>
 * Return a list of the strongest k values in the array. return the answer in any arbitrary order.
 * <p>
 * Median is the middle value in an ordered integer list. More formally, if the length of the list is n, the median is the element in position ((n - 1) / 2) in the sorted list (0-indexed).
 * <p>
 * For arr = [6, -3, 7, 2, 11], n = 5 and the median is obtained by sorting the array arr = [-3, 2, 6, 7, 11] and the median is arr[m] where m = ((5 - 1) / 2) = 2. The median is 6.
 * For arr = [-7, 22, 17, 3], n = 4 and the median is obtained by sorting the array arr = [-7, 3, 17, 22] and the median is arr[m] where m = ((4 - 1) / 2) = 1. The median is 3.
 * @author: LISHUAI
 * @createDate: 2023/2/6 21:56
 * @version: 1.0
 */

public class LeetCode_1471 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 2;
        int[] ints = getStrongest_02(arr, k);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    public static int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int mid = (arr.length - 1) >>> 1;
        Queue<Integer> queue = new PriorityQueue<Integer>((a, b) -> {
            int c = Math.abs(b - arr[mid]) - Math.abs(a - arr[mid]);
            if (c == 0) {
                c = b - a;
            }
            return c;
        });
        for (int i : arr) {
            queue.offer(i);
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }


    public static int[] getStrongest_02(int[] arr, int k) {
        Arrays.sort(arr);
        int mid = arr[(arr.length - 1) >>> 1];
        int[] ans = new int[k];
        int left = 0, right = arr.length - 1;
        int index = 0;

        while (index < k) {
            if (mid - arr[left] > arr[right] - mid) {
                ans[index++] = arr[left++];
            } else {
                ans[index++] = arr[right--];
            }
        }
        return ans;
    }
}
