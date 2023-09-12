package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @description: Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 * <p>
 * Implement the AllOne class:
 * <p>
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 * dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 * @author: LiShuai
 * @createDate: 2023/9/7 20:14
 * @version: 1.0
 */

public class LeetCode_432 {

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("world");
        allOne.inc("leet");
        allOne.inc("code");
        allOne.inc("ds");
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());
        allOne.inc("ds");
        allOne.dec("leet");
        System.out.println(allOne.getMaxKey());
        allOne.dec("ds");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());
        allOne.inc("hello");
        allOne.inc("hello");
        allOne.dec("world");
        allOne.dec("leet");
        allOne.dec("code");
        allOne.dec("ds");
    }


    static class AllOne {
        private Map<String, Integer> map;
        private TreeSet<String> set;

        public AllOne() {
            map = new HashMap<>();
            set = new TreeSet<>((a, b) -> {
                int c = map.get(a) - map.get(b);
                return c == 0 ? a.hashCode() - b.hashCode() : c;
            });
        }

        public void inc(String key) {
            if (map.containsKey(key))
                set.remove(key);
            map.put(key, map.getOrDefault(key, 0) + 1);
            set.add(key);
        }

        public void dec(String key) {
            if (map.containsKey(key)) {
                int k = map.get(key);
                set.remove(key);
                if (k == 1) {
                    map.remove(key);
                } else {
                    map.put(key, k - 1);
                    set.add(key);
                }
            }
        }

        public String getMaxKey() {
            return set.isEmpty() ? "" : set.last();
        }

        public String getMinKey() {
            return set.isEmpty() ? "" : set.first();
        }
    }
}
