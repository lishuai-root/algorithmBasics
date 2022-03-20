package leetcode;

/**
 * @description: You are playing a solitaire game with three piles of stones of sizes a, b, and c respectively.
 * Each turn you choose two different non-empty piles, take one stone from each, and add 1 point to your score.
 * The game stops when there are fewer than two non-empty piles (meaning there are no more available moves).
 * <p>
 * Given three integers a, b, and c,
 * return the maximum score you can get.
 * @author: LISHUAI
 * @createDate: 2022/3/8 22:17
 * @version: 1.0
 */

public class LeetCode_1753 {

    public static void main(String[] args) {
        int a = 4, b = 4, c = 6;
        int i = maximumScore(a, b, c);
        System.out.println(i);
    }

    public static int maximumScore(int a, int b, int c) {
        int min = Math.min(a, Math.min(b, c));
        int max = Math.max(a, Math.max(b, c));
        int cur = a ^ b ^ c ^ min ^ max;
        a = max - cur;
        if (a >= min) {
            return cur + min;
        } else {
            cur += a;
            return cur + (min - a) / 2;
        }
    }
}
