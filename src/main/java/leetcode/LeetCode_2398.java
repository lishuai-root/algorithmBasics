package leetcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @description: You have n robots. You are given two 0-indexed integer arrays, chargeTimes and runningCosts, both of length n. The ith robot costs chargeTimes[i] units to charge and costs runningCosts[i] units to run. You are also given an integer budget.
 * <p>
 * The total cost of running k chosen robots is equal to max(chargeTimes) + k * sum(runningCosts), where max(chargeTimes) is the largest charge cost among the k robots and sum(runningCosts) is the sum of running costs among the k robots.
 * <p>
 * Return the maximum number of consecutive robots you can run such that the total cost does not exceed budget.
 * @author: LiShuai
 * @createDate: 2023/8/16 19:22
 * @version: 1.0
 */

public class LeetCode_2398 {

    public static void main(String[] args) throws FileNotFoundException {
//        int[] chargeTimes = {3, 6, 1, 3, 4}, runningCosts = {2, 1, 3, 4, 5};
//        int budget = 25;
        int[] chargeTimes = {32, 83, 96, 70, 98, 80, 30, 42, 63, 67, 49, 10, 80, 13, 69, 91, 73, 10},
                runningCosts = {49, 67, 92, 26, 18, 22, 38, 34, 36, 26, 32, 84, 39, 42, 88, 51, 8, 2};
        int budget = 599;
//        int[] chargeTimes = new int[100000];
//        int[] runningCosts = new int[100000];
//        Arrays.fill(chargeTimes, 1);
//        Arrays.fill(runningCosts, 1);
//        chargeTimes[0] = 100000;
//        int[] chargeTimes = makeArr(new File("C:\\Users\\是李帅啊\\Desktop\\arr1.txt"));
//        int[] runningCosts = makeArr(new File("C:\\Users\\是李帅啊\\Desktop\\arr2.txt"));
//        long budget = 2500100000L;

//        System.out.println(maximumRobots_02(chargeTimes, runningCosts, budget));
        System.out.println(maximumRobots_window(chargeTimes, runningCosts, budget));
        System.out.println(maximumRobots_window_stack(chargeTimes, runningCosts, budget));
        int i = maximumRobots(chargeTimes, runningCosts, budget);
        System.out.println(i);
    }

    private static int[] makeArr(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String next = scanner.next();
        String[] split = next.split(",");
        int len = split.length;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        return arr;
    }

    public static int maximumRobots_02(int[] chargeTimes, int[] runningCosts, long budget) {
        int len = chargeTimes.length, ans = 0;
        for (int i = 0; i < len; i++) {
            long sum = 0;
            int max = 0, k = 0;
            for (int j = i; j < len; j++) {
                max = Math.max(max, chargeTimes[j]);
                sum += runningCosts[j];
                long l = max + ((k + 1) * sum);
                if (l > budget) {
                    break;
                }
                ++k;
            }
            ans = Math.max(ans, k);
        }
        return ans;
    }

    public static int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int len = chargeTimes.length, preMax = 0, preLen = 0, ans = 0;
        long sum = 0;

        for (int i = len - 1; i >= 0; --i) {
            preMax = Math.max(chargeTimes[i], preMax);
            sum += runningCosts[i];
            ++preLen;
            long l = preLen * sum;
            if (l > budget - preMax) {
                int k = i + preLen - 1;
                preMax = 0;
                sum = 0;
                preLen = 0;
                for (int j = i; j < k; j++) {
                    int m = Math.max(chargeTimes[j], preMax);
                    long s = sum + runningCosts[j];
                    l = (preLen + 1) * s;
                    if (l > budget - preMax) {
                        break;
                    }
                    ++preLen;
                    preMax = m;
                    sum = s;
                }
            }
            ans = Math.max(ans, preLen);
        }
        return ans;
    }


    public static int maximumRobots_window(int[] chargeTimes, int[] runningCosts, long budget) {
        int len = chargeTimes.length, left = 0, right = 0, ans = 0;
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> chargeTimes[b] - chargeTimes[a]);
        long sum = 0;

        while (right < len) {
            queue.offer(right);
            sum += runningCosts[right++];

            while (!queue.isEmpty() && right - left > ans) {
                int max = queue.peek();
                if (max < left) {
                    queue.poll();
                    continue;
                }

                int k = right - left;
                if (k * sum + chargeTimes[max] <= budget) {
                    ans = Math.max(ans, k);
                    break;
                }
                sum -= runningCosts[left++];
            }
        }
        return ans;
    }

    public static int maximumRobots_window_stack(int[] chargeTimes, int[] runningCosts, long budget) {
        int len = chargeTimes.length, ans = 0, index = -1, tailIndex = 0;
        int[] stack = new int[len];
        long sum = 0;
        int left = 0, right = 0;

        while (right < len) {
            while (index >= tailIndex && chargeTimes[stack[index]] <= chargeTimes[right]) {
                --index;
            }
            stack[++index] = right;
            sum += runningCosts[right++];
            while (left < right) {
                if (stack[tailIndex] < left) {
                    ++tailIndex;
                }
                int k = right - left;
                if (chargeTimes[stack[tailIndex]] + k * sum <= budget) {
                    ans = Math.max(ans, k);
                    break;
                }
                sum -= runningCosts[left++];
            }
        }
        return ans;
    }
}
