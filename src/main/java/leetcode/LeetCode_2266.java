package leetcode;

/**
 * @description: Alice is texting Bob using her phone. The mapping of digits to letters is shown in the figure below.
 * <p>
 * <p>
 * In order to add a letter, Alice has to press the key of the corresponding digit i times, where i is the position of the letter in the key.
 * <p>
 * For example, to add the letter 's', Alice has to press '7' four times. Similarly, to add the letter 'k', Alice has to press '5' twice.
 * Note that the digits '0' and '1' do not map to any letters, so Alice does not use them.
 * However, due to an error in transmission, Bob did not receive Alice's text message but received a string of pressed keys instead.
 * <p>
 * For example, when Alice sent the message "bob", Bob received the string "2266622".
 * Given a string pressedKeys representing the string received by Bob, return the total number of possible text messages Alice could have sent.
 * <p>
 * Since the answer may be very large, return it modulo 109 + 7.
 * @author: LiShuai
 * @createDate: 2023/8/3 21:45
 * @version: 1.0
 */

public class LeetCode_2266 {

    private static Integer[] cache;

    public static void main(String[] args) {
//        String pressedKeys = "22233";
        String pressedKeys = "222222222222222222222222222222222222";
        int i = countTexts(pressedKeys);
        System.out.println(i);
        System.out.println(countTexts_dp(pressedKeys));
    }

    public static int countTexts(String pressedKeys) {
        cache = new Integer[pressedKeys.length()];
        return countTextsProcess(pressedKeys, 0);
    }

    private static int countTextsProcess(String pressedKeys, int index) {
        if (index >= pressedKeys.length()) {
            return 1;
        }
        if (cache[index] != null) {
            return cache[index];
        }
        int p = 0;
        char c = pressedKeys.charAt(index);
        int len = Math.min(pressedKeys.length(), index + (c == '7' || c == '9' ? 4 : 3));
        for (int i = index; i < len && c == pressedKeys.charAt(i); i++) {
            p += countTextsProcess(pressedKeys, i + 1);
            p %= 1000000007;
        }
        cache[index] = p;
        return p;
    }

    public static int countTexts_dp(String pressedKeys) {
        char[] chars = pressedKeys.toCharArray();
        int len = chars.length;
        int[] dp = new int[len + 1];
        dp[len] = 1;

        for (int i = len - 1; i >= 0; --i) {
            int k = Math.min(len, i + (chars[i] == '7' || chars[i] == '9' ? 4 : 3));
            for (int j = i; j < k && chars[i] == chars[j]; ++j) {
                dp[i] += dp[j + 1];
                dp[i] %= 1000000007;
            }
        }
        return dp[0];
    }
}
