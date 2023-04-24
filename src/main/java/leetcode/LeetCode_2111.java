package leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @description: You are given a 0-indexed array arr consisting of n positive integers, and a positive integer k.
 * <p>
 * The array arr is called K-increasing if arr[i-k] <= arr[i] holds for every index i, where k <= i <= n-1.
 * <p>
 * For example, arr = [4, 1, 5, 2, 6, 2] is K-increasing for k = 2 because:
 * arr[0] <= arr[2] (4 <= 5)
 * arr[1] <= arr[3] (1 <= 2)
 * arr[2] <= arr[4] (5 <= 6)
 * arr[3] <= arr[5] (2 <= 2)
 * However, the same arr is not K-increasing for k = 1 (because arr[0] > arr[1]) or k = 3 (because arr[0] > arr[3]).
 * In one operation, you can choose an index i and change arr[i] into any positive integer.
 * <p>
 * Return the minimum number of operations required to make the array K-increasing for the given k.
 * @author: LISHUAI
 * @createDate: 2022/12/13 10:57
 * @version: 1.0
 */

public class LeetCode_2111 {

    public static void main(String[] args) {
//        int[] arr = {5, 4, 3, 2, 1};
//        int k = 1;
//        int[] arr = {4, 1, 5, 2, 6, 2};
//        int k = 2;
        int[] arr = {4, 1, 5, 2, 6, 2};
        int k = 3;
//        int[] arr = {12, 6, 12, 6, 14, 2, 13, 17, 3, 8, 11, 7, 4, 11, 18, 8, 8, 3};
//        int k = 1;
        int i = kIncreasing(arr, k);
        System.out.println(i);

        TreeSet<Integer> infos = new TreeSet<>();
        infos.add(1);
        infos.add(3);
        infos.add(4);
        System.out.println(infos.ceiling(2));
    }

    public static int kIncreasing(int[] arr, int k) {
        int len = arr.length;
        int[] cache = new int[len + 1];
        int ans = 0;

        for (int i = 1; i <= k; i++) {
            int max = Integer.MIN_VALUE;
            int size = 0;
            TreeSet<Integer> set = new TreeSet<>();
            Arrays.fill(cache, 0);
            for (int j = len - i; j >= 0; j -= k) {
                size++;
                int curMax = 0;
                Integer curIndex = arr[j];
                while (curIndex != null) {
                    curMax = Math.max(curMax, cache[curIndex]);
                    curIndex = set.ceiling(curIndex + 1);
                }
                cache[arr[j]] = Math.max(cache[arr[j]], curMax + 1);
                max = Math.max(max, cache[arr[j]]);
                set.add(arr[j]);
            }
            ans += (size - max);
        }
        return ans;
    }

    public int kIncreasing_other(int[] arr, int k) {
        int count = 0;
        for (int i = 0; i < k; i++) {
            count += longestSub(arr, k, i);
        }
        return arr.length - count;
    }

    public int longestSub(int[] arr, int k, int i) {
        TreeSet<Integer> set = new TreeSet<>((a, b) -> { // store index to solve duplication issue
            return arr[a] == arr[b] ? a - b : arr[a] - arr[b];
        });
        for (; i < arr.length; i += k) {
            if (set.higher(i) != null) {
                set.remove(set.higher(i));
            }
            set.add(i);
        }
        return set.size();
    }
}
