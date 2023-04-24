package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given an array of strings words, return the smallest string that contains each string in words as a substring. If there are multiple valid strings of the smallest length, return any of them.
 * <p>
 * You may assume that no string in words is a substring of another string in words.
 * @author: LISHUAI
 * @createDate: 2023/1/13 21:39
 * @version: 1.0
 */

public class LeetCode_943 {

    private static int ansSize;
    private static long reTime = 0;
    private static int count = 0;

    public static void main(String[] args) {
        String[] words = {"bccbacbcbabb", "wuyhrlvbvzfrop", "baaaaaabbbaaabbab", "kjhajgsbic", "eccge", "ccac", "fdgfgccfcefedfeda", "babcba", "ghahcebhgceecgfia", "baaabbabbac", "beaddcdabeafbfc", "rsaac"};
        long start = System.currentTimeMillis();
        String s = shortestSuperstring(words);
        long end = System.currentTimeMillis();
        System.out.println(s);
        System.out.println((end - start));
        System.out.println(reTime);
        System.out.println(count);
        int c = 1;
    }

    public static String shortestSuperstring(String[] words) {
        int len = words.length;
        int size = 0;
        for (String s : words) {
            size = Math.max(size, s.length());
        }
        boolean[] bls = new boolean[len];
        char[] chars = new char[len * size];
//        ansSize = Integer.MAX_VALUE;
        return shortestSuperstringProcess(words, bls, chars, 0, 0);
//        String ans = "";
//        int k = Integer.MAX_VALUE;
//        for (int i = 0; i < words.length; i++) {
//            String s = words[i];
//            int index = 0;
//            for (int j = 0; j < s.length(); j++) {
//                chars[index++] = s.charAt(j);
//            }
//            bls[i] = true;
//            String p = shortestSuperstringProcess(words, bls, chars, index, 1);
//            bls[i] = false;
//            if (p.length() < k) {
//                k = p.length();
//                ans = p;
//            }
//        }
//        return ans;
    }

    private static String shortestSuperstringProcess(String[] words, boolean[] bls, char[] chars, int index, int size) {
        count++;
        if (size == words.length) {
            String s = String.valueOf(chars, 0, index);
//            ansSize = Math.min(ansSize, s.length());
            return s;
        }

//        int len = Integer.MAX_VALUE;
//        String ans = "";
//        for (int i = 0; i < words.length; i++) {
//            if (!bls[i]) {
//                String s = words[i];
//                int start = reSize(chars, index, s);
//                if (ansLen < index + s.length() - start) {
//                    continue;
//                }
//                bls[i] = true;
//                int p = 0;
//                while (start + p < s.length()) {
//                    chars[index + p] = s.charAt(start + p);
//                    p++;
//                }
//                String q = shortestSuperstringProcess(words, bls, chars, index + p, size + 1);
//                if (q.length() != 0 && q.length() < len) {
//                    len = q.length();
//                    ans = q;
//                }
//                bls[i] = false;
//            }
//        }


        int len = -1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (bls[i]) {
                continue;
            }
            long start = System.currentTimeMillis();
            int p = reSize(chars, index, words[i]);
            long end = System.currentTimeMillis();
            reTime += (end - start);
            if (len <= p) {
                if (len < p) {
                    list.clear();
                    len = p;
                }
                list.add(i);
            }
        }
        String ans = "";
        int ansLen = Integer.MAX_VALUE;
        for (int n : list) {
            String next = words[n];
            int q = 0;
            while (len + q < next.length()) {
                chars[index + q] = next.charAt(len + q);
                q++;
            }
            bls[n] = true;
            String s = shortestSuperstringProcess(words, bls, chars, index + q, size + 1);
            bls[n] = false;
            if (s.length() < ansLen) {
                ans = s;
                ansLen = s.length();
            }
        }

        return ans;
    }

    private static int reSize(char[] chars, int index, String str) {
        if (String.valueOf(chars, 0, index).contains(str)) {
            return str.length();
        }
        int ans = 0, size = index - Math.min(index, str.length());
        while (++size < index) {
            int p = 0;
            int sum = 0;
            while (size + p < index) {
                if (chars[size + p] != str.charAt(p)) {
                    break;
                }
                p++;
                sum++;
            }
            if (size + sum == index) {
                return sum;
            }
        }
        return 0;
    }
}
