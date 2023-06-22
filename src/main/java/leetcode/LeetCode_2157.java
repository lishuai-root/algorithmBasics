package leetcode;

import java.util.Arrays;

/**
 * @description: You are given a 0-indexed array of strings words. Each string consists of lowercase English letters only. No letter occurs more than once in any string of words.
 * <p>
 * Two strings s1 and s2 are said to be connected if the set of letters of s2 can be obtained from the set of letters of s1 by any one of the following operations:
 * <p>
 * Adding exactly one letter to the set of the letters of s1.
 * Deleting exactly one letter from the set of the letters of s1.
 * Replacing exactly one letter from the set of the letters of s1 with any letter, including itself.
 * The array words can be divided into one or more non-intersecting groups. A string belongs to a group if any one of the following is true:
 * <p>
 * It is connected to at least one other string of the group.
 * It is the only string present in the group.
 * Note that the strings in words should be grouped in such a manner that a string belonging to a group cannot be connected to a string present in any other group. It can be proved that such an arrangement is always unique.
 * <p>
 * Return an array ans of size 2 where:
 * <p>
 * ans[0] is the maximum number of groups words can be divided into, and
 * ans[1] is the size of the largest group.
 * @author: LISHUAI
 * @createDate: 2023/4/28 21:47
 * @version: 1.0
 */

public class LeetCode_2157 {

    public static void main(String[] args) {
        String[] words = {"a", "b", "ab", "cde"};
//        String[] words = {"web", "a", "te", "hsx", "v", "k", "a", "roh"};
//        String[] words = {"a", "ab", "abc"};
//        String[] words = {"xhg", "kove", "ti", "cs", "itfzx", "m", "nrszq", "suc", "gs"};
        int[] ints = groupStrings(words);
        System.out.println(ints[0] + " : " + ints[1]);
        ints = groupStrings_02(words);
        System.out.println(ints[0] + " : " + ints[1]);
    }

    public static int[] groupStrings(String[] words) {
        int[] nums = new int[1 << 26];
        int ag = 0, am = 0;

        for (String word : words) {
            int bit = 0;
            for (int i = 0; i < word.length(); i++) {
                bit |= (1 << (word.charAt(i) - 'a'));
            }
            if (nums[bit] == 0) {
                ag++;
            }
            nums[bit]++;
            for (int i = 0; i < 26; i++) {
                int b = bit ^ (1 << i);
                if (nums[b] != 0) {
                    nums[bit] += nums[b];
                    nums[b] = 0;
                    ag--;
                }
            }
            am = Math.max(am, nums[bit]);
        }
        return new int[]{ag, am};
    }


    public static int[] groupStrings_02(String[] words) {
        int len = words.length;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            String word = words[i];
            int bit = 0;
            for (int j = 0; j < word.length(); j++) {
                bit |= (1 << (word.charAt(j) - 'a'));
            }
            nums[i] = bit;
        }
        Arrays.sort(nums);
        int[] hs = new int[len];
        int index = 0;
        for (int i = 1; i < len; i++) {
            if (nums[i] != nums[i - 1]) {
                index++;
            }
            hs[i] = index;
        }
        int[] cache = new int[index + 1];
        int[] reIndex = new int[index + 1];
        for (int i = 0; i < reIndex.length; i++) {
            reIndex[i] = i;
        }
        int ag = index + 1, am = 0;
        for (int i = 0; i < len; i++) {
            int id = find(reIndex, hs[i]);
            cache[id]++;
            if (i > 0 && nums[i] != nums[i - 1]) {
                int bit = nums[i];
                for (int j = 0; j < 26; j++) {
                    int b = bit ^ (1 << j);
                    index = search(nums, 0, len - 1, b);
                    if (index != -1) {
                        int k = find(reIndex, hs[index]);
                        if (id != k) {
                            cache[id] += cache[k];
                            reIndex[k] = id;
                            hs[index] = id;
                            ag--;
                        }
                    }
                }
                int t = bit;
                while (t != 0) {
                    int q = bit;
                    int p = (t & -t);
                    t ^= p;
                    q ^= p;
                    for (int j = 0; j < 26; j++) {
                        int z = (1 << j);
                        if (z == p || (q & z) != 0) {
                            continue;
                        }
                        index = search(nums, 0, len - 1, (q | z));
                        if (index != -1) {
                            int k = find(reIndex, hs[index]);
                            if (id != k) {
                                cache[id] += cache[k];
                                reIndex[k] = id;
                                hs[index] = id;
                                ag--;
                            }
                        }
                    }
                }
            }

            am = Math.max(am, cache[id]);
        }
        return new int[]{ag, am};
    }

    private static int search(int[] nums, int left, int right, int target) {
        int mid = 0;
        while (right >= left) {
            mid = left + ((right - left) >>> 1);
            if (nums[mid] == target) {
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[mid] == target ? mid : -1;
    }

    private static int find(int[] index, int p) {
        return (index[p] == p ? p : (index[p] = find(index, index[p])));
    }
}
