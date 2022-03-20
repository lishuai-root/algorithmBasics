package leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @description: Implement the RandomizedSet class:
 * <p>
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present.
 * Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present.
 * Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called).
 * Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 * @author: LISHUAI
 * @createDate: 2022/2/21 19:41
 * @version: 1.0
 */

public class LeetCode_038 {

    class RandomizedSet {

        private final float DEFAULT_LOAD_FACTOR = 0.75f;
        private int[] table;
        private int size = 0, len = 64;

        public RandomizedSet() {
            table = new int[len];
        }

        public boolean insert(int val) {
            if (size >= (int) (len * DEFAULT_LOAD_FACTOR)) {
                reserve();
            }

            int r = val & (len - 1);
            int i = table[r] & (1 << (val & 32 - 1));
            if (i == 0) {
                table[r] = table[r] | (1 << (val & 32 - 1));
            }
            size++;
            return i != 0;
        }

        public boolean remove(int val) {
            int r = val & (len - 1);
            int i = table[r] & (1 << (val & 32 - 1));
            if (i != 0) {
                table[r] = table[r] ^ (1 << (val & 32 - 1));
            }
            size--;
            return i == 0;
        }

        public int getRandom() {
            return 0;
        }

        private void reserve() {
            int[] t = table;
            len <<= 1;
            table = new int[len];
            for (int i = 0; i < t.length; i++) {
                table[i & (len - 1)] = t[i];
            }
        }
    }

    class RandomizedSet_02 {

        private final Set<Integer> set;
        private final Random r;

        public RandomizedSet_02() {
            set = new HashSet<>();
            r = new Random();
        }

        public boolean insert(int val) {
            return set.add(val);
        }

        public boolean remove(int val) {
            return set.remove(val);
        }

        public int getRandom() {
            int i = r.nextInt(set.size());
            Iterator<Integer> iterator = set.iterator();
            for (int j = 1; j < i; j++) {
                iterator.next();
            }
            return iterator.next();
        }
    }
}
