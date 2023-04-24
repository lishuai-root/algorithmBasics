package leetcode;

import java.util.Arrays;

/**
 * @description: You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.
 * <p>
 * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.
 * <p>
 * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.
 * @author: LISHUAI
 * @createDate: 2023/1/31 19:08
 * @version: 1.0
 */

public class LeetCode_1626 {
    class Player {

        public int age;
        public int score;

        public Player(int age, int score) {
            this.age = age;
            this.score = score;
        }

    }


    class Solution {
        public int bestTeamScore(int[] scores, int[] ages) {

            Player[] players = new Player[scores.length];

            //group scores and ages in single data structure
            for (int i = 0; i < players.length; i++) {
                players[i] = new Player(ages[i], scores[i]);
            }

            //sort on the basis of ages, use score for breaking ties
            Arrays.sort(players, (player1, player2) -> player1.age == player2.age ? player1.score - player2.score : player1.age - player2.age);

            int dp[] = new int[players.length];

            //for one player, their score is max score.
            int max = dp[0] = players[0].score;

            for (int i = 1; i < players.length; i++) {

                dp[i] = players[i].score;
                //for one player their score is max score
                for (int j = 0; j < i; j++) {

                    //for all player with ages less than this player
                    if (players[j].score <= players[i].score) {

                        //if the score is less than this player than add the score till those player in case we included any of those players as well in the team

                        //note we're maximizing the inclusion instead of straight up doing summation

                        //because a player that is valid for this player might not be valid for another player that is also valid for this player

                        //because dp[j] simple considers the case where it was the last player till that position and the score of the team after chosing that player and removing other players.

                        //which means each dp[j] in itself is a valid answer till that point with all the info, so we just need to take the maximum

                        //For the rest of the valid players which will lie ahead of it, second loop will handle the things.

                        dp[i] = Math.max(dp[i], dp[j] + players[i].score);

                    }

                }
                //maximize for the inclusion of every single player
                max = Math.max(max, dp[i]);

            }


            return max;
        }
    }
}
