package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * <p>
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
 * @author: LISHUAI
 * @createDate: 2022/5/10 21:58
 * @version: 1.0
 */

public class LeetCode_216 {
    private static List<List<Integer>> ans;

    public static void main(String[] args) {
//        int k = 3, n = 7;
//        int k = 3, n = 9;
        int k = 3, n = 9;
        List<List<Integer>> lists = combinationSum3(k, n);
        for (List<Integer> l : lists) {
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSum3Process(k, n, 1, 0, list);
        return ans;
    }

    private static void combinationSum3Process(int k, int n, int cur, int sum, List<Integer> list) {
        if (list.size() == k && sum == n) {
            ans.add(new ArrayList<>(list));
            return;
        }
        if (cur > 9 || list.size() >= k || sum >= n) {
            return;
        }

        combinationSum3Process(k, n, cur + 1, sum, list);
        list.add(cur);
        combinationSum3Process(k, n, cur + 1, sum + cur, list);
        list.remove(list.size() - 1);
    }
}
