package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @description: Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri),
 * remove all intervals that are covered by another interval in the list.
 * <p>
 * The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
 * <p>
 * Return the number of remaining intervals.
 * @author: LISHUAI
 * @createDate: 2022/2/20 13:45
 * @version: 1.0
 */

public class LeetCode_1288 {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        map.put(4, 1);

        int[][] ints = {{1, 2}, {1, 4}, {3, 4}};
        int i = removeCoveredIntervals(ints);
        int i1 = removeCoveredIntervals_02(ints);
        System.out.println(i);
        System.out.println(i1);
    }

    public static int removeCoveredIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> {
            int ans = a[0] - b[0];
            if (ans == 0) {
                ans = a[1] - b[1];
            }
            return ans;
        });

        int ans = 0, cur = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            while (i < intervals.length - 1 && intervals[i][0] == intervals[i + 1][0]) {
                i++;
            }
            if (intervals[i][1] > cur) {
                cur = intervals[i][1];
                ans++;
            }
        }
        return ans;
    }

    public static int removeCoveredIntervals_02(int[][] intervals) {

        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int[] ints : intervals) {
            if (!map.containsKey(ints[0])) {
                queue.offer(ints[0]);
                map.put(ints[0], ints[1]);
            } else {
                map.put(ints[0], Math.max(map.get(ints[0]), ints[1]));
            }
        }

        int ans = 0, max = Integer.MIN_VALUE;
//        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            if (max < map.get(poll)) {
                max = map.get(poll);
                ans++;
            }
        }

//        while (!queue.isEmpty()) {
//
//            int[] poll = queue.poll();
//            if (poll[1] > max) {
//                ans++;
//                max = poll[1];
//            }
//        }
        return ans;
    }
}
