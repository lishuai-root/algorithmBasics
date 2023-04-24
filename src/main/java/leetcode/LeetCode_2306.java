package leetcode;

import java.util.HashSet;
import java.util.List;

/**
 * @description: You are given an array of strings ideas that represents a list of names to be used in the process of naming a company. The process of naming a company is as follows:
 * <p>
 * Choose 2 distinct names from ideas, call them ideaA and ideaB.
 * Swap the first letters of ideaA and ideaB with each other.
 * If both of the new names are not found in the original ideas, then the name ideaA ideaB (the concatenation of ideaA and ideaB, separated by a space) is a valid company name.
 * Otherwise, it is not a valid name.
 * Return the number of distinct valid names for the company.
 * @author: LISHUAI
 * @createDate: 2023/2/9 21:02
 * @version: 1.0
 */

public class LeetCode_2306 {

    public static long distinctNames(String[] ideas) {
        HashSet<String> exists = new HashSet<String>();
        exists.addAll(List.of(ideas));
        int ans = 0;
        for (int i = 0; i < ideas.length; i++) {
            String idea = ideas[i];
            char c = idea.charAt(0);
            for (int j = i + 1; j < ideas.length; j++) {
                String s = ideas[j];
                char sc = s.charAt(0);
                if (!exists.contains(c + s.substring(1)) && !exists.contains(sc + idea.substring(1))) {
                    ans += 2;
                }
            }
        }
        return ans;
    }

    public long distinctNames_other(String[] ideas) {
        HashSet<String>[] groups = new HashSet[26];
        for (int i = 0; i < 26; ++i) {
            groups[i] = new HashSet<>();
        }
        for (String idea : ideas) {
            groups[idea.charAt(0) - 'a'].add(idea.substring(1));
        }

        long answer = 0;
        for (int i = 0; i < 25; ++i) {
            for (int j = i + 1; j < 26; ++j) {

                long mutuals = 0;
                for (String ideaA : groups[i]) {
                    if (groups[j].contains(ideaA)) {
                        mutuals++;
                    }
                }
                answer += 2 * (groups[i].size() - mutuals) * (groups[j].size() - mutuals);
            }
        }

        return answer;
    }
}
