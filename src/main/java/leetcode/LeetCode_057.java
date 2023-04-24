package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * @author: LISHUAI
 * @createDate: 2022/12/26 22:09
 * @version: 1.0
 */

public class LeetCode_057 {

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] insert = insert(intervals, newInterval);
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>(intervals.length + 1);
        boolean b = false;
        int[] ints = new int[]{newInterval[0], newInterval[1]};

        for (int[] cur : intervals) {
            if (cur[1] < newInterval[0] || cur[0] > newInterval[1]) {
                if (!b && cur[0] > newInterval[1]) {
                    b = true;
                    ans.add(ints);
                }
                ans.add(cur);
                continue;
            }
            if (cur[0] <= newInterval[0] || cur[1] >= newInterval[1]) {
                ints[0] = Math.min(ints[0], cur[0]);
                ints[1] = Math.max(ints[1], cur[1]);
                if (!b) {
                    ans.add(ints);
                    b = true;
                }
            }
        }
        if (!b) {
            ans.add(ints);
        }
        return ans.toArray(new int[0][0]);
    }
}
