package leetcode;

/**
 * @description: You are given a string, message, and a positive integer, limit.
 * <p>
 * You must split message into one or more parts based on limit. Each resulting part should have the suffix "<a/b>", where "b" is to be replaced with the total number of parts and "a" is to be replaced with the index of the part, starting from 1 and going up to b. Additionally, the length of each resulting part (including its suffix) should be equal to limit, except for the last part whose length can be at most limit.
 * <p>
 * The resulting parts should be formed such that when their suffixes are removed and they are all concatenated in order, they should be equal to message. Also, the result should contain as few parts as possible.
 * <p>
 * Return the parts message would be split into as an array of strings. If it is impossible to split message as required, return an empty array.
 * @author: LISHUAI
 * @createDate: 2023/4/12 22:29
 * @version: 1.0
 */

public class LeetCode_2468 {

    public static void main(String[] args) {
        String message = "short message";
        int limit = 15;
        String[] strings = splitMessage(message, limit);
    }

    public static String[] splitMessage(String message, int limit) {
        int len = message.length(), total = 0, suf = 3, size = limit - suf;
        int as = 0, al = limit - 4, cl = 9;

        while (as < len) {
            size -= 2;
            if (size <= 0) {
                return new String[0];
            }
            --al;
            if (as + cl * size < len) {
                total += cl;
                as -= total;
                as += (size * cl);
            } else {
                total += (len - as + size - 1) / size;
                as = len;
            }
            cl *= 10;
        }
        String[] ans = new String[total];
        int index = 0, t = 10, k = 1;
        while (index < len) {
            while (k < t && index < len) {
                int end = Math.min(len, index + al);
                ans[k - 1] = message.substring(index, end) + "<" + k + "/" + total + ">";
                k++;
                index = end;
            }
            al -= 1;
            t = Math.min(total + 1, t * 10);
        }
        return ans;
    }
}
