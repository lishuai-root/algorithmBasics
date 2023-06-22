package leetcode;

/**
 * @description: Given a square matrix mat, return the sum of the matrix diagonals.
 * <p>
 * Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.
 * @author: LISHUAI
 * @createDate: 2023/5/8 21:45
 * @version: 1.0
 */

public class LeetCode_1572 {

    public static int diagonalSum(int[][] mat) {
        int len = mat.length, left = 0, right = mat.length - 1;
        int ans = 0;

        for (int[] ints : mat) {
            if (right != left) {
                ans += ints[right];
            }
            right--;
            ans += ints[left++];
        }
        return ans;
    }
}
