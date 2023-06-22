package leetcode;

/**
 * @description: You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:
 * <p>
 * nums.length == n
 * nums[i] is a positive integer where 0 <= i < n.
 * abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
 * The sum of all the elements of nums does not exceed maxSum.
 * nums[index] is maximized.
 * Return nums[index] of the constructed array.
 * <p>
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 * @author: LiShuai
 * @createDate: 2023/6/11 17:59
 * @version: 1.0
 */

public class LeetCode_1802 {

    public static void main(String[] args) {
//        int n = 4, index = 2, maxSum = 6;
//        int n = 7, index = 5, maxSum = 30;
//        int n = 6, index = 1, maxSum = 10;
//        int n = 3, index = 2, maxSum = 18;
//        int n = 4, index = 0, maxSum = 4;
//        int n = 5, index = 0, maxSum = 28;
        int n = 8257285, index = 4828516, maxSum = 850015631;
        int i = maxValue(n, index, maxSum);
        System.out.println(i);
        System.out.println(maxValue_02(n, index, maxSum));
    }

    public static int maxValue(int n, int index, int maxSum) {
        int ans = 1, sum = n;
        int max = Math.max(index, n - index - 1);
        int min = Math.min(index, n - index - 1);

        for (int i = 0; i <= max; i++) {
            int t = (i > min ? i + min : (i << 1)) + 1;
            if (sum + t <= maxSum) {
                sum += t;
                ++ans;
            } else {
                break;
            }
        }
        ans += (maxSum - sum) / n;
        return ans;
    }


    public static int maxValue_02(int n, int index, int maxSum) {
        int ans = 1, sum = n;
        int max = Math.max(index, n - index - 1);
        int min = Math.min(index, n - index - 1);
        int left = 0, right = max;
        long m = (((long) min << 1) + 2) * (min + 1) / 2;

        while (left <= right) {
            int mod = (left + right) >> 1;
            long t;
            if (mod <= min) {
                t = (((long) mod << 1) + 2) * (mod + 1) / 2;
            } else {
                t = m + (((long) min * 2 + 2) + (mod + min + 1)) * (mod - min) / 2;
            }
            if (t + n <= maxSum) {
                ans = mod + 2;
                left = mod + 1;
                sum = (int) (t + n);
            } else {
                right = mod - 1;
            }
        }

        ans += (maxSum - sum) / n;
        return ans;
    }
}
