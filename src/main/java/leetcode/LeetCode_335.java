package leetcode;

/**
 * @description: You are given an array of integers distance.
 * <p>
 * You start at point (0,0) on an X-Y plane and you move distance[0] meters to the north,
 * then distance[1] meters to the west, distance[2] meters to the south, distance[3] meters to the east, and so on.
 * In other words, after each move, your direction changes counter-clockwise.
 * <p>
 * Return true if your path crosses itself, and false if it does not.
 * @author: LISHUAI
 * @createDate: 2022/5/12 22:09
 * @version: 1.0
 */

public class LeetCode_335 {
    private static int[][] tmp = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) {
//        int[] distance = {2, 1, 1, 2};
//        int[] distance = {1, 2, 3, 4};
//        int[] distance = {3, 3, 4, 2, 2};
        int[] distance = {1, 1, 2, 2, 3, 3, 4, 4, 10, 4, 4, 3, 3, 2, 2, 1, 1};
//        int[] distance = {1, 1, 1, 1};
//        int[] distance = {1, 1, 2, 1, 1};
        boolean selfCrossing = isSelfCrossing_02(distance);
        System.out.println(selfCrossing);
    }

    public static boolean isSelfCrossing(int[] distance) {
        if (distance.length < 4) {
            return false;
        }
        int skip = 1;
        if (distance[0] > distance[1]) {
            System.out.println("--------");
            skip = -1;
        }

        for (int i = 3; i < distance.length; i++) {
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSelfCrossing_02(int[] distance) {
        if (distance.length < 4) {
            return false;
        }
        int len = distance.length;
        int[][] ways = new int[len + 1][4];
        int index = 0, curX = 0, curY = 0, wayIndex = -1;
        for (int i = 0; i < 4; i++) {
            int[] ints = tmp[index++ & 3];
            int x = distance[i] * ints[0] + curX;
            int y = distance[i] * ints[1] + curY;
            int[] way = ways[++wayIndex];
            way[0] = curX;
            way[1] = curY;
            way[2] = x;
            way[3] = y;
            curX = x;
            curY = y;
        }
        if (isCha(ways[3], ways[0])) {
            System.out.println("---");
            return true;
        }

        for (int i = 4; i < len; i++) {
            int[] ints = tmp[index++ & 3];
            int x = distance[i] * ints[0] + curX;
            int y = distance[i] * ints[1] + curY;
            int[] way = ways[++wayIndex];
            way[0] = curX;
            way[1] = curY;
            way[2] = x;
            way[3] = y;
            curX = x;
            curY = y;
            if (isCha(ways[wayIndex], ways[wayIndex - 3]) || isCha(ways[wayIndex], ways[wayIndex - 4])) {
                System.out.println(i);
                System.out.println("---");
                return true;
            }
            if (i >= 5 && isCha(ways[wayIndex], ways[wayIndex - 5])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCha(int[] curLine, int[] line) {
        int[] maxLine, minLine;
        // 两条竖线
        if (curLine[0] - curLine[2] == 0 && line[0] - line[2] == 0) {
            if (Math.max(curLine[1], curLine[3]) > Math.max(line[1], line[3])) {
                maxLine = curLine;
                minLine = line;
            } else {
                maxLine = line;
                minLine = curLine;
            }
            return maxLine[0] == minLine[0] && Math.min(maxLine[1], maxLine[3]) <= Math.max(minLine[1], minLine[3]);
        }
        // 两条横线
        else if (curLine[1] - curLine[3] == 0 && line[0] - line[3] == 0) {
            if (Math.max(curLine[0], curLine[2]) > Math.max(line[0], line[2])) {
                maxLine = curLine;
                minLine = line;
            } else {
                maxLine = line;
                minLine = curLine;
            }
            return curLine[1] == line[1] && Math.min(maxLine[0], maxLine[2]) <= Math.max(minLine[0], minLine[2]);
        }
        // 一条横线，一条竖线
        else {
            int[] hx, sx;
            if (curLine[1] == curLine[3]) {
                hx = curLine;
                sx = line;
            } else {
                hx = line;
                sx = curLine;
            }

            return Math.max(hx[0], hx[2]) >= sx[0] && Math.min(hx[0], hx[2]) <= sx[0]
                    && Math.max(sx[1], sx[3]) >= hx[1] && Math.min(sx[1], sx[3]) <= hx[1];
        }
    }
}
