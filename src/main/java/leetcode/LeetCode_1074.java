package leetcode;

/**
 * @description: Given a matrix and a target, return the number of non-empty submatrices that sum to target.
 * <p>
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 * <p>
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
 * @author: LISHUAI
 * @createDate: 2022/7/18 19:12
 * @version: 1.0
 */

public class LeetCode_1074 {


    public static void main(String[] args) {
//        int[][] matrix = {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
//        int target = 0;
        int[][] matrix = {{1, -1}, {-1, 1}};
        int target = 0;

        int i = numSubmatrixSumTarget(matrix, target);
        System.out.println(i);
    }

    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rLen = matrix.length, cLen = matrix[0].length;
        int[][] totalSum = new int[rLen + 1][cLen];

        System.arraycopy(matrix[0], 0, totalSum[1], 0, cLen);
        for (int i = 0; i < cLen; i++) {
            for (int j = 1; j < rLen; j++) {
                totalSum[j + 1][i] = totalSum[j][i] + matrix[j][i];
            }
        }
        int ans = 0;

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                ans += numSubmatrixSumTargetProcess(matrix, totalSum, target, i, j);
            }
        }
        return ans;
    }

    private static int numSubmatrixSumTargetProcess(int[][] matrix, int[][] totalSum, int target, int row, int col) {
        int rLen = matrix.length, cLen = matrix[row].length;
        int ans = 0;

        for (int i = row; i < rLen; i++) {
            int sum = 0;
            for (int j = col; j < cLen; j++) {
                sum += totalSum[i + 1][j] - totalSum[row][j];
                if (sum == target) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
