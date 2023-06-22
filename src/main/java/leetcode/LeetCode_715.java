package leetcode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @description: A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.
 * <p>
 * A half-open interval [left, right) denotes all the real numbers x where left <= x < right.
 * <p>
 * Implement the RangeModule class:
 * <p>
 * RangeModule() Initializes the object of the data structure.
 * void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
 * boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
 * void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).
 * @author: LISHUAI
 * @createDate: 2023/5/9 21:09
 * @version: 1.0
 */

public class LeetCode_715 {

    class RangeModule {
        TreeMap<Integer, Integer> map;

        public RangeModule() {
            map = new TreeMap<>();
        }

        public void addRange(int left, int right) {
            if (right <= left) return;
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (start == null && end == null) {
                map.put(left, right);
            } else if (start != null && map.get(start) >= left) {
                map.put(start, Math.max(map.get(end), Math.max(map.get(start), right)));
            } else {
                map.put(left, Math.max(map.get(end), right));
            }
            Map<Integer, Integer> subMap = map.subMap(left, false, right, true);
            Set<Integer> set = new HashSet(subMap.keySet());
            map.keySet().removeAll(set);
        }

        public boolean queryRange(int left, int right) {
            Integer start = map.floorKey(left);
            if (start == null) return false;
            return map.get(start) >= right;
        }

        public void removeRange(int left, int right) {
            if (right <= left) return;
            Integer start = map.floorKey(left);
            Integer end = map.floorKey(right);
            if (end != null && map.get(end) > right) {
                map.put(right, map.get(end));
            }
            if (start != null && map.get(start) > left) {
                map.put(start, left);
            }
            Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
            Set<Integer> set = new HashSet(subMap.keySet());
            map.keySet().removeAll(set);

        }
    }

}
