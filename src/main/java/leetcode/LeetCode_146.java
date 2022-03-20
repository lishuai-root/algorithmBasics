package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description: Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise,
 * add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation,
 * evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * @author: LISHUAI
 * @createDate: 2021/11/25 21:12
 * @version: 1.0
 */

public class LeetCode_146 {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1, 1);

        lruCache.put(2, 2);

//        lruCache.showMap();

        System.out.println(lruCache.get(1));

        lruCache.put(3, 3);

        System.out.println(lruCache.get(2));

        lruCache.put(4, 4);

        lruCache.showMap();

        System.out.println(lruCache.get(1));

        System.out.println(lruCache.get(3));

        System.out.println(lruCache.get(4));
    }

    static class LRUCache {

        int size = 0, capacity;

        Map<Integer, Node> map;

        Node head, tail;

        public LRUCache(int capacity) {

            this.capacity = capacity;

            this.map = new HashMap<>();

            init();
        }

        public void init() {

            head = new Node();

            tail = new Node();

            tail.pre = head;

            head.next = tail;
        }

        public void showMap() {

            Set<Integer> integers = map.keySet();

            Iterator<Integer> iterator = integers.iterator();

            while (iterator.hasNext()) {

                System.out.println(iterator.next());
            }
        }

        public int get(int key) {

            if (!map.containsKey(key)) {

                return -1;
            }

            Node node = map.get(key);

            reset(node);

            return node.val;
        }

        public void put(int key, int value) {

            Node node;

            if (map.containsKey(key)) {

                node = map.get(key);

                node.val = value;

                reset(node);

                return;
            }

            if (size == this.capacity) {

                node = tail.pre;

                map.remove(node.key);

                map.put(key, node);

                node.key = key;

                node.val = value;

                reset(node);

                return;
            }

            node = new Node(key, value);

            map.put(key, node);

            node.next = head.next;

            head.next.pre = node;

            head.next = node;

            node.pre = head;

            size++;
        }

        public void reset(Node node) {

            if (size == 1) {

                return;
            }

            node.pre.next = node.next;

            node.next.pre = node.pre;

            node.next = head.next;

            head.next.pre = node;

            head.next = node;

            node.pre = head;
        }

        class Node {

            Node pre, next;

            int key = -1, val = -1;

            public Node() {
            }

            public Node(int key, int val) {

                this.key = key;

                this.val = val;
            }
        }
    }
}

