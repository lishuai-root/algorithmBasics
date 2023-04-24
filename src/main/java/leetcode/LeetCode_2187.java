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
        long low = Long.MAX_VALUE;
        long high = 0;
        long min = Long.MAX_VALUE;
        for (int it : time) {
            low = Math.min(low, it);
            min = Math.min(min, it);
        }
        high = totalTrips * min;
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (blackbox(mid, totalTrips, time)) {
                high = mid;
            } else
                low = mid + 1;
        }
        return low;
    }

    public static boolean blackbox(long isvalidtime, int totalTrips, int[] time) {
        long trips = 0;
        for (int it : time) {
            trips += isvalidtime / it;
        }
        if (trips >= totalTrips)
            return true;
        return false;
    }
}
