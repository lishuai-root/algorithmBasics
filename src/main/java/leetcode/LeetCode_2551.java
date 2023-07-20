package leetcode;

import java.util.Arrays;

/**
 * @description: You have k bags. You are given a 0-indexed integer array weights where weights[i] is the weight of the ith marble. You are also given the integer k.
 * <p>
 * Divide the marbles into the k bags according to the following rules:
 * <p>
 * No bag is empty.
 * If the ith marble and jth marble are in a bag, then all marbles with an index between the ith and jth indices should also be in that same bag.
 * If a bag consists of all the marbles with an index from i to j inclusively, then the cost of the bag is weights[i] + weights[j].
 * The score after distributing the marbles is the sum of the costs of all the k bags.
 * <p>
 * Return the difference between the maximum and minimum scores among marble distributions.
 * @author: LiShuai
 * @createDate: 2023/7/8 21:02
 * @version: 1.0
 */

public class LeetCode_2551 {

    public static long putMarbles(int[] weights, int k) {
        int len = weights.length;
        int[] cache = new int[len - 1];
        for (int i = 1; i < len; i++) {
            cache[i - 1] = weights[i] + weights[i - 1];
        }
        Arrays.sort(cache);
        long ans = 0;
        for (int i = 0; i < k - 1; i++) {
            ans += cache[len - 2 - i] - cache[i];
        }
        return ans;
    }
}
