package leetcode;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following three operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 *
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/7/27 19:42
 * @version: 1.0
 */

public class LeetCode_72 {

    @SneakyThrows
    public static void main(String[] args) {


//        int a = 12;
//
//        System.out.println((a << 29) >>> 29);
//
//        HashMap<String, String> map = new HashMap<>(5);
//        HashMap<String, String> maps = new HashMap<>();
//
//        int i = tableSizeFor(5);
//
//
//        System.out.println(i);

//        int i = fn_001(1, 2);
//
//        System.out.println(i);

//        System.out.println((int) 'a');

//        String str = "abc";
//
//        System.out.println(str.substring(str.length(), str.length()));

        String word1 = "horse", word2 = "ros";

        int i = minDistance(word1, word2);

        System.out.println(i);
    }

    /**
     * 求a的开c次方，向下取整
     *
     * @param a
     * @param c
     * @return
     */
    private static int fn_001(int a, int c) {

        int b = tableSizeFor(a);

        System.out.println(b);

        int resule = a == b ? 0 : 0 - 1;

        while (b > 1) {

            b >>= 1;

            resule++;
        }

//        return Math.max(0, resule);
        return resule;
    }

    /**
     * 返回大于cap离cap最近的2的次幂
     * <p>
     * **hashCode源码
     *
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= (1 << 30)) ? (1 << 30) : n + 1;
    }


    public static int minDistance(String word1, String word2) {

        int result = 0;

        Queue<String> queue = new LinkedList<>();

        HashMap<String, Integer> map = new HashMap<>();

        queue.add(word1);

        map.put(word1, 0);

        String str = "", newStr = "";

        while (!queue.isEmpty()) {

            str = queue.poll();

//            result++;


            if (str.equals(word2)) {

                break;
            }

//            if (!str.equals("b")) {
//
//                continue;
//            }

//            System.out.println(" *** : " + str);

            for (int i = 0; i < str.length(); i++) {

//                System.out.println("--------------");

//                System.out.println("---------- : " + map.containsKey(str.substring(0, i) + str.substring(i + 1, str.length())));

                newStr = str.substring(0, i) + str.substring(i + 1, str.length());

                if (!map.containsKey(newStr)) {
                    queue.add(newStr);

                    map.put(newStr, map.getOrDefault(str, 0) + 1);
                }


                for (int j = 0; j < 26; j++) {

                    if (str.charAt(i) != (j + 97)) {

//                        System.out.println("=== : " + map.containsKey(str.substring(0, i) + (char) (j + 97) + str.substring(i + 1, str.length())));

                        newStr = str.substring(0, i) + (char) (j + 97) + str.substring(i + 1, str.length());

                        if (!map.containsKey(newStr)) {
                            queue.add(newStr);
                            map.put(newStr, map.getOrDefault(str, 0) + 1);
                        }


                    }

//                    System.out.println("+++++++++++++ : " + map.containsKey(str.substring(0, i) + (char) (j + 97) + str.substring(i, str.length())));

                    newStr = str.substring(0, i) + (char) (j + 97) + str.substring(i, str.length());

                    if (str.length() <= word2.length()
                            && !map.containsKey(newStr)) {
                        queue.add(newStr);
                        map.put(newStr, map.getOrDefault(str, 0) + 1);
                    }

                    if (i == str.length()
                            && !map.containsKey(str + (char) (j + 97))) {

                        queue.add(str + (char) (j + 97));
                        map.put(str + (char) (j + 97), map.getOrDefault(str, 0) + 1);
                    }

                }

            }

        }


        System.out.println("--- : " + word2);
        System.out.println("--------- : " + str);
        return map.get(str);
    }

    public static int minDistance_02(String word1, String word2) {

        HashSet<String> set = new HashSet<>();

        return process(word1, word2, 0, 0, set);
    }

    public static int process(String word1, String word2, int index, int resule, HashSet<String> set) {

        if (word1.equals(word2)) {

            return resule;
        }

        int num = 0;

        String str = "";

//        for (int i = 0; i < word1.length(); i++) {
//
//            for (int j = 0; j < 26; j++) {
//
//                str =
//
//                if (!set.contains())
//            }
//
//        }

        return 0;
    }
}
