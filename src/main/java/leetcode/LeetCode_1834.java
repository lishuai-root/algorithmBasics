package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.
 * <p>
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 * <p>
 * If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * Once a task is started, the CPU will process the entire task without stopping.
 * The CPU can finish a task then start a new one instantly.
 * Return the order in which the CPU will process the tasks.
 * @author: LISHUAI
 * @createDate: 2022/12/29 15:11
 * @version: 1.0
 */

public class LeetCode_1834 {

    public static void main(String[] args) {
        int[][] tasks = {{19, 13}, {16, 9}, {21, 10}, {32, 25}, {37, 4}, {49, 24}, {2, 15}, {38, 41}, {37, 34}, {33, 6}, {45, 4}, {18, 18}, {46, 39}, {12, 24}};
        int[] order = getOrder(tasks);
        for (int i : order) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] getOrder(int[][] tasks) {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> {
            int c = tasks[a][1] - tasks[b][1];
            if (c == 0) {
                c = a - b;
            }
            return c;
        });
        Queue<Integer> queueInit = new PriorityQueue<>((a, b) -> {
            int c = tasks[a][0] - tasks[b][0];
            if (c == 0) {
                c = tasks[a][1] - tasks[b][1];
            }
            return c;
        });
        int len = tasks.length;
        for (int i = 0; i < len; i++) {
            queueInit.offer(i);
        }
        int index = 0, cur = 0, curIndex = 0;
        int[] ans = new int[len];

        while (!queueInit.isEmpty()) {
            if (tasks[queueInit.peek()][0] <= cur || queue.isEmpty()) {
                cur = Math.max(cur, tasks[queueInit.peek()][0]);
                queue.offer(queueInit.poll());
            } else {
                int c = queue.poll();
                ans[index++] = c;
                cur = cur + tasks[c][1];
            }
        }
        while (!queue.isEmpty()) {
            ans[index++] = queue.poll();
        }
        return ans;
    }

    public static int[] getOrder_02(int[][] tasks) {
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> {
            int c = tasks[a][1] - tasks[b][1];
            if (c == 0) {
                c = a - b;
            }
            return c;
        });
        int len = tasks.length;
        Integer[] temp = new Integer[len];
        for (int i = 0; i < len; i++) {
            temp[i] = i;
        }
        Arrays.sort(temp, (a, b) -> {
            int c = tasks[a][0] - tasks[b][0];
            if (c == 0) {
                c = tasks[a][1] - tasks[b][1];
            }
            return c;
        });
        int index = 0, cur = 0;
        int[] ans = new int[len];
        for (int i = 0; i < len; ) {
            if (tasks[temp[i]][0] <= cur || queue.isEmpty()) {
                cur = Math.max(cur, tasks[temp[i]][0]);
                queue.offer(temp[i++]);
            } else {
                int c = queue.poll();
                ans[index++] = c;
                cur = cur + tasks[c][1];
            }
        }
        while (!queue.isEmpty()) {
            ans[index++] = queue.poll();
        }
        return ans;
    }
}
