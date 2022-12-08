package leetcode;

/**
 * @description: You are given a string s and a positive integer k.
 * <p>
 * Select a set of non-overlapping substrings from the string s that satisfy the following conditions:
 * <p>
 * The length of each substring is at least k.
 * Each substring is a palindrome.
 * Return the maximum number of substrings in an optimal selection.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * @author: LISHUAI
 * @createDate: 2022/12/9 3:13
 * @version: 1.0
 */

public class LeetCode_2472 {

    public static void main(String[] args) {
//        String s = "fttfjofpnpfydwdwdnns";
//        String s = "aaaaaaaaaaaaaaa";
//        int k = 3;
        String s = "sjbxiufnaanqkwsqswkqrcznzcddhtuhtthuttjfuufjtcfywgecegwyhhnnhtozczirynhhnyrire";
        int k = 3;
        int i = maxPalindromes(s, k);
        System.out.println(i);
//        System.out.println("aaaaaaaaaaaaaaa".length());
    }


    public static int maxPalindromes(String s, int k) {
        int ans = 0, mid = k >> 1, left = 0;

        for (int i = 0; i < s.length(); i++) {
            int q = isPalindromes(s, i, i, left, k);
            if (q < k) {
                q = isPalindromes(s, i, i + 1, left, k);
            }
            if (q >= k) {
                ans++;
                i += (q >> 1);
                left = i + 1;
            }
        }
        return ans;
    }

    private static int isPalindromes(String s, int left, int right, int start, int k) {
        while (left >= start && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left - 1 >= k) {
                break;
            }
            left--;
            right++;
        }
        return right - left - 1;
    }
}
