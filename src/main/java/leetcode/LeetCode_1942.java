package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description: There is a party where n friends numbered from 0 to n - 1 are attending.
 * There is an infinite number of chairs in this party that are numbered from 0 to infinity.
 * When a friend arrives at the party, they sit on the unoccupied chair with the smallest number.
 * <p>
 * For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
 * When a friend leaves the party, their chair becomes unoccupied at the moment they leave.
 * If another friend arrives at that same moment, they can sit in that chair.
 * <p>
 * You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi], indicating the arrival and leaving times of the ith friend respectively,
 * and an integer targetFriend. All arrival times are distinct.
 * <p>
 * Return the chair number that the friend numbered targetFriend will sit on.
 * @author: LISHUAI
 * @createDate: 2022/3/14 13:29
 * @version: 1.0
 */

public class LeetCode_1942 {

    public static void main(String[] args) {
//        int[][] times = {{4, 5}, {12, 13}, {5, 6}, {1, 2}, {8, 9}, {9, 10}, {6, 7}, {3, 4}, {7, 8}, {13, 14}, {15, 16}, {14, 15}, {10, 11}, {11, 12}, {2, 3}, {16, 17}};
//        int targetFriend = 15;
        int[][] times = {{33889, 98676}, {80071, 89737}, {44118, 52565}, {52992, 84310}, {78492, 88209}, {21695, 67063}, {84622, 95452}, {98048, 98856}, {98411, 99433}, {55333, 56548}, {65375, 88566}, {55011, 62821}, {48548, 48656}, {87396, 94825}, {55273, 81868}, {75629, 91467}};
        int targetFriend = 6;
        int i = smallestChair(times, targetFriend);
        System.out.println(i);
    }

    public static int smallestChair(int[][] times, int targetFriend) {
        int[] target = times[targetFriend];
        Arrays.sort(times, (a, b) -> {
            int r = a[0] - b[0];
            if (r == 0) {
                r = a[1] - b[1];
            }
            return r;
        });


        int com = 0, index = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        PriorityQueue<int[]> queueInts = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

//        for (int i = 0; i < times.length; i++) {
//            queue.offer(i);
//        }

        while (com < times.length) {


            while (!queueInts.isEmpty() && queueInts.peek()[1] <= times[com][0]) {
                int[] poll = queueInts.poll();
                queue.offer(poll[0]);
            }


            index = queue.isEmpty() ? queueInts.size() : queue.poll();

            if (times[com][0] == target[0]) {
                break;
            }
            times[com][0] = index;
            queueInts.offer(times[com]);
            com++;
        }
        return index;
    }
}
