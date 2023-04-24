package leetcode;

/**
 * @description: A conveyor belt has packages that must be shipped from one port to another within days days.
 * <p>
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 * @author: LISHUAI
 * @createDate: 2022/12/27 13:56
 * @version: 1.0
 */

public class LeetCode_1011 {

    public static void main(String[] args) {
        int[] weight = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        int i = shipWithinDays(weight, days);
        System.out.println(i);
    }

    public static int shipWithinDays(int[] weights, int D) {
        if (weights == null || weights.length == 0) {
            return 0;
        }
        int start = Integer.MIN_VALUE;
        int end = 0;
        for (int x : weights) {
            start = Math.max(start, x);
            end += x;
        }
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (check(weights, D, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private static boolean check(int[] weights, int D, int cap) {
        int sum = 0, days = 1;
        for (int x : weights) {
            if (sum + x > cap) {
                sum = x;
                days++;
                if (days > D) {
                    return false;
                }
            } else {
                sum += x;
            }
        }
        return days <= D;
    }

}
