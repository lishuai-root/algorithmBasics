package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/8 10:54
 * @version: 1.0
 */

public class LeetCode_76_bak {

    public static void main(String[] args) {

        String s = "bba";

        String t = "ab";

        String s1 = minWindow(s, t);

        System.out.println(s1);

    }

    public static String minWindow(String s, String t) {

        if (s.length() < t.length()) {
            return "";
        }

        int sLen = s.length(), tLen = t.length();

        int left = 0, right = 0, max = Integer.MAX_VALUE, mLeft = 0, mRight = -1;

        char c;

        HashMap<Character, Integer> tMap = new HashMap<>(tLen);

        HashMap<Character, Integer> map = new HashMap<>(tLen);

        HashSet<Character> set = new HashSet<>();

        HashSet<Character> reSet = new HashSet<>();

        for (int i = 0; i < tLen; i++) {

            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);

            map.put(t.charAt(i), 0);

            set.add(t.charAt(i));

            reSet.add(t.charAt(i));
        }

        for (; right < sLen; right++) {

            c = s.charAt(right);

            if (set.contains(c)) {

                map.put(c, map.getOrDefault(c, 0) + 1);

                check(tMap, map, c, reSet);

                while (reSet.isEmpty()) {

                    while (!set.contains(s.charAt(left))) {
                        left++;
                    }

                    if (max > right - left) {

                        mRight = right;

                        mLeft = left;

                        max = mRight - mLeft;
                    }

                    map.put(s.charAt(left), map.get(s.charAt(left)) - 1);

                    check(tMap, map, s.charAt(left), reSet);

                    left++;

                    while (left < sLen && !set.contains(s.charAt(left))) {
                        left++;
                    }

                }
            }
        }

        return s.substring(mLeft, mRight + 1);
    }

    public static void check(HashMap<Character, Integer> tMap, HashMap<Character, Integer> map, char c, HashSet<Character> set) {

        if (map.get(c) >= tMap.get(c)) {

            set.remove(c);
        } else {

            set.add(c);
        }
    }


    public static String minWindow_02(String s, String t) {

        if (s.length() < t.length()) {
            return "";
        }

        int sLen = s.length(), tLen = t.length();

        int left = 0, right = 0, max = Integer.MAX_VALUE, mLeft = 0, mRight = -1, size = 0;

        char c;

        HashMap<Character, Integer> tMap = new HashMap<>(tLen);

        HashMap<Character, Integer> map = new HashMap<>(tLen);

        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < tLen; i++) {

            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);

            map.put(t.charAt(i), 0);

            set.add(t.charAt(i));
        }

        for (; right < sLen; right++) {

            c = s.charAt(right);

            if (set.contains(c)) {

                map.put(c, map.getOrDefault(c, 0) + 1);

                size++;

                while (size >= tLen && check(tMap, map, t)) {

                    while (!set.contains(s.charAt(left))) {
                        left++;
                    }

                    if (max > right - left) {

                        mRight = right;

                        mLeft = left;

                        max = mRight - mLeft;
                    }

                    map.put(s.charAt(left), map.get(s.charAt(left)) - 1);

                    size--;

                    left++;

                    while (left < sLen && !set.contains(s.charAt(left))) {
                        left++;
                    }

                }
            }
        }

        return s.substring(mLeft, mRight + 1);
    }

    public static boolean check(HashMap<Character, Integer> tMap, HashMap<Character, Integer> map, String str) {

        for (int i = 0; i < str.length(); i++) {

            if (map.get(str.charAt(i)) < tMap.get(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
