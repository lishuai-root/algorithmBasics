package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.
 * <p>
 * There is a meeting for the representatives of each city. The meeting is in the capital city.
 * <p>
 * There is a car in each city. You are given an integer seats that indicates the number of seats in each car.
 * <p>
 * A representative can use the car in their city to travel or change the car and ride with another representative. The cost of traveling between two cities is one liter of fuel.
 * <p>
 * Return the minimum number of liters of fuel to reach the capital city.
 * @author: LISHUAI
 * @createDate: 2023/1/9 20:43
 * @version: 1.0
 */

public class LeetCode_2477 {

    public static long minimumFuelCost(int[][] roads, int seats) {
        if (roads.length == 0) {
            return 0;
        }
        Map<Integer, List<Integer>> graph = makeGraph(roads);
        boolean[] bls = new boolean[roads.length + 1];
        Info info = minimumFuelCostProcess(graph, 0, seats, bls);
        return info.fuelCost;
    }

    private static Info minimumFuelCostProcess(Map<Integer, List<Integer>> graph, int cur, int seats, boolean[] bls) {
        if (bls[cur] || !graph.containsKey(cur)) {
            return new Info();
        }

        bls[cur] = true;
        List<Integer> list = graph.get(cur);
        long fuelCost = 0;
        int people = 1;
        for (int next : list) {
            Info info = minimumFuelCostProcess(graph, next, seats, bls);
            fuelCost += ((info.people + seats - 1) / seats) + info.fuelCost;
            people += info.people;
        }
        return new Info(people, fuelCost);
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] roads) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        List<Integer> list;
        for (int[] ints : roads) {
            list = graph.computeIfAbsent(ints[0], o -> new ArrayList<>());
            list.add(ints[1]);
            list = graph.computeIfAbsent(ints[1], o -> new ArrayList<>());
            list.add(ints[0]);
        }
        return graph;
    }

    static class Info {
        int people;
        long fuelCost;

        public Info() {
        }

        public Info(int people, long fuelCost) {
            this.people = people;
            this.fuelCost = fuelCost;
        }
    }
}
