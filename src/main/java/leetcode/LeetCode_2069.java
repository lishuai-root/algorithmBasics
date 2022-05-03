package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: A width x height grid is on an XY-plane with the bottom-left cell at (0, 0) and the top-right cell at (width - 1, height - 1).
 * The grid is aligned with the four cardinal directions ("North", "East", "South", and "West"). A robot is initially at cell (0, 0) facing direction "East".
 * <p>
 * The robot can be instructed to move for a specific number of steps. For each step, it does the following.
 * <p>
 * Attempts to move forward one cell in the direction it is facing.
 * If the cell the robot is moving to is out of bounds, the robot instead turns 90 degrees counterclockwise and retries the step.
 * After the robot finishes moving the number of steps required, it stops and awaits the next instruction.
 * <p>
 * Implement the Robot class:
 * <p>
 * Robot(int width, int height) Initializes the width x height grid with the robot at (0, 0) facing "East".
 * void step(int num) Instructs the robot to move forward num steps.
 * int[] getPos() Returns the current cell the robot is at, as an array of length 2, [x, y].
 * String getDir() Returns the current direction of the robot, "North", "East", "South", or "West".
 * @author: LISHUAI
 * @createDate: 2022/4/17 20:02
 * @version: 1.0
 */

public class LeetCode_2069 {

    public static void main(String[] args) {
        Robot robot = new Robot(6, 3);
        robot.step(2);
        robot.step(2);
        robot.step(2);
        robot.step(1);
        robot.step(4);
        int[] pos = robot.getPos();
        for (int i : pos) {
            System.out.print(i + " ");
        }
        System.out.println();
        Map<String, String> map = new HashMap<>();
    }

    static class Robot {

        private final int width, height, C;
        private final int[] cur = new int[2];
        private int dir = 2;

        public Robot(int width, int height) {
            this.width = width;
            this.height = height;
            C = (width + height - 2) << 1;
        }

        public void step(int num) {
            num %= C;
            while (num > 0) {
                if ((cur[0] >= width - 1 || cur[0] == 0) && (cur[1] >= height - 1 || cur[1] == 0)) {
                    dir = (dir + 5) & 3;

                }
                int tmp;
                if (dir == 3) {
                    tmp = Math.min(num, width - cur[0] - 1);
                    cur[0] += tmp;
                } else if (dir == 1) {
                    tmp = Math.min(num, cur[0]);
                    cur[0] -= tmp;
                } else if (dir == 0) {
                    tmp = Math.min(num, height - cur[1] - 1);
                    cur[1] += tmp;
                } else {
                    tmp = Math.min(num, cur[1]);
                    cur[1] -= tmp;
                }

                num -= tmp;
            }
        }


        public int[] getPos() {
            return cur;
        }

        public String getDir() {
            if (dir == 0) {
                return "North";
            } else if (dir == 1) {
                return "West";
            } else if (dir == 2) {
                return "South";
            } else {
                return "East";
            }

        }
    }
}
