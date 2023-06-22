package leetcode;

/**
 * @description: There is a robot starting at the position (0, 0), the origin, on a 2D plane. Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.
 * <p>
 * You are given a string moves that represents the move sequence of the robot where moves[i] represents its ith move. Valid moves are 'R' (right), 'L' (left), 'U' (up), and 'D' (down).
 * <p>
 * Return true if the robot returns to the origin after it finishes all of its moves, or false otherwise.
 * <p>
 * Note: The way that the robot is "facing" is irrelevant. 'R' will always make the robot move to the right once, 'L' will always make it move left, etc. Also, assume that the magnitude of the robot's movement is the same for each move.
 * @author: LiShuai
 * @createDate: 2023/6/4 21:41
 * @version: 1.0
 */

public class LeetCode_657 {

    public static boolean judgeCircle(String moves) {
        int row = 0, col = 0, len = moves.length();
        final int[][] tmp = new int[26][2];
        tmp['L' - 'A'] = new int[]{0, -1};
        tmp['R' - 'A'] = new int[]{0, 1};
        tmp['U' - 'A'] = new int[]{1, 0};
        tmp['D' - 'A'] = new int[]{-1, 0};

        for (int i = 0; i < len; i++) {
            int[] t = tmp[moves.charAt(i) - 'A'];
            row += t[0];
            col += t[1];
        }
        return row == 0 && col == 0;
    }
}
