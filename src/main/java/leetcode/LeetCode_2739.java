package leetcode;

/**
 * @description: A truck has two fuel tanks. You are given two integers, mainTank representing the fuel present in the main tank in liters and additionalTank representing the fuel present in the additional tank in liters.
 * <p>
 * The truck has a mileage of 10 km per liter. Whenever 5 liters of fuel get used up in the main tank, if the additional tank has at least 1 liters of fuel, 1 liters of fuel will be transferred from the additional tank to the main tank.
 * <p>
 * Return the maximum distance which can be traveled.
 * <p>
 * Note: Injection from the additional tank is not continuous. It happens suddenly and immediately for every 5 liters consumed.
 * @author: LiShuai
 * @createDate: 2023/6/23 22:38
 * @version: 1.0
 */

public class LeetCode_2739 {

    public static int distanceTraveled(int mainTank, int additionalTank) {
        int ans = 0;
        while (mainTank >= 5 && additionalTank > 0) {
            int t = mainTank / 5;
            int p = mainTank % 5;
            ans += t * 50;
            t = Math.min(additionalTank, t);
            mainTank = p + t;
            additionalTank -= t;
        }
        ans += mainTank * 10;
        return ans;
    }
}
