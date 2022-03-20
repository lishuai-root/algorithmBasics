package leetcode;

/**
 * @description: Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * @author: LISHUAI
 * @createDate: 2021/11/29 22:47
 * @version: 1.0
 */

public class LeetCode_739 {

    public static void main(String[] args) {

        int[] arr = new int[]{55, 38, 53, 81, 61, 93, 97, 32, 43, 78};

        int[] ints = dailyTemperatures_02(arr);

        for (int i : ints) {

            System.out.print(i + "  ");
        }

        System.out.println();
    }

    public static int[] dailyTemperatures(int[] temperatures) {

        int[] stack = new int[temperatures.length];

        int[] result = new int[temperatures.length];

        int index = -1, cur;

        for (int i = 0; i < temperatures.length; i++) {

            while (index != -1 && temperatures[i] > temperatures[stack[index]]) {

                cur = stack[index--];

                result[cur] = i - cur;
            }

            stack[++index] = i;
        }

        return result;
    }

    public static int[] dailyTemperatures_02(int[] temperatures) {

        int[] stack = new int[temperatures.length];

        int index = -1, cur;

        for (int i = 0; i < temperatures.length; i++) {

            while (index != -1 && temperatures[i] > temperatures[stack[index]]) {

                cur = stack[index--];

                temperatures[cur] = i - cur;
            }

            stack[++index] = i;
        }

        for (int i = index; i >= 0; i--) {


            temperatures[stack[i]] = 0;
        }

        return temperatures;
    }
}
