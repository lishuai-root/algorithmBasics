package leetcode;

/**
 * @description: There are buckets buckets of liquid, where exactly one of the buckets is poisonous. To figure out which one is poisonous, you feed some number of (poor) pigs the liquid to see whether they will die or not. Unfortunately, you only have minutesToTest minutes to determine which bucket is poisonous.
 * <p>
 * You can feed the pigs according to these steps:
 * <p>
 * Choose some live pigs to feed.
 * For each pig, choose which buckets to feed it. The pig will consume all the chosen buckets simultaneously and will take no time.
 * Wait for minutesToDie minutes. You may not feed any other pigs during this time.
 * After minutesToDie minutes have passed, any pigs that have been fed the poisonous bucket will die, and all others will survive.
 * Repeat this process until you run out of time.
 * Given buckets, minutesToDie, and minutesToTest, return the minimum number of pigs needed to figure out which bucket is poisonous within the allotted time.
 * @author: LISHUAI
 * @createDate: 2022/8/6 17:21
 * @version: 1.0
 */

public class LeetCode_458 {

    public static void main(String[] args) {
//        int buckets = 1000, minutesToDie = 15, minutesToTest = 60;
        int buckets = 5, minutesToDie = 15, minutesToTest = 60;
//        int buckets = 4, minutesToDie = 15, minutesToTest = 15;
        int i = poorPigs(buckets, minutesToDie, minutesToTest);
        System.out.println(i);
        System.out.println(Math.pow(2, 3));
    }

    public static int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int k = minutesToTest / minutesToDie + 1;
        int ans = 0, sum = 1;
        while (sum < buckets) {
            sum *= k;
            ans++;
        }
        return ans;
    }

    public int poorPigs_other(int buckets, int tdie, int ttest) {
        int test = ttest / tdie;
        int i = 0;
        while (Math.pow(test + 1, i) < buckets) {
            i++;
        }
        return i;
    }
}
