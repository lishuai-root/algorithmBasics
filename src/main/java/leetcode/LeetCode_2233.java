package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given an array of non-negative integers nums and an integer k. In one operation, you may choose any element from nums and increment it by 1.
 * <p>
 * Return the maximum product of nums after at most k operations. Since the answer may be very large, return it modulo 109 + 7. Note that you should maximize the product before taking the modulo.
 * @author: LiShuai
 * @createDate: 2023/7/6 22:32
 * @version: 1.0
 */

public class LeetCode_2233 {

    private static final int T = 1000000007;

    public static void main(String[] args) {
        int[] nums = {9, 7, 8};
        int k = 9;
//        int[] nums = {0, 4};
//        int k = 5;
        int i = maximumProduct_02(nums, k);
        System.out.println(i);
    }

    public static int maximumProduct(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0] + k;
        }
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i : nums) {
            queue.offer(i);
        }
        long ans = 1;
        for (int i = 0; i < k; ) {
            int poll = queue.poll();
            int p = Math.min(Math.max(1, queue.peek() - poll), k - i);
            queue.offer(poll + p);
            i += p;
        }
        while (!queue.isEmpty()) {
            ans *= queue.poll();
            ans %= T;
        }
        return (int) ans;
    }

    public static int maximumProduct_02(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0] + k;
        }
        Arrays.sort(nums);
        int len = nums.length, n = nums[0];
        int[] cache = new int[len + 1];

        for (int i = 1; i < len && k > 0; i++) {
            if (nums[i] != n) {
                int p = Math.min(nums[i] - n, k / i);
                cache[0] += p;
                cache[i] -= p;
                n += p;
                k -= (p * i);
                if (n != nums[i]) {
                    ++cache[0];
                    --cache[k];
                    k = 0;
                }
            }
        }
        if (k > 0) {
            int p = k / len;
            cache[0] += p;
            p = k % len;
            if (p > 0) {
                ++cache[0];
                --cache[p];
            }
        }
        long ans = 1;
        n = 0;
        for (int i = 0; i < len; i++) {
            n += cache[i];
            ans *= (nums[i] + n);
            ans %= T;
        }
        return (int) ans;
    }
}
