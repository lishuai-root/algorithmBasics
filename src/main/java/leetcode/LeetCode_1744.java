package leetcode;

/**
 * @description: You are given a (0-indexed) array of positive integers candiesCount where candiesCount[i] represents the number of candies of the ith type you have.
 * You are also given a 2D array queries where queries[i] = [favoriteTypei, favoriteDayi, dailyCapi].
 * <p>
 * You play a game with the following rules:
 * <p>
 * You start eating candies on day 0.
 * You cannot eat any candy of type i unless you have eaten all candies of type i - 1.
 * You must eat at least one candy per day until you have eaten all the candies.
 * Construct a boolean array answer such that answer.length == queries.length and answer[i] is true if you can eat a candy of type favoriteTypei on day favoriteDayi without eating more than dailyCapi candies on any day,
 * and false otherwise. Note that you can eat different types of candy on the same day, provided that you follow rule 2.
 * <p>
 * Return the constructed array answer.
 * <p>
 * <p>
 * 1- If you eat 2 candies (type 0) on day 0 and 2 candies (type 0) on day 1, you will eat a candy of type 0 on day 2.
 * 2- You can eat at most 4 candies each day.
 * If you eat 4 candies every day, you will eat 4 candies (type 0) on day 0 and 4 candies (type 0 and type 1) on day 1.
 * On day 2, you can only eat 4 candies (type 1 and type 2), so you cannot eat a candy of type 4 on day 2.
 * 3- If you eat 1 candy each day, you will eat a candy of type 2 on day 13.
 * @author: LISHUAI
 * @createDate: 2022/4/10 23:03
 * @version: 1.0
 */

public class LeetCode_1744 {

    public static boolean[] canEat(int[] candiesCount, int[][] queries) {
        return null;
    }
}
