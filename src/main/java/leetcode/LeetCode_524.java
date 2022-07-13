package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @description: Given a string s and a string array dictionary,
 * return the longest string in the dictionary that can be formed by deleting some of the given string characters.
 * If there is more than one possible result, return the longest word with the smallest lexicographical order.
 * If there is no possible result, return the empty string.
 * @author: LISHUAI
 * @createDate: 2022/6/8 21:11
 * @version: 1.0
 */

public class LeetCode_524 {

    public static void main(String[] args) {
        String s = "okbmfyugqqongobwofraotanviqjraaktmmwyxzdnnwwaqsnvfxwngglybtiqwblprgvbgmolotnppzbovnstxmcmocixresdpojmntcdkyjzgbhhigkiyatrgzayivjyqiopvvdftkbsgwgnidsecvydvltaxxywydawrsseyolixznuyhjolngdsmjhepregixtklanjjggzssrbtmhhpamcfeghgssmrjrpelabojfhkdfvscjwhqxubwrhryqmtkiksljezeembngdjyhgmdzahxvvpkqwvhkqlensuxbrcdctqojqnlogjbpovdsbvurwcoehtmtkwxswrwhszuyesdqspnwqxlmjxrbdqbnbbyxhhlabxrdjxtjgpugojsexmrhrfzryapdzglalqzuzfpxeaemjkyfhpbnmdxjtxnxyjecfsapcjyglmtivnsfalpxzdkylgcixjljaqjwminyaodeekpzzpchhceguzayeul";
        List<String> list = new ArrayList<>();
        list.add("ettphsiunoglotjlccurlre");
        String longestWord = findLongestWord(s, list);
        System.out.println(longestWord);
        System.out.println("ntgcykxhdfescxxypyew".length());
    }

    public static String findLongestWord(String s, List<String> dictionary) {
        int maxSize = 0;
        String ans = "";
        for (String s1 : dictionary) {
            if (s1.length() > s.length()) {
                continue;
            }
            if (s1.length() == s.length()) {
                if (s1.equals(s)) {
                    return s;
                } else {
                    continue;
                }
            }

            int index = 0;
            int cur = 0;
            while (index < s1.length()) {
                while (cur < s.length() && s.charAt(cur) != s1.charAt(index)) {
                    cur++;
                }
                if (cur >= s.length()) {
                    break;
                }
                index++;
            }

            if (index >= s1.length()) {
                if (s1.length() > maxSize) {
                    maxSize = s1.length();
                    ans = s1;
                } else if (s1.length() == maxSize) {
                    ans = min(ans, s1);
                }
            }
        }
        return ans;
    }

    private static String findLongestWordProcess(Set<String> set, String s, char[] chars, int len, int index) {
        if (index >= chars.length) {
            return null;
        }
        String ans = String.valueOf(chars, 0, len);
        if (!set.contains(ans)) {
            ans = "";
        }

        String s1 = findLongestWordProcess(set, s, chars, len, index + 1);
        if (s1 != null) {
            if (ans.equals("")) {
                ans = s1;
            } else {
                ans = min(ans, s1);
            }
        }
        chars[len] = s.charAt(index);
        String s2 = findLongestWordProcess(set, s, chars, len + 1, index + 1);
        if (s2 != null) {
            if (ans.equals("")) {
                ans = s2;
            } else {
                ans = min(ans, s2);
            }
        }
        return ans;
    }

    private static String min(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return s2;
        }
        if (s1.length() < s2.length()) {
            return s1;
        }
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) < s2.charAt(i)) {
                return s1;
            } else if (s1.charAt(i) > s2.charAt(i)) {
                return s2;
            }
        }
        return s1;
    }
}
