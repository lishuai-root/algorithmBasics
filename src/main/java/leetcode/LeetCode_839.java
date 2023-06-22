package leetcode;

/**
 * @description: Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
 * <p>
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
 * <p>
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
 * <p>
 * We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?
 * @author: LISHUAI
 * @createDate: 2023/4/28 21:12
 * @version: 1.0
 */

public class LeetCode_839 {

    public static void main(String[] args) {
//        String[] strs = {"tars", "rats", "arts", "star"};
        String[] strs = {"omv", "ovm"};
        int i = numSimilarGroups(strs);
        System.out.println(i);
    }

    public static int numSimilarGroups(String[] strs) {
        int len = strs.length;
        UF uf = new UF(len);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    uf.union(i, j);
                }
            }
        }
        return uf.group;
    }

    private static boolean isSimilar(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int len = s1.length(), ans = 0, pre = -1;

        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (pre == -1) {
                    pre = i;
                } else {
                    if (s1.charAt(i) == s2.charAt(pre) && s2.charAt(i) == s1.charAt(pre)) {
                        pre = -1;
                        ans++;
                    } else {
                        return false;
                    }
                }
            }
        }
        return ans == 0 || ans == 1 && pre == -1;
    }

    static class UF {
        int[] uf;
        int group;

        public UF(int size) {
            uf = new int[size];
            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }
            group = size;
        }

        public void union(int p, int q) {
            p = find(p);
            q = find(q);
            if (p != q) {
                uf[q] = p;
                group--;
            }
        }

        public int find(int p) {
            return (uf[p] == p ? p : (uf[p] = find(uf[p])));
        }
    }
}
