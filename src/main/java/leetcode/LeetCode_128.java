package leetcode;

import java.util.*;

/**
 * @description: Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * @author: LISHUAI
 * @createDate: 2021/11/21 20:31
 * @version: 1.0
 */

public class LeetCode_128 {

    public static void main(String[] args) {

        int[] arr = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        int i = longestConsecutive_02(arr);

        System.out.println(i);
    }

    public static int longestConsecutive(int[] nums) {

        Arrays.sort(nums);

        int maxLen = 0, left = 0, iog = 0;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] > nums[i - 1] + 1) {

                maxLen = Math.max(i - left - iog, maxLen);

                left = i;

                iog = 0;
            }

            if (nums[i] == nums[i - 1]) {

                iog++;
            }
        }

        return Math.max(nums.length - left - iog, maxLen);
    }


    public static int longestConsecutive_02(int[] nums) {

        Map<Integer, Integer> minMap = new HashMap<>();

        Map<Integer, Integer> maxMap = new HashMap<>();

        Map<Integer, List<Integer>> indexMap = new HashMap<>();

        List<Integer> list = new ArrayList<>();

        List<Integer> l;

        Set<Integer> set = new HashSet<>(nums.length);

        int maxLen = 0, nowIndex;

        for (int num : nums) {

            if (set.contains(num)) {

                continue;
            }

            if (minMap.containsKey(num + 1)) {

                nowIndex = minMap.get(num + 1);

                l = indexMap.get(nowIndex);

                l.add(num);

                minMap.remove(num + 1);

                minMap.put(num, nowIndex);

                list.add(nowIndex, list.get(nowIndex) + 1);

                maxLen = Math.max(maxLen, list.get(nowIndex));

//                if (maxMap.containsKey(num - 1)) {
//
//                    list.add(maxMap.get(num - 1), list.get(maxMap.get(num - 1)) + list.get(nowIndex));
//
//                    list.add(nowIndex, list.get(maxMap.get(num - 1)));
//
//                    maxLen = Math.max(maxLen, list.get(nowIndex));
//                }

            } else if (maxMap.containsKey(num - 1)) {

                nowIndex = maxMap.get(num - 1);

                l = indexMap.get(nowIndex);

                l.add(num);

                maxMap.remove(num - 1);

                maxMap.put(num, nowIndex);

                list.add(nowIndex, list.get(nowIndex) + 1);

                maxLen = Math.max(maxLen, list.get(nowIndex));

//                if (minMap.containsKey(num + 1)) {
//
//                    list.add(minMap.get(num + 1), list.get(minMap.get(num + 1)) + list.get(nowIndex));
//
//                    list.add(nowIndex, list.get(minMap.get(num + 1)));
//
//                    maxLen = Math.max(maxLen, list.get(nowIndex));
//                }
            } else {

                maxLen = Math.max(maxLen, 1);

                list.add(1);

                minMap.put(num, list.size() - 1);

                maxMap.put(num, list.size() - 1);

                l = new ArrayList<>();

                l.add(num);

                l.add(num);

                indexMap.put(list.size() - 1, l);

            }

            if (maxMap.containsKey(num - 1)) {

                nowIndex = minMap.get(num);

                list.add(maxMap.get(num - 1), list.get(maxMap.get(num - 1)) + list.get(nowIndex));

                list.add(nowIndex, list.get(maxMap.get(num - 1)));

                maxLen = Math.max(maxLen, list.get(nowIndex));

                l = indexMap.get(maxMap.get(num - 1));

                for (Integer integer : l) {
                    maxMap.put(integer, nowIndex);
                }

                indexMap.remove(maxMap.get(num - 1));


                if (indexMap.get(nowIndex) != null) {
                    l.addAll(indexMap.get(nowIndex));
                }

                indexMap.put(nowIndex, l);

            }

            if (minMap.containsKey(num + 1)) {

                nowIndex = maxMap.get(num);

                list.add(minMap.get(num + 1), list.get(minMap.get(num + 1)) + list.get(nowIndex));

                list.add(nowIndex, list.get(minMap.get(num + 1)));

                maxLen = Math.max(maxLen, list.get(nowIndex));

                minMap.put(minMap.get(num + 1), nowIndex);

                l = indexMap.get(maxMap.get(num + 1));

                for (Integer integer : l) {
                    minMap.put(integer, nowIndex);
                }
                indexMap.remove(minMap.get(num + 1));

                if (indexMap.get(nowIndex) != null) {
                    l.addAll(indexMap.get(nowIndex));
                }


                indexMap.put(nowIndex, l);

            }

            set.add(num);
        }


        for (Integer integer : list) {

            System.out.print(integer + "   ");
        }

        System.out.println();

        Set<Integer> integers = minMap.keySet();

        Iterator<Integer> iterator = integers.iterator();

        while (iterator.hasNext()) {

            Integer next = iterator.next();

            System.out.println("min next : " + next + "   val : " + list.get(minMap.get(next)));
        }

        System.out.println("----------------");

        integers = maxMap.keySet();

        iterator = integers.iterator();

        while (iterator.hasNext()) {

            Integer next = iterator.next();

            System.out.println("max next : " + next + "   val : " + list.get(maxMap.get(next)));
        }
        System.out.println();

        return maxLen;
    }
}
