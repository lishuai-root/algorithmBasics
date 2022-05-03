package leetcode;

import java.util.*;

/**
 * @description: You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 * <p>
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 * <p>
 * Return the minimum cost to make all points connected.
 * All points are connected if there is exactly one simple path between any two points.
 * @author: LISHUAI
 * @createDate: 2022/4/26 17:27
 * @version: 1.0
 */

public class LeetCode_1584 {

    public static void main(String[] args) {
//        int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
//        int[][] points = {{0, 0}, {1, 1}, {1, 0}, {-1, 1}};
        int[][] points = {{2, -3}, {-17, -8}, {13, 8}, {-17, -15}};
        int i = minCostConnectPoints_03(points);
        System.out.println(i);
    }

    public static int minCostConnectPoints(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        List<Integer> arr = new ArrayList<>(points.length);
        int ans = 0, cur = 0, last = 0;
        for (int i = 0; i < points.length; i++) {
            arr.add(i);
        }

        for (int i = 0; i < points.length; i++) {
            Set<Integer> set = new HashSet<>(arr);
            set.remove(i);
            map.put(i, set);
        }

        for (int i = 0; i < points.length - 1; i++) {
            int min = Integer.MAX_VALUE;
            Set<Integer> set = map.get(i);
            for (int c : set) {
                int m = Math.abs(points[i][0] - points[c][0]) + Math.abs(points[i][1] - points[c][1]);
                if (m < min) {
                    min = m;
                    cur = c;
                }
            }
            map.get(i).remove(cur);
            map.get(cur).remove(i);
            System.out.println(i + " : " + cur + " : " + min);
            if (cur == points.length - 1) {
                last = min;
            }
            ans += min;

        }

        Set<Integer> set = map.get(points.length - 1);
        int min = Integer.MAX_VALUE;
        for (int c : set) {
            int m = Math.abs(points[points.length - 1][0] - points[c][0]) + Math.abs(points[points.length - 1][1] - points[c][1]);
            min = Math.min(min, m);
        }
        if (last > min) {
            ans -= (last - min);
        }
        return ans;
    }


    public static int minCostConnectPoints_02(int[][] points) {
        int ans = 0;

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[2] - b[2];
        });
        boolean[] bs = new boolean[points.length];
        queue.add(new int[]{0, 0, 0});
        int size = points.length;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            if (!bs[arr[1]]) {
                --size;
                ans += arr[2];
                if (size == 0) {
                    break;
                }
                int m = arr[1];
                bs[m] = true;
                for (int i = 1; i < points.length; i++) {
                    if (!bs[i]) {
                        int c = Math.abs(points[m][0] - points[i][0]) + Math.abs(points[m][1] - points[i][1]);
                        queue.add(new int[]{0, i, c});
                    }
                }
            }
        }
        return ans;
    }

    public static int minCostConnectPoints_03(int[][] points) {
        int ans = 0;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        Queue<int[]> tmp = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        }), tp;

        for (int i = 1; i < points.length; i++) {
            int c = Math.abs(points[0][0] - points[i][0]) + Math.abs(points[0][1] - points[i][1]);
            queue.add(new int[]{i, c});
        }
        queue.add(new int[]{0, 0});
        
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            ans += arr[1];
            int c = arr[0];
            while (!queue.isEmpty()) {
                arr = queue.poll();
                int t = arr[0];
                int m = Math.abs(points[c][0] - points[t][0]) + Math.abs(points[c][1] - points[t][1]);
                arr[1] = Math.min(m, arr[1]);
                tmp.add(arr);
            }
            tp = queue;
            queue = tmp;
            tmp = tp;
        }

        return ans;
    }
}
