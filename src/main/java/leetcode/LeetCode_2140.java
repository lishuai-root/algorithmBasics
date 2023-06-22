package leetcode;

/**
 * @description: You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].
 * <p>
 * The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0) and make a decision whether to solve or skip each question. Solving question i will earn you pointsi points but you will be unable to solve each of the next brainpoweri questions. If you skip question i, you get to make the decision on the next question.
 * <p>
 * For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
 * If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
 * If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
 * Return the maximum points you can earn for the exam.
 * @author: LISHUAI
 * @createDate: 2023/5/12 21:28
 * @version: 1.0
 */

public class LeetCode_2140 {

    public static long mostPoints(int[][] questions) {
        return mostPointsProcess(questions, 0);
    }

    private static long mostPointsProcess(int[][] questions, int index) {
        if (index >= questions.length) {
            return 0;
        }
        long p1 = mostPointsProcess(questions, index + questions[index][1] + 1) + questions[index][0];
        long p2 = mostPointsProcess(questions, index + 1);
        return Math.max(p1, p2);
    }

    public static long mostPoints_dp(int[][] questions) {
        int len = questions.length;
        long[] dp = new long[len + 1];

        for (int i = len - 1; i >= 0; i--) {
            long p1 = questions[i][0];
            if (i + questions[i][1] + 1 < len) {
                p1 += dp[i + questions[i][1] + 1];
            }
            dp[i] = Math.max(p1, dp[i + 1]);
        }
        return dp[0];
    }

}
