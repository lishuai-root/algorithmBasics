package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: A robot on an infinite XY-plane starts at point (0, 0) facing north.
 * The robot can receive a sequence of these three possible types of commands:
 * <p>
 * -2: Turn left 90 degrees.
 * -1: Turn right 90 degrees.
 * 1 <= k <= 9: Move forward k units, one unit at a time.
 * Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi).
 * If the robot runs into an obstacle, then it will instead stay in its current location and move on to the next command.
 * <p>
 * Return the maximum Euclidean distance that the robot ever gets from the origin squared (i.e. if the distance is 5, return 25).
 * <p>
 * Note:
 * <p>
 * North means +Y direction.
 * East means +X direction.
 * South means -Y direction.
 * West means -X direction.
 * <p>
 * Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
 * @author: LISHUAI
 * @createDate: 2022/4/17 19:14
 * @version: 1.0
 */

public class LeetCode_874 {

    public static void main(String[] args) {
//        int[] commands = {6, -1, -1, 6};
//        int[][] obstacles = {};
        int[] commands = {4, -1, 4, -2, 4};
        int[][] obstacles = {{2, 4}};
        int i = robotSim(commands, obstacles);
        System.out.println(i);
    }

    public static int robotSim(int[] commands, int[][] obstacles) {

        Set<String> set = new HashSet<>();
        for (int[] ints : obstacles) {
            set.add(ints[0] + "-" + ints[1]);
        }
        int curX = 0, curY = 0, x, y, m = 0, ans = 0;

        for (int command : commands) {
            if (command < 0) {
                if (command == -1) {
                    m = (m + 4 - 1) & 3;
                } else if (command == -2) {
                    m = (m + 5) & 3;
                }
                continue;
            }

            if (m == 0) {
                x = 0;
                y = 1;
            } else if (m == 1) {
                x = -1;
                y = 0;
            } else if (m == 2) {
                x = 0;
                y = -1;
            } else {
                x = 1;
                y = 0;
            }

            for (int j = 0; j < command; j++) {
                if (set.contains((curX + x) + "-" + (curY + y))) {
                    break;
                }
                curX += x;
                curY += y;
            }
            ans = Math.max(ans, curX * curX + curY * curY);
        }

        System.out.println("curX : " + curX + ", curY : " + curY);
        return ans;
    }
}








