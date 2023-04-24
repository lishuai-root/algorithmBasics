package leetcode;

/**
 * @description: Given an array of integers arr and an integer d. In one step you can jump from index i to index:
 * <p>
 * i + x where: i + x < arr.length and  0 < x <= d.
 * i - x where: i - x >= 0 and  0 < x <= d.
 * In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).
 * <p>
 * You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * @author: LISHUAI
 * @createDate: 2022/12/10 12:13
 * @version: 1.0
 */

public class LeetCode_1340 {

    public static void main(String[] args) {
//        int[] arr = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
//        int d = 2;
        int[] arr = {22, 29, 52, 97, 29, 75, 78, 2, 92, 70, 90, 12, 43, 17, 97, 18, 58, 100, 41, 32};
        int d = 17;
//        int[] arr = {3, 3, 3, 3, 3};
//        int d = 3;
        int i = maxJumps(arr, d);
        System.out.println(i);
    }

    public static int maxJumps(int[] arr, int d) {
        int len = arr.length, ans = 0, index = -1;
        int[] stack = new int[len];
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            while (index != -1 && arr[i] > arr[stack[index]]) {
                int max = 0;
                for (int j = stack[index] + 1; j <= Math.min(stack[index] + d, i - 1); j++) {
                    if (arr[j] == arr[stack[index]]) {
                        break;
                    }
                    max = Math.max(max, dp[j]);
                }
                dp[stack[index]] = Math.max(dp[stack[index]], max) + 1;
                ans = Math.max(ans, dp[stack[index--]]);
            }

            int k = 0;
            if (index != -1) {
                k = stack[index] + 1;
            }
            int max = 0;
            for (int j = i - 1; j >= Math.max(k, i - d); j--) {
                if (arr[j] == arr[i]) {
                    break;
                }
                max = Math.max(max, dp[j]);
            }
            dp[i] = max;
            stack[++index] = i;
        }

        while (index >= 0) {
            int max = 0;
            for (int j = stack[index] + 1; j <= Math.min(stack[index] + d, len - 1); j++) {
                if (arr[j] == arr[stack[index]]) {
                    break;
                }
                max = Math.max(max, dp[j]);
            }
            dp[stack[index]] = Math.max(dp[stack[index]], max) + 1;
            ans = Math.max(ans, dp[stack[index--]]);
        }
        return ans;
    }

}
