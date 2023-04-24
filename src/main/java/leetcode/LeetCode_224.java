package leetcode;

/**
 * @description: Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * <p>
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * @author: LISHUAI
 * @createDate: 2022/12/17 10:51
 * @version: 1.0
 */

public class LeetCode_224 {

    public static void main(String[] args) {
//        String s = "1 + 1";
//        String s = " 2-1 + 2 ";
//        String s = "(1+(4+5+2)-3)+(6+8)";
        String s = "2-4-(8+2-6+(8+4-(1)+8-10))";
        System.out.println(calculate(s));
    }

    public static int calculate(String s) {
        int len = s.length(), opIndex = -1, countIndex = 0;
        int[] numStack = new int[len];
        int[] opStack = new int[len];
        int[] countStack = new int[len];
        int left = 0, right = 0;

        while (right < len) {
            if (s.charAt(right) == ' ') {
                right++;
                continue;
            }
            char c = s.charAt(right++);
            if (c == '+') {
                opStack[++opIndex] = 1;
            } else if (c == '-') {
                if (countStack[countIndex] == 0) {
                    numStack[opIndex + 1] = 0;
                }
                opStack[++opIndex] = 2;
            } else if (c == '(') {
                opStack[++opIndex] = 3;
                countIndex++;
            } else if (c == ')') {
                int p = opIndex;
                while (opStack[p] != 3) {
                    p--;
                }
                int q = p;
                int curNum = numStack[p + 1];
                for (int i = p + 1; i <= opIndex; i++) {
                    curNum = computer(curNum, numStack[i + 1], opStack[i]);
                }
                opIndex = p - 1;
                numStack[opIndex + 1] = curNum;
                countStack[--countIndex]++;
            } else {
                left = right - 1;
                while (right < len && s.charAt(right) >= '0' && s.charAt(right) <= '9') {
                    right++;
                }
                int curNum = Integer.parseInt(s.substring(left, right));
                numStack[opIndex + 1] = curNum;
                countStack[countIndex]++;
            }
        }
        int ans = numStack[0];
        for (int i = 0; i <= opIndex; i++) {
            ans = computer(ans, numStack[i + 1], opStack[i]);
        }
        return ans;
    }

    private static int computer(int q, int p, int op) {
        if (op == 1) {
            return q + p;
        } else {
            return q - p;
        }
    }
}
