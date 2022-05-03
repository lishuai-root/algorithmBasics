package leetcode;

import java.util.*;

/**
 * @description: You are given an array of variable pairs equations and an array of real numbers values,
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 * Each Ai or Bi is a string that represents a single variable.
 * <p>
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 * <p>
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * <p>
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 * @author: LISHUAI
 * @createDate: 2022/4/30 16:41
 * @version: 1.0
 */

public class LeetCode_399 {

    private static Set<String> set;
    private static Map<String, Map<String, Double>> cache;

    public static void main(String[] args) {
//        String[][] equations = {{"a", "b"}, {"b", "c"}};
//        double[] values = {2.0, 3.0};
//        String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        String[][] equations = {{"a", "b"}, {"b", "c"}, {"a", "c"}};
        double[] values = {2.0, 3.0, 6.0};
        String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
//        String[][] equations = {{"a", "b"}, {"b", "c"}, {"bc", "cd"}};
//        double[] values = {1.5, 2.5, 5.0};
//        String[][] queries = {{"a", "c"}, {"c", "b"}, {"bc", "cd"}, {"cd", "bc"}};
//        String[][] equations = {{"a", "b"}};
//        double[] values = {0.5};
//        String[][] queries = {{"a", "b"}, {"b", "a"}, {"a", "c"}, {"x", "y"}};
        List<List<String>> graph = new ArrayList<>();
        List<List<String>> qs = new ArrayList<>();
        for (String[] ss : equations) {
            List<String> list = new ArrayList<>();
            Collections.addAll(list, ss);
            graph.add(list);
        }

        for (String[] ss : queries) {
            List<String> list = new ArrayList<>();
            Collections.addAll(list, ss);
            qs.add(list);
        }
        double[] doubles = calcEquation(graph, values, qs);

        for (double d : doubles) {
            System.out.println(d);
        }
    }


    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        set = new HashSet<>();
        double[] ans = new double[queries.size()];

        Map<String, Map<String, Double>> graph = makeGraph(equations, values);
        for (int i = 0; i < queries.size(); i++) {
            List<String> list = queries.get(i);

            if (set.contains(list.get(0)) && set.contains(list.get(1))) {
                double dfs = dfs(graph, list.get(0), list.get(1), 1.0D);
                ans[i] = dfs > 0 ? dfs : -1;
//                ans[i] = (connected(graph, list.get(0), list.get(1)) >= 1)
//                        ? dfs(graph, list.get(0), list.get(1), 1) : -1.0;
            } else {
                ans[i] = -1.0;
            }

//            ans[i] = dfs(graph, list.get(0), list.get(1), 1.0D);
        }
        return ans;
    }

    private static int connected(Map<String, Map<String, Double>> graph, String cur, String end) {
        if (cur.equals(end)) {
            return 1;
        }
        int ans = 0;
        set.remove(cur);
        Map<String, Double> tmp = graph.get(cur);
        for (String s : tmp.keySet()) {
            if (set.contains(s)) {
                ans += connected(graph, s, end);
            }
            if (ans != 0) {
                break;
            }
        }
        set.add(cur);
        return ans;
    }

    private static double dfs(Map<String, Map<String, Double>> graph, String cur, String end, double d) {
        if (cur.equals(end)) {
            return d;
        }


        double ans = 0.0D;
        set.remove(cur);
        Map<String, Double> tmp = graph.get(cur);
        for (String s : tmp.keySet()) {
            if (set.contains(s)) {
                ans = dfs(graph, s, end, d * tmp.get(s));
            }
            if (ans != 0) {
                break;
            }
        }
        set.add(cur);
        return ans;
    }

    private static Map<String, Map<String, Double>> makeGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        Map<String, Double> cur;

        for (int i = 0; i < equations.size(); i++) {
            List<String> list = equations.get(i);
            cur = graph.computeIfAbsent(list.get(0), k -> new HashMap<String, Double>());
            cur.put(list.get(1), values[i]);
            set.add(list.get(0));
            cur = graph.computeIfAbsent(list.get(1), k -> new HashMap<String, Double>());
            cur.put(list.get(0), 1 / values[i]);
            set.add(list.get(1));
        }

        return graph;
    }
}
