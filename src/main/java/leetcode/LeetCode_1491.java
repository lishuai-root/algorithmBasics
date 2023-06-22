package leetcode;

/**
 * @description: You are given an array of unique integers salary where salary[i] is the salary of the ith employee.
 * <p>
 * Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the actual answer will be accepted.
 * @author: LISHUAI
 * @createDate: 2023/5/1 20:56
 * @version: 1.0
 */

public class LeetCode_1491 {

    public static double average(int[] salary) {
        double sum = 0;
        int max = 0, min = Integer.MAX_VALUE;
        for (int i : salary) {
            sum += i;
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        return (sum - max - min) / (salary.length - 2);
    }
}
