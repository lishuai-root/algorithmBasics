package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows:
 * <p>
 * 'a' maps to ".-",
 * 'b' maps to "-...",
 * 'c' maps to "-.-.", and so on.
 * For convenience, the full table for the 26 letters of the English alphabet is given below:
 * <p>
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * Given an array of strings words where each word can be written as a concatenation of the Morse code of each letter.
 * <p>
 * For example, "cab" can be written as "-.-..--...", which is the concatenation of "-.-.", ".-", and "-...". We will call such a concatenation the transformation of a word.
 * Return the number of different transformations among all words we have.
 * @author: LISHUAI
 * @createDate: 2022/8/17 19:49
 * @version: 1.0
 */

public class LeetCode_804 {

    private static final String[] TEMP = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public static int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        int ans = 0;

        for (String word : words) {
            StringBuilder sbr = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sbr.append(TEMP[word.charAt(i) - 'a']);
            }
            String line = sbr.toString();
            if (!set.contains(line)) {
                set.add(line);
                ans++;
            }
        }
        return ans;
    }
}
