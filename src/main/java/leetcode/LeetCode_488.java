package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: You are playing a variation of the game Zuma.
 * <p>
 * In this variation of Zuma, there is a single row of colored balls on a board, where each ball can be colored red 'R',
 * yellow 'Y', blue 'B', green 'G', or white 'W'. You also have several colored balls in your hand.
 * <p>
 * Your goal is to clear all of the balls from the board. On each turn:
 * <p>
 * Pick any ball from your hand and insert it in between two balls in the row or on either end of the row.
 * If there is a group of three or more consecutive balls of the same color, remove the group of balls from the board.
 * If this removal causes more groups of three or more of the same color to form, then continue removing each group until there are none left.
 * If there are no more balls on the board, then you win the game.
 * Repeat this process until you either win or do not have any more balls in your hand.
 * Given a string board, representing the row of balls on the board, and a string hand, representing the balls in your hand,
 * return the minimum number of balls you have to insert to clear all the balls from the board. If you cannot clear all the balls from the board using the balls in your hand, return -1.
 * <p>
 * The initial row of balls on the board will not have any groups of three or more consecutive balls of the same color.
 * @author: LISHUAI
 * @createDate: 2022/4/9 22:40
 * @version: 1.0
 */

public class LeetCode_488 {
    private static int COUNT = 0;
    private static Map<String, Integer> cache;

    public static void main(String[] args) {
//        String board = "WWRRBBWW", hand = "WRBRW";
//        String board = "WRRBBW", hand = "RB";
//        String board = "G", hand = "GGGGG";
//        String board = "RBYYBBRRB", hand = "YRBGB";
//        String board = "RRGGBBYYWWRRGGBB", hand = "RGBYW";
//        String board = "WR", hand = "WWRR";
//        String board = "WRR", hand = "WWRR";
        String board = "GWRBGYWGWGWYGRYW", hand = "BRGGW";
//        String board = "W", hand = "WWRR";
//        String board = "RRWWRRW", hand = "WR";
//        String board = "RRWWRRBBRR", hand = "WB";
//        String board = "WWBBWBBWW", hand = "BB";
        int minStep = findMinStep(board, hand);
        System.out.println(minStep);
        System.out.println(COUNT);
//
        Solution solution = new Solution();
        int minStep1 = solution.findMinStep(board, hand);
        System.out.println("minStep1 : " + minStep1);

    }

    private static String makeString(int size) {
        char[] chars = new char[size];
        int c = 0;
        for (int i = 0; i < size; ) {
            c = ++c % 26;
            for (int j = 0; j < 3 && i < size; j++, i++) {
                chars[i] = (char) (c + 'a');
            }

        }

        return new String(chars);
    }

    public static int findMinStep(String board, String hand) {
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < hand.length(); i++) {
//            map.put(hand.charAt(i), map.getOrDefault(hand.charAt(i), 0) + 1);
//        }
        cache = new HashMap<>();
        int size = hand.length();
        int i = findMinStepProcess(board, hand);
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private static int findMinStepProcess(String board, String hand) {
        ++COUNT;
//        System.out.println("board : " + board);

        if (board.length() == 0) {
//            System.out.println("--------");
            return 0;
        }
        if (hand.length() == 0) {
            return Integer.MAX_VALUE;
        }

        if (cache.containsKey(board + ":" + hand)) {
            return cache.get(board + ":" + hand);
        }
        int ans = Integer.MAX_VALUE;


        for (int i = 0; i < board.length(); i++) {
//            char c = board.charAt(i);
            while (i + 1 < board.length() && board.charAt(i) == board.charAt(i + 1)) {
                i++;
            }

//            char k = board.charAt(i);
//            if (map.get(k) != null && map.get(k) > 0) {
//                map.put(k, map.get(k) - 1);
////                String fun = fun(board.substring(0, i) + k + board.substring(i), i, false);
////                ans = Math.min(ans, findMinStepProcess(fun, map, size - 1));
////                String fun = fun(board.substring(0, i) + k + board.substring(i), i, true);
//                String fun = updateBoard(board.substring(0, i) + k + board.substring(i));
//                ans = Math.min(ans, findMinStepProcess(fun, map, size - 1));
//                map.put(k, map.get(k) + 1);
////                System.out.println(fun + " : " + ans);
//            }

            for (int j = 0; j < hand.length(); j++) {
                char k = hand.charAt(j);
//                String fun = fun(board.substring(0, i) + k + board.substring(i), i, true);
//                System.out.println(fun);

                String fun = updateBoard(board.substring(0, i) + k + board.substring(i));
                int process = findMinStepProcess(fun, hand.substring(0, j) + hand.substring(j + 1));
                ans = Math.min(ans, process);
//                System.out.println(board + ", : " + board.substring(0, i) + k + board.substring(i) + " , fun :" + fun + ", hand : " + hand.substring(0, j) + hand.substring(j + 1)
//                        + " , process : " + process);
            }
//            for (char k : map.keySet()) {
//                if (map.get(k) <= 0) {
//                    continue;
//                }
//                map.put(k, map.get(k) - 1);
////                String fun = fun(board.substring(0, i) + k + board.substring(i), i);
////                System.out.println(fun);
//                String fun = fun(board.substring(0, i) + k + board.substring(i), i, true);
//                ans = Math.min(ans, findMinStepProcess(fun, map, size - 1));
////                if (ans == 1) {
////                    System.out.println("ans : " + board.substring(0, i) + k + board.substring(i));
////                }
//                map.put(k, map.get(k) + 1);
//            }
        }
//        if (board.length() == 1) {
//            for (char k : map.keySet()) {
//                if (map.get(k) <= 0 || board.charAt(0) != k) {
//                    continue;
//                }
//                map.put(k, map.get(k) - 1);
//                ans = Math.min(ans, findMinStepProcess(board + k, map, size - 1));
//                map.put(k, map.get(k) + 1);
//            }
//        }

//        System.out.println(ans);
        ans = ans == Integer.MAX_VALUE ? Integer.MAX_VALUE : ans + 1;
        cache.put(board + ":" + hand, ans);
//        System.out.println(board + " :  " + ans);

        return ans;
    }

    private static String fun(String board, int index, boolean bl) {
        if (board.length() < 3) {
            return board;
        }
        int left = index - 1, right = index, count = -1, backLeft = index - 1, backRight = index, tmp = 0;
        do {
//            backLeft = left;
//            backRight = right;
            while (left >= 0 && board.charAt(left) == board.charAt(index)) {
                left--;
            }
            while (right < board.length() && board.charAt(right) == board.charAt(index)) {
                right++;
            }
            index = left < 0 ? right : left;
            count = right - left - 1 - tmp;
            tmp += count;
            if (count >= 3) {
                backLeft = left;
                backRight = right;
            }
        } while (count >= 3 && bl);

        return board.substring(0, backLeft + 1) + board.substring(backRight);
    }

    private static String updateBoard(String board) {
        int n = board.length();
        for (int i = 0, j = 0; j <= n; j++) {
            if (j < n && board.charAt(j) == board.charAt(i)) continue;
            if (j - i >= 3) return updateBoard(board.substring(0, i) + board.substring(j));
            else i = j;
        }
        return board;
    }

    static class Solution {
        static final int MAXSCORE = 6;

        public int findMinStep(String board, String hand) {
            Map<String, Integer> scoreMap = new HashMap<>();
            String status = board + "," + hand;
            if (backtrack(status, scoreMap) == MAXSCORE) return -1;
            return scoreMap.get(status);
        }

        private int backtrack(String status, Map<String, Integer> scoreMap) {
            if (status.charAt(status.length() - 1) == ',') return MAXSCORE;
            if (scoreMap.containsKey(status)) return scoreMap.get(status);

            String[] game = status.split(",");
            StringBuilder board = new StringBuilder(game[0]);
            StringBuilder hand = new StringBuilder(game[1]);
            int boardLen = board.length();
            int handLen = hand.length();

            int score = MAXSCORE;
            for (int i = 0; i < handLen; i++) {
                for (int j = 0; j <= boardLen; j++) {
                    board.insert(j, game[1].charAt(i));
                    String newBoard = updateBoard(board.toString());
                    if (newBoard.length() == 0) {
                        scoreMap.put(status, 1);
                        return 1;
                    }
                    hand.deleteCharAt(i);
                    score = Math.min(score, backtrack(newBoard + "," + hand.toString(), scoreMap) + 1);
                    board.deleteCharAt(j);
                    hand.insert(i, game[1].charAt(i));
                }
            }
            scoreMap.put(status, score);
            return score;
        }

        private String updateBoard(String board) {
            int n = board.length();
            for (int i = 0, j = 0; j <= n; j++) {
                if (j < n && board.charAt(j) == board.charAt(i)) continue;
                if (j - i >= 3) return updateBoard(board.substring(0, i) + board.substring(j));
                else i = j;
            }
            return board;
        }
    }
}















