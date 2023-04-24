package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 * <p>
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 * @author: LISHUAI
 * @createDate: 2022/12/9 5:47
 * @version: 1.0
 */

public class LeetCode_386 {

    public static void main(String[] args) {
        int n = 100;
        List<Integer> integers = lexicalOrder(n);
        for (int i : integers) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= Math.min(9, n); i++) {
            list.add(i);
            lexicalOrderProcess(n, list);
        }
        return list;
    }

    private static void lexicalOrderProcess(int n, List<Integer> list) {

        int c = list.get(list.size() - 1);
        for (int i = 0; i < 10; i++) {
            if ((c * 10 + i) > n) {
                break;
            }
            list.add(c * 10 + i);
            lexicalOrderProcess(n, list);
        }
    }
}
