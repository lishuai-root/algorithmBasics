package leetcode;

/**
 * @description: You are given an integer array rolls of length n and an integer k. You roll a k sided dice numbered from 1 to k, n times, where the result of the ith roll is rolls[i].
 * <p>
 * Return the length of the shortest sequence of rolls that cannot be taken from rolls.
 * <p>
 * A sequence of rolls of length len is the result of rolling a k sided dice len times.
 * <p>
 * Note that the sequence taken does not have to be consecutive as long as it is in order.
 * @author: LISHUAI
 * @createDate: 2022/12/3 1:47
 * @version: 1.0
 */

public class LeetCode_2350 {

    public static void main(String[] args) {
        int[] rolls = {4, 2, 1, 2, 3, 3, 2, 4, 1};
        int k = 4;
//        int[] rolls = {1, 1, 3, 2, 2, 2, 3, 3};
//        int k = 4;
//        int[] rolls = {2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 1, 2, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1, 2, 2, 1, 1, 2, 2, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1, 2, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 2, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 1, 1, 1, 2, 2, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 1, 1, 2, 1, 2, 2, 2, 2, 2};
//        int k = 2;
//        int[] rolls = {3, 2, 1, 3, 3, 3, 3, 3, 1, 2, 2, 3, 1, 3, 3, 1, 1, 3, 1, 1, 1, 3, 1, 3, 3, 1, 2, 3, 2, 1, 1, 2};
//        int k = 3;
        int i = shortestSequence(rolls, k);
        System.out.println(i);
        System.out.println(shortestSequence_dp(rolls, k));
    }

    public static int shortestSequence(int[] rolls, int k) {
        boolean[] bls = new boolean[k + 1];
        for (int i = 1; i <= rolls.length; i++) {
            boolean b = shortestSequenceProcess(rolls, k, i, 0);
            if (!b) {
                return i;
            }
        }
        return -1;
    }

    private static boolean shortestSequenceProcess(int[] rolls, int k, int q, int index) {
        if (q == 0) {
            return true;
        }
        if (index >= rolls.length) {
            return false;
        }

        for (int i = 1; i <= k; i++) {
            boolean ans = false;
            for (int j = index; j < rolls.length; j++) {
                if (rolls[j] == i) {
                    ans = shortestSequenceProcess(rolls, k, q - 1, j + 1);
                    break;
                }
            }
            if (!ans) {
                return false;
            }
        }
        return true;
    }

    public static int shortestSequence_dp(int[] rolls, int k) {
        int ans = 1, size = 0;
        int[] cache = new int[k + 1];

        for (int i = rolls.length - 1; i >= 0; i--) {
            if (cache[rolls[i]] < ans) {
                cache[rolls[i]]++;
                size++;
                if (size == k) {
                    ans++;
                    size = 0;
                }
            }
        }
        return ans;
    }
}
