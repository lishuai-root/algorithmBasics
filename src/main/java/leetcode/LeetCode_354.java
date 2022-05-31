package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 * <p>
 * One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.
 * <p>
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 * <p>
 * Note: You cannot rotate an envelope.
 * @author: LISHUAI
 * @createDate: 2022/5/25 21:16
 * @version: 1.0
 */

public class LeetCode_354 {

    public static void main(String[] args) {
//        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
//        int[][] envelopes = {{46, 89}, {50, 53}, {52, 68}, {72, 45}, {77, 81}};
//        int[][] envelopes = {{1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}};
//        int[][] envelopes = {{1, 1}, {1, 1}, {1, 1}};
        int[][] envelopes = {{2, 100}, {3, 200}, {4, 300}, {5, 500}, {5, 400}, {5, 250}, {6, 370}, {6, 360}, {7, 380}};
        int i = maxEnvelopes_dp_02(envelopes);
        System.out.println(i);
        int i1 = maxEnvelopes_dp(envelopes);
        System.out.println(i1);
    }

    public static int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
//        int[][] stack = new int[len][2];
        int[] curs = new int[]{0, 0};
        int index = -1, max = 0;
        Arrays.sort(envelopes, (a, b) -> {
            int c = a[0] - b[0];
            if (c == 0) {
                c = a[1] - b[1];
            }
            return c;
        });

//        for (int[] ints : envelopes) {
//            if (ints[0] > curs[0] && ints[1] > curs[1]) {
//                curs = ints;
//                max++;
//            }
//        }

//        for (int[] ints : envelopes) {
//            while (index != -1 && (stack[index][0] >= ints[0] || stack[index][1] >= ints[1])) {
//                index--;
//            }
//            stack[++index] = ints;
//            max = Math.max(max, index + 1);
//        }
//        return max;
        return maxEnvelopesProcess(envelopes, 0, -1);
    }

    public static int maxEnvelopesProcess(int[][] envelopes, int cur, int pre) {
        if (cur >= envelopes.length) {
            return 0;
        }

        int ans = 0;
        if (pre == -1 || (envelopes[cur][0] > envelopes[pre][0] && envelopes[cur][1] > envelopes[pre][1])) {
            ans = maxEnvelopesProcess(envelopes, cur + 1, cur) + 1;
        }
        ans = Math.max(ans, maxEnvelopesProcess(envelopes, cur + 1, pre));
        return ans;
    }

    public static int maxEnvelopes_02(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            int c = a[0] - b[0];
            if (c == 0) {
                c = a[1] - b[1];
            }
            return c;
        });
        int max = 0;

        for (int i = 0; i < envelopes.length; i++) {
            int[] curs = {0, 0};
            int sum = 0;
            for (int j = i; j < envelopes.length; j++) {
                if (envelopes[j][0] > curs[0] && envelopes[j][1] > curs[1]) {
                    sum++;
                    curs = envelopes[j];
                }
//                if (envelopes[j][0] < curs[0] || envelopes[j][1] < curs[1]) {
//                    break;
//                }
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    public static int maxEnvelopes_dp(int[][] envelopes) {
        int len = envelopes.length, ans = 1;
        int dp;
        int[] max = new int[len];
        max[len - 1] = 1;
        Arrays.sort(envelopes, (a, b) -> {
            int c = a[0] - b[0];
            if (c == 0) {
                c = a[1] - b[1];
            }
            return c;
        });

        for (int i = len - 2; i >= 0; i--) {
            dp = 0;

            for (int j = len - 1; j >= i; j--) {
                if (envelopes[j][0] > envelopes[i][0] && envelopes[j][1] > envelopes[i][1]) {
                    dp = Math.max(max[j], dp);
                }
            }
            max[i] = dp + 1;
            ans = Math.max(ans, dp + 1);
        }

        return ans;
    }

    public static int maxEnvelopes_dp_02(int[][] envelopes) {
        int len = envelopes.length, ans = 1;
        int dp;
        int[] max = new int[len + 1];
        max[len - 1] = 1;
        Arrays.sort(envelopes, (a, b) -> {
            int c = a[0] - b[0];
            if (c == 0) {
                c = a[1] - b[1];
            }
            return c;
        });


        for (int i = len - 2; i >= 0; i--) {

            int j = i + 1;
            while (j < len && (envelopes[i][0] >= envelopes[j][0] || envelopes[i][1] >= envelopes[j][1])) {
                j++;
            }

            max[i] = max[j] + 1;
            ans = Math.max(ans, max[i]);
        }
        for (int i : max) {
            System.out.print(i + " ");
        }
        System.out.println();
        return ans;
    }
}


//    This problem is asking for LIS in two dimensions, width and height. Sorting the width reduces the problem by one dimension. If width is strictly increasing,
//        the problem is equivalent to finding LIS in only the height dimension. However, when there is a tie in width, a strictly increasing sequence in height
//        may not be a correct solution. For example, [[3,3] cannot fit in [3,4]]. Sorting height in descending order when there is a tie prevents
//        such a sequence to be included in the solution.
//
//        The same idea can be applied to problems of higher dimensions. For example, box fitting is three dimensions, width, height, and length. Sorting width
//        ascending and height descending reduces the problem by one dimension. Finding the LIS by height further reduces the problem by another dimension.
//        When find LIS based on only length, it becomes a standard LIS problem.

// binary search solution: O(nlogn)
// width is increasing, but if two widths are the same, the height is decreasing
// after sorting, all envolopes are valid 'based on width', so we just binary search based on 'heights'
// to choose 'some of them' to meet the requirement
// Ex. after sorting: (1,3), (3,5), (6,8), (6,7), (8,4), (9,5)
// transform to question find LIS: [3,5,8,7,4,5] => like '300. Longest Increasing Subsequence'
class Solution_03 {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length < 2) return envelopes.length;

        Arrays.sort(envelopes, new EnvelopeComparator());
        int[] dp = new int[envelopes.length];
        int size = 0;

        for (int[] envelope : envelopes) {
            // binary search
            int left = 0, right = size, middle = 0;     // right = size
            while (left < right) {
                middle = left + (right - left) / 2;
                if (dp[middle] < envelope[1]) left = middle + 1;
                else right = middle;
            }

            // left is the right position to 'replace' in dp array
            dp[left] = envelope[1];
            if (left == size) size++;
        }
        return size;
    }

    class EnvelopeComparator implements Comparator<int[]> {
        public int compare(int[] e1, int[] e2) {
            return e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0];
        }
    }
}
//    This problem is technically the same as 300. Longest Increasing Subsequence, below is the solution for that problem.

// binary search: O(nlogn)
class Solution_02 {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int size = 0;

        for (int num : nums) {
            // binary search
            int left = 0, right = size, middle = 0;     // right = size
            while (left < right) {
                middle = left + (right - left) / 2;
                if (dp[middle] < num) left = middle + 1;
                else right = middle;
            }

            // left is the right position to 'replace' in dp array
            dp[left] = num;
            if (left == size) size++;
        }
        return size;
    }
}
