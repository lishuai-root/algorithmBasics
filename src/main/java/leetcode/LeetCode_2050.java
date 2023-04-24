package leetcode;

import java.util.*;

/**
 * @description: You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given a 2D integer array relations where relations[j] = [prevCoursej, nextCoursej] denotes that course prevCoursej has to be completed before course nextCoursej (prerequisite relationship). Furthermore, you are given a 0-indexed integer array time where time[i] denotes how many months it takes to complete the (i+1)th course.
 * <p>
 * You must find the minimum number of months needed to complete all the courses following these rules:
 * <p>
 * You may start taking a course at any time if the prerequisites are met.
 * Any number of courses can be taken at the same time.
 * Return the minimum number of months needed to complete all the courses.
 * <p>
 * Note: The test cases are generated such that it is possible to complete every course (i.e., the graph is a directed acyclic graph).
 * @author: LISHUAI
 * @createDate: 2022/12/10 5:08
 * @version: 1.0
 */

public class LeetCode_2050 {

    private static int[] cache;

    public static void main(String[] args) {
//        int n = 3;
//        int[][] relations = {{1, 3}, {2, 3}};
//        int[] time = {3, 2, 5};
        int n = 5;
        int[][] relations = {{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5}};
        int[] time = {1, 2, 3, 4, 5};
        int i = minimumTime(n, relations, time);
        System.out.println(i);
    }

    public static int minimumTime(int n, int[][] relations, int[] time) {
        int[] weight = new int[n + 1];
        Map<Integer, List<Integer>> graph = reMakeGraph(relations, weight);
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (weight[i] == 0) {
                ans = Math.max(ans, minimumTimeProcess(graph, time, i));
            }
        }
        return ans;
    }

    private static int minimumTimeProcess(Map<Integer, List<Integer>> graph, int[] time, int cur) {
        if (!graph.containsKey(cur)) {
            return time[cur - 1];
        }

        if (cache[cur] != -1) {
            return cache[cur];
        }
        int ans = 0;
        List<Integer> list = graph.get(cur);
        for (int next : list) {
            ans = Math.max(ans, minimumTimeProcess(graph, time, next));
        }
        cache[cur] = ans + time[cur - 1];
        return cache[cur];
    }

    private static Map<Integer, List<Integer>> makeGraph(int[][] relations, int[] weight) {
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>(relations.length);
        for (int[] ints : relations) {
            weight[ints[1]]++;
            List<Integer> list = graph.computeIfAbsent(ints[0], o -> new ArrayList<>());
            list.add(ints[1]);
        }
        return graph;
    }

    private static Map<Integer, List<Integer>> reMakeGraph(int[][] relations, int[] weight) {
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>(relations.length);
        for (int[] ints : relations) {
            weight[ints[0]]++;
            List<Integer> list = graph.computeIfAbsent(ints[1], o -> new ArrayList<>());
            list.add(ints[0]);
        }
        return graph;
    }
}
