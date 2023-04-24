package leetcode;

/**
 * @description: In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 * <p>
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
 * @author: LISHUAI
 * @createDate: 2023/2/2 19:42
 * @version: 1.0
 */

public class LeetCode_953 {

    public static boolean isAlienSorted(String[] words, String order) {
        if (words.length <= 1) {
            return true;
        }

        int[] orders = new int[26];
        for (int i = 0; i < order.length(); i++) {
            orders[order.charAt(i) - 'a'] = i;
        }
        String pre = words[0];
        for (int i = 1; i < words.length; i++) {
            String cur = words[i];
            int k = Math.min(pre.length(), cur.length());
            int co = 0, po = 0;
            for (int j = 0; j < k; j++) {
                co = orders[cur.charAt(j) - 'a'];
                po = orders[pre.charAt(j) - 'a'];
                if (co > po) {
                    break;
                }
                if (co < po) {
                    return false;
                }
            }
            if (co == po && cur.length() < pre.length()) {
                return false;
            }
            pre = cur;
        }
        return true;
    }
}
