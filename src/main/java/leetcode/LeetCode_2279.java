package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You have n bags numbered from 0 to n - 1. You are given two 0-indexed integer arrays capacity and rocks. The ith bag can hold a maximum of capacity[i] rocks and currently contains rocks[i] rocks. You are also given an integer additionalRocks, the number of additional rocks you can place in any of the bags.
 * <p>
 * Return the maximum number of bags that could have full capacity after placing the additional rocks in some bags.
 * @author: LISHUAI
 * @createDate: 2022/12/27 12:05
 * @version: 1.0
 */

public class LeetCode_2279 {

    public static int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < capacity.length; i++) {
            queue.offer(capacity[i] - rocks[i]);
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (poll <= additionalRocks) {
                ans++;
                additionalRocks -= poll;
            } else {
                break;
            }
        }
        return ans;
    }

    public static int maximumBags_02(int[] capacity, int[] rocks, int additionalRocks) {
        int len = capacity.length;
        int[] sub = new int[len];
        for (int i = 0; i < len; i++) {
            sub[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(sub);
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (additionalRocks < sub[i]) {
                break;
            }
            ans++;
            additionalRocks -= sub[i];
        }
        return ans;
    }

    public static int maximumBags_03(int[] capacity, int[] rocks, int additionalRocks) {
        int len = capacity.length;
        int[] sub = new int[len];
        long sum = 0;
        for (int i = 0; i < len; i++) {
            sub[i] = capacity[i] - rocks[i];
            sum += sub[i];
        }
        if (additionalRocks >= sum) {
            return len;
        }
        Arrays.sort(sub);
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (additionalRocks < sub[i]) {
                break;
            }
            ans++;
            additionalRocks -= sub[i];
        }
        return ans;
    }
}
