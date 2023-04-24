package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Design and implement a data structure for a Least Frequently Used (LFU) cache.
 * <p>
 * Implement the LFUCache class:
 * <p>
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 * <p>
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 * <p>
 * The functions get and put must each run in O(1) average time complexity.
 * @author: LISHUAI
 * @createDate: 2023/1/29 19:48
 * @version: 1.0
 */

public class LeetCode_460 {

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        System.out.println(lfuCache.get(2));
        lfuCache.put(2, 6);
        System.out.println(lfuCache.get(1));
        lfuCache.put(1, 5);
        lfuCache.put(1, 2);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(2));
    }

    static class LFUCache {

        int size, maxSize;
        Map<Integer, Info> map;
        private Info[][] infos;

        private int index;

        public LFUCache(int capacity) {
            infos = new Info[16][2];
            fillInfos(infos, 0);
            size = 0;
            maxSize = capacity;
            map = new HashMap<Integer, Info>();
            index = 0;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Info info = map.get(key);
            remove(info);
            info.counter++;
            upCount(info);
            return info.value;
        }

        public void put(int key, int value) {
            if (maxSize == 0) {
                return;
            }

            Info info = map.get(key);
            if (info == null) {
                info = new Info(key, value);
                map.put(key, info);
                if (size == maxSize) {
                    remove();
                }
                size++;
            } else {
                info.value = value;
                info.counter++;
                remove(info);
            }
            upCount(info);
        }

        private void upCount(Info info) {
            int curIndex = info.counter;
            if (curIndex >= infos.length) {
                reSize();
            }

            Info curTail = infos[curIndex][1];
            info.prev = curTail.prev;
            curTail.prev.next = info;
            info.next = curTail;
            curTail.prev = info;
            index = Math.min(index, info.counter);
        }

        private void reSize() {
            Info[][] newInfos = new Info[infos.length << 1][2];
            System.arraycopy(infos, 0, newInfos, 0, infos.length);
            fillInfos(newInfos, infos.length);
            infos = newInfos;
        }

        private void fillInfos(Info[][] infos, int start) {
            for (int i = start; i < infos.length; i++) {
                Info head = new Info();
                Info tail = new Info();
                head.next = tail;
                tail.prev = head;
                infos[i][0] = head;
                infos[i][1] = tail;
            }
        }

        private void remove() {
            while (index < infos.length) {
                Info[] info = infos[index];
                if (info[0].next != info[1]) {
                    Info next = info[0].next;
                    remove(next);
                    map.remove(next.key);
                    size--;
                    break;
                }
                index++;
            }
        }

        private void remove(Info info) {
            info.prev.next = info.next;
            info.next.prev = info.prev;
            info.next = null;
            info.prev = null;
        }
    }

    static class Info {
        int counter, key, value;
        Info prev, next;

        public Info(int key, int value) {
            this.key = key;
            this.value = value;
            this.counter = 0;
        }

        public Info() {
            this.counter = 0;
        }
    }
}
