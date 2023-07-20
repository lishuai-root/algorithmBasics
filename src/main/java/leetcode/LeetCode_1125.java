package leetcode;

import java.util.*;

/**
 * @description: In a project, you have a list of required skills req_skills, and a list of people. The ith person people[i] contains a list of skills that the person has.
 * <p>
 * Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill. We can represent these teams by the index of each person.
 * <p>
 * For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 * Return any sufficient team of the smallest possible size, represented by the index of each person. You may return the answer in any order.
 * <p>
 * It is guaranteed an answer exists.
 * @author: LiShuai
 * @createDate: 2023/7/16 20:09
 * @version: 1.0
 */

public class LeetCode_1125 {

    private static final List<Integer> NULL = new ArrayList<>();
    private static int minResult;
    private static int[] result;
    private static Map<String, Set<Integer>>[] dp;

    public static int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Set<String> set = new HashSet<>(List.of(req_skills));
        minResult = Integer.MAX_VALUE;
        int[] cache = new int[people.size()];
        return smallestSufficientTeamProcess(set, people, 0, cache, 0);
//        return result;
    }

    private static int[] smallestSufficientTeamProcess(Set<String> set, List<List<String>> people, int index, int[] cache, int k) {
        if (set.isEmpty()) {
//            if (k < minResult) {
//                minResult = k;
//                result = Arrays.copyOfRange(cache, 0, k);
//            }
            return Arrays.copyOfRange(cache, 0, k);
        }
        if (index >= people.size()) {
            return null;
        }
        int[] p1 = smallestSufficientTeamProcess(set, people, index + 1, cache, k);
        List<String> l = people.get(index);
        Set<String> t = new HashSet<>();
        for (String str : l) {
            if (set.remove(str)) {
                t.add(str);
            }
        }
        cache[k] = index;
        int[] p2 = smallestSufficientTeamProcess(set, people, index + 1, cache, k + 1);
//        if (p2 != Integer.MAX_VALUE) {
//            ++p2;
//        }
        set.addAll(t);
//        return Math.min(p1, p2);
        if (p1 == p2) {
            return null;
        } else if (p1 == null) {
            return p2;
        } else if (p2 == null) {
            return p1;
        }
        return p1.length <= p2.length ? p1 : p2;
    }


    public static void main(String[] args) {
//        String[] req_skills = {"gvp", "jgpzzicdvgxlfix", "kqcrfwerywbwi", "jzukdzrfgvdbrunw", "k"};
//        String[][] p = {{}, {}, {}, {}, {"jgpzzicdvgxlfix"}, {"jgpzzicdvgxlfix", "k"}, {"jgpzzicdvgxlfix", "kqcrfwerywbwi"}, {"gvp"}, {"jzukdzrfgvdbrunw"}, {"gvp", "kqcrfwerywbwi"}};
        String[] req_skills = {"cdkpfwkhlfbps", "hnvepiymrmb", "cqrdrqty", "pxivftxovnpf", "uefdllzzmvpaicyl", "idsyvyl"};
        String[][] p = {{}, {"hnvepiymrmb"}, {"uefdllzzmvpaicyl"}, {}, {"hnvepiymrmb", "cqrdrqty"}, {"pxivftxovnpf"}, {"hnvepiymrmb", "pxivftxovnpf"}, {"hnvepiymrmb"}, {"cdkpfwkhlfbps"}, {"idsyvyl"}, {}, {"cdkpfwkhlfbps", "uefdllzzmvpaicyl"}, {"cdkpfwkhlfbps", "uefdllzzmvpaicyl"}, {"pxivftxovnpf", "uefdllzzmvpaicyl"}, {}, {"cqrdrqty"}, {}, {"cqrdrqty", "pxivftxovnpf", "idsyvyl"}, {"hnvepiymrmb", "idsyvyl"}, {}};
        List<List<String>> people = new ArrayList<>();
        for (String[] t : p) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList(t));
            people.add(list);
        }
        int[] ints = smallestSufficientTeam_02(req_skills, people);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] smallestSufficientTeam_02(String[] req_skills, List<List<String>> people) {
        Set<String> set = new HashSet<String>(List.of(req_skills));
        dp = new Map[people.size()];
        for (int i = 0; i < people.size(); i++) {
            dp[i] = new HashMap<>();
        }
        Set<Integer> list = smallestSufficientTeamProcess(set, people, 0);
        int[] ans = new int[list.size()];
        int index = 0;
        for (int i : list) {
            ans[index++] = i;
        }
        return ans;
    }

    private static Set<Integer> smallestSufficientTeamProcess(Set<String> set, List<List<String>> people, int index) {
        if (set.isEmpty()) {
            return new HashSet<>();
        }
        if (index >= people.size()) {
            return null;
        }
        int i = set.hashCode();
        Map<String, Set<Integer>> map = dp[index];
        String s = set.toString();
        if (map.containsKey(String.valueOf(i))) {
            Set<Integer> ss = map.get(String.valueOf(i));
            System.out.println(index + " map : " + (ss == null ? null : ss.size()) + " : " + set.size() + " : " + set.toString());
            return map.get(String.valueOf(i));
        }

        Set<Integer> p1 = smallestSufficientTeamProcess(set, people, index + 1);
        System.out.println(index + " p1 : " + (p1 == null ? null : p1.size()) + " : " + set.size() + " : " + set.toString());
        List<String> l = people.get(index);
        Set<String> t = new HashSet<>();
        for (String str : l) {
            if (set.remove(str)) {
                t.add(str);
            }
        }
//        if (t.isEmpty()) {
//            if (p1 != null) {
//                map.put(String.valueOf(i), new HashSet<>(p1));
//            } else {
//                map.put(String.valueOf(i), null);
//            }
//            return p1;
//        }
        Set<Integer> p2 = smallestSufficientTeamProcess(set, people, index + 1);
        System.out.println(index + " p2 : " + (p2 == null ? null : p2.size()) + " : " + set.size() + " : " + set.toString());
        set.addAll(t);
        Set<Integer> ans;
        if (p2 != null) {
            p2.add(index);
        }
        if (p1 == null && p2 == null) {
            ans = null;
        } else if (p1 == null) {
            ans = p2;
        } else if (p2 == null) {
            ans = p1;
        } else {
            ans = p2.size() <= p1.size() ? p2 : p1;
        }
        if (ans != null) {
            map.put(String.valueOf(i), new HashSet<>(ans));
        } else {
            map.put(String.valueOf(i), null);
        }

        System.out.println(index + " : " + (ans == null ? null : ans.size()) + " : " + set.size() + " : " + set.toString());
        return ans;
    }
}

