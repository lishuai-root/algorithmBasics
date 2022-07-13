package leetcode;

/**
 * @description: There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
 * @author: LISHUAI
 * @createDate: 2022/6/12 14:31
 * @version: 1.0
 */

public class LeetCode_134 {

    public static void main(String[] args) {
//        int[] gas = {1, 2, 3, 4, 5}, cost = {3, 4, 5, 1, 2};
        int[] gas = {5, 5, 1, 3, 4}, cost = {8, 1, 7, 1, 1};
//        int[] gas = {2, 3, 4}, cost = {3, 4, 3};
//        int[] gas = {5, 1, 2, 3, 4}, cost = {4, 4, 1, 5, 1};
        int i = canCompleteCircuit(gas, cost);
        System.out.println(i);
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int hasCode = 0, needCode = 0, moreCode;
        int[] needs = new int[len];

        moreCode = gas[len - 1] - cost[len - 1];
        needs[len - 1] = Math.max(0, cost[len - 1] - gas[len - 1]);
        for (int i = len - 2; i >= 0; i--) {
            moreCode += (gas[i] - cost[i]);
            needs[i] = Math.max(cost[i] - gas[i] + needs[i + 1], 0);
        }


        for (int i = 0; i < len; i++) {
            if (gas[i] >= cost[i] && moreCode >= needCode && needs[i] <= 0) {
                return i;
            }
            moreCode -= (gas[i] - cost[i]);
            hasCode += gas[i] - cost[i];
            if (hasCode < 0) {
                needCode -= hasCode;
                hasCode = 0;
            }
        }
        return -1;
    }

    /**
     * other people method
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit_other(int[] gas, int[] cost) {
        int sum = 0, surplus = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            surplus += gas[i] - cost[i];
            if (surplus < 0) {
                surplus = 0;
                start = i + 1;
            }
        }
        return sum >= 0 ? start : -1;
    }
}


