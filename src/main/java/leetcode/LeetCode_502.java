package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 * <p>
 * You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
 * <p>
 * Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 * <p>
 * Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
 * <p>
 * The answer is guaranteed to fit in a 32-bit signed integer.
 * @author: LISHUAI
 * @createDate: 2023/2/23 22:09
 * @version: 1.0
 */

public class LeetCode_502 {

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        Queue<Integer> minCapital = new PriorityQueue<>(capital.length, (a, b) -> capital[a] - capital[b]);
        Queue<Integer> maxProfits = new PriorityQueue<>(capital.length, (a, b) -> profits[b] - profits[a]);
        for (int i = 0; i < capital.length; i++) {
            minCapital.offer(i);
        }
        int ans = w;
        if (k == capital.length) {
            while (!minCapital.isEmpty()) {
                if (ans >= capital[minCapital.peek()]) {
                    ans += profits[minCapital.poll()];
                } else {
                    break;
                }
            }
            return ans;
        }

        while (k-- > 0) {
            while (!minCapital.isEmpty() && ans >= capital[minCapital.peek()]) {
                maxProfits.offer(minCapital.poll());
            }
            if (maxProfits.isEmpty()) {
                break;
            }
            int cur = maxProfits.poll();
            ans += profits[cur];
        }
        return ans;
    }


    public static int findMaximizedCapital_02(int k, int w, int[] profits, int[] capital) {

        Integer[] minCapital = new Integer[capital.length];
        for (int i = 0; i < capital.length; i++) {
            minCapital[i] = i;
        }
        Arrays.sort(minCapital, (a, b) -> capital[a] - capital[b]);
        int ans = w, index = 0;
        if (k == capital.length) {
            for (int i = 0; i < k; i++) {
                if (ans >= capital[minCapital[i]]) {
                    ans += profits[minCapital[i]];
                } else {
                    break;
                }
            }
            return ans;
        }
        Queue<Integer> maxProfits = new PriorityQueue<>(capital.length, (a, b) -> b - a);

        while (k-- > 0) {
            while (index < capital.length && ans >= capital[minCapital[index]]) {
                maxProfits.offer(profits[minCapital[index++]]);
            }
            if (maxProfits.isEmpty()) {
                break;
            }
            ans += maxProfits.poll();
        }
        return ans;
    }
}
