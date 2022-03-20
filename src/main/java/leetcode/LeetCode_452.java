package leetcode;

import java.util.Arrays;

/**
 * @description: There are some spherical balloons taped onto a flat wall that represents the XY-plane.
 * The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend.
 * You do not know the exact y-coordinates of the balloons.
 * <p>
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 * <p>
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 * @author: LISHUAI
 * @createDate: 2022/1/13 21:54
 * @version: 1.0
 */

public class LeetCode_452 {

    public static void main(String[] args) {

        int[][] arr = {{9, 12}, {1, 10}, {4, 11}, {8, 12}, {3, 9}, {6, 9}, {6, 7}};

        System.out.println(findMinArrowShots(arr));

        System.out.println(-2147483646 - 2147483646);

        System.out.println(Integer.MIN_VALUE);
    }

    public static int findMinArrowShots(int[][] points) {

        Arrays.sort(points, (a, b) -> {

            int compare = Integer.compare(a[0], b[0]);

            if (compare == 0) {

                compare = Integer.compare(a[1], b[1]);
            }

            return compare;
        });

        int ans = 0, i = 0, j, min;

        while (i < points.length) {

            j = points[i][1];

            min = j;

            while (i < points.length && j >= points[i][0] && points[i][0] <= min) {

                min = Math.min(min, points[i][1]);

                i++;
            }

            ans++;
        }

        return ans;
    }
}







