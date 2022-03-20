package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/7/18 17:54
 * @version: 1.0
 */

public class LeetCode_30 {

    public static void main(String[] args) {

        String[] words = {"word", "good", "best", "good"};

        fn_001();
//        Map<String, Integer> frequencyMap = getFrequencyMap(words);
//
//        Set<String> strings = frequencyMap.keySet();
//
//        Iterator<String> iterator = strings.iterator();
//
//        String next = null;
//
//        while (iterator.hasNext()) {
//            next = iterator.next();
//
//            System.out.print("key : " + next);
//
//            System.out.print("   values : " + frequencyMap.get(next));
//
//            System.out.println();
//        }

    }

    private static void fn_001() {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good"};

        List<Integer> substring = findSubstring(s, words);

        System.out.println("size : " + substring.size());
        for (Integer i : substring)
            System.out.println(i);
    }


    public static List<Integer> findSubstring(String s, String[] words) {

        int wordsLen = words.length;

        int wLen = words[0].length();

        int sLen = wordsLen * wLen;

        int len = s.length() - sLen;

        int subIndex = 0, j = 0;

        String sline = null;

        Map<String, Integer> map = new HashMap<>(), cacheMap = null;

        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        List<Integer> list = new ArrayList<>();


        for (int i = 0; i <= len; i++) {

            cacheMap = new HashMap<>(map.size());

            j = 0;

            for (; j < wordsLen; j++) {

                subIndex = i + j * wLen;

                sline = s.substring(subIndex, subIndex + wLen);

                if (!map.containsKey(sline))
                    break;

                cacheMap.put(sline, cacheMap.getOrDefault(sline, 0) + 1);

                if (cacheMap.get(sline) > map.getOrDefault(sline, 0))
                    break;

            }

            if (j == wordsLen)
                list.add(i);

        }

        return list;
    }

}
