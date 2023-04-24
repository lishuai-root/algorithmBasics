package leetcode;

/**
 * @description: You are given an array of n strings strs, all of the same length.
 * <p>
 * The strings can be arranged such that there is one on each line, making a grid. For example, strs = ["abc", "bce", "cae"] can be arranged as:
 * <p>
 * abc
 * bce
 * cae
 * You want to delete the columns that are not sorted lexicographically. In the above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted while column 1 ('b', 'c', 'a') is not, so you would delete column 1.
 * <p>
 * Return the number of columns that you will delete.
 * @author: LISHUAI
 * @createDate: 2023/1/3 18:03
 * @version: 1.0
 */

public class LeetCode_944 {

    public static int minDeletionSize(String[] strs) {
        int ssLen = strs.length, sLen = strs[ssLen - 1].length();
        int ans = 0;

        for (int i = 0; i < sLen; i++) {
            for (int j = 1; j < ssLen; j++) {
                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
