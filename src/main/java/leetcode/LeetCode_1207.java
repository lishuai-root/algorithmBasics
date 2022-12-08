package leetcode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description: Given an array of integers arr, return true if the number of occurrences of each value in the array is unique, or false otherwise.
 * @author: LISHUAI
 * @createDate: 2022/11/30 23:43
 * @version: 1.0
 */

public class LeetCode_1207 {

    public static void main(String[] args) {
//        int[] arr = {1, 2, 2, 1, 1, 3};
//        int[] arr = {1, 2, 2};
        int[] arr = getArr(10000000);
//        int[] arr = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        long start = System.currentTimeMillis();
        boolean b = uniqueOccurrences_03(arr);
        long end = System.currentTimeMillis();
        System.out.println(b + " - " + "uniqueOccurrences_03 - " + (end - start));
        start = System.currentTimeMillis();
        b = uniqueOccurrences_04(arr);
        end = System.currentTimeMillis();
        System.out.println(b + " - " + "uniqueOccurrences_04 - " + (end - start));
    }

    private static int[] getArr(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> valueIndex = new HashMap<>();
        Map<Integer, Integer> countIndex = new HashMap<>();
        int ans = 0;
        for (int i : arr) {
            Integer targetCount = valueIndex.getOrDefault(i, 0);
            valueIndex.put(i, targetCount + 1);
            if (countIndex.containsKey(targetCount)) {
                if (countIndex.get(targetCount) > 1) {
                    ans--;
                }
                countIndex.put(targetCount, countIndex.get(targetCount) - 1);
            }
            countIndex.put(targetCount + 1, countIndex.getOrDefault(targetCount + 1, 0) + 1);
            if (countIndex.get(targetCount + 1) > 1) {
                ans++;
            }
        }
        return ans == 0;
    }

    public static boolean uniqueOccurrences_02(int[] arr) {
        Map<Integer, Integer> valueIndex = new HashMap<>();
        int len = arr.length;
        int[] countIndex = new int[len + 1];
        int ans = 0;
        for (int i : arr) {
            Integer targetCount = valueIndex.getOrDefault(i, 0);
            valueIndex.put(i, targetCount + 1);
            if (countIndex[targetCount] > 1) {
                ans--;
            }
            countIndex[targetCount] = Math.max(0, countIndex[targetCount] - 1);
            countIndex[targetCount + 1] = countIndex[targetCount + 1] + 1;
            if (countIndex[targetCount + 1] > 1) {
                ans++;
            }
        }
        return ans == 0;
    }

    public static boolean uniqueOccurrences_03(int[] arr) {
        Arrays.sort(arr);
        int len = arr.length, l = 0, r = 1;
        int[] cache = new int[len + 1];
        while (r < len) {
            if (arr[r] != arr[l]) {
                int c = r - l;
                if (cache[c] != 0) {
                    return false;
                }
                cache[c]++;
                l = r;
            }
            r++;
        }
        return cache[r - l] == 0;
    }

    public static boolean uniqueOccurrences_04(int[] arr) {
        Arrays.sort(arr);
        int len = arr.length, l = 0, r = 0, q = arr[0];
        while (r < len) {
            int p = arr[r];
            arr[r] = 0;
            if (p != q) {
                int c = r - l - 1;
                if (arr[c] != 0) {
                    return false;
                }
                arr[c]++;
                l = r;
                q = p;
            }
            r++;
        }
        return arr[r - l - 1] == 0;
    }
}
