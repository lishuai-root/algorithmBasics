package leetcode;

/**
 * @description: There is a row of m houses in a small city, each house must be painted with one of the n colors (labeled from 1 to n),
 * some houses that have been painted last summer should not be painted again.
 * <p>
 * A neighborhood is a maximal group of continuous houses that are painted with the same color.
 * <p>
 * For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1}, {2,2}, {3,3}, {2}, {1,1}].
 * Given an array houses, an m x n matrix cost and an integer target where:
 * <p>
 * houses[i]: is the color of the house i, and 0 if the house is not painted yet.
 * cost[i][j]: is the cost of paint the house i with the color j + 1.
 * Return the minimum cost of painting all the remaining houses in such a way that there are exactly target neighborhoods.
 * If it is not possible, return -1.
 * @author: LISHUAI
 * @createDate: 2022/5/20 22:05
 * @version: 1.0
 */

public class LeetCode_1473 {

    public static void main(String[] args) {
//        int[] houses = {3, 1, 2, 3};
//        int[][] cost = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
//        int m = 4, n = 3, target = 3;
        int[] houses = {0, 2, 1, 2, 0};
        int[][] cost = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
        int m = 5, n = 2, target = 3;
        int i = minCost(houses, cost, m, n, target);
        System.out.println(i);
        System.out.println(minCost_03(houses, cost, m, n, target));
    }

    public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][] costSum = new int[m][n];
        System.arraycopy(cost[0], 0, costSum[0], 0, n);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                costSum[i][j] = cost[i][j] + costSum[i - 1][j];
            }
        }
        int i = minCostProcess(houses, costSum, 0, 0, target, houses[0]);
        return i == Integer.MAX_VALUE ? -1 : i;
    }


    private static int minCostProcess(int[] houses, int[][] costSum, int left, int right, int target, int cur) {
        if (right >= houses.length) {
            if (target == 1) {
                return getSum(costSum, left, right - 1);
            } else {
                return Integer.MAX_VALUE;
            }
        }

        if (target <= 0) {
            return Integer.MAX_VALUE;
        }
        int p1 = Integer.MAX_VALUE;
        if (houses[right] == 0 || houses[right] == cur || cur == 0) {
            if (cur == 0) {
                cur = houses[right];
            }
            p1 = minCostProcess(houses, costSum, left, right + 1, target, cur);
        }
        int p2 = minCostProcess(houses, costSum, right, right, target - 1, houses[right]);
        if (p2 != Integer.MAX_VALUE && right - 1 >= left) {
            p2 += getSum(costSum, left, right - 1);
        }
        return Math.min(p1, p2);
    }

    private static int getSum(int[][] costSum, int left, int right) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < costSum[right].length; i++) {
            if (left == 0) {
                min = Math.min(min, costSum[right][i]);
            } else {
                min = Math.min(min, costSum[right][i] - costSum[left - 1][i]);
            }
        }
        return min;
    }

    public static int minCost_03(int[] houses, int[][] cost, int m, int n, int target) {
        var price = minCost(houses, cost, m, n, target, 0, 0, 0, new Integer[m][n + 1][target + 1]);
        return (price == Integer.MAX_VALUE) ? -1 : price;
    }

    public static int minCost(int[] houses, int[][] cost, int m, int n, int target, int i, int prev, int hoods, Integer[][][] memo) {
        if (hoods > target)
            return Integer.MAX_VALUE;
        if (i == m)
            return (hoods == target) ? 0 : Integer.MAX_VALUE;
        if (memo[i][prev][hoods] != null)
            return memo[i][prev][hoods];

        var minPrice = Integer.MAX_VALUE;

        if (houses[i] == 0)
            for (var j = 0; j < n; j++) {
                var newHoods = (prev == j + 1) ? hoods : hoods + 1;
                var price = minCost(houses, cost, m, n, target, i + 1, j + 1, newHoods, memo);
                if (price != Integer.MAX_VALUE)
                    minPrice = Math.min(minPrice, cost[i][j] + price);
            }
        else {
            var newHoods = (houses[i] == prev) ? hoods : hoods + 1;
            minPrice = Math.min(minPrice, minCost(houses, cost, m, n, target, i + 1, houses[i], newHoods, memo));
        }
        return memo[i][prev][hoods] = minPrice;
    }

    public int minCost_02(int[] houses, int[][] cost, int m, int n, int target) {
        var price = minCost(houses, cost, m, n, target, 0, 0, 0);
        return price == Integer.MAX_VALUE ? -1 : price;
    }

    public int minCost(int[] houses, int[][] cost, int m, int n, int target, int i, int prev, int hoods) {
        if (hoods > target)
            return Integer.MAX_VALUE;
        if (i == m)
            return hoods == target ? 0 : Integer.MAX_VALUE;

        var minPrice = Integer.MAX_VALUE;

        if (houses[i] == 0)
            for (var j = 0; j < n; j++) {
                var newHoods = (prev == j + 1) ? hoods : hoods + 1;
                var price = minCost(houses, cost, m, n, target, i + 1, j + 1, newHoods);
                if (price != Integer.MAX_VALUE)
                    minPrice = Math.min(minPrice, cost[i][j] + price);
            }
        else {
            var newHoods = (houses[i] == prev) ? hoods : hoods + 1;
            minPrice = Math.min(minPrice, minCost(houses, cost, m, n, target, i + 1, houses[i], newHoods));
        }

        return minPrice;
    }
}
