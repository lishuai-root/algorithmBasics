package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * <p>
 * You may return the answer in any order.
 * @author: LISHUAI
 * @createDate: 2023/2/8 19:29
 * @version: 1.0
 */

public class LeetCode_077 {

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>(k);
        process(ans, list, 1, k, n);
        return ans;
    }

    private static void process(List<List<Integer>> ans, List<Integer> list, int cur, int k, int n) {
        if (list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (k - list.size() > n - cur + 1) {
            return;
        }

        for (int i = cur; i <= n; i++) {
            list.add(i);
            process(ans, list, i + 1, k, n);
            list.remove(list.size() - 1);
        }
    }
}
