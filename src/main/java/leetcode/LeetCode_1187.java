package leetcode;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @description: Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
 * <p>
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 * <p>
 * If there is no way to make arr1 strictly increasing, return -1.
 * @author: LiShuai
 * @createDate: 2023/6/17 21:37
 * @version: 1.0
 */

public class LeetCode_1187 {

    private static Integer[][] cache;

    public static void main(String[] args) {
//        int[] arr1 = {1, 5, 3, 6, 7}, arr2 = {1, 3, 2, 4};
//        int[] arr1 = {1, 5, 3, 6, 7}, arr2 = {4, 3, 1};
        int[] arr1 = {1, 5, 3, 6, 7}, arr2 = {5, 4, 11, 10, 1, 0};
        int i = makeArrayIncreasing(arr1, arr2);
        System.out.println(i);
    }

    public static int makeArrayIncreasing(int[] arr1, int[] arr2) {
//        int i = makeArrayIncreasingProcess(arr1, arr2, 0);
        Arrays.sort(arr2);
        int index = 1;
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[i] != arr2[i - 1]) {
                index++;
            }
        }
        int[] arr = new int[index];
        arr[0] = arr2[0];
        index = 1;
        for (int i = 1; i < arr2.length; i++) {
            if (arr2[i] != arr2[i - 1]) {
                arr[index++] = arr2[i];
            }
        }
        cache = new Integer[arr1.length][arr.length + 1];
        int i = makeArrayIncreasingProcess(arr1, arr, 0, arr.length);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private static int makeArrayIncreasingProcess(int[] arr1, int[] arr2, int index) {
        if (index >= arr1.length) {
            return 0;
        }
        int ans = index == 0 || arr1[index] > arr1[index - 1] ? makeArrayIncreasingProcess(arr1, arr2, index + 1) : Integer.MAX_VALUE;
        int c = arr1[index];
        for (int j : arr2) {
            if (index == 0 || arr1[index - 1] < j) {
                arr1[index] = j;
                int p = makeArrayIncreasingProcess(arr1, arr2, index + 1);
                if (p != Integer.MAX_VALUE) {
                    ++p;
                }
                ans = Math.min(ans, p);
            }
        }
        arr1[index] = c;
        return ans;
    }


    private static int makeArrayIncreasingProcess(int[] arr1, int[] arr2, int index, int pre) {
        if (index >= arr1.length) {
            return 0;
        }

        if (cache[index][pre] != null) {
            return cache[index][pre];
        }

        int preNum = pre == arr2.length ? (index == 0 ? Integer.MIN_VALUE : arr1[index - 1]) : arr2[pre];
        int ans = arr1[index] > preNum ? makeArrayIncreasingProcess(arr1, arr2, index + 1, arr2.length) : Integer.MAX_VALUE;

        for (int i = 0; i < arr2.length; i++) {
            if (index == 0 || arr2[i] > preNum) {
                int p = makeArrayIncreasingProcess(arr1, arr2, index + 1, i);
                if (p != Integer.MAX_VALUE) {
                    ans = Math.min(ans, p + 1);
                }
            }
        }
        cache[index][pre] = ans;
        return ans;
    }

    public int makeArrayIncreasing_other(int[] A, int[] B) { // A = arr1, B = arr2
        TreeSet<Integer> set = new TreeSet<>(Arrays.stream(B).boxed().toList());
        int[] dp = new int[B.length + 1];
        dp[0] = -1;
        int INF = (int) 2e9;
        for (int i = 0; i < A.length; i++) {
            for (int j = B.length; j >= 0; j--) {
                int a = A[i] > dp[j] ? A[i] : INF; // option A - don't swap
                Integer b = set.higher(j == 0 ? INF : dp[j - 1]); // option B - swap
                dp[j] = Math.min(a, b == null ? INF : b); // take the min of A and B
            }
        }
        for (int i = 0; i <= B.length; i++)
            if (dp[i] != INF) {
                return i;
            }
        return -1;
    }
}
