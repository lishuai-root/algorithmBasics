package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: An underground railway system is keeping track of customer travel times between different stations.
 * They are using this data to calculate the average time it takes to travel from one station to another.
 * <p>
 * Implement the UndergroundSystem class:
 * <p>
 * void checkIn(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks in at the station stationName at time t.
 * A customer can only be checked into one place at a time.
 * void checkOut(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks out from the station stationName at time t.
 * double getAverageTime(string startStation, string endStation)
 * Returns the average time it takes to travel from startStation to endStation.
 * The average time is computed from all the previous traveling times from startStation to endStation that happened directly,
 * meaning a check in at startStation followed by a check out from endStation.
 * The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
 * There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
 * You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.
 * <p>
 * All strings consist of uppercase and lowercase English letters and digits.
 * There will be at most 2 * 104 calls in total to checkIn, checkOut, and getAverageTime.
 * Answers within 10-5 of the actual value will be accepted.
 * @author: LISHUAI
 * @createDate: 2022/4/24 18:28
 * @version: 1.0
 */

public class LeetCode_1396 {

    class UndergroundSystem {

        private final Map<String, Map<String, Double[]>> graph;
        private final Map<Integer, Info> cur;

        public UndergroundSystem() {
            graph = new HashMap<>();
            cur = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            cur.put(id, new Info(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            Info info = cur.remove(id);
            String name = info.stationName;
            Map<String, Double[]> map = null;
            if ((map = graph.get(name)) == null) {
                map = new HashMap<>();
                graph.put(name, map);
            }
            Double[] longs = null;
            if ((longs = map.get(stationName)) == null) {
                longs = new Double[]{0D, 0D};
                map.put(stationName, longs);
            }
            longs[0] += t - info.t;
            ++longs[1];
        }

        public double getAverageTime(String startStation, String endStation) {
            Map<String, Double[]> map = graph.get(startStation);
            Double[] d = map.get(endStation);
            return d[0] / d[1];
        }
    }

    class Info {
        String stationName;
        int t;

        public Info(String stationName, int t) {
            this.stationName = stationName;
            this.t = t;
        }
    }
}
