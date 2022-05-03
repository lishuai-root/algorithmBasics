package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Given an integer array nums and an integer k,
 * return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence,
 * nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 * <p>
 * A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array,
 * leaving the remaining elements in their original order.
 * @author: LISHUAI
 * @createDate: 2022/3/24 22:01
 * @version: 1.0
 */

public class LeetCode_1425 {

    private static Integer[] dp;

    public static void main(String[] args) {
//        int[] nums = {10, 2, -10, 5, 20};
//        int k = 2;
//        int[] nums = {-1, -2, -3};
//        int k = 1;
//        int[] nums = {10, -2, -10, -5, 20};
//        int k = 2;

        int[] nums = {100, -10, -10, -10, -2, -2, -10, -100, 15, -5, -10, 10, 2, -10, 5, 20};
        int k = 2;

//        int[] nums = {-5266, 4019, 7336, -3681, -5767};
//        int k = 2;
        int i = constrainedSubsetSum_02(nums, k);
        System.out.println(i);
    }

    public static int constrainedSubsetSum(int[] nums, int k) {

        int ans = Integer.MIN_VALUE;
        dp = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, constrainedSubsetSumProcess(nums, k, i + 1, nums[i]));
            System.out.println(ans);
        }
        System.out.println("--------------");
        return ans;
    }

    private static int constrainedSubsetSumProcess(int[] nums, int k, int index, int sum) {

        if (index >= nums.length) {
            return sum;
        }

//        if (dp[index] != null) {
//            return dp[index];
//        }
        int ans = sum;
        for (int i = index; i < k + index && i < nums.length; i++) {
//            ans = Math.max(ans, Math.max(constrainedSubsetSumProcess(nums, k, i + 1),
//                    constrainedSubsetSumProcess(nums, k, i + 1) + nums[i]));
            ans = Math.max(ans,
                    constrainedSubsetSumProcess(nums, k, i + 1, sum + nums[i]));
        }

//        dp[index] = ans;
        return ans;
    }

    public static int constrainedSubsetSum_02(int[] nums, int k) {
        int[] dp = new int[nums.length];
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> {
            return -(dp[a] - dp[b]);
        });
        dp[0] = nums[0];
        int ans = dp[0];
        queue.offer(0);

        for (int i = 1; i < nums.length; i++) {
            while (queue.peek() < i - k) {
                queue.poll();
            }
            dp[i] = Math.max(nums[i], dp[queue.peek()] + nums[i]);
            ans = Math.max(ans, dp[i]);
            queue.offer(i);
        }
        for (int i : dp) {
            System.out.println(i);
        }
        System.out.println("----------------");
        return ans;
    }


    /**
     * other people method
     *
     * @param nums
     * @param k
     * @return
     */
    public int constrainedSubsetSum_03(int[] nums, int k) {
        Deque<int[]> deque = new ArrayDeque<>();
        int max = nums[0];
        deque.offerLast(new int[]{0, nums[0]});
        for (int i = 1; i < nums.length; i++) {
            int cursum = nums[i];
            while (!deque.isEmpty() && deque.peekFirst()[0] < i - k) deque.pollFirst();
            if (!deque.isEmpty()) {
                cursum = Math.max(cursum, deque.peekFirst()[1] + nums[i]);
                while (!deque.isEmpty() && deque.peekLast()[1] < cursum) {
                    deque.pollLast();
                }
            }

            max = Math.max(cursum, max);
            deque.offerLast(new int[]{i, cursum});
        }

        return max;
    }
}
