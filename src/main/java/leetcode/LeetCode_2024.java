package leetcode;

/**
 * @description: A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false. He wants to confuse the students by maximizing the number of consecutive questions with the same answer (multiple trues or multiple falses in a row).
 * <p>
 * You are given a string answerKey, where answerKey[i] is the original answer to the ith question. In addition, you are given an integer k, the maximum number of times you may perform the following operation:
 * <p>
 * Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
 * Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.
 * @author: LiShuai
 * @createDate: 2023/7/7 22:27
 * @version: 1.0
 */

public class LeetCode_2024 {

    public static int maxConsecutiveAnswers(String answerKey, int k) {
        char[] arr = answerKey.toCharArray();
        int ans = 0;
        int st = 0, c = 0;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 'F') {
                c++;
            }
            while (c > k) {
                if (arr[st] == 'F')
                    c--;
                st++;
            }
            ans = Math.max(ans, i - st + 1);
        }
        st = 0;
        c = 0;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 'T') {
                c++;
            }
            while (c > k) {

                if (arr[st] == 'T')
                    c--;
                st++;

            }
            ans = Math.max(ans, i - st + 1);
        }
        return ans;
    }
}
