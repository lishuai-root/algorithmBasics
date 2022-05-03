package leetcode;

/**
 * @description: Given an array arr of positive integers, consider all binary trees such that:
 * <p>
 * Each node has either 0 or 2 children;
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
 * The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree, respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.
 * It is guaranteed this sum fits into a 32-bit integer.
 * <p>
 * A node is a leaf if and only if it has zero children.
 * @author: LISHUAI
 * @createDate: 2022/1/9 16:46
 * @version: 1.0
 */

public class LeetCode_1130 {

    public static void main(String[] args) {

        int[] arr = {6, 2, 4};
//        int[] arr = {4, 11};

        int i = mctFromLeafValues(arr);

        System.out.println("---------");
        System.out.println(i);
    }

    public static int mctFromLeafValues(int[] arr) {

        return process(arr, 0, arr.length - 1);
    }


    private static int process(int[] arr, int start, int end) {

        if (start >= end) {
            return 0;
        }

        if (start == end) {
            return arr[start];
        }

        int left = start, right = start, ans = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            if (arr[left] < arr[i]) {
                left = i;
            }
        }
        right = start == left ? left + 1 : start;
        for (int i = start; i < end; i++) {

            if (i != left && arr[right] < arr[i]) {
                right = i;
            }
        }

        for (int i = Math.min(left, right); i < Math.max(left, right); i++) {
            ans = Math.min(ans, process(arr, start, i) + process(arr, i + 1, end));
        }

        return ans == Integer.MAX_VALUE ? arr[left] * arr[right] : ans + arr[left] * arr[right];
    }

}
