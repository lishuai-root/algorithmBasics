package leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @description: You are given a string s that contains digits 0-9, addition symbols '+', and multiplication symbols '*' only, representing a valid math expression of single digit numbers (e.g., 3+5*2). This expression was given to n elementary school students. The students were instructed to get the answer of the expression by following this order of operations:
 * <p>
 * Compute multiplication, reading from left to right; Then,
 * Compute addition, reading from left to right.
 * You are given an integer array answers of length n, which are the submitted answers of the students in no particular order. You are asked to grade the answers, by following these rules:
 * <p>
 * If an answer equals the correct answer of the expression, this student will be rewarded 5 points;
 * Otherwise, if the answer could be interpreted as if the student applied the operators in the wrong order but had correct arithmetic, this student will be rewarded 2 points;
 * Otherwise, this student will be rewarded 0 points.
 * Return the sum of the points of the students.
 * @author: LISHUAI
 * @createDate: 2022/12/11 8:41
 * @version: 1.0
 */

public class LeetCode_2019 {

    public static void main(String[] args) {
//        String s = "7+3*1*2";
//        int[] answers = {20, 13, 42};
        String s = "4+2*2+3*1+2";
        int[] answers = {42, 17, 13, 66, 32, 547, 21, 90, 13, 33, 45, 66, 34, 21, 13, 13, 13, 46, 21, 177, 18, 13, 18, 16, 16, 678, 13, 42, 66, 13, 18, 18, 777, 21, 924, 13, 268, 13, 13, 13, 25, 62, 45, 33, 888, 779, 13, 206, 48, 13, 34, 17};
        int i = scoreOfStudents(s, answers);
        System.out.println(i);
        System.out.println(scoreOfStudents_other(s, answers));
    }

    public static int scoreOfStudents(String s, int[] answers) {
        int len = s.length();
        int[] numStack = new int[len];
        int[] numOperation = new int[len];
        Set<Integer> set = new HashSet<Integer>();
        int index = 0, numIndex = 0, opeIndex = 0, pre = 0, ans = 0;
        while (index < s.length()) {
            while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                index++;
            }
            numStack[numIndex++] = Integer.parseInt(s.substring(pre, index));
            if (index < s.length()) {
                numOperation[opeIndex++] = (s.charAt(index++) == '+' ? 1 : 2);
            }
            pre = index;
        }
        int really = computeScore(numStack, numOperation, opeIndex);
        int[] nums = new int[len];
        nums[0] = numStack[0];
        scoreOfStudentsProcess(numStack, numOperation, opeIndex, nums, new int[len], 1, 0, 0, set);
        for (int i : answers) {
            if (i == really) {
                ans += 5;
            } else if (set.contains(i)) {
                ans += 2;
            }
        }
        return ans;
    }

    private static void scoreOfStudentsProcess(int[] numStack, int[] numOperation, int opeIndex,
                                               int[] num, int[] operation, int nIndex, int oIndex,
                                               int index, Set<Integer> set) {

        if (index >= opeIndex) {
            set.add(computeScore(num, operation, oIndex));
            return;
        }

        num[nIndex] = numStack[index + 1];
        operation[oIndex] = numOperation[index];
        scoreOfStudentsProcess(numStack, numOperation, opeIndex, num, operation, nIndex + 1, oIndex + 1, index + 1, set);
        if (numOperation[index] == 1) {
            num[nIndex - 1] += numStack[index + 1];
        } else {
            num[nIndex - 1] *= numStack[index + 1];
        }
        scoreOfStudentsProcess(numStack, numOperation, opeIndex, num, operation, nIndex, oIndex, index + 1, set);
    }


    private static int computeScore(int[] numStack, int[] numOperation, int opeIndex) {
        int[] nStack = new int[opeIndex + 1];
        int index = 0;
        nStack[index++] = numStack[0];
        for (int i = 0; i < opeIndex; i++) {
            if (numOperation[i] != 1) {
                nStack[index - 1] *= numStack[i + 1];
            } else {
                nStack[index++] = numStack[i + 1];
            }
        }
        int ans = 0;
        for (int i = 0; i < index; i++) {
            ans += nStack[i];
        }
        return ans;
    }

    public static int scoreOfStudents_other(String s, int[] answers) {
        int len = s.length();

        //cache all possible answer for s[i, j]
        Set<Integer>[][] dp = new Set[len][len];
        for (int i = 0; i < len; i += 2) {
            dp[i][i] = new HashSet<>();
            dp[i][i].add(s.charAt(i) - '0');
        }
        for (int k = 3; k <= len; k += 2) {
            for (int start = 0; start + k <= len; start += 2) {
                int end = start + k - 1;
                dp[start][end] = new HashSet<>();

                for (int op = start + 1; op < end; op += 2) {
                    for (int left : dp[start][op - 1]) {
                        for (int right : dp[op + 1][end]) {
                            int res = s.charAt(op) == '*' ? left * right : left + right;
                            if (res <= 1000) {
                                dp[start][end].add(res);
                            }
                        }
                    }
                }
            }
        }
        int correct = getAnswer(s);
        return getResult(answers, correct, dp[0][len - 1]);
    }

    private static int getAnswer(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                stack.push(stack.pop() * (s.charAt(++i) - '0'));
            } else if (c != '+') {
                stack.push(s.charAt(i) - '0');
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    private static int getResult(int[] answers, int correct, Set<Integer> incorrect) {
        int res = 0;
        for (int i = 0; i < answers.length; i++) {
            int ans = answers[i];
            if (ans == correct) {
                res += 5;
                System.out.println(ans + " : 5");
            } else if (incorrect.contains(ans)) {
                res += 2;
                System.out.println(ans + " : 2");
            }
        }
        return res;
    }
}
