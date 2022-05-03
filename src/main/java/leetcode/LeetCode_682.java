package leetcode;

/**
 * @description: You are keeping score for a baseball game with strange rules. The game consists of several rounds, where the scores of past rounds may affect future rounds' scores.
 * <p>
 * At the beginning of the game, you start with an empty record. You are given a list of strings ops, where ops[i] is the ith operation you must apply to the record and is one of the following:
 * <p>
 * An integer x - Record a new score of x.
 * "+" - Record a new score that is the sum of the previous two scores. It is guaranteed there will always be two previous scores.
 * "D" - Record a new score that is double the previous score. It is guaranteed there will always be a previous score.
 * "C" - Invalidate the previous score, removing it from the record. It is guaranteed there will always be a previous score.
 * Return the sum of all the scores on the record.
 * @author: LISHUAI
 * @createDate: 2022/4/10 17:15
 * @version: 1.0
 */

public class LeetCode_682 {

    public static void main(String[] args) {
        String[] ops = {"5", "2", "C", "D", "+"};
        int i = calPoints(ops);
        System.out.println(i);
    }

    public static int calPoints(String[] ops) {
        int[] stack = new int[ops.length];
        int index = -1, ans = 0, tmp;

        for (String s : ops) {
            if ("+".equals(s)) {
                tmp = stack[index] + stack[index - 1];
            } else if ("D".equals(s)) {
                tmp = stack[index] << 1;
            } else if ("C".equals(s)) {
                ans -= stack[index--];
                continue;
            } else {
                tmp = Integer.parseInt(s);
            }
            stack[++index] = tmp;
            ans += tmp;
        }
        for (int i : stack) {
            System.out.print(i + " ");
        }
        System.out.println();
        return ans;
    }
}
