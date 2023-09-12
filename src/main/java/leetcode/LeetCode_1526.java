package leetcode;

/**
 * @description: You are given an integer array target. You have an integer array initial of the same size as target with all elements initially zeros.
 * <p>
 * In one operation you can choose any subarray from initial and increment each value by one.
 * <p>
 * Return the minimum number of operations to form a target array from initial.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * @author: LiShuai
 * @createDate: 2023/8/27 15:37
 * @version: 1.0
 */

public class LeetCode_1526 {

    public static int minNumberOperations(int[] target) {
        int len = target.length, index = -1, ans = 0;
        int[] stack = new int[len];

        for (int i = 0; i < len; i++) {
            int k = index == -1 ? target[i] : 0;
            while (index != -1 && target[stack[index]] <= target[i]) {
                k = Math.max(k, target[i] - target[stack[index--]]);
            }
            stack[++index] = i;
            ans += k;
        }
        return ans;
    }


    public static int minNumberOperations_02(int[] target) {
        int len = target.length, ans = target[0];

        for (int i = 1; i < len; i++) {
            if (target[i] > target[i - 1]) {
                ans += target[i] - target[i - 1];
            }
        }
        return ans;
    }
}
