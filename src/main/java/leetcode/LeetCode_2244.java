package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task. In each round, you can complete either 2 or 3 tasks of the same difficulty level.
 * <p>
 * Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.
 * @author: LISHUAI
 * @createDate: 2023/1/4 17:56
 * @version: 1.0
 */

public class LeetCode_2244 {

    public static void main(String[] args) {
        int[] tasks = {66, 66, 63, 61, 63, 63, 64, 66, 66, 65, 66, 65, 61, 67, 68, 66, 62, 67, 61, 64, 66, 60, 69, 66, 65, 68, 63, 60, 67, 62, 68, 60, 66, 64, 60, 60, 60, 62, 66, 64, 63, 65, 60, 69, 63, 68, 68, 69, 68, 61};
        int i = minimumRounds(tasks);
        System.out.println(i);
    }

    public static int minimumRounds(int[] tasks) {
        List<Integer> list = hashTasks(tasks);
        int ans = 0;
        for (int i : list) {
            int x = i / 3;

            while (true) {
                int y = i - (x * 3);
                if ((y & 1) == 0) {
                    ans += x + (y >> 1);
                    break;
                }
                if (x == 0) {
                    return -1;
                }
                x--;
            }
        }
        return ans;
    }


    public static int minimumRounds_02(int[] tasks) {
        List<Integer> list = hashTasks(tasks);
        int ans = 0;
        for (int i : list) {
            if (i < 2) {
                return -1;
            }
            int x = i % 3;
            ans += (i / 3);
            if (x != 0) {
                ans += 1;
            }
        }
        return ans;
    }

    private static List<Integer> hashTasks(int[] tasks) {
        Arrays.sort(tasks);
        List<Integer> list = new ArrayList<>();
        int left = 0, right = 0;
        while (right < tasks.length) {
            if (tasks[left] != tasks[right]) {
                list.add(right - left);
                left = right;
            }
            right++;
        }
        list.add(right - left);
        return list;
    }
}
