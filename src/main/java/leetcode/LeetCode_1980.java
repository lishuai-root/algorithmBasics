package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: Given an array of strings nums containing n unique binary strings each of length n,
 * return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.
 * @author: LISHUAI
 * @createDate: 2022/5/28 22:57
 * @version: 1.0
 */

public class LeetCode_1980 {

    public static void main(String[] args) {
        String[] nums = {"111", "011", "001"};
        String differentBinaryString_02 = findDifferentBinaryString_02(nums);
        System.out.println(differentBinaryString_02);
    }

    public static String findDifferentBinaryString(String[] nums) {
        Set<Integer> set = new HashSet<>();

        for (String num : nums) {
            char[] chars = num.toCharArray();
            int len = chars.length;
            int cur = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '1') {
                    cur += (1 << (len - 1 - i));
                }
            }
            set.add(cur);
        }
        int ans = 0;
        while (set.contains(ans)) {
            ans++;
        }

        String s = Integer.toBinaryString(ans);
        int skip = nums.length - s.length();
        s = "0".repeat(skip) + s;
        return s;
    }


    public static String findDifferentBinaryString_02(String[] nums) {
        PreNode root = makePreTree(nums);
        char[] chars = new char[nums.length];
        return findDifferentBinaryStringProcess(root, chars, 0);
    }

    public static String findDifferentBinaryStringProcess(PreNode node, char[] chars, int index) {
        if (index >= chars.length) {
            return String.valueOf(chars);
        }
        if (node.nodes[0] == null) {
            for (int i = index; i < chars.length; i++) {
                chars[i] = '0';
            }
            return String.valueOf(chars);
        }
        if (node.nodes[1] == null) {
            for (int i = index; i < chars.length; i++) {
                chars[i] = '1';
            }
            return String.valueOf(chars);
        }

        String ans = null;
        chars[index] = '0';
        ans = findDifferentBinaryStringProcess(node.nodes[0], chars, index + 1);
        if (ans == null) {
            chars[index] = '1';
            ans = findDifferentBinaryStringProcess(node.nodes[1], chars, index + 1);
        }
        return ans;
    }


    private static PreNode makePreTree(String[] nums) {
        PreNode root = new PreNode();

        for (String s : nums) {
            char[] chars = s.toCharArray();
            PreNode node = root;
            for (char c : chars) {
                if (node.nodes[c - '0'] == null) {
                    node.nodes[c - '0'] = new PreNode();
                }
                node = node.nodes[c - '0'];
            }
            node.isEnd = true;
        }
        return root;
    }

    static class PreNode {
        PreNode[] nodes;
        boolean isEnd;

        public PreNode() {
            nodes = new PreNode[2];
            isEnd = false;
        }
    }
}
