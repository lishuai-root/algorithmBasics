package leetcode;

import java.util.Arrays;

/**
 * @description: A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as shown in the figure above.
 * <p>
 * Given the array reservedSeats containing the numbers of seats already reserved, for example, reservedSeats[i] = [3,8] means the seat located in row 3 and labelled with 8 is already reserved.
 * <p>
 * Return the maximum number of four-person groups you can assign on the cinema seats.
 * A four-person group occupies four adjacent seats in one single row. Seats across an aisle (such as [3,3] and [3,4]) are not considered to be adjacent,
 * but there is an exceptional case on which an aisle split a four-person group, in that case, the aisle split a four-person group in the middle, which means to have two people on each side.
 * @author: LISHUAI
 * @createDate: 2022/12/12 8:29
 * @version: 1.0
 */

public class LeetCode_1386 {

    public static void main(String[] args) {
        int n = 3;
        int[][] reservedSeats = {{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}};
        int i = maxNumberOfFamilies(n, reservedSeats);
        System.out.println(i);
    }

    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Arrays.sort(reservedSeats, (a, b) -> {
            int c = a[0] - b[0];
            if (c == 0) {
                c = a[1] - b[1];
            }
            return c;
        });
        int ans = 0, colIndex = 0;

        for (int i = 1; i <= n; i++) {
            int cRow = (colIndex < reservedSeats.length ? reservedSeats[colIndex][0] : n + 1);
            if (cRow != i) {
                ans += ((cRow - i) * 2);
                i = cRow - 1;
                continue;
            }
            int col = 0;
            while (colIndex < reservedSeats.length && reservedSeats[colIndex][0] == i) {
                int c = reservedSeats[colIndex++][1];
                col = (col & 1) == 1 ? col + 1 : col + 2;
                ans += Math.max(0, ((c - col) / 4));
                col = c;
            }
            if (col < 6) {
                col = (col & 1) == 1 ? col + 1 : col + 2;
                ans += Math.max(0, ((11 - col) / 4));
            }
        }
        return ans;
    }


}
