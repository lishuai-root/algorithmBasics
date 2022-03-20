package leetcode;

/**
 * @description: You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.
 * <p>
 * Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip.
 * Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.
 * <p>
 * You are also given an integer totalTrips, which denotes the number of trips all buses should make in total.
 * Return the minimum time required for all buses to complete at least totalTrips trips.
 * @author: LISHUAI
 * @createDate: 2022/2/28 19:14
 * @version: 1.0
 */

public class LeetCode_2187 {

    public static void main(String[] args) {
        int[] time = {1, 2, 3};
        int totalTrips = 5;
        long l = minimumTime(time, totalTrips);
        System.out.println(l);
    }

    public static long minimumTime(int[] time, int totalTrips) {
        long ans = 0, roald = 0;

        while (ans < totalTrips) {
            roald++;
            ans = 0;
            for (int i = 0; i < time.length; i++) {
                ans += roald / time[i];
            }
        }

        return roald;
    }
}
