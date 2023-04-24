package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given an integer n denoting the number of cities in a country. The cities are numbered from 0 to n - 1.
 * <p>
 * You are also given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.
 * <p>
 * You need to assign each city with an integer value from 1 to n, where each value can only be used once. The importance of a road is then defined as the sum of the values of the two cities it connects.
 * <p>
 * Return the maximum total importance of all roads possible after assigning the values optimally.
 * @author: LISHUAI
 * @createDate: 2022/12/11 20:37
 * @version: 1.0
 */

public class LeetCode_2285 {

    public static long maximumImportance(int n, int[][] roads) {
        int[] weights = new int[n];
        for (int[] ints : roads) {
            weights[ints[0]]++;
            weights[ints[1]]++;
        }
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> (weights[b] - weights[a]));
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }
        int k = n;
        while (!queue.isEmpty()) {
            int q = queue.poll();
            weights[q] = k--;
        }
        long ans = 0;
        for (int[] ints : roads) {
            ans += weights[ints[0]] + weights[ints[1]];
        }
        return ans;
    }
}
