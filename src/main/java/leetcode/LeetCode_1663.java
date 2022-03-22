package leetcode;

/**
 * @description: The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet,
 * so the numeric value of a is 1, the numeric value of b is 2, the numeric value of c is 3, and so on.
 * <p>
 * The numeric value of a string consisting of lowercase characters is defined as the sum of its characters' numeric values.
 * For example, the numeric value of the string "abe" is equal to 1 + 2 + 5 = 8.
 * <p>
 * You are given two integers n and k. Return the lexicographically smallest string with length equal to n and numeric value equal to k.
 * <p>
 * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order,
 * that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
 * @author: LISHUAI
 * @createDate: 2022/3/22 19:27
 * @version: 1.0
 */

public class LeetCode_1663 {

    public static void main(String[] args) {
        int n = 5, k = 73;
        String smallestString = getSmallestString_02(n, k);
        System.out.println(smallestString);
    }

    public static String getSmallestString(int n, int k) {
        int base = 'a' - 1, index = 0, sum = 0;
        char[] chars = new char[n];

        while (index < n) {
            sum++;
            int cur = n - index - 1;
            if (cur * 26 == k - sum) {
                chars[index++] = 'a';
                break;
            } else if (cur * 26 < k - sum) {
                chars[index++] = (char) (k - sum - cur * 26 + base + 1);
                break;
            }
            chars[index++] = 'a';
        }

        while (index < n) {
            chars[index++] = 'z';
        }

        return String.valueOf(chars);
    }


    public static String getSmallestString_02(int n, int k) {
        int base = 'a' - 1, index = 0, sum = 0;
        char[] chars = new char[n];

        while (index < n) {
            sum++;
            int cur = n - index - 1;

            if (cur * 26 >= k - sum) {
                chars[index++] = 'a';
            } else {
                chars[index++] = (char) (k - sum - cur * 26 + base + 1);
                break;
            }
        }

        while (index < n) {
            chars[index++] = 'z';
        }

        return String.valueOf(chars);
    }
}
















