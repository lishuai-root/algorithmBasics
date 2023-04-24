package leetcode;

/**
 * @description: You are given a 0-indexed 2D integer array brackets where brackets[i] = [upperi, percenti] means that the ith tax bracket has an upper bound of upperi and is taxed at a rate of percenti. The brackets are sorted by upper bound (i.e. upperi-1 < upperi for 0 < i < brackets.length).
 * <p>
 * Tax is calculated as follows:
 * <p>
 * The first upper0 dollars earned are taxed at a rate of percent0.
 * The next upper1 - upper0 dollars earned are taxed at a rate of percent1.
 * The next upper2 - upper1 dollars earned are taxed at a rate of percent2.
 * And so on.
 * You are given an integer income representing the amount of money you earned. Return the amount of money that you have to pay in taxes. Answers within 10-5 of the actual answer will be accepted.
 * @author: LISHUAI
 * @createDate: 2023/4/2 20:02
 * @version: 1.0
 */

public class LeetCode_2303 {

    public static double calculateTax(int[][] brackets, int income) {
        if (income == 0) {
            return 0;
        }
        int pre = 0;
        double ans = 0D;
        for (int[] curs : brackets) {
            if (curs[0] >= income) {
                ans += ((double) (income - pre)) * curs[1] / 100;
                break;
            } else {
                ans += ((double) (curs[0] - pre)) * curs[1] / 100;
            }
            pre = curs[0];
        }
        return ans;
    }
}
