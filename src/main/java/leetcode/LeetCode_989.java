package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: The array-form of an integer num is an array representing its digits in left to right order.
 * <p>
 * For example, for num = 1321, the array form is [1,3,2,1].
 * Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.
 * @author: LISHUAI
 * @createDate: 2022/7/14 22:04
 * @version: 1.0
 */

public class LeetCode_989 {

    public static List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();
        int pre = 0, index = num.length - 1;

        while (index >= 0 || k != 0) {
            if (k != 0) {
                pre += (k % 10);
                k /= 10;
            }
            if (index >= 0) {
                pre += num[index--];
            }
            ans.add(pre % 10);
            pre /= 10;
        }

        if (pre != 0) {
            ans.add(pre);
        }
        List<Integer> result = new ArrayList<>(ans.size());
        int size = ans.size();
        for (int i = 0; i < size; i++) {
            result.add(ans.get(size - i - 1));
        }
        return result;
    }
}
