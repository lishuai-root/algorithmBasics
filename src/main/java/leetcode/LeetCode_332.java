package leetcode;

import java.util.*;

/**
 * @description: You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight.
 * Reconstruct the itinerary in order and return it.
 * <p>
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK".
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * <p>
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 * @author: LISHUAI
 * @createDate: 2022/5/10 22:12
 * @version: 1.0
 */

public class LeetCode_332 {

    private static int graphSize;
    private static List<String> ans;
    private static Map<String, Map<String, Integer>> ways;

    public static void main(String[] args) {
        String[][] strings = {{"EZE", "TIA"}, {"EZE", "AXA"}, {"AUA", "EZE"}, {"EZE", "JFK"}, {"JFK", "ANU"}, {"JFK", "ANU"}, {"AXA", "TIA"}, {"JFK", "AUA"}, {"TIA", "JFK"}, {"ANU", "EZE"}, {"ANU", "EZE"}, {"TIA", "AUA"}};
//        String[][] strings = {{"EZE", "AXA"}, {"TIA", "ANU"}, {"ANU", "JFK"}, {"JFK", "ANU"}, {"ANU", "EZE"}, {"TIA", "ANU"}, {"AXA", "TIA"}, {"TIA", "JFK"}, {"ANU", "TIA"}, {"JFK", "TIA"}};
//        String[][] strings = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
//        String[][] strings = {{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}};
        List<List<String>> tickets = new ArrayList<>();
        for (String[] ss : strings) {
            ArrayList<String> list = new ArrayList<>();
            list.add(ss[0]);
            list.add(ss[1]);
            tickets.add(list);
        }
        List<String> itinerary = findItinerary(tickets);
        for (String s : itinerary) {
            System.out.print(s + " ");
        }
        System.out.println();
//        List<String> list = new ArrayList<>();
//        list.add("c");
//        list.add("a");
//        list.add("b");
//        list.add("d");
//        list.sort(String::compareTo);
//        for (String s : list) {
//            System.out.println(s);
//        }
    }

    public static List<String> findItinerary(List<List<String>> tickets) {
        ans = new ArrayList<>();
        Map<String, List<String>> graph = makeGraph(tickets);
        graphSize = tickets.size();
        System.out.println(graphSize);
        Map<String, Set<String>> ways = new HashMap<>();
        ans.add("JFK");

        findItineraryProcess(graph, "JFK", 0);
        return ans;
    }

    private static boolean findItineraryProcess(Map<String, List<String>> graph,
                                                String cur, int size) {
        if (size == graphSize) {
            return true;
        }
        List<String> list = graph.get(cur);
        if (list == null || list.isEmpty()) {
            return size == graphSize;
        }

        Map<String, Integer> map = ways.get(cur);

        boolean b = false;
        for (String next : list) {
            if (map.get(next) > 0) {
                map.put(next, map.get(next) - 1);
                ans.add(next);
                b = findItineraryProcess(graph, next, size + 1);
                map.put(next, map.get(next) + 1);
                if (b) {
                    break;
                }

                ans.remove(ans.size() - 1);
            }
        }
        return b;
    }

    private static Map<String, List<String>> makeGraph(List<List<String>> tickets) {
        Map<String, List<String>> graph = new HashMap<>();
        ways = new HashMap<>();
        List<String> l;
        Map<String, Integer> map;
        for (List<String> list : tickets) {
            l = graph.computeIfAbsent(list.get(0), k -> new ArrayList<>());
            map = ways.computeIfAbsent(list.get(0), k -> new HashMap<>());
            add(l, list.get(1));
            map.put(list.get(1), map.getOrDefault(list.get(1), 0) + 1);
        }
//        for (String key : graph.keySet()) {
//            graph.get(key).sort(String::compareTo);
//        }
        return graph;
    }

    private static void add(List<String> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (compareString(str, list.get(i)) < 0) {
                list.add(i, str);
                return;
            }
        }
        list.add(str);
    }

    private static int compareString(String p, String q) {
        int size = Math.min(p.length(), q.length());
        for (int i = 0; i < size; i++) {
            if (p.charAt(i) > q.charAt(i)) {
                return 1;
            } else if (p.charAt(i) < q.charAt(i)) {
                return -1;
            }
        }
        return p.length() - q.length();
    }
}
