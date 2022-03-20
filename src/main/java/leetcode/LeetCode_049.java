package leetcode;

import java.util.*;

/**
 * @description: Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * @author: LISHUAI
 * @createDate: 2021/11/18 20:10
 * @version: 1.0
 */

public class LeetCode_049 {

    private static int[] nums = new int[26];

    static {

        for (int i = 0; i < nums.length; i++) {

            nums[i] = i + 1;
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"ac", "c"};

        List<List<String>> lists = groupAnagrams_02(strs);

        for (List<String> list : lists) {

            for (String s : list) {

                System.out.print(s + "   ");
            }

            System.out.println();
        }

    }

    private static int process(String str) {

        int hashCode = 0;

        for (int j = 0; j < str.length(); j++) {

            hashCode += str.charAt(j);
        }

        return hashCode;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        String line;

        for (String str : strs) {

            char[] chars = str.toCharArray();

            Arrays.sort(chars);

            line = Arrays.toString(chars);

            map.putIfAbsent(line, new ArrayList<>());

            map.get(line).add(str);
        }

        List<List<String>> list = new ArrayList<>(map.values());

        return list;
    }

    public static List<List<String>> groupAnagrams_02(String[] strs) {

        Map<Integer, List<String>> map = new HashMap<>();

        String line;


        for (String str : strs) {

            int hasCode = 0;

            for (int i = 0; i < str.length(); i++) {

                hasCode = str.charAt(i) ^ hasCode;
            }

            System.out.println(hasCode);

            map.putIfAbsent(hasCode, new ArrayList<>());

            map.get(hasCode).add(str);
        }

        List<List<String>> list = new ArrayList<>(map.values());

        return list;
    }
}
