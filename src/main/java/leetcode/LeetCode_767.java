package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * <p>
 * Return any possible rearrangement of s or return "" if not possible.
 * @author: LiShuai
 * @createDate: 2023/8/23 21:47
 * @version: 1.0
 */

public class LeetCode_767 {

    public static void main(String[] args) {
        String s = "bfrbs";
        String s1 = reorganizeString(s);
        System.out.println(s1);
    }

    public static String reorganizeString(String s) {
        int len = s.length(), index = 0;
        char[] chars = new char[len];
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            ++count[s.charAt(i) - 'a'];
        }
        Queue<Integer> queue = new PriorityQueue<>((a, b) -> count[b] - count[a]);
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cs = queue.poll();
            if (index > 0 && chars[index - 1] - 'a' == cs) {
                if (queue.isEmpty()) {
                    return "";
                }
                int ncs = queue.poll();
                queue.offer(cs);
                cs = ncs;
            }
            chars[index++] = (char) (cs + 'a');
            --count[cs];
            if (count[cs] != 0) {
                queue.offer(cs);
            }
        }
        return String.valueOf(chars);
    }
}
