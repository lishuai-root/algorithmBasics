package leetcode;

import java.util.Arrays;

/**
 * @description: You are given an array tasks where tasks[i] = [actuali, minimumi]:
 * <p>
 * actuali is the actual amount of energy you spend to finish the ith task.
 * minimumi is the minimum amount of energy you require to begin the ith task.
 * For example, if the task is [10, 12] and your current energy is 11, you cannot start this task. However, if your current energy is 13, you can complete this task, and your energy will be 3 after finishing it.
 * <p>
 * You can finish the tasks in any order you like.
 * <p>
 * Return the minimum initial amount of energy you will need to finish all the tasks.
 * @author: LISHUAI
 * @createDate: 2022/7/26 22:55
 * @version: 1.0
 */

public class LeetCode_1665 {

    public static void main(String[] args) {
//        int[][] tasks = {{1, 7}, {2, 8}, {3, 9}, {4, 10}, {5, 11}, {6, 12}};
        int[][] tasks = {{1, 3}, {2, 4}, {10, 11}, {10, 12}, {8, 9}};
        int i = minimumEffort(tasks);
        System.out.println(i);
    }

    public static int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> {
            int c = b[1] - a[1];
            if (c == 0) {
                c = b[0] - a[0];
            }
            return c;
        });

        int ans = 0, cur = 0;
        for (int[] ints : tasks) {
            int max = Math.max(ints[0], ints[1]);
            if (cur < max) {
                ans += max - cur;
                cur = max;
            }
            cur -= ints[0];
        }
        return ans;
    }

}
