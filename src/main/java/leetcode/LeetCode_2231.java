package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: You are given a positive integer num.
 * You may swap any two digits of num that have the same parity (i.e. both odd digits or both even digits).
 * <p>
 * Return the largest possible value of num after any number of swaps.
 * @author: LISHUAI
 * @createDate: 2022/5/2 16:27
 * @version: 1.0
 */

public class LeetCode_2231 {

    public static void main(String[] args) {
        int i = largestInteger_04(65875);
        System.out.println(i);
    }


    public static int largestInteger_04(int num) {
        char[] chars = (num + "").toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int c = (chars[i] - '0') & 1;
            int max = i;
            for (int j = i + 1; j < chars.length; j++) {
                if ((chars[j] - '0' & 1) == c && chars[j] > chars[max]) {
                    max = j;
                }
            }
            c = chars[i];
            chars[i] = chars[max];
            chars[max] = (char) c;
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    public static int largestInteger_03(int num) {
        Queue<Integer> j = new PriorityQueue<>();
        Queue<Integer> o = new PriorityQueue<>();
        int ans = 0, tmp = num;
        while (tmp > 0) {
            int c = tmp % 10;
            if ((c & 1) == 1) {
                j.offer(c);
            } else {
                o.offer(c);
            }
            tmp /= 10;
        }

        tmp = num;
        int n = 1;
        while (tmp > 0) {
            int c = tmp % 10;
            if ((c & 1) == 1) {
                ans = ans + j.poll() * n;
            } else {
                ans = ans + o.poll() * n;
            }
            tmp /= 10;
            n *= 10;
        }
        return ans;
    }

    public static int largestInteger(int num) {
        String n = num + "";
        char[] chars = n.toCharArray();
        Queue<Character> j = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        Queue<Character> o = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for (char c : chars) {
            if (((c - '0') & 1) == 1) {
                j.offer(c);
            } else {
                o.offer(c);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (((chars[i] - '0') & 1) == 1) {
                chars[i] = j.poll();
            } else {
                chars[i] = o.poll();
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    public static int largestInteger_02(int num) {
        List<Character> j = new ArrayList<>();
        List<Character> o = new ArrayList<>();
        String n = num + "";
        char[] chars = n.toCharArray();
        for (char c : chars) {
            if (((c - '0') & 1) == 1) {
                j.add(c);
            } else {
                o.add(c);
            }
        }
        j.sort(Character::compare);
        o.sort(Character::compare);
        int jLen = j.size(), oLen = o.size();
        for (int i = 0; i < chars.length; i++) {
            if (((chars[i] - '0') & 1) == 1) {
                chars[i] = j.get(--jLen);
            } else {
                chars[i] = o.get(--oLen);
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
