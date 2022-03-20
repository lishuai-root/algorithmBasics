package leetcode;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/7/22 21:31
 * @version: 1.0
 */

public class LeetCode_212 {

    public static void main(String[] args) {

        String s = "\uFFFF10";

        String s1 = "-1-12";

        String s2 = "\uFFFF01";

        System.out.println(s.hashCode());
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        System.out.println((char) -1);
    }
}
