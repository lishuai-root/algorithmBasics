package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given a string s, partition s such that every
 * substring
 * of the partition is a
 * palindrome
 * . Return all possible palindrome partitioning of s.
 * @author: LISHUAI
 * @createDate: 2022/12/9 5:00
 * @version: 1.0
 */

public class LeetCode_131 {

    public static List<List<String>> partition(String s) {
        int len = s.length();
        boolean[][] bls = new boolean[len + 1][len + 1];
        for (int i = 0; i < s.length(); i++) {
            maxPalindromes(s, i, i, bls);
            maxPalindromes(s, i, i + 1, bls);
        }
        List<List<String>> ans = new ArrayList<>();
        partitionProcess(s, 0, 0, ans, new ArrayList<>(), bls);
        return ans;
    }

    private static void partitionProcess(String s, int left, int right, List<List<String>> ans, List<String> list, boolean[][] bls) {
        if (right >= s.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = right; i < s.length(); i++) {
            if (bls[left][i]) {
                list.add(s.substring(left, i + 1));
                partitionProcess(s, i + 1, i + 1, ans, list, bls);
                list.remove(list.size() - 1);
            }
        }
    }

    private static int maxPalindromes(String s, int left, int right, boolean[][] bls) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            bls[left][right] = true;
            left--;
            right++;
        }
        return right - left - 1;
    }
}
