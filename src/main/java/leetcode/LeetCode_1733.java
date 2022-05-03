package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description: On a social network consisting of m users and some friendships between users,
 * two users can communicate with each other if they know a common language.
 * <p>
 * You are given an integer n, an array languages, and an array friendships where:
 * <p>
 * There are n languages numbered 1 through n,
 * languages[i] is the set of languages the i(th) user knows, and
 * friendships[i] = [ui,vi] denotes a friendship between the users ui and vi.
 * You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.
 * <p>
 * Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.
 * @author: LISHUAI
 * @createDate: 2022/3/26 15:34
 * @version: 1.0
 */

public class LeetCode_1733 {

    public static void main(String[] args) {
//        int[][] languages = {{1}, {2}, {1, 2}}, friendships = {{1, 2}, {1, 3}, {2, 3}};
//        int n = 2;
        int[][] languages = {{2}, {1, 3}, {1, 2}, {3}}, friendships = {{1, 4}, {1, 2}, {3, 4}, {2, 3}};
        int n = 3;
        int i = minimumTeachings(n, languages, friendships);
        System.out.println(i);
    }

    public static int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> nums = new HashMap<>();
        for (int[] ints : friendships) {
            Set<Integer> set;
            if ((set = map.get(ints[0])) == null) {
                set = new HashSet<>();
                map.put(ints[0], set);
            }
            for (int i : languages[ints[0] - 1]) {
                set.add(i);
            }

            if ((set = map.get(ints[1])) == null) {
                set = new HashSet<>();
                map.put(ints[1], set);
            }
            for (int i : languages[ints[1] - 1]) {
                set.add(i);
            }
        }

        a:
        for (int[] ints : friendships) {
            Set<Integer> set = map.get(ints[1]);
            for (int i : languages[ints[0] - 1]) {
                if (set.contains(i)) {
                    continue a;
                }
            }

            if (!nums.containsKey(ints[0])) {
                nums.put(ints[0], new HashMap<>());
            }
            if (!nums.containsKey(ints[1])) {
                nums.put(ints[1], new HashMap<>());
            }
            Map<Integer, Integer> m = nums.get(ints[1]);
            for (int i : languages[ints[0] - 1]) {
                m.put(i, m.getOrDefault(i, 0) + 1);
            }

            m = nums.get(ints[0]);
            for (int i : languages[ints[1] - 1]) {
                m.put(i, m.getOrDefault(i, 0) + 1);
            }

        }

        return 0;
    }

    /**
     * other people method
     */
    class Solution {

        /**
         * Algorithm:
         * <p>
         * 1. We will initialize the variable 'minimumUsers' = total users, and it will store the minimum number of users that needs to learn the common language.
         * 2. Maintain a user Map, which stores users who cannot communicate with their friends.
         * Check if users 'user1' and 'user2' shares a common language.
         * If both users do not share a common language, then insert both users in the Map.
         * 3. Iterate thru the list of languages,
         * We will set the variable 'totalUsers' as 0, which will store the total number of users need to learn a common languge.
         * Iterate it over the user MAP
         * Check if the user at it cannot speak a language, then increment 'totalUsers' by 1.
         * If 'minimumUsers' is more than 'totalUsers', then update 'minimumUsers' with 'totalUsers'.
         * 4. Return the variable 'minimumUsers'.
         * <p>
         * Time Complexity: O(NxM)
         * N: number of languages
         * M: number of users
         * Space Complexity: O(NxM)
         **/


        public int minimumTeachings(int n, int[][] languages, int[][] friendships) {

            //minimumUsers first start with all users
            //and will be updated to store the minimum number of users to teach
            int minimumUsers = friendships.length;

            //Map to store User and the Languagues that user can speak.
            Map<Integer, Set<Integer>> user_languages = new HashMap<>();
            for (int user = 0; user < languages.length; user++) {
                Set<Integer> lst_languages = new HashSet<>();
                for (int language = 0; language < languages[user].length; language++) {
                    lst_languages.add(languages[user][language]);
                }
                user_languages.put(user + 1, lst_languages); //user+1 because the no. of users start with 1 rather than 0
            }

            //Map to store User and the his friends who he cant communicate with.
            Map<Integer, Set<Integer>> user_friends = new HashMap<>();
            for (int i = 0; i < friendships.length; i++) {
                int[] friends = friendships[i];
                int user1 = friends[0];
                int user2 = friends[1];

                if (!canCommunicate(user_languages.get(user1), user_languages.get(user2))) {
                    //store user1
                    user_friends.putIfAbsent(user1, new HashSet<>());
                    user_friends.get(user1).add(user2);

                    //store user2
                    user_friends.putIfAbsent(user2, new HashSet<>());
                    user_friends.get(user2).add(user1);
                }

            }

            //loop thru every language and check to see if a user need to learn a common langue
            for (int language = 1; language <= n; language++) {
                //the variable 'totalUsers' as 0,
                //which will store the total number of users needs to learn a common language
                int totalUsers = 0;
                for (int user : user_friends.keySet()) {
                    if (!user_languages.get(user).contains(language)) {
                        totalUsers++;
                    }
                }
                minimumUsers = Math.min(minimumUsers, totalUsers);
            }
            return minimumUsers;

        }

        private boolean canCommunicate(Set<Integer> user1_languages, Set<Integer> user2_languages) {
            for (int user2_language : user2_languages) {
                if (user1_languages.contains(user2_language)) {
                    return true;
                }
            }
            return false;
        }
    }
}
