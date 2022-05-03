package leetcode;

/**
 * @description: You want to water n plants in your garden with a watering can.
 * The plants are arranged in a row and are labeled from 0 to n - 1 from left to right where the ith plant is located at x = i.
 * There is a river at x = -1 that you can refill your watering can at.
 * <p>
 * Each plant needs a specific amount of water. You will water the plants in the following way:
 * <p>
 * Water the plants in order from left to right.
 * After watering the current plant, if you do not have enough water to completely water the next plant,
 * return to the river to fully refill the watering can.
 * You cannot refill the watering can early.
 * You are initially at the river (i.e., x = -1). It takes one step to move one unit on the x-axis.
 * <p>
 * Given a 0-indexed integer array plants of n integers, where plants[i] is the amount of water the ith plant needs,
 * and an integer capacity representing the watering can capacity, return the number of steps needed to water all the plants.
 * <p>
 * plants = [2,2,3,3], capacity = 5
 * Start at the river with a full watering can:
 * - Walk to plant 0 (1 step) and water it. Watering can has 3 units of water.
 * - Walk to plant 1 (1 step) and water it. Watering can has 1 unit of water.
 * - Since you cannot completely water plant 2, walk back to the river to refill (2 steps).
 * - Walk to plant 2 (3 steps) and water it. Watering can has 2 units of water.
 * - Since you cannot completely water plant 3, walk back to the river to refill (3 steps).
 * - Walk to plant 3 (4 steps) and water it.
 * Steps needed = 1 + 1 + 2 + 3 + 3 + 4 = 14.
 * @author: LISHUAI
 * @createDate: 2022/4/7 23:06
 * @version: 1.0
 */

public class LeetCode_2079 {

    public static void main(String[] args) {
//        int[] plants = {7, 7, 7, 7, 7, 7, 7};
//        int capacity = 8;
        int[] plants = {1, 1, 1, 4, 2, 3};
        int capacity = 4;
        int i = wateringPlants(plants, capacity);
        System.out.println(i);


    }

    public static int wateringPlants(int[] plants, int capacity) {
        int ans = 0, sum;
        for (int i = 0; i < plants.length; ) {
            ans += i;
            sum = 0;
            while (i < plants.length && (sum += plants[i]) <= capacity) {
                ++i;
            }
            ans += i;
        }
        return ans;
    }
}
