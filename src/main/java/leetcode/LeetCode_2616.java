package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the maximum difference amongst all the pairs is minimized. Also, ensure no index appears more than once amongst the p pairs.
 * <p>
 * Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where |x| represents the absolute value of x.
 * <p>
 * Return the minimum maximum difference among all p pairs. We define the maximum of an empty set to be zero.
 * @author: LiShuai
 * @createDate: 2023/8/9 21:57
 * @version: 1.0
 */

public class LeetCode_2616 {

    private static int[][] pdp;

    public static void main(String[] args) {
        int[] nums = {3, 11, 4, 3, 5, 7, 4, 4, 5, 5};
        int p = 3;
//        int[] nums = {10, 1, 2, 7, 1, 3};
//        int p = 2;
//        int[] nums = {4, 2, 1, 2};
//        int p = 1;

        int i = minimizeMax_02(nums, p);
        System.out.println(i);
        nums = new int[100000];
        for (int j = 0; j < 100000; j++) {
            nums[j] = j + 1;
        }
        long start = System.currentTimeMillis();
        System.out.println(minimizeMax_dp(nums, 50000));
        long end = System.currentTimeMillis();
        System.out.println("func : " + (end - start));
    }

    public static int minimizeMax(int[] nums, int p) {
        if (p == 0) {
            return 0;
        }
        int len = nums.length;
        int[] cache = new int[len];
        Arrays.sort(nums);
        for (int i = 1; i < len; i++) {
            cache[i] = nums[i] - nums[i - 1];
        }
        int ans = Integer.MAX_VALUE;
        Queue<Integer> jq = new PriorityQueue<>((a, b) -> cache[b] - cache[a]);
        Queue<Integer> oq = new PriorityQueue<>((a, b) -> cache[b] - cache[a]);

        for (int i = 1; i < len; i++) {
            Queue<Integer> queue = null;
            if ((i & 1) == 1) {
                jq.offer(i);
                queue = jq;
            } else {
                oq.offer(i);
                queue = oq;
            }
            if (queue.size() >= p) {
                while (queue.peek() <= i - (p << 1)) {
                    queue.poll();
                }
                ans = Math.min(ans, cache[queue.peek()]);
            }
        }
        return ans;
    }

    public static int minimizeMax_02(int[] nums, int p) {
        if (p == 0) {
            return 0;
        }
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len - 1; i++) {
            nums[i] = nums[i + 1] - nums[i];
        }
        pdp = new int[len - 1][p + 1];
        for (int[] ints : pdp) {
            Arrays.fill(ints, -1);
        }
        return minimizeMaxProcess(nums, 0, p);
    }

    public static int minimizeMaxProcess(int[] nums, int index, int p) {
        if (p == 0) {
            return 0;
        }
        if (index >= nums.length - 1) {
            return Integer.MAX_VALUE;
        }
        if (pdp[index][p] != -1) {
            return pdp[index][p];
        }
        int p1 = minimizeMaxProcess(nums, index + 1, p);
        int p2 = Math.max(nums[index], minimizeMaxProcess(nums, index + 2, p - 1));
        pdp[index][p] = Math.min(p1, p2);
        return Math.min(p1, p2);
    }

    public static int minimizeMax_dp(int[] nums, int p) {
        if (p == 0) {
            return 0;
        }
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len - 1; i++) {
            nums[i] = nums[i + 1] - nums[i];
        }
        int[] dp = new int[len + 1];
        int[] cache = new int[len + 1];
        dp[len - 1] = Integer.MAX_VALUE;
        dp[len] = Integer.MAX_VALUE;
        for (int i = p - 1; i >= 0; --i) {
            for (int j = len - 2; j >= i; --j) {
                dp[j] = Math.min(dp[j + 1], Math.max(nums[j], cache[j + 2]));
            }
            System.arraycopy(dp, 0, cache, 0, len + 1);
        }
        return dp[0];
    }

    public boolean solve(int[] nums, int d, int p) {
        int n = nums.length;
        int c = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] <= d) {
                c++;
                i++;
            }
            if (c >= p) {
                return true;
            }
        }
        return false;
    }

    public int minimizeMax_other(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length;
        int lo = 0, hi = nums[n - 1] - nums[0];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (solve(nums, mid, p)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
