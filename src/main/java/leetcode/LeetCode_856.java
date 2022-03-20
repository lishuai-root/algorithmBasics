package leetcode;

/**
 * @description: Given a balanced parentheses string s, return the score of the string.
 * <p>
 * The score of a balanced parentheses string is based on the following rule:
 * <p>
 * "()" has score 1.
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 * <p>
 * s is a balanced parentheses string.
 * @author: LISHUAI
 * @createDate: 2022/3/17 17:31
 * @version: 1.0
 */

public class LeetCode_856 {

    public static void main(String[] args) {
        String s = "()";
        int i = scoreOfParentheses(s);
        System.out.println(i);
    }

    public static int scoreOfParentheses(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length, wIndex = -1, mIndex = -1;
        int[] wStack = new int[len], fStack = new int[len], mStack = new int[len];

        for (int i = 0; i < len; i++) {
            char c = chars[i];

            if (c == ')') {
                int cur = wStack[wIndex--];

                int max = 0;
                while (mIndex >= 0 && mStack[mIndex] > cur) {
                    max += fStack[mStack[mIndex--]];
                }
                fStack[cur] = Math.max(max << 1, 1);
                mStack[++mIndex] = cur;
                continue;
            }
            wStack[++wIndex] = i;
        }

        int ans = 0;
        while (mIndex >= 0) {
            ans += fStack[mStack[mIndex--]];
        }
        return ans;
    }
}
