package leetcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @description: There is an undirected weighted connected graph.
 * You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n,
 * and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.
 * <p>
 * A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.
 * <p>
 * The distance of a path is the sum of the weights on the edges of the path.
 * Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x.
 * A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
 * <p>
 * Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.
 * <p>
 * <p>
 * Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
 * @author: LISHUAI
 * @createDate: 2022/3/10 23:40
 * @version: 1.0
 */

public class LeetCode_1786 {

    private static final int P = 1000000007;
    private static List<List<int[]>> graph;
    //    private static Map<Integer, List<int[]>> graph;
    private static int[] minPaths;
    private static int[] cache;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(P * 2);
//        1000000007
//        926335851
//        454920483
//        55077920
//        int[][] edges = {{1, 2, 3}, {1, 3, 3}, {2, 3, 1}, {1, 4, 2}, {5, 2, 2}, {3, 5, 1}, {5, 4, 10}};
//        int n = 5;
//        int[][] edges = {{1, 3, 1}, {4, 1, 2}, {7, 3, 4}, {2, 5, 3}, {5, 6, 1}, {6, 7, 2}, {7, 5, 3}, {2, 6, 4}};
//        int n = 7;
        int[][] edges = makeArr();
        int n = 20000;
        long start = System.currentTimeMillis();
        int i = countRestrictedPaths(n, edges);
        long end = System.currentTimeMillis();
        System.out.println(i);
        System.out.println("times : " + (end - start));

    }

    private static int[][] makeArr() throws FileNotFoundException {
        String filePath = "C:\\Users\\是李帅啊\\Desktop\\bigArr.txt";
        Scanner scanner = new Scanner(new File(filePath));

        List<int[]> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] split = line.split(",");
            int[] arr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                arr[i] = Integer.parseInt(split[i]);
            }
            list.add(arr);
        }

        return list.toArray(new int[0][0]);
    }

    public static int countRestrictedPaths(int n, int[][] edges) {
        int len = edges.length;
        graph = makeGraph(edges, n);
//        long start = System.currentTimeMillis();
        minPaths = minPath_02(n);
//        long end = System.currentTimeMillis();
//        System.out.println("times : " + (end - start));
//        System.out.println("------");
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
        return countRestrictedPathsProcess(1, n);
    }


    public static int countRestrictedPathsProcess(int start, int end) {

        if (start == end) {
            return 1;
        }

        if (cache[start] != -1) {
            return cache[start];
        }
        int ans = 0;

        List<int[]> list = graph.get(start);
        for (int[] ints : list) {

            if (minPaths[start] > minPaths[ints[0]]) {
                ans += countRestrictedPathsProcess(ints[0], end);
                ans = ans % P;

            }
        }
        cache[start] = ans;
        return ans;
    }

    private static List<List<int[]>> makeGraph(int[][] edges, int n) {
        List<List<int[]>> graph = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<int[]>());
        }
        List<int[]> list;
        for (int[] ints : edges) {

            list = graph.get(ints[0]);
            list.add(new int[]{ints[1], ints[2]});

            list = graph.get(ints[1]);
            list.add(new int[]{ints[0], ints[2]});

        }

        return graph;
    }

    private static Map<Integer, List<int[]>> makeGraph_bak(int[][] edges, int n) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        List<int[]> list;
        for (int[] ints : edges) {

            if ((list = graph.get(ints[0])) == null) {
                list = new ArrayList<>();
                graph.put(ints[0], list);
            }
            list.add(new int[]{ints[1], ints[2]});

            if ((list = graph.get(ints[1])) == null) {
                list = new ArrayList<>();
                graph.put(ints[1], list);
            }
            list.add(new int[]{ints[0], ints[2]});

        }

        return graph;
    }

    private static Map<Integer, Integer> minPath(Map<Integer, List<int[]>> graph, int n) {

        Map<Integer, Integer> path = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        set.add(n);
        path.put(n, 0);
        minPathProcess(graph, path, set);
        return path;
    }

    private static void minPathProcess(Map<Integer, List<int[]>> graph, Map<Integer, Integer> path, Set<Integer> set) {


        while (!set.isEmpty()) {
            Set<Integer> setTmp = new HashSet<>();
            for (Integer integer : set) {
                relax(graph, path, setTmp, integer);
            }
            set = setTmp;
        }
    }

    private static int[] minPath_02(int n) {
        int[] paths = new int[n + 1];
        Arrays.fill(paths, Integer.MAX_VALUE);
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                Comparator.comparingInt(a -> paths[a])
        );

        minPathProcess_02(n, queue, paths);
        return paths;
    }

    private static void minPathProcess_02(int n, PriorityQueue<Integer> queue, int[] paths) {

        paths[n] = 0;
        queue.offer(n);
        while (!queue.isEmpty()) {
            relaxQueue(queue.poll(), queue, paths);
        }
    }

    private static void relaxQueue(int c, PriorityQueue<Integer> queue, int[] paths) {

        List<int[]> list = graph.get(c);
        for (int[] ints : list) {
            if (paths[c] + ints[1] < paths[ints[0]]) {
                paths[ints[0]] = paths[c] + ints[1];
                queue.offer(ints[0]);
            }
        }
    }

    private static void relax(Map<Integer, List<int[]>> graph, Map<Integer, Integer> path, Set<Integer> set, int c) {

        List<int[]> list = graph.get(c);

        for (int[] ints : list) {
            if (path.get(c) + ints[1] < path.getOrDefault(ints[0], Integer.MAX_VALUE)) {
                set.add(ints[0]);
                path.put(ints[0], path.get(c) + ints[1]);
            }
        }
    }


}







