package leetcode;

/**
 * @description: There are n friends that are playing a game.
 * The friends are sitting in a circle and are numbered from 1 to n in clockwise order.
 * More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n,
 * and moving clockwise from the nth friend brings you to the 1st friend.
 * <p>
 * The rules of the game are as follows:
 * <p>
 * Start at the 1st friend.
 * Count the next k friends in the clockwise direction including the friend you started at.
 * The counting wraps around the circle and may count some friends more than once.
 * The last friend you counted leaves the circle and loses the game.
 * If there is still more than one friend in the circle,
 * go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
 * Else, the last friend in the circle wins the game.
 * Given the number of friends, n, and an integer k, return the winner of the game.
 * @author: LISHUAI
 * @createDate: 2022/3/17 18:50
 * @version: 1.0
 */

public class LeetCode_1823 {

    public static void main(String[] args) {
        int n = 6, k = 5;
        int theWinner = findTheWinner(n, k);
        System.out.println(theWinner);
    }

    public static int findTheWinner(int n, int k) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = i;
        }

        int sum = -1, size = n;
        while (size > 1) {
            for (int j = 0; j < k; j++) {
                sum = (sum + 1) % n;
                while (ans[sum] != sum) {
                    sum = ans[sum];
                }
            }
            ans[sum] = ans[(sum + 1) % n];
            size--;
        }
        for (int i = 0; i < n; i++) {
            if (ans[i] == i) {
                sum = i + 1;
                break;
            }
        }
        return sum;
    }
}
