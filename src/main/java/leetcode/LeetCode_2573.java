package leetcode;

/**
 * @description: We define the lcp matrix of any 0-indexed string word of n lowercase English letters as an n x n grid such that:
 * <p>
 * lcp[i][j] is equal to the length of the longest common prefix between the substrings word[i,n-1] and word[j,n-1].
 * Given an n x n matrix lcp, return the alphabetically smallest string word that corresponds to lcp. If there is no such string, return an empty string.
 * <p>
 * A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b. For example, "aabd" is lexicographically smaller than "aaca" because the first position they differ is at the third letter, and 'b' comes before 'c'.
 * @author: LiShuai
 * @createDate: 2023/8/28 20:43
 * @version: 1.0
 */

public class LeetCode_2573 {

    public static void main(String[] args) {
//        int[][] lcp = {{4, 3, 2, 1}, {3, 3, 2, 1}, {2, 2, 2, 1}, {1, 1, 1, 1}};
//        int[][] lcp = {{4, 1, 1, 1}, {1, 3, 1, 1}, {1, 1, 2, 1}, {1, 1, 1, 1}};
        int[][] lcp = {{3, 2, 0}, {2, 2, 1}, {0, 1, 1}};
        String theString = findTheString(lcp);
        System.out.println(theString);
    }

    public static String findTheString(int[][] lcp) {
        int len = lcp.length;
        UF uf = new UF(len);

        for (int i = 0; i < len; i++) {
            if (lcp[i][i] != len - i) {
                return "";
            }
            for (int j = i + 1; j < len; j++) {
                int k = lcp[i][j];
                if (j + k > len || lcp[i][j] != lcp[j][i]) {
                    return "";
                }
                if (k == 0 && uf.find(i) == uf.find(j)) {
                    return "";
                }
                if (i + k < len && j + k < len && lcp[i + k][j + k] != 0) {
                    return "";
                }

                for (int l = 0; l < k; l++) {
                    int p1 = uf.find(j + l);
                    if (p1 == j + l) {
                        uf.union(i + l, j + l);
                    } else if (p1 != uf.find(i + l)) {
                        return "";
                    }
                }
            }
        }
        char[] chars = new char[len];
        char c = 'a';
        for (int i = 0; i < len; i++) {
            int p1 = uf.find(i);
            if (p1 == i) {
                if (c > 'z') {
                    return "";
                }
                chars[i] = c;
                ++c;
            } else {
                chars[i] = chars[p1];
            }
        }
        return String.valueOf(chars);
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
                uf[q] = p;
            }
        }

        public int find(int q) {
            return uf[q] == q ? uf[q] : (uf[q] = find(uf[q]));
        }
    }
}
