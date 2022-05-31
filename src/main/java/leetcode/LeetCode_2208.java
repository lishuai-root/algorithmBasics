package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given an array nums of positive integers. In one operation,
 * you can choose any number from nums and reduce it to exactly half the number.
 * (Note that you may choose this reduced number in future operations.)
 * <p>
 * Return the minimum number of operations to reduce the sum of nums by at least half.
 * @author: LISHUAI
 * @createDate: 2022/5/20 22:51
 * @version: 1.0
 */

public class LeetCode_2208 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1, 1, 1};
        int i = halveArray(nums);
        System.out.println(i);
    }

    public static int halveArray(int[] nums) {
        double sum = 0, mid;
        int ans = 0;
        Queue<Double> queue = new PriorityQueue<>((a, b) -> {
            if (a > b) {
                return -1;
            } else if (b > a) {
                return 1;
            }
            return 0;
        });

        for (int i : nums) {
            sum += i;
            queue.offer((double) i);
        }

        mid = sum / 2;
        while (sum > mid) {
            ans++;
            double cur = queue.poll();
            cur /= 2;
            sum -= cur;
            queue.offer(cur);
        }
        return ans;
    }
}
