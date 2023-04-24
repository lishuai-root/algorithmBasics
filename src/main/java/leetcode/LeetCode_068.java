package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 * <p>
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * <p>
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * <p>
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 * <p>
 * Note:
 * <p>
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 * @author: LISHUAI
 * @createDate: 2023/2/14 20:51
 * @version: 1.0
 */

public class LeetCode_068 {

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> list = fullJustify(words, maxWidth);
        for (String word : list) {
            System.out.println(word);
        }
        System.out.println(fullJustify_02(words, maxWidth));
    }

    public static List<String> fullJustify_02(String[] words, int maxWidth) {
        int left = 0, right = 1, len = words.length, index = 0;
        char[] chars = new char[maxWidth];
        List<String> ans = new ArrayList<>();
        int size = words[0].length();

        while (right < len) {
            int t = size + words[right].length() + right - left;
            if (t > maxWidth) {
                int p = right - left - 1;
                int q = 0, k = 0;
                if (p == 0) {
                    q = maxWidth - size;
                } else {
                    q = (maxWidth - size) / p;
                    k = (maxWidth - size) % p;
                }
                while (left < right) {
                    char[] cs = words[left++].toCharArray();
                    System.arraycopy(cs, 0, chars, index, cs.length);
                    index += cs.length;
                    if (index < maxWidth) {
                        int x = index + q - 1;
                        if (k > 0) {
                            x++;
                            k--;
                        }
                        int y = x;
                        while (index <= y) {
                            chars[index++] = ' ';
                            chars[y--] = ' ';
                        }
                        index = x + 1;
                    }
                }
                ans.add(String.valueOf(chars));
                index = 0;
                size = 0;
            }
            size += words[right].length();
            right++;
        }

        while (left < right) {
            char[] cs = words[left++].toCharArray();
            System.arraycopy(cs, 0, chars, index, cs.length);
            index += cs.length;
            if (index < maxWidth) {
                chars[index++] = ' ';
            }
        }
        int x = chars.length - 1;
        while (index <= x) {
            chars[index++] = ' ';
            chars[x--] = ' ';
        }
        ans.add(String.valueOf(chars));
        return ans;
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0, right = 1, len = words.length;
        StringBuilder sbr = new StringBuilder(maxWidth);
        sbr.append(words[0]);
        int size = words[0].length();
        List<String> ans = new ArrayList<>();

        while (right < len) {
            int t = size + words[right].length() + right - left;
            if (t > maxWidth) {
                int p = right - left - 1;
                if (p == 0) {
                    sbr.append(" ".repeat(maxWidth - size));
                } else {
                    int q = (maxWidth - size) / p;
                    int k = (maxWidth - size) % p;
                    while (++left < right) {
                        if (k > 0) {
                            sbr.append(" ".repeat(q + 1));
                            k--;
                        } else {
                            sbr.append(" ".repeat(q));
                        }
                        sbr.append(words[left]);
                    }
                }
                ans.add(sbr.toString());
                sbr.delete(0, sbr.length());
                sbr.append(words[right]);
                left = right;
                size = 0;
            }
            size += words[right].length();
            right++;
        }

        while (++left < right) {
            sbr.append(" ")
                    .append(words[left]);
        }
        if (sbr.length() < maxWidth) {
            sbr.append(" ".repeat(maxWidth - sbr.length()));
        }
        ans.add(sbr.toString());
        return ans;
    }
}
