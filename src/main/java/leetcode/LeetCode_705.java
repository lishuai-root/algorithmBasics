package leetcode;

/**
 * @description: Design a HashSet without using any built-in hash table libraries.
 * <p>
 * Implement MyHashSet class:
 * <p>
 * void add(key) Inserts the value key into the HashSet.
 * bool contains(key) Returns whether the value key exists in the HashSet or not.
 * void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 * @author: LiShuai
 * @createDate: 2023/5/23 21:05
 * @version: 1.0
 */

public class LeetCode_705 {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE / 64);
        System.out.println(Math.sqrt(1000000));
    }

    class MyHashSet {

        private static final int F = 1000;
        private int[][] values;

        public MyHashSet() {
            values = new int[1001][1000];
        }

        public void add(int key) {
            values[key / 1000][key % 1000] = 1;
        }

        public void remove(int key) {
            values[key / 1000][key % 1000] = 0;
        }

        public boolean contains(int key) {
            return values[key / 1000][key % 1000] == 1;
        }
    }
}
