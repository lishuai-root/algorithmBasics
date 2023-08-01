package leetcode;

/**
 * @description: You are given a floating-point number hour, representing the amount of time you have to reach the office. To commute to the office, you must take n trains in sequential order. You are also given an integer array dist of length n, where dist[i] describes the distance (in kilometers) of the ith train ride.
 * <p>
 * Each train can only depart at an integer hour, so you may need to wait in between each train ride.
 * <p>
 * For example, if the 1st train ride takes 1.5 hours, you must wait for an additional 0.5 hours before you can depart on the 2nd train ride at the 2 hour mark.
 * Return the minimum positive integer speed (in kilometers per hour) that all the trains must travel at for you to reach the office on time, or -1 if it is impossible to be on time.
 * <p>
 * Tests are generated such that the answer will not exceed 107 and hour will have at most two digits after the decimal point.
 * @author: LiShuai
 * @createDate: 2023/7/26 22:24
 * @version: 1.0
 */

public class LeetCode_1870 {

    public static void main(String[] args) {
        int[] dist = {1, 1, 100000};
        double hour = 2.01;
        int i = minSpeedOnTime(dist, hour);
        System.out.println(i);
    }

    public static int minSpeedOnTime(int[] dist, double hour) {
        int l = 1;
        int r = (int) 1e7;
        int minspeed = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(dist, hour, mid)) {
                minspeed = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return minspeed;
    }

    public static boolean check(int[] dist, double hour, int speed) {
        int n = dist.length;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double time = (double) dist[i] / (double) speed;
            if (i == n - 1) sum += time;
            else sum = sum + Math.ceil(time);
            if (sum > hour) return false;
        }
        return sum <= hour;
    }


}
