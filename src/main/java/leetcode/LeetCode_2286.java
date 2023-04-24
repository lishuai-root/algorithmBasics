package leetcode;

/**
 * @description: A concert hall has n rows numbered from 0 to n - 1, each with m seats, numbered from 0 to m - 1. You need to design a ticketing system that can allocate seats in the following cases:
 * <p>
 * If a group of k spectators can sit together in a row.
 * If every member of a group of k spectators can get a seat. They may or may not sit together.
 * Note that the spectators are very picky. Hence:
 * <p>
 * They will book seats only if each member of their group can get a seat with row number less than or equal to maxRow. maxRow can vary from group to group.
 * In case there are multiple rows to choose from, the row with the smallest number is chosen. If there are multiple seats to choose in the same row, the seat with the smallest number is chosen.
 * Implement the BookMyShow class:
 * <p>
 * BookMyShow(int n, int m) Initializes the object with n as number of rows and m as number of seats per row.
 * int[] gather(int k, int maxRow) Returns an array of length 2 denoting the row and seat number (respectively) of the first seat being allocated to the k members of the group, who must sit together. In other words, it returns the smallest possible r and c such that all [c, c + k - 1] seats are valid and empty in row r, and r <= maxRow. Returns [] in case it is not possible to allocate seats to the group.
 * boolean scatter(int k, int maxRow) Returns true if all k members of the group can be allocated seats in rows 0 to maxRow, who may or may not sit together. If the seats can be allocated, it allocates k seats to the group with the smallest row numbers, and the smallest possible seat numbers in each row. Otherwise, returns false.
 * @author: LISHUAI
 * @createDate: 2022/12/11 20:49
 * @version: 1.0
 */

public class LeetCode_2286 {

    public static void main(String[] args) {
        int n = 2, m = 5;
        BookMyShow bookMyShow = new BookMyShow(n, m);
        int[] gather = bookMyShow.gather(4, 0);
        for (int i : gather) {
            System.out.print(i + " ");
        }
        System.out.println();
        gather = bookMyShow.gather(2, 0);
        for (int i : gather) {
            System.out.print(i + " ");
        }
        System.out.println();
        boolean scatter = bookMyShow.scatter(5, 1);
        System.out.println(scatter);
        scatter = bookMyShow.scatter(5, 1);
        System.out.println(scatter);
    }

    static class BookMyShow {
        private final int[] NOT_SHOW = new int[]{};
        private final int[] rows;
        private final int cols;
        private int showIndex;
        private long sum;

        public BookMyShow(int n, int m) {
            rows = new int[n];
            cols = m;
            showIndex = 0;
            sum = (long) n * m;
        }

        public int[] gather(int k, int maxRow) {
            if (k > cols || sum < k) {
                return NOT_SHOW;
            }
            for (int i = showIndex; i <= maxRow; i++) {
                if (rows[i] + k <= cols) {
                    int c = rows[i];
                    rows[i] += k;
                    if (i == showIndex && rows[i] == cols) {
                        showIndex = i + 1;
                    }
                    sum -= k;
                    return new int[]{i, c};
                }
            }
            return NOT_SHOW;
        }

        public boolean scatter(int k, int maxRow) {
            if (sum < k) {
                return false;
            }
            int q = k;
            for (int i = showIndex; i <= maxRow; i++) {
                int c = cols - rows[i];
                if (c >= q) {
                    rows[i] += q;
                    showIndex = (c > q ? i : i + 1);
                    sum -= k;
                    return true;
                }
                q -= c;
            }
            return false;
        }
    }

}
