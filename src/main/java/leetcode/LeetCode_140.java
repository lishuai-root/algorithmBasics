package leetcode;

import java.util.*;

/**
 * @description: Given a string s and a dictionary of strings wordDict,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences in any order.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * @author: LISHUAI
 * @createDate: 2021/11/24 22:30
 * @version: 1.0
 */

public class LeetCode_140 {

    private static Set<String> set;

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaa";
        String[] wordDict = {"aaaa", "aa", "a"};

        ArrayList<String> list = new ArrayList<>(Arrays.asList(wordDict));

        long start = System.currentTimeMillis();
        List<String> list1 = wordBreak(s, list);
        long end = System.currentTimeMillis();
        for (String str : list1) {

            System.out.println(str);
        }

        System.out.println(end - start);
//        boolean b = wordBreak_02(s, list);
//
//        System.out.println(b);
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {

        List<String> result = new ArrayList<String>();

        StringBuilder sb = new StringBuilder();

        set = new HashSet<>();

        for (int i = 0; i < wordDict.size(); i++) {

            if (wordDict.get(i).length() <= s.length() && wordDict.get(i).equals(s.substring(0, wordDict.get(i).length()))) {

                sb.append(wordDict.get(i))
                        .append(" ");

                process_02(s, wordDict.get(i).length(), s.length(), wordDict, result, sb);

                sb.delete(0, sb.length());
            }
        }

        return result;
    }

    public static boolean wordBreak_02(String s, List<String> wordDict) {

        for (int i = 0; i < wordDict.size(); i++) {

            if (wordDict.get(i).equals(s.substring(0, wordDict.get(i).length()))) {

                boolean process = process(s, wordDict.get(i).length(), s.length(), wordDict);

                if (process) {

                    return true;
                }
            }
        }

        return false;
    }

    private static boolean process(String s, int left, int right, List<String> wordDict) {

        if (left >= right) {
            System.out.println("------");
            return true;
        }

        boolean bl = false;

        String line;

        int len;

        for (int i = 0; !bl && i < wordDict.size(); i++) {

            int l = left, r = right;

            line = wordDict.get(i);

            len = line.length();

            for (int j = 1; !bl && len * j <= r - l &&
                    line.equals(s.substring(l, l + len)); j++) {

                l += wordDict.get(i).length();

                bl = bl || process(s, l, r, wordDict);

            }
        }


        return bl;
    }

    private static void process_02(String s, int left, int right, List<String> wordDict, List<String> result, StringBuilder sb) {

        if (left >= right) {

            String s1 = sb.delete(sb.length() - 1, sb.length()).toString();

            if (!set.contains(s1)) {

                result.add(s1);

                set.add(s1);
            }
        }

        String line;

        int len, sbLen;

        sbLen = sb.length();

        for (int i = 0; i < wordDict.size(); i++) {

            int l = left, r = right;

            line = wordDict.get(i);

            len = line.length();

            for (int j = 1; len * j <= r - l && line.equals(s.substring(l, l + len)); j++) {

                sb.append(line)
                        .append(" ");

                l += wordDict.get(i).length();

                process_02(s, l, r, wordDict, result, sb);

            }

            sb.delete(sbLen, sb.length());
        }
    }
}
