package leetcode;

/**
 * @description: You are given two strings of the same length s1 and s2 and a string baseStr.
 * <p>
 * We say s1[i] and s2[i] are equivalent characters.
 * <p>
 * For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
 * Equivalent characters follow the usual rules of any equivalence relation:
 * <p>
 * Reflexivity: 'a' == 'a'.
 * Symmetry: 'a' == 'b' implies 'b' == 'a'.
 * Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
 * For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.
 * <p>
 * Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.
 * @author: LISHUAI
 * @createDate: 2023/1/14 14:24
 * @version: 1.0
 */

public class LeetCode_1061 {

    public static void main(String[] args) {
        String s1 = "ohfionaooekkcejlgehcakocmnoneooocnmjmdhkiaekbomolc";
        String s2 = "nlolhhgdfjkihlnahglekgjnhfifgjajmfddnigdoooiakcnjf";
        String baseStr = "qsjjppkfbtpnmcgkjtgrdpguqhlajjdurthsklnwmvjmsoklml";
//        String s1 = "parker";
//        String s2 = "morris";
//        String baseStr = "parser";
        String s = smallestEquivalentString(s1, s2, baseStr);
        System.out.println(s);
    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        Info[] nodes = new Info[26];
        for (int i = 0; i < s1.length(); i++) {
            int p1 = s1.charAt(i) - 'a';
            if (nodes[p1] == null) {
                nodes[p1] = new Info(s1.charAt(i));
            }
            int p2 = s2.charAt(i) - 'a';
            if (nodes[p2] == null) {
                nodes[p2] = new Info(s2.charAt(i));
            }
            if (p1 != p2) {
                nodes[p1].child[p2] = nodes[p2];
                nodes[p2].child[p1] = nodes[p1];
            }
        }
        int len = baseStr.length();
        char[] chars = new char[len];
        boolean[] bls = new boolean[26];
        char[] cache = new char[26];
        for (int i = 0; i < len; i++) {
            int p = baseStr.charAt(i) - 'a';
            if (cache[p] != 0) {
                chars[i] = cache[p];
                continue;
            }
            char c;
            if (nodes[p] == null) {
                c = baseStr.charAt(i);
            } else {
                c = findChar(nodes[p], bls);
            }
            chars[i] = c;
            cache[p] = c;
        }
        return String.valueOf(chars);
    }

    private static char findChar(Info node, boolean[] bls) {
        if (node == null) {
            return 'z' + 1;
        }

        if (bls[node.value - 'a']) {
            return node.value;
        }
        bls[node.value - 'a'] = true;
        char ans = node.value;
        for (Info n : node.child) {
            if (ans == 'a') {
                break;
            }
            char c = findChar(n, bls);
            if (ans > c) {
                ans = c;
            }
        }
        bls[node.value - 'a'] = false;
        return ans;
    }

    static class Info {
        char value;
        Info[] child;

        public Info(char value) {
            this.value = value;
            this.child = new Info[26];
        }
    }
}
