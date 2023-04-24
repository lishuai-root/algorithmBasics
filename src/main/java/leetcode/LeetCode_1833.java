package leetcode;

import java.util.Arrays;

/**
 * @description: It is a sweltering summer day, and a boy wants to buy some ice cream bars.
 * <p>
 * At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice cream bars as possible.
 * <p>
 * Return the maximum number of ice cream bars the boy can buy with coins coins.
 * <p>
 * Note: The boy can buy the ice cream bars in any order.
 * @author: LISHUAI
 * @createDate: 2023/1/6 18:50
 * @version: 1.0
 */

public class LeetCode_1833 {

    public static int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int ans = 0;
        for (int cost : costs) {
            if (coins >= cost) {
                ans++;
                coins -= cost;
            } else {
                break;
            }
        }
        return ans;
    }
}
