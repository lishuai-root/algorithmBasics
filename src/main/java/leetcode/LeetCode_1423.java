package leetcode;

/**
 * @description: There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.
 * <p>
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * <p>
 * Your score is the sum of the points of the cards you have taken.
 * <p>
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 * @author: LISHUAI
 * @createDate: 2022/6/26 22:14
 * @version: 1.0
 */

public class LeetCode_1423 {

    public static void main(String[] args) {
//        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
//        int k = 3;
        int[] cardPoints = {53, 14, 91, 35, 51, 9, 80, 27, 6, 15, 77, 86, 34, 62, 55, 45, 91, 45, 23, 75, 66, 42, 62, 13, 34, 18, 89, 67, 93, 83, 100, 14, 92, 73, 48, 2, 47, 93, 99, 100, 88, 84, 48};
        int k = 43;
        int i = maxScore_02(cardPoints, k);
        System.out.println(i);
    }

    public static int maxScore(int[] cardPoints, int k) {
        return maxScoreProcess(cardPoints, k, 0, cardPoints.length - 1);
    }


    private static int maxScoreProcess(int[] cardPoints, int k, int left, int right) {
        if (k == 0 || left > right) {
            return 0;
        }

        int p1 = maxScoreProcess(cardPoints, k - 1, left + 1, right) + cardPoints[left];
        int p2 = maxScoreProcess(cardPoints, k - 1, left, right - 1) + cardPoints[right];
        return Math.max(p1, p2);
    }

    public static int maxScore_02(int[] cardPoints, int k) {
        int sum = 0, len = cardPoints.length, ans;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[len - i - 1];
        }
        if (k == len) {
            return sum;
        }
        ans = sum;
        k = Math.min(k, len - k);
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
            sum -= cardPoints[len - k + i];
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
