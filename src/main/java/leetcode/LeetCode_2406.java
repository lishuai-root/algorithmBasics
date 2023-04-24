package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given a 2D integer array intervals where intervals[i] = [lefti, righti] represents the inclusive interval [lefti, righti].
 * <p>
 * You have to divide the intervals into one or more groups such that each interval is in exactly one group, and no two intervals that are in the same group intersect each other.
 * <p>
 * Return the minimum number of groups you need to make.
 * <p>
 * Two intervals intersect if there is at least one common number between them. For example, the intervals [1, 5] and [5, 8] intersect.
 * @author: LISHUAI
 * @createDate: 2023/4/5 21:32
 * @version: 1.0
 */

public class LeetCode_2406 {

    public static int minGroups(int[][] intervals) {
        int len = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Queue<Integer> queue = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (!queue.isEmpty() && queue.peek() < interval[0]) {
                queue.poll();
            }
            queue.offer(interval[1]);
        }
        return queue.size();
    }
}
