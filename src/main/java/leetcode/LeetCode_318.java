package leetcode;

/**
 * @description: Given a string array words,
 * return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.
 * @author: LISHUAI
 * @createDate: 2022/5/29 17:49
 * @version: 1.0
 */

public class LeetCode_318 {

    public static int maxProduct(String[] words) {
        int ans = 0;

        for (int i = 0; i < words.length; i++) {
            char[] chars = new char[26];
            String cur = words[i];
            for (int j = 0; j < cur.length(); j++) {
                chars[cur.charAt(j) - 'a']++;
            }
            for (int j = i + 1; j < words.length; j++) {
                if (isEquals(chars, words[j])) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }

    private static boolean isEquals(char[] chars, String str2) {
        for (int i = 0; i < str2.length(); i++) {
            if (chars[str2.charAt(i) - 'a'] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int maxProduct_02(String[] words) {
        int[] nums = getArr(words);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] & nums[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }

    private static int[] getArr(String[] words) {
        int[] nums = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int cur = 0;
            String line = words[i];
            for (int j = 0; j < line.length(); j++) {
                cur |= (1 << (line.charAt(j) - 'a'));
            }
            nums[i] = cur;
        }
        return nums;
    }
}
