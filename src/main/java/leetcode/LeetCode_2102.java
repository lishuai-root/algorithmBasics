package leetcode;


import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: A scenic location is represented by its name and attractiveness score,
 * where name is a unique string among all locations and score is an integer. Locations can be ranked from the best to the worst.
 * The higher the score, the better the location. If the scores of two locations are equal,
 * then the location with the lexicographically smaller name is better.
 * <p>
 * You are building a system that tracks the ranking of locations with the system initially starting with no locations.
 * It supports:
 * <p>
 * Adding scenic locations, one at a time.
 * Querying the ith best location of all locations already added, where i is the number of times the system has been queried (including the current query).
 * For example, when the system is queried for the 4th time, it returns the 4th best location of all locations already added.
 * <p>
 * Note that the test data are generated so that at any time, the number of queries does not exceed the number of locations added to the system.
 * <p>
 * Implement the SORTracker class:
 * <p>
 * SORTracker() Initializes the tracker system.
 * void add(string name, int score) Adds a scenic location with name and score to the system.
 * string get() Queries and returns the ith best location, where i is the number of times this method has been invoked (including this invocation).
 * @author: LISHUAI
 * @createDate: 2022/4/8 22:21
 * @version: 1.0
 */

public class LeetCode_2102 {

    public static void main(String[] args) {
        SORTracker sorTracker = new SORTracker();
        sorTracker.add("da", 2);
    }

    static class SORTracker {

        private Queue<Info> minQueue, maxQueue;
        private int size;

        public SORTracker() {
            minQueue = new PriorityQueue<>(Collections.reverseOrder());
            maxQueue = new PriorityQueue<>();
            size = 1;
        }

        public void add(String name, int score) {

            Info info = new Info(name, score);

            if (minQueue.size() < size) {
                if (maxQueue.isEmpty() || maxQueue.peek().compareTo(info) > 0) {
                    minQueue.add(info);
                } else {
                    minQueue.add(maxQueue.poll());
                    maxQueue.add(info);
                }
            } else {
                if (minQueue.peek().compareTo(info) > 0) {
                    maxQueue.add(minQueue.poll());
                    minQueue.add(info);
                } else {
                    maxQueue.add(info);
                }
            }
        }

        public String get() {
            ++size;
            String name = minQueue.peek().name;
            if (!maxQueue.isEmpty()) {
                minQueue.add(maxQueue.poll());
            }
            return name;
        }

        static class Info implements Comparable<Info> {
            String name;
            int score;

            public Info(String name, int score) {
                this.name = name;
                this.score = score;
            }

            @Override
            public int compareTo(Info o) {
                int ans = o.score - this.score;
                if (ans == 0) {
                    ans = this.name.compareTo(o.name);
                }
                return ans;
            }
        }
    }
}
