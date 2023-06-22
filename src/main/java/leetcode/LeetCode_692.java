package leetcode;

import java.util.*;

/**
 * @description: Given an array of strings words and an integer k, return the k most frequent strings.
 * <p>
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 * @author: LISHUAI
 * @createDate: 2023/5/22 22:23
 * @version: 1.0
 */

public class LeetCode_692 {

    public static void main(String[] args) {
//        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
//        int k = 2;
//        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
//        int k = 4;
        String[] words = {"plpaboutit", "jnoqzdute", "sfvkdqf", "mjc", "nkpllqzjzp", "foqqenbey", "ssnanizsav", "nkpllqzjzp", "sfvkdqf", "isnjmy", "pnqsz", "hhqpvvt", "fvvdtpnzx", "jkqonvenhx", "cyxwlef", "hhqpvvt", "fvvdtpnzx", "plpaboutit", "sfvkdqf", "mjc", "fvvdtpnzx", "bwumsj", "foqqenbey", "isnjmy", "nkpllqzjzp", "hhqpvvt", "foqqenbey", "fvvdtpnzx", "bwumsj", "hhqpvvt", "fvvdtpnzx", "jkqonvenhx", "jnoqzdute", "foqqenbey", "jnoqzdute", "foqqenbey", "hhqpvvt", "ssnanizsav", "mjc", "foqqenbey", "bwumsj", "ssnanizsav", "fvvdtpnzx", "nkpllqzjzp", "jkqonvenhx", "hhqpvvt", "mjc", "isnjmy", "bwumsj", "pnqsz", "hhqpvvt", "nkpllqzjzp", "jnoqzdute", "pnqsz", "nkpllqzjzp", "jnoqzdute", "foqqenbey", "nkpllqzjzp", "hhqpvvt", "fvvdtpnzx", "plpaboutit", "jnoqzdute", "sfvkdqf", "fvvdtpnzx", "jkqonvenhx", "jnoqzdute", "nkpllqzjzp", "jnoqzdute", "fvvdtpnzx", "jkqonvenhx", "hhqpvvt", "isnjmy", "jkqonvenhx", "ssnanizsav", "jnoqzdute", "jkqonvenhx", "fvvdtpnzx", "hhqpvvt", "bwumsj", "nkpllqzjzp", "bwumsj", "jkqonvenhx", "jnoqzdute", "pnqsz", "foqqenbey", "sfvkdqf", "sfvkdqf"};
        int k = 1;
        List<String> list = topKFrequent(words, k);
    }

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Queue<Info> queue = new PriorityQueue<>((a, b) -> {
            String as = a.key;
            String bs = b.key;
            int c = b.value - a.value;
            if (c != 0) {
                return c;
            }
            c = Math.min(as.length(), bs.length());
            for (int i = 0; i < c; i++) {
                if (as.charAt(i) != bs.charAt(i)) {
                    return as.charAt(i) - bs.charAt(i);
                }
            }
            return as.length() - bs.length();
        });
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
            queue.offer(new Info(word, map.get(word)));
        }
        List<String> ans = new ArrayList<>(k);
        Set<String> set = new HashSet<>();
        for (int i = 0; i < k; i++) {
            Info info = queue.poll();
            if (!set.contains(info.key)) {
                ans.add(info.key);
                set.add(info.key);
            } else {
                i--;
            }
        }
        return ans;
    }

    static class Info {
        String key;
        int value;

        public Info(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
