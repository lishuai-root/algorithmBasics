package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @description: A car travels from a starting position to a destination which is target miles east of the starting position.
 * <p>
 * There are gas stations along the way. The gas stations are represented as an array stations where stations[i] = [positioni, fueli] indicates that the ith gas station is positioni miles east of the starting position and has fueli liters of gas.
 * <p>
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it. It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 * <p>
 * Return the minimum number of refueling stops the car must make in order to reach its destination. If it cannot reach the destination, return -1.
 * <p>
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there. If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 * @author: LISHUAI
 * @createDate: 2022/8/21 6:29
 * @version: 1.0
 */

public class LeetCode_871 {

    private static Integer[][] cache;


    public static void main(String[] args) {
//        int target = 1, startFuel = 1;
//        int[][] stations = {};
//        int target = 100, startFuel = 1;
//        int[][] stations = {{10, 100}};
//        int target = 100, startFuel = 10;
//        int[][] stations = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        int target = 1000, startFuel = 10;
        int[][] stations = {{7, 217}, {10, 211}, {17, 146}, {21, 232}, {25, 310}, {48, 175}, {53, 23}, {63, 158}, {71, 292}, {96, 85}, {100, 302}, {102, 295}, {116, 110}, {122, 46}, {131, 20}, {132, 19}, {141, 13}, {163, 85}, {169, 263}, {179, 233}, {191, 169}, {215, 163}, {224, 231}, {228, 282}, {256, 115}, {259, 3}, {266, 245}, {283, 331}, {299, 21}, {310, 224}, {315, 188}, {328, 147}, {345, 74}, {350, 49}, {379, 79}, {387, 276}, {391, 92}, {405, 174}, {428, 307}, {446, 205}, {448, 226}, {452, 275}, {475, 325}, {492, 310}, {496, 94}, {499, 313}, {500, 315}, {511, 137}, {515, 180}, {519, 6}, {533, 206}, {536, 262}, {553, 326}, {561, 103}, {564, 115}, {582, 161}, {593, 236}, {599, 216}, {611, 141}, {625, 137}, {626, 231}, {628, 66}, {646, 197}, {665, 103}, {668, 8}, {691, 329}, {699, 246}, {703, 94}, {724, 277}, {729, 75}, {735, 23}, {740, 228}, {761, 73}, {770, 120}, {773, 82}, {774, 297}, {780, 184}, {791, 239}, {801, 85}, {805, 156}, {837, 157}, {844, 259}, {849, 2}, {860, 115}, {874, 311}, {877, 172}, {881, 109}, {888, 321}, {894, 302}, {899, 321}, {908, 76}, {916, 241}, {924, 301}, {933, 56}, {960, 29}, {979, 319}, {983, 325}, {988, 190}, {995, 299}, {996, 97}};
        int i = minRefuelStops(target, startFuel, stations);
        System.out.println(i);
        System.out.println(minRefuelStops_dp_02(target, startFuel, stations));
        int[] ints = new int[100000000];
    }

    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
//        int i = minRefuelStopsProcess(0, target, startFuel, 0, stations);
        cache = new Integer[stations.length][target];
        int i = minRefuelStopsProcess(target, startFuel, 0, stations);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private static int minRefuelStopsProcess(int cur, int target, int startFuel, int index, int[][] stations) {
        if (cur + startFuel >= target) {
            return 0;
        }
        if (index >= stations.length) {
            return Integer.MAX_VALUE;
        }
        if (cur + startFuel < stations[index][0]) {
            return Integer.MAX_VALUE;
        }

        int p1 = minRefuelStopsProcess(stations[index][0], target, startFuel + stations[index][1] - stations[index][0] + cur, index + 1, stations);
        if (p1 != Integer.MAX_VALUE) {
            p1 += 1;
        }
        int p2 = minRefuelStopsProcess(stations[index][0], target, startFuel - stations[index][0] + cur, index + 1, stations);
        return Math.min(p1, p2);
    }


    private static int minRefuelStopsProcess(int target, int startFuel, int index, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        }
        if (index >= stations.length) {
            return Integer.MAX_VALUE;
        }
        if (startFuel < stations[index][0]) {
            return Integer.MAX_VALUE;
        }

        if (cache[index][startFuel] != null) {
            return cache[index][startFuel];
        }
        int p1 = minRefuelStopsProcess(target, startFuel + stations[index][1], index + 1, stations);
        if (p1 != Integer.MAX_VALUE) {
            p1++;
        }
        int p2 = minRefuelStopsProcess(target, startFuel, index + 1, stations);
        cache[index][startFuel] = Math.min(p1, p2);
        return cache[index][startFuel];
    }


    public static int minRefuelStops_dp_02(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        }
        int len = stations.length;
        int[][] arr = new int[len + 1][2];
        arr[0][1] = startFuel;
        System.arraycopy(stations, 0, arr, 1, len);
        stations = arr;
        len = stations.length;
        int[] dp = new int[len + 1];
        for (int i = 0; i < len; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < len; j++) {
                if (stations[j][0] < stations[i][0]) {
                    dp[j] = Integer.MAX_VALUE;
                } else {
                    int p1 = 0;
                    if (stations[j][0] + stations[i][1] < target) {
                        int max = getMax(stations, stations[j][0] + stations[i][1], 0, len - 1);
                        if (max != -1) {
                            p1 = dp[max];
                        }

                    }
                    if (p1 != Integer.MAX_VALUE) {
                        p1++;
                    }
                    dp[j] = Math.min(p1, dp[j]);
                }
            }
        }

//        int index = 0;
//        while (index < len && stations[index][0] <= startFuel) {
//            index++;
//        }
//        if (index == 0) {
//            return -1;
//        }
        return dp[0] == Integer.MAX_VALUE ? -1 : dp[0] - 1;
    }


    private static int getMax(int[][] stations, int target, int left, int right) {
        int l = left, r = right, mid = -1;

        while (l <= r) {
            int c = (r + l) >>> 1;
            if (stations[c][0] > target) {
                r = c - 1;
            } else if (stations[c][0] < target) {
                mid = c;
                l = c + 1;
            } else {
                mid = c;
                break;
            }
        }
        return mid;
    }

    public int minRefuelStops_other(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int prevLocation = 0;
        int curFuel = startFuel;
        int refuel = 0;

        for (int[] station : stations) {
            int location = station[0];
            int fuel = station[1];

            curFuel = curFuel - (location - prevLocation);

            // refuel from previously visited station until current fuel level remains below zero
            while (curFuel < 0 && !pq.isEmpty()) {
                curFuel += pq.poll();
                refuel++;
            }

            // if we cannot get enough fuel then return -1
            if (curFuel < 0) return -1;

            pq.add(fuel);
            prevLocation = location;
        }

        curFuel = curFuel - (target - prevLocation);

        // if there is not enough fuel to reach target location from last visited station then
        // take fuel from previously visited stations
        while (curFuel < 0 && !pq.isEmpty()) {
            curFuel += pq.poll();
            refuel++;
        }

        // if we cannot get enough fuel then return -1
        if (curFuel < 0) return -1;
        return refuel;
    }
}
