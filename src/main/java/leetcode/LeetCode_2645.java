package leetcode;

/**
 * @description: Given a string word to which you can insert letters "a", "b" or "c" anywhere and any number of times, return the minimum number of letters that must be inserted so that word becomes valid.
 * <p>
 * A string is called valid if it can be formed by concatenating the string "abc" several times.
 * @author: LiShuai
 * @createDate: 2023/8/28 20:33
 * @version: 1.0
 */

public class LeetCode_2645 {

    public static int addMinimum(String word) {
        int len = word.length(), ans = 0, index = 0;
        for (int i = 0; i < len; i++) {
            if (word.charAt(i) != ((char) (index + 'a'))) {
                ++ans;
                --i;
            }
            index = (++index % 3);
        }
        return ans + (3 - (index == 0 ? 3 : index));
    }
}
