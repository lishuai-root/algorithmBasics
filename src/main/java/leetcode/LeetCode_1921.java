package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are playing a video game where you are defending your city from a group of n monsters.
 * You are given a 0-indexed integer array dist of size n, where dist[i] is the initial distance in kilometers of the ith monster from the city.
 * <p>
 * The monsters walk toward the city at a constant speed. The speed of each monster is given to you in an integer array speed of size n, where speed[i] is the speed of the ith monster in kilometers per minute.
 * <p>
 * You have a weapon that, once fully charged, can eliminate a single monster. However, the weapon takes one minute to charge.The weapon is fully charged at the very start.
 * <p>
 * You lose when any monster reaches your city. If a monster reaches the city at the exact moment the weapon is fully charged, it counts as a loss, and the game ends before you can use your weapon.
 * <p>
 * Return the maximum number of monsters that you can eliminate before you lose, or n if you can eliminate all the monsters before they reach the city.
 * @author: LISHUAI
 * @createDate: 2022/6/4 21:51
 * @version: 1.0
 */

public class LeetCode_1921 {

    public static void main(String[] args) {
//        int[] dist = {1, 3, 4}, speed = {1, 1, 1};
//        int[] dist = {1, 1, 2, 3}, speed = {1, 1, 1, 1};
        int[] dist = {3, 2, 4}, speed = {5, 3, 2};
        int i = eliminateMaximum(dist, speed);
        System.out.println(i);
    }

    public static int eliminateMaximum(int[] dist, int[] speed) {
        Queue<Double> queue = new PriorityQueue<>();
        for (int i = 0; i < dist.length; i++) {
            queue.offer((double) dist[i] / (double) speed[i]);
        }
        int ans = 0, pre = 0;

        while (!queue.isEmpty()) {
            double cur = queue.poll();
            if (cur <= pre) {
                break;
            }
            pre++;
            ans++;
        }
        return ans;
    }

    public static int eliminateMaximum_order(int[] dist, int[] speed) {

        int[] dp = new int[dist.length];
        for (int i = 0; i < dist.length; i++) {
            dp[i] = (dist[i] + speed[i] - 1) / speed[i];
        }
        Arrays.sort(dp);
        int ans = 0, pre = 0;

        for (int d : dp) {
            if (d <= pre) {
                break;
            }
            pre++;
            ans++;
        }
        return ans;
    }
}
