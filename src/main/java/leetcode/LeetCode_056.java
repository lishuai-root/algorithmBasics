package leetcode;

import java.util.Arrays;

/**
 * @description: Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * @author: LISHUAI
 * @createDate: 2021/11/20 16:34
 * @version: 1.0
 */

public class LeetCode_056 {
    public static void main(String[] args) {

        int[][] ints = new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}

        };

        int[][] merge = merge(ints);

        for (int[] value : merge) {

            for (int i : value) {

                System.out.print(i + "   ");
            }
            System.out.println();
        }
    }

    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int index = 0;

        int[][] ints = new int[intervals.length][2];

        ints[0][0] = intervals[0][0];
        ints[0][1] = intervals[0][1];

        for (int[] interval : intervals) {

            if (interval[0] > ints[index][1]) {

                ints[++index][0] = interval[0];

                ints[index][1] = interval[1];
            } else if (interval[1] > ints[index][1]) {

                ints[index][1] = interval[1];
            }

        }

        return Arrays.copyOfRange(ints, 0, index + 1);
    }
}
