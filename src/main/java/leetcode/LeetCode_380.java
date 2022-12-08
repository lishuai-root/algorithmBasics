package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description: Implement the RandomizedSet class:
 * <p>
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 * @author: LISHUAI
 * @createDate: 2022/11/29 23:08
 * @version: 1.0
 */

public class LeetCode_380 {


    public static void main(String[] args) {
//        RandomizedSet set = new RandomizedSet();
//        boolean insert = set.insert(0);
//        set.remove(0);
//        set.insert(1);
//        set.remove(0);
//        set.insert(2);
//        set.remove(1);
//        int random = set.getRandom();
//        System.out.println(random);
//        System.out.println(insert);
//        int i = new Random().nextInt(1);
//        System.out.println(i);
//        System.out.println(-2147483646);
//        System.out.println(-2147483647 ^ 1);
//        System.out.println(-2147483646 >> 16);
//        System.out.println((-2147483647 ^ 1) >> 16);
//        System.out.println(((-2147483647 ^ 1) >> 16) ^ (-2147483647 ^ 1) & 65535);
//        System.out.println((-2147483646 >> 16) ^ -2147483646 & 65535);
//        System.out.println(((-2147483647 ^ 1) >> 16) ^ (-2147483647 ^ 1));
//        System.out.println((-2147483646 >> 16) ^ -2147483646);
        int count = Integer.MAX_VALUE;
//        System.out.println(0 ^ 0);
//        System.out.println(0 ^ 1);
//        System.out.println(1 ^ 1);
//        int k = Integer.MAX_VALUE;
//        int v = k;
//        v = (v ^ 1) ^ (1 << 15);
//        System.out.println(k + " - " + v + " - " + hashCustom(k) + " - " + hashCustom(v));
//        Random r = new Random();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = Integer.MIN_VALUE; i < count; i++) {
//            int m = r.nextInt();
//            int q = r.nextInt(32);
//            int n = m ^ (1 << q);
//            int rm = hashCustom(m);
//            int rn = hashCustom(n);
//            if (rm == rn) {
//                System.out.println(m + " - " + n + " - " + q + " - " + rm + " - " + rn);
//            }
            int j = hashCustom(i);
            if (map.containsKey(j)) {
                System.out.println(j + " - " + i + " - " + map.get(j));
            }
            map.put(j, i);
        }
//        -52838 - -2146848365 - -2146913902
//        63458 - -2146934140 - -2146934141
//        63458 - -2146934139 - -2146934140
//        63458 - -2146934138 - -2146934139
//        System.out.println(hashCustom(-2146848365));
//        System.out.println(hashCustom(-2146913902));
//        System.out.println(Integer.MIN_VALUE);
//        StringBuilder sbr = new StringBuilder();
//        System.out.print(-2146848365 + " : ");
//        for (int i = 31; i >= 0; i--) {
//            if ((-2146848365 & (1 << i)) == 0) {
//                System.out.print('0');
//            } else {
//                System.out.print('1');
//            }
//            if ((-2146913902 & (1 << i)) == 0) {
//                sbr.append('0');
//            } else {
//                sbr.append('1');
//            }
//        }
//
//        System.out.println();
//        System.out.println(sbr.length());
//        System.out.println(-2146913902 + " : " + sbr.toString());
    }

    private static int hashCustom(int value) {
//        return (~(value >> 16)) ^ value & 65535;
        int q = value >> 24;
        int p = q ^ value;
        q = value >> 8;
        return (p ^ (q ^ value)) & 65535;
    }

    static class RandomizedSet {

        private final Map<Integer, Integer> keyMap;
        private final Random random;
        private int[] indexes;
        private int size;

        public RandomizedSet() {
            keyMap = new HashMap<>();
            random = new Random();
            indexes = new int[16];
            size = 0;
        }

        private int[] reSize(int[] arr, int size) {
            if (size < arr.length) {
                return arr;
            }
            int[] newArr = new int[arr.length << 1];
            System.arraycopy(arr, 0, newArr, 0, size);
            return newArr;
        }

        public boolean insert(int val) {
            if (!keyMap.containsKey(val)) {
                indexes = reSize(indexes, size);
                keyMap.put(val, size);
                indexes[size++] = val;
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (keyMap.containsKey(val)) {
                int deleteIndex = keyMap.get(val);
                indexes[deleteIndex] = indexes[--size];
                keyMap.remove(indexes[deleteIndex]);
                keyMap.put(indexes[deleteIndex], deleteIndex);
                keyMap.remove(val);
                return true;
            }
            return false;
        }

        public int getRandom() {
            if (size > 0) {
                return indexes[random.nextInt(size)];
            }
            return -1;
        }
    }
}
