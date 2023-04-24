package leetcode;

/**
 * @description: You are given a binary string s. In one second, all occurrences of "01" are simultaneously replaced with "10". This process repeats until no occurrences of "01" exist.
 * <p>
 * Return the number of seconds needed to complete this process.
 * @author: LISHUAI
 * @createDate: 2022/12/12 16:41
 * @version: 1.0
 */

public class LeetCode_2380 {

    public static int secondsToRemoveOccurrences(String s) {
        char[] chars = s.toCharArray();
        int ans = 0;
        while (true) {
            int size = 0;
            for (int i = chars.length - 1; i > 0; i--) {
                if (chars[i] == '1' && chars[i - 1] == '0') {
                    chars[i] = '0';
                    chars[i - 1] = '1';
                    --i;
                    size++;
                }
            }
            if (size == 0) {
                break;
            }
            ans++;
        }
        return ans;
    }
}
