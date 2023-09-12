package leetcode;

/**
 * @description: Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b​​​​​​ to be a substring of a after repeating it, return -1.
 * <p>
 * Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and repeated 2 times is "abcabc".
 * @author: LiShuai
 * @createDate: 2023/8/21 22:22
 * @version: 1.0
 */

public class LeetCode_686 {

    public static int repeatedStringMatch(String a, String b) {
        int aLen = a.length(), bLen = b.length();
        if (aLen >= bLen) {
            if (a.contains(b)) {
                return 1;
            }
            String s = a + a;
            int i = s.indexOf(b);
            return i >= 0 ? 2 : -1;
        }
        int k = (bLen + aLen - 1) / aLen;
        String s = a.repeat(k);
        int ans = s.indexOf(b);
        if (ans == -1) {
            s = s + a;
            ans = s.indexOf(b);
            ++k;
        }
        return ans == -1 ? -1 : k;
    }
}
