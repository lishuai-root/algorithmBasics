package leetcode;

import java.util.*;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/7/19 21:05
 * @version: 1.0
 */

public class LeetCode_76 {

    public static void main(String[] args) {
        fn_001();
    }

    private static String makeString(int size) {

        StringBuffer buffer = new StringBuffer();

        Random random = new Random();

        char c;

        int in;
        for (int i = 0; i < size; i++) {

            in = (int) random.nextInt(26);

//            if (Math.random() > 0.5)
            c = (char) (in + 65);
//            else
//                c = (char) (in + 97);

            buffer.append(c);
        }

        return buffer.toString();
    }

    private static void fn_001() {
//        String s = makeString(100000);
//        String t = makeString(10000);

        String s = "ADOBECODEBANC";

        String t = "ABC";

//        System.out.println("s : " + s);
//        System.out.println("t : " + t);

        long start, end;

        start = System.currentTimeMillis();
        String s1 = minWindow(s, t);
        end = System.currentTimeMillis();


        System.out.println("minWindow : " + (end - start));

//        start = System.currentTimeMillis();
//        String s2 = minWindow_03(s, t);
//        end = System.currentTimeMillis();

//        System.out.println("minWindow_03 : " + (end - start));

        start = System.currentTimeMillis();
        String s3 = minWindow_02(s, t);
        end = System.currentTimeMillis();

        System.out.println("minWindow_02 : " + (end - start));

//        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
//        System.out.println(s1);

        System.out.println("minWindow : " + s1);
        System.out.println("minWindow_02 : " + s3);
    }

    public static String minWindow(String s, String t) {

        int sLen = s.length(), tLen = t.length();

        if (sLen < tLen)
            return "";

        int left = 0, right = 0, size = tLen, minLen = Integer.MAX_VALUE;

        String c;

        String line = "";

        char[] chars = t.toCharArray();

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < tLen; i++) {

            map.put(chars[i] + "", map.getOrDefault(chars[i] + "", 0) + 1);
        }

        HashMap<String, Integer> hashMap = new HashMap<>(map);

//        Set<String> characters = map.keySet();
//
//        Iterator<String> iterator = characters.iterator();
//
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//
//            System.out.println("next : " + next + "   " + map.get(next));
//        }

        for (; right < sLen; right++) {

            c = s.substring(right, right + 1);

            if (hashMap.containsKey(c) && hashMap.get(c) > 0) {

                hashMap.put(c, hashMap.get(c) - 1);

                size--;
            }

            if (size == 0) {

                hashMap = new HashMap<>(map);

                hashMap.put(c, hashMap.get(c) - 1);

                size++;

                left = right;


                while (size < tLen && left > 0) {

                    left--;

                    c = s.substring(left, left + 1);

//                    left--;

                    if (!hashMap.containsKey(c))
                        continue;

                    if (hashMap.get(c) <= 0)
                        continue;

                    hashMap.put(c, hashMap.get(c) - 1);

                    size++;


                }

                line = right - left + 1 > minLen ? line : s.substring(left, right + 1);

                minLen = line.length();

//                left = right - tLen;

                right = left + 1;

                hashMap = new HashMap<>(map);

            }
        }

        return line;
    }


    public static String minWindow_01(String s, String t) {

        int sLen = s.length(), tLen = t.length();

        if (sLen < tLen)
            return "";

        int left = 0, right = 0, size = sLen - tLen, minLen = Integer.MAX_VALUE;

        String c;

        String line = "";

        char[] chars = t.toCharArray();

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < tLen; i++) {

            map.put(chars[i] + "", map.getOrDefault(chars[i] + "", 0) + 1);
        }

        HashMap<String, Integer> hashMap = new HashMap<>(map);


        for (; right < sLen; right++) {

            c = s.substring(right, right + 1);

            if (hashMap.containsKey(c) && hashMap.get(c) > 0) {

                hashMap.put(c, hashMap.get(c) - 1);

                size--;
            }

            if (size == 0) {

                hashMap = new HashMap<>(map);

                hashMap.put(c, hashMap.get(c) - 1);

                size++;

                left = right;


                while (size < tLen && left > 0) {

                    left--;

                    c = s.substring(left, left + 1);

//                    left--;

                    if (!hashMap.containsKey(c))
                        continue;

                    if (hashMap.get(c) <= 0)
                        continue;

                    hashMap.put(c, hashMap.get(c) - 1);

                    size++;


                }

                line = right - left + 1 > minLen ? line : s.substring(left, right + 1);

                minLen = line.length();

//                left = right - tLen;

                left--;

            }
        }

        for (right++; right < sLen; right++) {

            c = s.substring(right, right + 1);

            if (!hashMap.containsKey(c)) {
                continue;
            }

            while (left <= right) {

                if (c.equals(s.substring(left, ++left))) {

                    line = right - left + 1 > minLen ? line : s.substring(left, right + 1);

                    minLen = line.length();
                }
            }
        }

        return line;
    }

    public static String minWindow_02(String s, String t) {

        int sLen = s.length(), tLen = t.length();

        if (sLen < tLen)
            return "";

        int left = 0, right = sLen - 1, size = sLen - tLen, minLen = Integer.MAX_VALUE;

        String c;

        String line = "";

        char[] chars = t.toCharArray();

        HashMap<String, Integer> sMap = new HashMap<String, Integer>();

        HashMap<String, Integer> tMap = new HashMap<String, Integer>();

        for (int i = 0; i < sLen; i++) {

            sMap.put(s.substring(i, i + 1), sMap.getOrDefault(s.substring(i, i + 1), 0) + 1);
        }

        for (int i = 0; i < tLen; i++) {

            tMap.put(t.substring(i, i + 1), tMap.getOrDefault(t.substring(i, i + 1), 0) + 1);
        }

        Set<String> strings = tMap.keySet();

        Iterator<String> iterator = strings.iterator();

        while (iterator.hasNext()) {
            line = iterator.next();
            if (!sMap.containsKey(line) || sMap.get(line) < tMap.get(line))
                return "";
        }

        Map<String, Integer> map = new HashMap<>(sMap);

        while (true) {

            c = s.substring(left, left + 1);

            if (!tMap.containsKey(c)) {

            } else if (map.get(c) > tMap.get(c))
                map.put(c, map.get(c) - 1);
            else
                break;

            left++;

        }

        while (true) {

            c = s.substring(right, right + 1);

            if (!tMap.containsKey(c)) {


            } else if (map.get(c) > tMap.get(c))
                map.put(c, map.get(c) - 1);
            else
                break;

            right--;

        }

        line = right - left + 1 > minLen ? line : s.substring(left, right + 1);

        minLen = line.length();

        map = new HashMap<>(sMap);

        left = 0;
        right = sLen - 1;

        while (true) {

            c = s.substring(right, right + 1);

            if (!tMap.containsKey(c)) {


            } else if (map.get(c) > tMap.get(c))
                map.put(c, map.get(c) - 1);
            else
                break;

            right--;

        }


        while (true) {

            c = s.substring(left, left + 1);

            if (!tMap.containsKey(c)) {


            } else if (map.get(c) > tMap.get(c))
                map.put(c, map.get(c) - 1);
            else
                break;

            left++;

        }

        line = right - left + 1 > minLen ? line : s.substring(left, right + 1);

        minLen = line.length();

        return line;
    }

    public static String minWindow_03(String s, String t) {
        int sLen = s.length(), tLen = t.length();

        if (sLen < tLen)
            return "";

        int left = 0, right = 0, size = sLen - tLen, minLen = Integer.MAX_VALUE, len = tLen;

        String c;

        String line = "";

        HashMap<String, Integer> sMap = new HashMap<String, Integer>();

        HashMap<String, Integer> tMap = new HashMap<String, Integer>();

        for (int i = 0; i < tLen; i++) {

            sMap.put(t.substring(i, i + 1), sMap.getOrDefault(t.substring(i, i + 1), 0) + 1);
        }

        for (; left < size; left++) {

//            StringBuffer buffer = new StringBuffer();

            tMap = new HashMap<>(sMap);

            len = tLen;

            for (right = left; right < sLen; right++) {

                c = s.substring(right, right + 1);

                if (tMap.containsKey(c) && tMap.get(c) > 0) {

                    tMap.put(c, tMap.get(c) - 1);

                    len--;
                }

                if (len == 0)
                    break;
            }

            if (len == 0) {
                line = right - left + 1 > minLen ? line : s.substring(left, right + 1);

                minLen = line.length();
            }

        }
        return line;
    }
}
