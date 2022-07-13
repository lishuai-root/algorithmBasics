package test;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/14 17:39
 * @version: 1.0
 */

public class Debug {

    public static void main(String[] args) {
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sbr.append('a');
        }
        sbr.append('b');
        String str1 = sbr.toString();
        sbr.delete(0, sbr.length());
        sbr.append("a".repeat(100000));
        sbr.append('c');
        String str2 = sbr.toString();
        System.out.println(str1.indexOf(str2));
    }
}