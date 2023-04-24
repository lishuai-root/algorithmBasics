package leetcode;

/**
 * @description: Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.
 * <p>
 * For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.
 * <p>
 * Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.
 * @author: LISHUAI
 * @createDate: 2023/1/11 23:30
 * @version: 1.0
 */

public class LeetCode_1444 {

    private static final int TEMP = 1000000007;
    private static Integer[][][] cache;

    public static void main(String[] args) {
//        String[] pizza = {"A..", "AAA", "..."};
//        int k = 3;
        String[] pizza = {"..A.A.AAA...AAAAAA.AA..A..A.A......A.AAA.AAAAAA.AA", "A.AA.A.....AA..AA.AA.A....AAA.A........AAAAA.A.AA.", "A..AA.AAA..AAAAAAAA..AA...A..A...A..AAA...AAAA..AA", "....A.A.AA.AA.AA...A.AA.AAA...A....AA.......A..AA.", "AAA....AA.A.A.AAA...A..A....A..AAAA...A.A.A.AAAA..", "....AA..A.AA..A.A...A.A..AAAA..AAAA.A.AA..AAA...AA", "A..A.AA.AA.A.A.AA..A.A..A.A.AAA....AAAAA.A.AA..A.A", ".AA.A...AAAAA.A..A....A...A.AAAA.AA..A.AA.AAAA.AA.", "A.AA.AAAA.....AA..AAA..AAAAAAA...AA.A..A.AAAAA.A..", "A.A...A.A...A..A...A.AAAA.A..A....A..AA.AAA.AA.AA.", ".A.A.A....AAA..AAA...A.AA..AAAAAAA.....AA....A....", "..AAAAAA..A..A...AA.A..A.AA......A.AA....A.A.AAAA.", "...A.AA.AAA.AA....A..AAAA...A..AAA.AAAA.A.....AA.A", "A.AAAAA..A...AAAAAAAA.AAA.....A.AAA.AA.A..A.A.A...", "A.A.AA...A.A.AA...A.AA.AA....AA...AA.A..A.AA....AA", "AA.A..A.AA..AAAAA...A..AAAAA.AA..AA.AA.A..AAAAA..A", "...AA....AAAA.A...AA....AAAAA.A.AAAA.A.AA..AA..AAA", "..AAAA..AA..A.AA.A.A.AA...A...AAAAAAA..A.AAA..AA.A", "AA....AA....AA.A......AAA...A...A.AA.A.AA.A.A.AA.A", "A.AAAA..AA..A..AAA.AAA.A....AAA.....A..A.AA.A.A...", "..AA...AAAAA.A.A......AA...A..AAA.AA..A.A.A.AA..A.", ".......AA..AA.AAA.A....A...A.AA..A.A..AAAAAAA.AA.A", ".A.AAA.AA..A.A.A.A.A.AA...AAAA.A.A.AA..A...A.AAA..", "A..AAAAA.A..A..A.A..AA..A...AAA.AA.A.A.AAA..A.AA..", "A.AAA.A.AAAAA....AA..A.AAA.A..AA...AA..A.A.A.AA.AA", ".A..AAAA.A.A.A.A.......AAAA.AA...AA..AAA..A...A.AA", "A.A.A.A..A...AA..A.AAA..AAAAA.AA.A.A.A..AA.A.A....", "A..A..A.A.AA.A....A...A......A.AA.AAA..A.AA...AA..", ".....A..A...A.A...A..A.AA.A...AA..AAA...AA..A.AAA.", "A...AA..A..AA.A.A.AAA..AA..AAA...AAA..AAA.AAAAA...", "AA...AAA.AAA...AAAA..A...A..A...AA...A..AA.A...A..", "A.AA..AAAA.AA.AAA.A.AA.A..AAAAA.A...A.A...A.AA....", "A.......AA....AA..AAA.AAAAAAA.A.AA..A.A.AA....AA..", ".A.A...AA..AA...AA.AAAA.....A..A..A.AA.A.AA...A.AA", "..AA.AA.AA..A...AA.AA.AAAAAA.....A.AA..AA......A..", "AAA..AA...A....A....AA.AA.AA.A.A.A..AA.AA..AAA.AAA", "..AAA.AAA.A.AA.....AAA.A.AA.AAAAA..AA..AA.........", ".AA..A......A.A.AAA.AAAA...A.AAAA...AAA.AAAA.....A", "AAAAAAA.AA..A....AAAA.A..AA.A....AA.A...A.A....A..", ".A.A.AA..A.AA.....A.A...A.A..A...AAA..A..AA..A.AAA", "AAAA....A...A.AA..AAA..A.AAA..AA.........AA.AAA.A.", "......AAAA..A.AAA.A..AAA...AAAAA...A.AA..A.A.AA.A.", "AA......A.AAAAAAAA..A.AAA...A.A....A.AAA.AA.A.AAA.", ".A.A....A.AAA..A..AA........A.AAAA.AAA.AA....A..AA", ".AA.A...AA.AAA.A....A.A...A........A.AAA......A...", "..AAA....A.A...A.AA..AAA.AAAAA....AAAAA..AA.AAAA..", "..A.AAA.AA..A.AA.A...A.AA....AAA.A.....AAA...A...A", ".AA.AA...A....A.AA.A..A..AAA.A.A.AA.......A.A...A.", "...A...A.AA.A..AAAAA...AA..A.A..AAA.AA...AA...A.A.", "..AAA..A.A..A..A..AA..AA...A..AA.AAAAA.A....A..A.A"};
        int k = 8;
//        int ways = ways(pizza, k);
//        System.out.println(ways);
        System.out.println(ways_02(pizza, k));
    }

    public static int ways(String[] pizza, int k) {
        int rowLen = pizza.length;
        int colLen = pizza[rowLen - 1].length();
        int total = 0;
        for (String s : pizza) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'A') {
                    total++;
                }
            }
        }
        return waysProcess(pizza, rowLen, colLen, 0, 0, total, k - 1);
    }

    private static int waysProcess(String[] pizza, int rowLen, int colLen, int row, int col, int total, int k) {
        if (k == 0 && total > 0) {
            return 1;
        }
        if (total == 0) {
            return 0;
        }
        int ans = 0;
        int p = 0;
        for (int i = row; i < rowLen; i++) {
            String s = pizza[i];
            for (int j = col; j < colLen; j++) {
                if (s.charAt(j) == 'A') {
                    p++;
                }
            }
            if (p != 0) {
                int q = waysProcess(pizza, rowLen, colLen, i + 1, col, total - p, k - 1) % TEMP;
                ans += q;
                ans %= TEMP;
            }
        }
        p = 0;
        for (int i = col; i < colLen; i++) {
            for (int j = row; j < rowLen; j++) {
                String s = pizza[j];
                if (s.charAt(i) == 'A') {
                    p++;
                }
            }
            if (p != 0) {
                int q = waysProcess(pizza, rowLen, colLen, row, i + 1, total - p, k - 1) % TEMP;
                ans += q;
                ans %= TEMP;
            }
        }
        return ans;
    }


    public static int ways_02(String[] pizza, int k) {
        int rowLen = pizza.length;
        int colLen = pizza[rowLen - 1].length();
        int[][] sum = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            String s = pizza[i];
            for (int j = 0; j < colLen; j++) {
                int p = (j > 0 ? sum[i][j - 1] : 0);
                if (s.charAt(j) == 'A') {
                    p++;
                }
                sum[i][j] = p;
            }
        }

        for (int i = 0; i < colLen; i++) {
            for (int j = 1; j < rowLen; j++) {
                sum[j][i] += sum[j - 1][i];
            }
        }
        cache = new Integer[rowLen][colLen][k];
        return waysProcess(sum, rowLen, colLen, 0, 0, k - 1);
    }

    private static int waysProcess(int[][] sum, int rowLen, int colLen, int row, int col, int k) {
        if (row >= rowLen || col >= colLen) {
            return 0;
        }
        if (cache[row][col][k] != null) {
            return cache[row][col][k];
        }
        int t = computeSize(sum, row, col, rowLen - 1, colLen - 1);
        if (k == 0 && t > 0) {
            return 1;
        }
        if (t == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = row; i < rowLen; i++) {
            int p = computeSize(sum, row, col, i, colLen - 1);
            if (p != 0) {
                int q = waysProcess(sum, rowLen, colLen, i + 1, col, k - 1) % TEMP;
                ans += q;
                ans %= TEMP;
            }
        }

        for (int i = col; i < colLen; i++) {
            int p = computeSize(sum, row, col, rowLen - 1, i);
            if (p != 0) {
                int q = waysProcess(sum, rowLen, colLen, row, i + 1, k - 1) % TEMP;
                ans += q;
                ans %= TEMP;
            }
        }
        cache[row][col][k] = ans;
        return ans;
    }

    private static int computeSize(int[][] sum, int preR, int preC, int curR, int curC) {
        if (preR == 0 && preC == 0) {
            return sum[curR][curC];
        }
        int p = (preR > 0 ? sum[preR - 1][curC] : 0);
        int q = (preC > 0 ? sum[curR][preC - 1] : 0);
        int k = (preR > 0 && preC > 0 ? sum[preR - 1][preC - 1] : 0);
        return sum[curR][curC] - p - q + k;
    }
}
