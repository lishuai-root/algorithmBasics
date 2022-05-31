package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: You are given a string s, an integer k, a letter letter, and an integer repetition.
 * <p>
 * Return the lexicographically smallest subsequence of s of length k that has the letter letter appear at least repetition times.
 * The test cases are generated so that the letter appears in s at least repetition times.
 * <p>
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 * <p>
 * A string a is lexicographically smaller than a string b if in the first position where a and b differ,
 * string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
 * @author: LISHUAI
 * @createDate: 2022/5/5 20:08
 * @version: 1.0
 */

public class LeetCode_2030 {

    public static void main(String[] args) {
//        String s = "wuynymkihfdcbabefiiymnoyyytywzy";
//        int k = 16, repetition = 4;
//        char letter = 'y';
        String s = "abbdglhoquvvwwyzz";
        int k = 15, repetition = 1;
        char letter = 'l';
//        String s = "leetcode";
//        int k = 4, repetition = 2;
//        char letter = 'e';
//        String s = "adffhjfmmmmorsfff";
//        int k = 6, repetition = 5;
//        char letter = 'f';
//        String s = "aaabbbcccddd";
//        int k = 3, repetition = 2;
//        char letter = 'b';
//        String s = "leet";
//        int k = 3, repetition = 1;
//        char letter = 'e';
//        String s = "mmmxmxymmm";
//        int k = 8, repetition = 4;
//        char letter = 'm';
        String s1 = smallestSubsequence(s, k, letter, repetition);
        System.out.println(s1);
        String s2 = smallestSubsequence_02(s, k, letter, repetition);
        System.out.println(s2);
        String s3 = smallestSubsequence_03(s, k, letter, repetition);
        System.out.println(s3);


    }

    public static String smallestSubsequence(String s, int k, char letter, int repetition) {
        if (k == repetition) {
            char[] chars = new char[k];
            for (int i = 0; i < k; i++) {
                chars[i] = letter;
            }
            return String.valueOf(chars, 0, chars.length);
        }
        char[] chars = s.toCharArray();
        List<Character> stack = new ArrayList<>();
        List<Integer> queue = new ArrayList<>();
        int index = 0, letterCount = 0, existsLetter = 0;
        for (char c : chars) {
            if (c == letter) {
                letterCount++;
            }
        }

        for (char c : chars) {
            if (!stack.isEmpty() && c < stack.get(stack.size() - 1)) {
                queue.add(stack.size());
            }
            stack.add(c);
            if (c == letter) {
                existsLetter++;
                letterCount--;
            }

            index = 0;
            while (stack.size() > k && index < stack.size() - 1) {

                if (stack.get(index) > stack.get(index + 1)) {
                    if (stack.get(index) != letter || letterCount + existsLetter > repetition) {
                        if (stack.get(index) == letter) {
                            existsLetter--;
                        }
                        stack.remove(index);
                    }
                }
                ++index;
            }
            while (stack.size() > k) {
                index = stack.size();

                while (--index >= 0) {
                    if (stack.get(index) == letter && letterCount + existsLetter <= repetition) {
                        continue;
                    }
                    if (stack.get(index) == letter) {
                        existsLetter--;
                    }
                    stack.remove(index);
                    break;
                }

            }
        }

        StringBuilder buffer = new StringBuilder();
        for (char c : stack) {
            buffer.append(c);
        }
        return buffer.toString();
    }


    public static String smallestSubsequence_02(String s, int k, char letter, int repetition) {
        if (k == repetition) {
            char[] chars = new char[k];
            for (int i = 0; i < k; i++) {
                chars[i] = letter;
            }
            return String.valueOf(chars, 0, chars.length);
        }
        char[] chars = s.toCharArray();
        int index = 0, letterCount = 0, existsLetter = 0, x = 1;
        for (char c : chars) {
            if (c == letter) {
                letterCount++;
            }
        }

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == letter) {
                existsLetter++;
                letterCount--;
            }

            while (i - index - 1 >= 0 && chars[i] < chars[i - index - 1] && chars.length - index > k) {
                if (chars[i - index - 1] != letter || existsLetter + letterCount > repetition) {
                    if (chars[i - index - 1] == letter) {
                        existsLetter--;
                    }
                    ++index;
                } else {
                    break;
                }
            }
            chars[i - index] = chars[i];
        }

        index = chars.length - index;
        if (index == k) {
            return String.valueOf(chars, 0, index);
        }

        while (x != 0 && index > k) {
            x = 0;
            for (int i = 1; i < index; i++) {
                if (chars[i] < chars[i - 1] && index - x > k) {
                    if (chars[i - 1] != letter || existsLetter > repetition) {
                        if (chars[i - 1] == letter) {
                            existsLetter--;
                        }
                        ++x;
                    }
                }
                chars[i - x] = chars[i];
            }
            index = index - x;
        }

        x = 0;
        for (int i = index - 1; i >= 0; i--) {
            if (index - x > k) {
                if (chars[i] != letter || existsLetter > repetition) {
                    if (chars[i] == letter) {
                        existsLetter--;
                    }
                    ++x;
                    continue;
                }
            }

            chars[i + x] = chars[i];
        }

        return String.valueOf(chars, x, index - x);
    }


    public static String smallestSubsequence_03(String s, int k, char letter, int repetition) {
        if (k == repetition) {
            char[] chars = new char[k];
            for (int i = 0; i < k; i++) {
                chars[i] = letter;
            }
            return String.valueOf(chars, 0, chars.length);
        }
        char[] chars = s.toCharArray();
        int index = 0, letterCount = 0, existsLetter = 0;
        for (char c : chars) {
            if (c == letter) {
                letterCount++;
            }
        }

//        for (int i = 1; i < chars.length; i++) {
//            if (chars[i] == letter) {
//                existsLetter++;
//                letterCount--;
//            }
//
//            while (i - index - 1 >= 0 && chars[i] < chars[i - index - 1] && chars.length - index > k) {
//                if (chars[i - index - 1] != letter || existsLetter + letterCount > repetition) {
//                    if (chars[i - index - 1] == letter) {
//                        existsLetter--;
//                    }
//                    index++;
//                } else {
//                    break;
//                }
//            }
//            chars[i - index] = chars[i];
//        }
//
//        index = chars.length - index;
//        if (index == k) {
//            return String.valueOf(chars, 0, index);
//        }
        existsLetter = letterCount;
        index = chars.length;
        int x = 0;
        for (int i = 1; i < index; i++) {
            if (i - x - 1 >= 0 && chars[i] <= chars[i - x - 1] && index - x > k) {
                if (chars[i - x - 1] != letter || existsLetter > repetition) {
                    if (chars[i - x - 1] == letter) {
                        existsLetter--;
                    }
                    x++;
                }
            }
            chars[i - x] = chars[i];
        }
        index = index - x;

        x = 0;
        for (int i = index - 1; i >= 0; i--) {
            if (index - x > k) {
                if (chars[i] != letter || existsLetter > repetition) {
                    if (chars[i] == letter) {
                        existsLetter--;
                    }
                    ++x;
                    continue;
                }
            }

            chars[i + x] = chars[i];
        }

        return String.valueOf(chars, x, index - x);
    }
}
