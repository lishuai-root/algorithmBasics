package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @description: There are n cities connected by some number of flights.
 * You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 * <p>
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 * @author: LISHUAI
 * @createDate: 2022/2/18 21:01
 * @version: 1.0
 */

public class LeetCode_787 {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        int[][] flights = {{0, 3, 3}, {3, 4, 3}, {4, 1, 3}, {0, 5, 1}, {5, 1, 100}, {0, 6, 2}, {6, 1, 100}, {0, 7, 1}, {7, 8, 1}, {8, 9, 1}, {9, 1, 1}, {1, 10, 1}, {10, 2, 1}, {1, 2, 100}};
//        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
//        int n = 3, src = 0, dst = 2, k = 1;
        int n = 11, src = 0, dst = 2, k = 4;
        int i = findCheapestPrice_03(n, flights, src, dst, k);
        int i1 = findCheapestPrice_02(n, flights, src, dst, k);
        int i2 = findCheapestPrice(n, flights, src, dst, k);
//        System.out.println(i);

        System.out.println(tableSizeFor(200));
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        List<List<int[]>> graph = new ArrayList<>();
        List<int[]> l;
        int road = 0;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<int[]>());
        }
        for (int[] flight : flights) {

            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        int[] money = new int[n];

        Arrays.fill(money, Integer.MAX_VALUE);
        money[src] = 0;
//        bl[src] = true;
        ArrayList<Integer> queue = new ArrayList<>();
        int size, cur;
        queue.add(src);

        for (int i = 0; i <= k; i++) {
            size = queue.size();
            int[] curMoney = Arrays.copyOf(money, n);
            boolean[] bl = new boolean[n];
            for (int j = 0; j < size; j++) {
                cur = queue.remove(0);
                l = graph.get(cur);
                for (int[] c : l) {
                    money[c[0]] = Math.min(money[c[0]], curMoney[cur] + c[1]);
                    if (!bl[c[0]]) {
                        queue.add(c[0]);
                        bl[c[0]] = true;
                    }
                    road++;
                }
                bl[cur] = false;
            }
        }
        System.out.println(road);
        return money[dst] == Integer.MAX_VALUE ? -1 : money[dst];
    }


    public static int findCheapestPrice_02(int n, int[][] flights, int src, int dst, int k) {
        int[] money = new int[n];
        Arrays.fill(money, Integer.MAX_VALUE);
        money[src] = 0;
        int road = 0;
        for (int i = 0; i <= k; i++) {
            int[] curMoney = Arrays.copyOf(money, n);
            for (int[] cur : flights) {
                if (curMoney[cur[0]] != Integer.MAX_VALUE) {
                    money[cur[1]] = Math.min(money[cur[1]], curMoney[cur[0]] + cur[2]);
                }
                road++;
            }

        }
        System.out.println(road);
        return money[dst] == Integer.MAX_VALUE ? -1 : money[dst];
    }

    public static int findCheapestPrice_03(int n, int[][] flights, int src, int dst, int k) {
        int road = 0;
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<int[]>());
        }
        for (int[] line : flights) {
            graph.get(line[0]).add(new int[]{line[1], line[2]});
        }
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;
        HashMap<Integer, Integer> curMap = new HashMap<>(1);
        curMap.put(src, 0);
        for (int i = 0; i <= k; i++) {
            HashMap<Integer, Integer> nextMap = new HashMap<>();
            for (Entry<Integer, Integer> entry : curMap.entrySet()) {
                int from = entry.getKey();
                int preCost = entry.getValue();
                for (int[] line : graph.get(from)) {
                    int to = line[0];
                    int curCost = line[1];
                    distance[to] = Math.min(distance[to], preCost + curCost);
                    nextMap.put(to, Math.min(nextMap.getOrDefault(to, Integer.MAX_VALUE), preCost + curCost));
                    road++;
                }
            }
            curMap = nextMap;
        }
        System.out.println(road);
        return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
    }


    private static int numberOfLeadingZeros(int i) {
        // HD, Count leading 0's
        if (i <= 0)
            return i == 0 ? 32 : 0;
        int n = 31;
        if (i >= 1 << 16) {
            n -= 16;
            i >>>= 16;
        }
        if (i >= 1 << 8) {
            n -= 8;
            i >>>= 8;
        }
        if (i >= 1 << 4) {
            n -= 4;
            i >>>= 4;
        }
        if (i >= 1 << 2) {
            n -= 2;
            i >>>= 2;
        }
        return n - (i >>> 1);
    }

    private static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
