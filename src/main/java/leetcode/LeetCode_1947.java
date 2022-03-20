package leetcode;

/**
 * @description: There is a survey that consists of n questions where each question's answer is either 0 (no) or 1 (yes).
 * <p>
 * The survey was given to m students numbered from 0 to m - 1 and m mentors numbered from 0 to m - 1.
 * The answers of the students are represented by a 2D integer array students where students[i] is an integer array that contains the answers of the ith student (0-indexed).
 * The answers of the mentors are represented by a 2D integer array mentors where mentors[j] is an integer array that contains the answers of the jth mentor (0-indexed).
 * <p>
 * Each student will be assigned to one mentor, and each mentor will have one student assigned to them.
 * The compatibility score of a student-mentor pair is the number of answers that are the same for both the student and the mentor.
 * <p>
 * For example, if the student's answers were [1, 0, 1] and the mentor's answers were [0, 0, 1], then their compatibility score is 2 because only the second and the third answers are the same.
 * You are tasked with finding the optimal student-mentor pairings to maximize the sum of the compatibility scores.
 * <p>
 * Given students and mentors, return the maximum compatibility score sum that can be achieved.
 * @author: LISHUAI
 * @createDate: 2022/3/14 15:09
 * @version: 1.0
 */

public class LeetCode_1947 {
    private static int size;
    int[] sas;
    int[] mas;
    Integer[][] dp;
    int m, n;

    public static void main(String[] args) {
        int[][] students = {{1, 1, 0}, {1, 0, 1}, {0, 0, 1}}, mentors = {{1, 0, 0}, {0, 0, 1}, {1, 1, 0}};
        int i = maxCompatibilitySum(students, mentors);
        System.out.println(i);
        int i1 = maxCompatibilitySum_03(students, mentors);
        System.out.println(i1);
        System.out.println(Integer.bitCount(1));
        System.out.println(Integer.bitCount(3));
    }

    public static int maxCompatibilitySum(int[][] students, int[][] mentors) {
        boolean[] bl = new boolean[mentors.length];
        return maxCompatibilitySumProcess(students, mentors, 0, bl);
    }

    private static int maxCompatibilitySumProcess(int[][] students, int[][] mentors, int index, boolean[] bl) {
        if (index >= students.length) {
            return 0;
        }

        int ans = 0;

        for (int i = 0; i < mentors.length; i++) {
            if (!bl[i]) {
                bl[i] = true;
//                int tmp = max(students[index], mentors[i]);
                ans = Math.max(ans, maxCompatibilitySumProcess(students, mentors, index + 1, bl) + max(students[index], mentors[i]));
                bl[i] = false;
            }
        }
        return ans;
    }

    private static int max(int[] s, int[] m) {
        int i = 0, ans = 0;
        while (i < s.length && i < m.length) {
            if (s[i] == m[i]) {
                ans++;
            }
            i++;
        }
        return ans;
    }

    public static int maxCompatibilitySum_02(int[][] students, int[][] mentors) {

        boolean[] bl = new boolean[mentors.length];
        int index = 0, ans, sum = 0;

        for (int i = 0; i < students.length; i++) {
            ans = 0;
            for (int j = 0; j < mentors.length; j++) {
                if (!bl[j]) {
                    int tmp = max(students[i], mentors[j]);
                    if (tmp > ans) {
                        ans = tmp;
                        index = j;
                    }
                }
            }
            bl[index] = true;
            sum += ans;
        }

        return sum;
    }

    public static int maxCompatibilitySum_03(int[][] students, int[][] mentors) {
        int[] s = new int[students.length], m = new int[mentors.length];
        size = students[0].length;

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < size; j++) {
                s[i] |= students[i][j] << (size - j - 1);
                m[i] |= mentors[i][j] << (size - j - 1);
            }
        }

        return maxCompatibilitySumProcess_02(s, m, 0, 0);
    }

    private static int maxCompatibilitySumProcess_02(int[] s, int[] m, int index, int bit) {
        if (index >= s.length) {
            return 0;
        }

        int ans = 0;

        for (int i = 0; i < m.length; i++) {

            if ((bit & (1 << i)) == 0) {
                bit |= (1 << i);
                ans = Math.max(ans, maxCompatibilitySumProcess_02(s, m, index + 1, bit) + max_02(s[index], m[i]));
                bit ^= (1 << i);
            }
        }
        return ans;
    }

    private static int max_02(int s, int m) {
        return size - Integer.bitCount(s ^ m);
    }

    public int maxCompatibilitySum_other(int[][] students, int[][] mentors) {
        this.m = students.length;
        this.n = students[0].length;
        this.dp = new Integer[m][1 << m];
        this.sas = new int[m];
        this.mas = new int[m];

        for (int i = 0; i < m; i++) {
            int[] sa = students[i];
            int[] ma = mentors[i];
            for (int j = 0; j < n; j++) {
                sas[i] |= (sa[j] << j);
                mas[i] |= (ma[j] << j);
            }
        }

        return dfs(0, 0);
    }

    private int dfs(int idx, int state) {
        if (idx == m) {
            return 0;
        }

        if (dp[idx][state] != null) {
            return dp[idx][state];
        }

        int max = 0;
        for (int j = 0; j < m; j++) {
            if ((state & (1 << j)) != 0) {
                continue;
            }

            max = Math.max(max, getScore(idx, j) + dfs(idx + 1, state | (1 << j)));
        }

        dp[idx][state] = max;
        return max;
    }

    private int getScore(int i, int j) {
        int sa = sas[i], ma = mas[j];
        return n - Integer.bitCount(sa ^ ma);
    }
}
