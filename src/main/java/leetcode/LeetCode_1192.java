package leetcode;

import java.util.*;

/**
 * @description: There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi.
 * Any server can reach other servers directly or indirectly through the network.
 * <p>
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 * <p>
 * Return all critical connections in the network in any order.
 * @author: LISHUAI
 * @createDate: 2022/5/18 17:54
 * @version: 1.0
 */

public class LeetCode_1192 {

    public static void main(String[] args) {
//        int[][] connections = {{0, 1}};
//        int n = 4;
        int[][] connections = {{0, 1}, {1, 2}, {2, 0}, {1, 3}, {3, 4}, {4, 5}, {5, 3}};
        int n = 6;

        List<List<Integer>> c = getList(connections);
        List<List<Integer>> lists = criticalConnections_02(n, c);
        for (List<Integer> list : lists) {
            System.out.println(list.get(0) + " - " + list.get(1));
        }
    }

    private static List<List<Integer>> getList(int[][] connections) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] ints : connections) {
            ArrayList<Integer> l = new ArrayList<>();
            l.add(ints[0]);
            l.add(ints[1]);
            list.add(l);
        }
        return list;
    }

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = makeGraph(n, connections);
        boolean[] bls = new boolean[n];
        List<Set<Integer>> ways = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ways.add(new HashSet<>());
        }
        bls[0] = true;
        criticalConnectionsProcess(graph, ways, 0, bls);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < ways.size(); i++) {
            Set<Integer> set = ways.get(i);
            if (set.size() == 1) {
                List<Integer> l = new ArrayList<>(set);
                l.add(i);
                ans.add(l);
            }
        }
        return ans;
    }

    private static void criticalConnectionsProcess(List<List<Integer>> graph, List<Set<Integer>> ways, int cur, boolean[] bls) {
        List<Integer> list = graph.get(cur);
        if (list != null) {
            for (int c : list) {
                if (!bls[c]) {
                    bls[c] = true;
                    ways.get(c).add(cur);
                    criticalConnectionsProcess(graph, ways, c, bls);
                    bls[c] = false;
                }
            }
        }
    }

    private static List<List<Integer>> makeGraph(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> list : connections) {
            graph.get(list.get(0)).add(list.get(1));
            graph.get(list.get(1)).add(list.get(0));
        }
        return graph;
    }

    public static List<List<Integer>> criticalConnections_02(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = makeGraph(n, connections);
        Queue<int[]> queue = new LinkedList<>();
        List<Integer> list = graph.get(0);
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] left = new boolean[n], right = new boolean[n];
        for (int i : list) {
            queue.offer(new int[]{0, i});
        }
        left[0] = true;
        right[0] = true;

        while (!queue.isEmpty()) {

            int size = queue.size();
            int[] curs;
            if (size == 1) {
                curs = queue.peek();
                List<Integer> l = new ArrayList<>();
                l.add(curs[0]);
                l.add(curs[1]);
                ans.add(l);
            }

            for (int i = 0; i < size; i++) {
                curs = queue.poll();
                left[curs[1]] = true;
                right[curs[1]] = true;
                List<Integer> curList = graph.get(curs[1]);
                for (int c : curList) {
                    if (!right[c]) {
                        queue.offer(new int[]{curs[1], c});
                    }
                }
            }

        }

        return ans;
    }


    private static List<List<Integer>> makeDirectionGraph(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> list : connections) {
            graph.get(list.get(0)).add(list.get(1));
        }
        return graph;
    }
}
