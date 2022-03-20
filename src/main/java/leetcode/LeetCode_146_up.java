package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/25 21:51
 * @version: 1.0
 */

public class LeetCode_146_up {


    static class LRUCache {

        int capacity, size = 0, index = -1;

        Map<Integer, Integer> map;

        Node[] nodes;

        public LRUCache(int capacity) {

            this.capacity = capacity;

            map = new HashMap<>();

            nodes = new Node[this.capacity];
        }


        public int get(int key) {

            if (!map.containsKey(key)) {

                return -1;
            }

            return 0;
        }

        public void put(int key, int value) {

        }

        class Node {

            Node pre, next;

            int key, val;

            public Node() {
            }


            public Node(int key, int val) {

                this.key = key;

                this.val = val;
            }
        }
    }

}
