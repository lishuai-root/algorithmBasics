package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 * <p>
 * Note that division between two integers should truncate toward zero.
 * <p>
 * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.
 * @author: LISHUAI
 * @createDate: 2022/12/17 10:11
 * @version: 1.0
 */

public class LeetCode_150 {

    private static final Set<String> TEMP = new HashSet<String>(List.of(new String[]{"+", "-", "*", "/"}));

    public static int evalRPN(String[] tokens) {
        int len = tokens.length, index = -1;
        int[] stack = new int[len];

        for (String s : tokens) {
            if (TEMP.contains(s)) {
                int y = stack[index--];
                int x = stack[index--];
                if ("+".equals(s)) {
                    stack[++index] = x + y;
                } else if ("-".equals(s)) {
                    stack[++index] = x - y;
                } else if ("*".equals(s)) {
                    stack[++index] = x * y;
                } else {
                    stack[++index] = x / y;
                }
            } else {
                stack[++index] = Integer.parseInt(s);
            }
        }
        return stack[0];
    }
}
