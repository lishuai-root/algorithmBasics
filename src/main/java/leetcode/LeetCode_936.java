package leetcode;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @description: You are given two strings stamp and target. Initially, there is a string s of length target.length with all s[i] == '?'.
 * <p>
 * In one turn, you can place stamp over s and replace every letter in the s with the corresponding letter from stamp.
 * <p>
 * For example, if stamp = "abc" and target = "abcba", then s is "?????" initially. In one turn you can:
 * place stamp at index 0 of s to obtain "abc??",
 * place stamp at index 1 of s to obtain "?abc?", or
 * place stamp at index 2 of s to obtain "??abc".
 * Note that stamp must be fully contained in the boundaries of s in order to stamp (i.e., you cannot place stamp at index 3 of s).
 * We want to convert s to target using at most 10 * target.length turns.
 * <p>
 * Return an array of the index of the left-most letter being stamped at each turn. If we cannot obtain target from s within 10 * target.length turns, return an empty array.
 * @author: LISHUAI
 * @createDate: 2022/8/23 20:45
 * @version: 1.0
 */

public class LeetCode_936 {

    public static void main(String[] args) {
//        String stamp = "abc", target = "ababc";
//        String stamp = "abca", target = "aabcaca";
        String stamp = "aye", target = "eyeye";
        int[] ints = movesToStamp(stamp, target);
        for (int i : ints) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    @PostConstruct
    public static int[] movesToStamp(String stamp, String target) {
        if (stamp.length() > target.length()) {
            return new int[]{};
        }
        if (stamp.length() == target.length()) {
            return stamp.equals(target) ? new int[]{0} : new int[]{};
        }
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < target.length(); i++) {
            sum1 |= (1 << target.charAt(i) - 'a');
        }
        for (int i = 0; i < stamp.length(); i++) {
            sum2 |= (1 << stamp.charAt(i) - 'a');
        }
        if (sum1 != sum2) {
            return new int[]{};
        }
        int[] ans = new int[target.length() * 10];
        char[] chars = new char[target.length()];
        int b = movesToStampProcess(stamp.toCharArray(), target, ans, 0, chars, target.length() * 10, -1);
        if (b == -1) {
            return new int[]{};
        }
        return Arrays.copyOfRange(ans, 0, b);
    }

    private static int movesToStampProcess(char[] stamp, String target, int[] ans, int index, char[] chars, int count, int pre) {
//        System.out.println(count);
        if (target.equals(String.valueOf(chars))) {
            return index;
        }
        if (count <= 0) {
            return -1;
        }
        int sLen = stamp.length, tLen = target.length();
        char[] temp = new char[chars.length];

        for (int i = 0; i <= tLen - sLen; i++) {
            if (pre == i) {
                continue;
            }
            ans[index] = i;
            System.arraycopy(chars, 0, temp, 0, temp.length);
            System.arraycopy(stamp, 0, temp, i, stamp.length);
            int b = movesToStampProcess(stamp, target, ans, index + 1, temp, count - 1, i);
            if (b != -1) {
                return b;
            }
        }
        return -1;
    }

    class Solution {
        //eg :- stamp='abc', target='ababc' -> target='*****'
        //start iterating over target if target has stamp or not
        //if it contains stamp, then replace all characters corresponding to stamp with *
        //also while comparing target with stamp,
        //* can be substituted with any character because this will be overwritten

        //step 1:- target = 'ababc'
        //step 2:- target = 'ab***'(abc has been replaced with stars)
        //step 3:- target = '*****'(ab* can make abc by substituting * with c, therefore replace it with star)

        //this is the approach we would be following
        //carefully read the code to better understand the approach
        public int[] movesToStamp(String stamp, String target) {
            //reverse engineering should be used
            //start from T and convert it to all stars
            //if it is not possible, then return empty array

            char S[] = stamp.toCharArray();
            char T[] = target.toCharArray();
            ArrayList<Integer> arr = new ArrayList<>();
            //checking if we have already checked this index
            boolean visited[] = new boolean[T.length - S.length + 1];
            //checks if there is a change in T in this iteration
            boolean change = true;
            //stores number of stars
            int stars = 0;

            while (stars < T.length) {
                //initially keep it false
                change = false;

                for (int i = 0; i <= T.length - S.length; i++) {
                    //if we have not checked index i and from this index T follows the pattern of S
                    //then convert it to stars
                    //and add this index to arr
                    if (!visited[i] && check(S, T, i)) {
                        stars += replace(S, T, i);
                        change = true;
                        visited[i] = true;
                        arr.add(i);
                    }
                }

                //if we were not able to find any position for change
                //then return empty array
                if (change == false) {
                    return new int[0];
                }
            }

            int ans[] = new int[arr.size()];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = arr.get(arr.size() - i - 1);
            }

            return ans;
        }

        //checking if this substring follows the pattern of S
        private boolean check(char S[], char T[], int ind) {
            for (int i = 0; i < S.length; i++) {
                if (T[i + ind] != '*' && S[i] != T[i + ind]) {
                    return false;
                }
            }

            return true;
        }

        //replacing characters with stars
        private int replace(char S[], char T[], int ind) {
            int count = 0;
            for (int i = 0; i < S.length; i++) {
                if (T[i + ind] != '*') {
                    count++;
                    T[i + ind] = '*';
                }
            }
            return count;
        }
    }
}
