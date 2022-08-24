package leetcode;

import java.util.*;

/**
 * @description: You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.
 * <p>
 * Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.
 * @author: LISHUAI
 * @createDate: 2022/7/15 23:59
 * @version: 1.0
 */

public class LeetCode_1727 {
    private static int[] cache;

    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}};
//        int[][] matrix = {{1, 0, 1, 0, 1}};
//        int[][] matrix = {{1, 1, 0}, {1, 0, 1}};
        int i = largestSubmatrix_03(matrix);
        System.out.println(i);
    }

    public static int largestSubmatrix(int[][] matrix) {
        int rLen = matrix.length;
        int ans = 0;
        for (int i = 0; i < rLen; i++) {
            ans = Math.max(ans, largestSubmatrixProcess(matrix, i));
        }
        return ans;
    }

    private static int largestSubmatrixProcess(int[][] matrix, int row) {
        int cLen = matrix[row].length;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < cLen; i++) {
            if (matrix[row][i] == 1) {
                queue.addLast(i);
            }
        }
        if (queue.isEmpty()) {
            return 0;
        }
        int c = 1, ans = queue.size();

        while (c + row < matrix.length && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int s = queue.removeFirst();
                if (matrix[row + c][s] == 1) {
                    queue.addLast(s);
                }
            }
            c++;
            ans = Math.max(ans, c * queue.size());
        }
        return ans;
    }


    public static int largestSubmatrix_02(int[][] matrix) {
        int rLen = matrix.length, cLen = matrix[0].length;
        List<Set<Integer>> list = new ArrayList<>();
        int[] sum = new int[rLen];
        int ans = 0;

        for (int[] curs : matrix) {
            Set<Integer> l = new HashSet<>();
            for (int i = 0; i < curs.length; i++) {
                if (curs[i] == 1) {
                    l.add(i);
                }
            }
            list.add(l);
        }

        for (int i = 0; i < rLen; i++) {
            Set<Integer> pre = list.get(i);
            sum[i] += pre.size();
            ans = Math.max(ans, sum[i]);
            for (int j = i + 1; j < rLen; j++) {
                Set<Integer> s = new HashSet<>();
                Set<Integer> set = list.get(j);
                for (int k : pre) {
                    if (set.contains(k)) {
                        set.remove(k);
                        s.add(k);
                    }
                }
                sum[j] += s.size();
                pre = s;
                ans = Math.max(ans, (j - i + 1) * sum[j]);
                if (pre.isEmpty()) {
                    break;
                }
            }
        }
        return ans;
    }


    public static int largestSubmatrix_03(int[][] matrix) {
        int rLen = matrix.length, cLen = matrix[0].length;
        int queueLen = (1 << (32 - Integer.numberOfLeadingZeros(cLen - 1))) - 1;
        int[] queue = new int[queueLen + 1];
        int[] sum = new int[rLen];
        int ans = 0, head = 0, tail = 0, queueSize = 0;

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (matrix[i][j] == 1) {
                    queue[tail++] = j;
                    tail &= queueLen;
                    queueSize++;
                }
            }
            sum[i] += queueSize;
            ans = Math.max(ans, sum[i]);
            int index = i;
            while (++index < rLen && queueSize != 0) {
                int size = queueSize;
                queueSize = 0;
                for (int j = 0; j < size; j++) {
                    int c = queue[head++];
                    head &= queueLen;
                    if (matrix[index][c] == 1) {
                        queue[tail++] = c;
                        tail &= queueLen;
                        queueSize++;
                        matrix[index][c] = 0;
                    }
                }
                sum[index] += queueSize;
                ans = Math.max(ans, (index - i + 1) * sum[index]);
            }
            tail = head;
            queueSize = 0;
        }
        return ans;
    }
}
