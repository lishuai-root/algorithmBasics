package leetcode;

/**
 * @description: Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * <p>
 * Each letter in magazine can only be used once in ransomNote.
 * @author: LISHUAI
 * @createDate: 2022/8/25 20:20
 * @version: 1.0
 */

public class LeetCode_383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] ans = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            ans[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            ans[ransomNote.charAt(i) - 'a']--;
            if (ans[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
