package leetcode;

/**
 * @description: Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 * @author: LiShuai
 * @createDate: 2023/8/21 20:35
 * @version: 1.0
 */

public class LeetCode_459 {

    public static void main(String[] args) {
        String s = "abab";
        boolean b = repeatedSubstringPattern(s);
        System.out.println(b);
    }

    public static boolean repeatedSubstringPattern(String s) {
        int len = s.length(), index = 0, size = 0;
        for (int i = 1; i < len; i++) {
            index = index % (size + 1);
            if (s.charAt(i) == s.charAt(index)) {
                ++index;
            } else {
                while (len % (++size + 1) != 0) ;
                i = size;
                index = 0;
            }
        }
        return index > size;
    }
}
