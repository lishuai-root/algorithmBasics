package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: Given an integer numRows, return the first numRows of Pascal's triangle.
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 * @author: LISHUAI
 * @createDate: 2022/7/19 19:08
 * @version: 1.0
 */

public class LeetCode_118 {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        ans.add(list);
        List<Integer> pre = list;
        for (int i = 1; i < numRows; i++) {
            list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i; j++) {
                list.add(pre.get(j) + pre.get(j - 1));
            }
            list.add(1);
            ans.add(list);
            pre = list;
        }
        return ans;
    }
}
