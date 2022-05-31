package leetcode;

import java.io.FileReader;

/**
 * @description: You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them,
 * causing the left and the right side of the deleted substring to concatenate together.
 * <p>
 * We repeatedly make k duplicate removals on s until we no longer can.
 * <p>
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 * @author: LISHUAI
 * @createDate: 2022/5/6 19:12
 * @version: 1.0
 */

public class LeetCode_1209 {

    public static void main(String[] args) throws Exception {
//        String s = "abcd";
//        int k = 2;
//        String s = "deeedbbcccbdaa";
//        int k = 3;
//        String s = "pbbcggttciiippooaais";
        int k = 2;
//        String s1 = removeDuplicates(s, k);
//        System.out.println("result : " + s1);
        String s = getStr();
        String s3 = removeDuplicates(s, k);
//        System.out.println("result : " + s3);
        String s4 = removeDuplicates_02(s, k);
//        System.out.println("result : " + s4);
//        System.out.println("abcdefghijklmnopqrstuvwxyz".length());
        System.out.println(s3.equals(s4));
    }

    private static String makeStr() {
        char[] chars = new char[26 * 1923];
        for (int i = 0; i < 26 * 1923; i++) {
            chars[i] = (char) ((i % 26) + 'a');
        }
        return String.valueOf(chars, 0, 26 * 1923);
    }

    private static String getStr() throws Exception {
        String name = "src/main/resources/leet_code_1209.txt";
        FileReader reader = new FileReader(name);
        char[] chars = new char[1024 * 1024 * 10];
        int read = reader.read(chars);
        return String.valueOf(chars, 0, read);
    }

    public static String removeDuplicates(String s, int k) {
        char[] chars = s.toCharArray();
        int index = chars.length, x = 0, right = 0;

        while (right < index) {
            int cur = 1;
            while (cur < k && right - x - cur >= 0 && chars[right] == chars[right - x - cur]) {
                cur++;
            }
            if (cur == k) {
                x += k;
            } else {
                chars[right - x] = chars[right];
            }
            right++;
        }

        return String.valueOf(chars, 0, index - x);
    }

    public static String removeDuplicates_02(String s, int k) {
        char[] chars = s.toCharArray();
        Info[] stack = new Info[chars.length];
        int index = -1;

        for (char c : chars) {
            if (index != -1 && stack[index].c == c) {
                if (stack[index].size == k - 1) {
                    --index;
                } else {
                    stack[index].size++;
                }
            } else {
                stack[++index] = new Info(c);
            }
        }
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i <= index; i++) {
            sbr.append(String.valueOf(stack[i].c).repeat(Math.max(0, stack[i].size)));
        }

        return sbr.toString();
    }

    static class Info {
        char c;
        int size;

        public Info(char c) {
            this.c = c;
            this.size = 1;
        }
    }
}
