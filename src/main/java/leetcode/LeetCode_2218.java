package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.
 * <p>
 * In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.
 * <p>
 * Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom, and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.
 * @author: LISHUAI
 * @createDate: 2023/4/15 15:45
 * @version: 1.0
 */

public class LeetCode_2218 {

    private static Integer[][] cache;

    public static void main(String[] args) {
        List<List<Integer>> piles = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(100);
        list.add(3);
        piles.add(list);
        list = new ArrayList<>();
        list.add(7);
        list.add(8);
        list.add(9);
        piles.add(list);
        int k = 2;
        int i = maxValueOfCoins_02(piles, k);
        System.out.println(i);
        System.out.println(maxValueOfCoins_dp(piles, k));
    }

    public static int maxValueOfCoins(List<List<Integer>> piles, int k) {
        return maxValueOfCoinsProcess(piles, k);
    }

    private static int maxValueOfCoinsProcess(List<List<Integer>> piles, int k) {
        if (k == 0) {
            return 0;
        }
        int ans = 0;
        for (List<Integer> pile : piles) {
            if (!pile.isEmpty()) {
                int remove = pile.remove(0);
                ans = Math.max(maxValueOfCoinsProcess(piles, k - 1) + remove, ans);
                pile.add(0, remove);
            }
        }
        return ans;
    }


    public static int maxValueOfCoins_02(List<List<Integer>> piles, int k) {
        cache = new Integer[piles.size()][k + 1];
        return maxValueOfCoinsProcess(piles, 0, k);
    }

    private static int maxValueOfCoinsProcess(List<List<Integer>> piles, int index, int k) {
        if (k == 0 || index >= piles.size()) {
            return 0;
        }

        if (cache[index][k] != null) {
            return cache[index][k];
        }
        int ans = maxValueOfCoinsProcess(piles, index + 1, k);
        List<Integer> list = piles.get(index);
        int size = Math.min(list.size(), k);
        if (size > 0) {
            int cur = 0;
            for (int i = 0; i < size; i++) {
                cur += list.get(i);
                int p = maxValueOfCoinsProcess(piles, index + 1, k - i - 1) + cur;
                ans = Math.max(ans, p);
            }
        }
        cache[index][k] = ans;
        return ans;
    }

    public static int maxValueOfCoins_dp(List<List<Integer>> piles, int k) {
        int len = piles.size();
        int[][] dp = new int[len + 1][k + 1];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= k; j++) {
                int cur = 0;
                dp[i][j] = dp[i + 1][j];
                List<Integer> list = piles.get(i);
                int size = Math.min(list.size(), j);
                for (int l = 0; l < size; l++) {
                    cur += list.get(l);
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - l - 1] + cur);
                }
            }
        }
        return dp[0][k];
    }
}
