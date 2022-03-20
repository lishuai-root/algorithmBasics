package leetcode;

/**
 * @description: On an infinite plane, a robot initially stands at (0, 0) and faces north.
 * The robot can receive one of three instructions:
 * <p>
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degrees to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 * <p>
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 * <p>
 * The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 * The robot moves north indefinitely.
 * @author: LISHUAI
 * @createDate: 2022/1/9 14:01
 * @version: 1.0
 */

public class LeetCode_1041 {

    public static void main(String[] args) {

        String instructions = "GG";

        System.out.println(isRobotBounded(instructions));
    }


    public static boolean isRobotBounded(String instructions) {

        int[] ans = new int[2], doubleAns = new int[2];

        String doubleInstructions = "";

        for (int i = 0; i < 50; i++) {

            doubleInstructions += instructions;

            ans[0] = 0;

            ans[1] = 0;

            process(doubleInstructions, 0, ans, 0, 0, 0);

            System.out.println(ans[0] + " : " + ans[1]);
        }

//        String doubleInstructions = instructions + instructions + instructions + instructions + instructions;
//
//        process(instructions, 0, ans, 0, 0, 0);
//
//        process(doubleInstructions, 0, doubleAns, 0, 0, 0);

//        System.out.println(ans[0] + " : " + ans[1]);
//        System.out.println(doubleAns[0] + " : " + doubleAns[1]);

        return (ans[0] == 0 && ans[1] == 0) || Math.max(Math.abs(doubleAns[0]), Math.abs(doubleAns[1])) - Math.max(Math.abs(ans[0]), Math.abs(ans[1])) <= Math.max(Math.abs(ans[0]), Math.abs(ans[1]));

//        int l = 0, x = 0, y = 0;
//
//        for (int i = 0; i < instructions.length(); i++) {
//
//            if (instructions.charAt(i) == 'L') {
//
//                l = (l + 1) & 3;
//            } else if (instructions.charAt(i) == 'R') {
//
//                l = (l - 1) & 3;
//            } else {
//
//                if ((l & 1) == 0) {
//
//                    x += -l + 1;
//                } else {
//
//                    y -= -l + 2;
//                }
//            }
//        }
//
//        return (x == 0 && y == 0) || l > 0;
    }

    private static void process(String instructions, int index, int[] ans, int x, int y, int l) {

        if (index >= instructions.length()) {

            return;
        }

        if (instructions.charAt(index) == 'L') {

            process(instructions, index + 1, ans, x, y, (l + 1) & 3);
        } else if (instructions.charAt(index) == 'R') {

            process(instructions, index + 1, ans, x, y, (l - 1) & 3);
        } else {

            if (l == 0) {

                y++;
            } else if (l == 1) {

                x--;
            } else if (l == 2) {

                y--;
            } else {

                x++;
            }

//            if (x * x + y * y > ans[0] * ans[0] + ans[1] * ans[1]) {
//
//                ans[0] = x;
//
//                ans[1] = y;
//            }

            if (Math.abs(x) > Math.abs(ans[0]) || Math.abs(y) > Math.abs(ans[1])) {

                ans[0] = x;

                ans[1] = y;
            }

//            if (Math.max(Math.abs(x), Math.abs(y)) > Math.max(Math.abs(ans[0]), Math.abs(ans[1]))) {
//
//                ans[0] = x;
//
//                ans[1] = y;
//            }

            process(instructions, index + 1, ans, x, y, l);
        }
    }


}


















