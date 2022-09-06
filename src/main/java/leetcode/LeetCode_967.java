package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
 * <p>
 * Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.
 * <p>
 * You may return the answer in any order.
 * @author: LISHUAI
 * @createDate: 2022/9/3 10:15
 * @version: 1.0
 */

public class LeetCode_967 {

    public static void main(String[] args) {
        int n = 3, k = 7;
//        int n = 2, k = 1;
//        int n = 2, k = 0;
        int[] ints = numsSameConsecDiff(n, k);
        for (int i : ints) {
            System.out.println(i);
        }
        ClassLoader classLoader = LeetCode_967.class.getClassLoader();
        System.out.println(classLoader.toString());
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());

    }

    public static int[] numsSameConsecDiff(int n, int k) {
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) {
            numsSameConsecDiffProcess(ans, n - 1, k, i);
        }

        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        return result;
    }

    private static void numsSameConsecDiffProcess(List<Integer> ans, int n, int k, int cur) {
        if (n == 0) {
            ans.add(cur);
            return;
        }
        int c = cur % 10;
        int m = c - k;
        if (m >= 0) {
            numsSameConsecDiffProcess(ans, n - 1, k, cur * 10 + m);
        }
        int x = c + k;
        if (m != x && x <= 9) {
            numsSameConsecDiffProcess(ans, n - 1, k, cur * 10 + x);
        }
    }
}
