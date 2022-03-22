package leetcode;

/**
 * @description: Given a string s, find the first non-repeating character in it and return its index.
 * If it does not exist, return -1.
 * @author: LISHUAI
 * @createDate: 2022/3/21 22:55
 * @version: 1.0
 */

public class LeetCode_387 {

    public static void main(String[] args) {
        String s = "aabb";
        int i = firstUniqChar(s);
        System.out.println(i);
    }

    public static int firstUniqChar(String s) {
        int[] stack = new int[26], sum = new int[26];
        int index = -1, ans = -1, flag = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int c = chars[i] - 'a';
            if ((flag & (1 << c)) == 0) {
                flag |= (1 << c);
                stack[++index] = i;
            }
            sum[c]++;
        }

        for (int i = 0; i <= index; i++) {
            if (sum[chars[stack[i]] - 'a'] == 1) {
                ans = stack[i];
                break;
            }
        }
        return ans;
    }
}
