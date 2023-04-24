package leetcode;

/**
 * @description: You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.
 * <p>
 * Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.
 * @author: LISHUAI
 * @createDate: 2023/2/15 21:53
 * @version: 1.0
 */

public class LeetCode_990 {

    public static void main(String[] args) {
        String[] equations = {"a!=i", "g==k", "k==j", "k!=i", "c!=e", "a!=e", "k!=a", "a!=g", "g!=c"};
        boolean b = equationsPossible(equations);
        System.out.println(b);
    }

    public static boolean equationsPossible(String[] equations) {
        UF equals = new UF(26);

        for (String s : equations) {
            if (s.charAt(1) == '=') {
                equals.union(s.charAt(0) - 'a', s.charAt(3) - 'a');
            }
        }
        for (String s : equations) {
            if (s.charAt(1) == '!') {
                if (equals.find(s.charAt(0) - 'a') == equals.find(s.charAt(3) - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }

    static class UF {
        int[] uf;

        public UF(int size) {
            uf = new int[size];

            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }
        }

        public void union(int p, int q) {
            p = find(p);
            q = find(q);
            if (p != q) {
                uf[p] = q;
            }
        }

        public int find(int p) {
            return uf[p] == p ? p : (uf[p] = find(uf[p]));
        }
    }
}
