package leetcode;

import java.util.Arrays;

/**
 * @description: A company is planning to interview 2n people.
 * Given the array costs where costs[i] = [aCosti, bCosti], the cost of flying the ith person to city a is aCosti,
 * and the cost of flying the ith person to city b is bCosti.
 * <p>
 * Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
 * <p>
 * costs.length is even.
 * @author: LISHUAI
 * @createDate: 2022/3/25 18:47
 * @version: 1.0
 */

public class LeetCode_1029 {

    private static Integer[][][] dps;
    private static Integer[][] dpa;

    public static void main(String[] args) {
        int[][] costs = {{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}};
//        int[][] costs = {{10, 20}, {30, 200}, {400, 50}, {30, 20}};
//        int[][] costs = {{515, 563}, {451, 713}, {537, 709}, {343, 819}, {855, 779}, {457, 60}, {650, 359}, {631, 42}};
        int i = twoCitySchedCost_02(costs);
        System.out.println(i);
        int i1 = twoCitySchedCost(costs);
        System.out.println(i1);
    }

    public static int twoCitySchedCost(int[][] costs) {
        dps = new Integer[costs.length][(costs.length >>> 1) + 1][(costs.length >>> 1) + 1];
        dpa = new Integer[costs.length][(costs.length >>> 1) + 1];
        return twoCitySchedCostProcess(costs, 0, 0);
    }

    private static int twoCitySchedCostProcess(int[][] costs, int index, int aSum, int bSum, int aCost, int bCost) {

        if (index >= costs.length) {
            return aSum + bSum;
        }

        int ac = Integer.MAX_VALUE, bc = Integer.MAX_VALUE;

        if (aCost < costs.length >>> 1) {
            ac = twoCitySchedCostProcess(costs, index + 1, aSum + costs[index][0], bSum, aCost + 1, bCost);
        }
        if (bCost < costs.length >>> 1) {
            bc = twoCitySchedCostProcess(costs, index + 1, aSum, bSum + costs[index][1], aCost, bCost + 1);
        }
        return Math.min(ac, bc);
    }


    private static int twoCitySchedCostProcess(int[][] costs, int index, int aCost, int bCost) {

        if (index >= costs.length) {
            return 0;
        }

        if (dps[index][aCost][bCost] != null) {
            return dps[index][aCost][bCost];
        }
        int ac = Integer.MAX_VALUE, bc = Integer.MAX_VALUE;

        if (aCost < costs.length >>> 1) {
            ac = twoCitySchedCostProcess(costs, index + 1, aCost + 1, bCost) + costs[index][0];
        }
        if (bCost < costs.length >>> 1) {
            bc = twoCitySchedCostProcess(costs, index + 1, aCost, bCost + 1) + costs[index][1];
        }

        int ans = Math.min(ac, bc);
        dps[index][aCost][bCost] = ans;
        return ans;
    }

    private static int twoCitySchedCostProcess(int[][] costs, int index, int aCost) {

        if (index >= costs.length) {
            return 0;
        }

        if (dpa[index][aCost] != null) {
            return dpa[index][aCost];
        }
        int ac = Integer.MAX_VALUE, bc = Integer.MAX_VALUE;

        if (aCost < costs.length >>> 1) {
            ac = twoCitySchedCostProcess(costs, index + 1, aCost + 1) + costs[index][0];
        }
        if (index - aCost < costs.length >>> 1) {
            bc = twoCitySchedCostProcess(costs, index + 1, aCost) + costs[index][1];
        }

        int ans = Math.min(ac, bc);
        dpa[index][aCost] = ans;
        return ans;
    }

    public static int twoCitySchedCost_02(int[][] costs) {
        Arrays.sort(costs, (a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        int ans = 0, len = costs.length;
        for (int i = 0; i < len >>> 1; i++) {
            ans += costs[i][1] + costs[len - i - 1][0];
        }
        return ans;
    }
}
