package leetcode;

/**
 * @description: You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.
 * <p>
 * In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.
 * <p>
 * Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.
 * <p>
 * Each answer[i] is calculated considering the initial state of the boxes.
 * @author: LISHUAI
 * @createDate: 2023/4/18 20:01
 * @version: 1.0
 */

public class LeetCode_1769 {

    public static int[] minOperations(String boxes) {
        int len = boxes.length();
        int[] ans = new int[len];
        int ball = 0, op = 0;
        for (int i = len - 1; i >= 0; i--) {
            op += ball;
            if (boxes.charAt(i) == '1') {
                ball++;
            }
        }
        int b = 0, o = 0;
        for (int i = 0; i < len; i++) {
            ans[i] = op + o;
            if (boxes.charAt(i) == '1') {
                b++;
                ball--;
            }
            o += b;
            op -= ball;
        }
        return ans;
    }

    public static int[] minOperations_02(String boxes) {
        int len = boxes.length();
        int[] ans = new int[len];
        int ball = 0, op = 0;
        for (int i = 0; i < len; i++) {
            ans[i] = op;
            ball += boxes.charAt(i) - '0';
            op += ball;
        }
        ball = 0;
        op = 0;
        for (int i = len - 1; i >= 0; i--) {
            ans[i] += op;
            ball += boxes.charAt(i) - '0';
            op += ball;
        }
        return ans;
    }
}
