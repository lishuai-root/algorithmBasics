package leetcode;

import java.util.*;

/**
 * @description: Given an m x n board of characters and a list of strings words, return all words on the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * @author: LISHUAI
 * @createDate: 2021/7/22 21:31
 * @version: 1.0
 */

public class LeetCode_212 {

    private static final int[][] P = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static Set<String> ans, word;
    private static PrefixTree rootTree;
    private static List<String> result;

    public static void main(String[] args) {

//        String[] words = {"oath", "pea", "eat", "rain"};
//        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"};
        char[][] board = {
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'}};
        List<String> words1 = findWords_03(board, words);
        for (String s : words1) {
            System.out.println(s);
        }
    }

    public static List<String> findWords(char[][] board, String[] words) {
        ans = new HashSet<>();
        word = new HashSet<>();
        boolean[][] bl = new boolean[board.length][board[0].length];
        char[] chars = new char[board.length * board[0].length];
        int index = 0;
        word.addAll(Arrays.asList(words));

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                chars[index++] = board[i][j];
                bl[i][j] = true;
                findWordsProcess(board, chars, index, i, j, bl);
                --index;
                bl[i][j] = false;
            }
        }
        return new ArrayList<>(ans);
    }

    private static void findWordsProcess(char[][] board, char[] chars, int index, int row, int col, boolean[][] bl) {
        String line = new String(chars, 0, index);
        if (word.contains(line)) {
            ans.add(line);
        }

        int r, c;
        for (int[] ints : P) {
            r = row + ints[0];
            c = col + ints[1];
            if (r >= 0 && r < board.length && c >= 0 && c < board[r].length && !bl[r][c]) {
                bl[r][c] = true;
                chars[index++] = board[r][c];
                findWordsProcess(board, chars, index, r, c, bl);
                bl[r][c] = false;
                --index;
            }
        }
    }


    public static List<String> findWords_02(char[][] board, String[] words) {
        ans = new HashSet<>();
        word = new HashSet<>();
        boolean[][] bl = new boolean[board.length][board[0].length];
        char[] chars = new char[board.length * board[0].length];
        int index = 0;
        rootTree = makePrefixTree(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (rootTree.values[board[i][j] - 'a'] != null && rootTree.values[board[i][j] - 'a'].count > 0) {
                    chars[index++] = board[i][j];
                    bl[i][j] = true;
                    findWordsProcess(board, chars, index, i, j, bl, rootTree.values[board[i][j] - 'a']);
                    --index;
                    bl[i][j] = false;
                }
            }
        }
        return new ArrayList<>(ans);
    }

    private static void findWordsProcess(char[][] board, char[] chars, int index, int row, int col, boolean[][] bl, PrefixTree node) {
        if (node.isEnd) {
            ans.add(new String(chars, 0, index));
            removeNode(chars, index);
        }

        int r, c;
        for (int[] ints : P) {
            r = row + ints[0];
            c = col + ints[1];
            if (r >= 0 && r < board.length && c >= 0 && c < board[r].length && !bl[r][c]
                    && node.values[board[r][c] - 'a'] != null && node.values[board[r][c] - 'a'].count > 0) {
                bl[r][c] = true;
                chars[index++] = board[r][c];
                findWordsProcess(board, chars, index, r, c, bl, node.values[board[r][c] - 'a']);
                bl[r][c] = false;
                --index;
            }
        }
    }


    public static List<String> findWords_03(char[][] board, String[] words) {
        result = new ArrayList<>();
        boolean[][] bl = new boolean[board.length][board[0].length];
        char[] chars = new char[board.length * board[0].length];
        int index = 0;
        rootTree = makePrefixTree(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int t = board[i][j] - 'a';
                if (rootTree.values[t] != null && rootTree.values[t].count > 0) {
                    chars[index++] = board[i][j];
                    bl[i][j] = true;
                    rootTree.values[t].count--;
                    int cur = findWordsProcess_02(board, chars, index, i, j, bl, rootTree.values[t]);
                    if (cur > 0) {
                        rootTree.values[t].count -= (cur - 1);
                    } else {
                        rootTree.values[t].count++;
                    }
                    --index;
                    bl[i][j] = false;
                }
            }
        }
        return result;
    }

    private static int findWordsProcess_02(char[][] board, char[] chars, int index, int row, int col, boolean[][] bl, PrefixTree node) {

        int b = 0;
        if (node.isEnd) {
            ++b;
            node.endCount--;
            node.isEnd = node.endCount > 0;
            result.add(new String(chars, 0, index));
        }

        int r, c;
        for (int[] ints : P) {
            r = row + ints[0];
            c = col + ints[1];

            if (r >= 0 && r < board.length && c >= 0 && c < board[r].length && !bl[r][c]) {
                int t = board[r][c] - 'a';
                if (node.values[t] != null && node.values[t].count > 0) {

                    bl[r][c] = true;
                    chars[index++] = board[r][c];
                    node.values[t].count--;
                    int cur = findWordsProcess_02(board, chars, index, r, c, bl, node.values[t]);
                    b += cur;
                    if (cur > 0) {
                        node.values[t].count -= (cur - 1);
                    } else {
                        node.values[t].count++;
                    }
                    bl[r][c] = false;
                    --index;
                }
            }
        }

        return b;
    }

    private static void removeNode(char[] chars, int index) {
        PrefixTree node = rootTree;
        for (int i = 0; i < index; i++) {
            node = node.values[chars[i] - 'a'];
            node.count--;
        }
        node.endCount--;
        node.isEnd = node.endCount > 0;
    }

    private static PrefixTree makePrefixTree(String[] words) {
        PrefixTree root = new PrefixTree(), node;
        for (String s : words) {
            char[] chars = s.toCharArray();
            node = root;
            for (char c : chars) {
                int cs = c - 'a';
                if (node.values[cs] == null) {
                    node.values[cs] = new PrefixTree();
                }
                node = node.values[cs];
                node.count++;
            }
            node.endCount++;
            node.isEnd = true;
        }
        return root;
    }

    static class PrefixTree {
        PrefixTree[] values = new PrefixTree[26];
        int count = 0, endCount;
        boolean isEnd = false;

        public PrefixTree() {

        }
    }
}
