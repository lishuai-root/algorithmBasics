package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/9/2 20:13
 * @version: 1.0
 */

public class LeetCode_438 {

    public static void main(String[] args) {

        String s = "abacbabc",
                p = "abc";

        List<Integer> anagrams = findAnagrams(s, p);

    }

    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> list = new ArrayList<>();

        if (p.length() > s.length())
            return list;

        int all = p.length(), winLen = all;

        char[] sChars = s.toCharArray();

        char[] pChars = p.toCharArray();

        Map<Character, Integer> map = new HashMap<>(26);

        for (int i = 0; i < pChars.length; i++) {

            map.put(pChars[i], map.getOrDefault(pChars[i], 0) + 1);
        }

        for (int i = 0; i < pChars.length; i++) {

            if (map.containsKey(sChars[i])) {

                if (map.get(sChars[i]) > 0) {

                    all--;
                }

                map.put(sChars[i], map.get(sChars[i]) - 1);
            }


        }

//        System.out.println(all);

        if (all == 0) {

            list.add(0);
        }

        for (int i = pChars.length; i < sChars.length; i++) {

            if (map.containsKey(sChars[i - pChars.length])) {

                map.put(sChars[i - pChars.length], map.get(sChars[i - pChars.length]) + 1);

                if (map.get(sChars[i - pChars.length]) > 0) {

                    all++;
                }
            }


            if (map.containsKey(sChars[i])) {

                if (map.get(sChars[i]) > 0) {

                    all--;
                }

                map.put(sChars[i], map.get(sChars[i]) - 1);


            }

//            System.out.println(" --- " + (i - pChars.length + 1) + "   " + all);

            if (all <= 0) {

                list.add(i - pChars.length + 1);
            }

        }

        return list;
    }
}
