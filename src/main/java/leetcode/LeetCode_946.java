package leetcode;

/**
 * @description: Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result of a sequence of push and pop operations on an initially empty stack, or false otherwise.
 * @author: LISHUAI
 * @createDate: 2023/4/13 22:15
 * @version: 1.0
 */

public class LeetCode_946 {

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        int[] stack = new int[len];
        int index = -1, popIndex = 0;

        for (int j : pushed) {
            stack[++index] = j;
            while (index >= 0 && popIndex < len && stack[index] == popped[popIndex]) {
                --index;
                ++popIndex;
            }
        }
        return index == -1;
    }
}
