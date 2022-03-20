package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description: There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 * <p>
 * You are given the integer capacity and an array trips where trip[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively.
 * The locations are given as the number of kilometers due east from the car's initial location.
 * <p>
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 * @author: LISHUAI
 * @createDate: 2022/1/6 20:27
 * @version: 1.0
 */

public class LeetCode_1094 {

    public static boolean carPooling(int[][] trips, int capacity) {

        Arrays.sort(trips, (a, b) -> a[1] - b[1]);

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        int[] arr;

        int sum = 0;

        for (int i = 0; i < trips.length; i++) {

            while (!queue.isEmpty() && queue.peek()[2] <= trips[i][1]) {

//                arr = queue.poll();

                sum -= queue.poll()[0];
            }

            arr = trips[i];

            sum += arr[0];

            if (sum > capacity) {

                return false;
            }

            queue.add(arr);
        }

        return true;
    }
}
