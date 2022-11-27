package leetcode;

import java.util.Arrays;

/**
 * @description: You have an initial power of power, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith token (0-indexed).
 * <p>
 * Your goal is to maximize your total score by potentially playing each token in one of two ways:
 * <p>
 * If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining 1 score.
 * If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
 * Each token may be played at most once and in any order. You do not have to play all the tokens.
 * <p>
 * Return the largest possible score you can achieve after playing any number of tokens.
 * @author: LISHUAI
 * @createDate: 2022/9/12 18:43
 * @version: 1.0
 */

public class LeetCode_948 {

    public static void main(String[] args) {
//        int[] tokens = {100, 200};
//        int power = 150;
//        int[] tokens = {52, 65, 35, 88, 28, 1, 4, 68, 56, 95};
//        int power = 94;
//        int[] tokens = {100};
//        int power = 50;
//        int[] tokens = {100, 200, 300, 400};
//        int power = 200;
        int[] tokens = {58, 91};
        int power = 50;
        int i = bagOfTokensScore(tokens, power);
        System.out.println(i);
        int i1 = bagOfTokensScore_02(tokens, power);
        System.out.println(i1);
    }

    public static int bagOfTokensScore(int[] tokens, int power) {
        boolean[] bls = new boolean[tokens.length];
        return bagOfTokensScoreProcess(tokens, power, 0, 0, bls);
    }

    private static int bagOfTokensScoreProcess(int[] tokens, int power, int ans, int n, boolean[] bls) {
        if (n >= tokens.length) {
            return ans;
        }

        int p1 = bagOfTokensScoreProcess(tokens, power, ans, n + 1, bls);
        int p2 = ans;
        for (int i = 0; i < tokens.length; i++) {
            if (!bls[i]) {
                bls[i] = true;
                if (power >= tokens[i]) {
                    p2 = Math.max(p2, bagOfTokensScoreProcess(tokens, power - tokens[i], ans + 1, n + 1, bls));
                } else if (ans > 0) {
                    p2 = Math.max(p2, bagOfTokensScoreProcess(tokens, power + tokens[i], ans - 1, n + 1, bls));
                }
                bls[i] = false;
            }
        }
        return Math.max(p1, p2);
    }


    public static int bagOfTokensScore_02(int[] tokens, int power) {
        Arrays.sort(tokens);
        int ans = 0, left = 0, right = tokens.length - 1;

        while (left <= right) {
            if (tokens[left] <= power) {
                power -= tokens[left++];
                ans++;
            } else {
                if (left != right && ans > 0) {
                    power += tokens[right--];
                    ans--;
                } else {
                    break;
                }
            }
        }
        return ans;
    }
}
