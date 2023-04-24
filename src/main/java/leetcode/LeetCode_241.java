package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.
 * <p>
 * The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.
 * @author: LISHUAI
 * @createDate: 2023/1/1 17:57
 * @version: 1.0
 */

public class LeetCode_241 {

    private static final Map<Character, Integer> TEMP;

    static {
        TEMP = new HashMap<>(4);
        TEMP.put('+', 1);
        TEMP.put('-', 2);
        TEMP.put('*', 3);
    }

    public static void main(String[] args) {
        String expression = "2*3-4*5";
//        System.out.println(expression.length());
        List<Integer> list = diffWaysToCompute(expression);
        for (int i : list) {
            System.out.println(i);
        }
    }

    public static List<Integer> diffWaysToCompute(String expression) {
        int len = expression.length();
        int left = 0, right = 0;
        List<Integer> numStack = new ArrayList<>();
        List<Integer> expStack = new ArrayList<>();

        while (right < len) {
            char c = expression.charAt(right);
            if (c < '0' || c > '9') {
                numStack.add(Integer.parseInt(expression.substring(left, right)));
                expStack.add(TEMP.get(c));
                left = right + 1;
            }
            right++;
        }
        numStack.add(Integer.parseInt(expression.substring(left, right)));
        int[] ansNumStack = new int[numStack.size()];
        int[] ansExpStack = new int[expStack.size()];
        List<Integer> list = new ArrayList<>();
        diffWaysToComputeProcess(numStack, 0, expStack, 0, ansNumStack, 0, ansExpStack, 0, list);
        return list;
    }

    private static void diffWaysToComputeProcess(List<Integer> numStack, int numIndex,
                                                 List<Integer> expStack, int expIndex,
                                                 int[] ansNumStack, int ansNumIndex,
                                                 int[] ansExpStack, int ansExpIndex,
                                                 List<Integer> list) {

        if (numIndex >= numStack.size()) {
            int ans = ansNumStack[0];
            for (int i = 0; i < ansExpIndex; i++) {
                ans = compute(ans, ansNumStack[i + 1], ansExpStack[i]);
            }
            list.add(ans);
            return;
        }


        if (numIndex > 0) {
            ansExpStack[ansExpIndex++] = expStack.get(numIndex - 1);
        }
        ansNumStack[ansNumIndex++] = numStack.get(numIndex++);

        diffWaysToComputeProcess(numStack, numIndex, expStack, expIndex, ansNumStack, ansNumIndex, ansExpStack, ansExpIndex, list);
        if (ansNumIndex > 1) {
            ansNumStack[ansNumIndex - 2] = compute(ansNumStack[ansNumIndex - 2], ansNumStack[ansNumIndex - 1], ansExpStack[--ansExpIndex]);
            diffWaysToComputeProcess(numStack, numIndex, expStack, expIndex, ansNumStack, ansNumIndex - 1, ansExpStack, ansExpIndex, list);
        }
    }


    private static int compute(int ans, int num, int e) {
        if (e == 1) {
            ans += num;
        } else if (e == 2) {
            ans -= num;
        } else {
            ans *= num;
        }
        return ans;
    }

}
