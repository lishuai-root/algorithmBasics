package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.
 * <p>
 * You are also given two integers k and candidates. We want to hire exactly k workers according to the following rules:
 * <p>
 * You will run k sessions and hire exactly one worker in each session.
 * In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the last candidates workers. Break the tie by the smallest index.
 * For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will choose the 4th worker because they have the lowest cost [3,2,7,7,1,2].
 * In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but they have the smallest index [3,2,7,7,2]. Please note that the indexing may be changed in the process.
 * If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.
 * A worker can only be chosen once.
 * Return the total cost to hire exactly k workers.
 * @author: LiShuai
 * @createDate: 2023/6/26 20:08
 * @version: 1.0
 */

public class LeetCode_2462 {

    public static void main(String[] args) {
        int[] costs = {31, 25, 72, 79, 74, 65, 84, 91, 18, 59, 27, 9, 81, 33, 17, 58};
        int k = 11;
        int candidates = 2;
        long l = totalCost(costs, k, candidates);
        System.out.println(l);
    }

    public static long totalCost(int[] costs, int k, int candidates) {
        int len = costs.length;
        long ans = 0;
        int[] temp = new int[len];
        Queue<Integer> left = new PriorityQueue<>((a, b) -> costs[a] - costs[b]);
        Queue<Integer> right = new PriorityQueue<>((a, b) -> costs[a] - costs[b]);
        for (int i = 0; i < Math.min(len, candidates); i++) {
            left.offer(i);
            right.offer(len - 1 - i);
        }
        int l = candidates, r = len - candidates - 1;

        for (int i = 0; i < k; i++) {
            while (left.size() < candidates && l < len) {
                if (temp[l] == 0) {
                    left.offer(l);
                }
                ++l;
            }
            while (!left.isEmpty() && temp[left.peek()] != 0) {
                left.poll();
                while (l < len) {
                    if (temp[l] == 0) {
                        right.offer(l++);
                        break;
                    }
                    ++l;
                }
            }
            while (right.size() < candidates && r >= 0) {
                if (temp[r] == 0) {
                    right.offer(r);
                }
                --r;
            }
            while (!right.isEmpty() && temp[right.peek()] != 0) {
                right.poll();
                while (r >= 0) {
                    if (temp[r] == 0) {
                        right.offer(r--);
                        break;
                    }
                    --r;
                }
            }
            int p = left.isEmpty() ? Integer.MAX_VALUE : left.peek();
            int q = right.isEmpty() ? Integer.MAX_VALUE : right.peek();
            if (costs[p] > costs[q]) {
                right.poll();
                temp[q] = 1;
                ans += costs[q];
            } else {
                left.poll();
                temp[p] = 1;
                ans += costs[p];
            }
        }
        return ans;
    }


    public static long totalCost_02(int[] costs, int k, int candidates) {
        int len = costs.length;
        long ans = 0;
        Queue<Integer> left = new PriorityQueue<>((a, b) -> costs[a] - costs[b]);
        Queue<Integer> right = new PriorityQueue<>((a, b) -> costs[a] - costs[b]);
        int l = 0, r = len - 1;
        for (int i = 0; i < Math.min(len, candidates); i++) {
            if (l <= r) {
                left.offer(l++);
            } else {
                break;
            }
            if (r >= l) {
                right.offer(r--);
            } else {
                break;
            }
        }

        for (int i = 0; i < k; i++) {
            if (left.isEmpty()) {
                ans += costs[right.poll()];
            } else if (right.isEmpty()) {
                ans += costs[left.poll()];
            } else {
                if (costs[left.peek()] > costs[right.peek()]) {
                    ans += costs[right.poll()];
                    if (r >= l) {
                        right.offer(r--);
                    }
                } else {
                    ans += costs[left.poll()];
                    if (l <= r) {
                        left.offer(l++);
                    }
                }
            }
        }
        return ans;
    }


    public static long totalCost_03(int[] costs, int k, int candidates) {
        int len = costs.length;
        long ans = 0;
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> {
            if (costs[a] != costs[b]) {
                return costs[a] - costs[b];
            }
            return a - b;
        });
        int l = 0, r = len - 1;
        while (l < candidates) {
            queue.offer(l++);
        }
        while (r >= Math.max(l, len - candidates)) {
            queue.offer(r--);
        }
        for (int i = 0; i < k; i++) {
            int p = queue.poll();
            ans += costs[p];
            if (p < l) {
                if (l <= r) {
                    queue.offer(l++);
                }
            } else {
                if (r >= l) {
                    queue.offer(r--);
                }
            }
        }
        return ans;
    }
}
