package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 * @author: LiShuai
 * @createDate: 2023/9/8 21:57
 * @version: 1.0
 */

public class LeetCode_119 {

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            int p = 1;
            for (int j = 1; j < i; j++) {
                int q = list.get(j);
                list.set(j, p + q);
                p = q;

            }
            list.add(1);
        }
        return list;
    }
}
