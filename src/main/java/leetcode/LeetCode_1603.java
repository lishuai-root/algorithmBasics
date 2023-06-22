package leetcode;

/**
 * @description: Design a parking system for a parking lot. The parking lot has three kinds of parking spaces: big, medium, and small, with a fixed number of slots for each size.
 * <p>
 * Implement the ParkingSystem class:
 * <p>
 * ParkingSystem(int big, int medium, int small) Initializes object of the ParkingSystem class. The number of slots for each parking space are given as part of the constructor.
 * bool addCar(int carType) Checks whether there is a parking space of carType for the car that wants to get into the parking lot. carType can be of three kinds: big, medium, or small, which are represented by 1, 2, and 3 respectively. A car can only park in a parking space of its carType. If there is no space available, return false, else park the car in that size space and return true.
 * @author: LiShuai
 * @createDate: 2023/5/29 21:15
 * @version: 1.0
 */

public class LeetCode_1603 {

    class ParkingSystem {

        private int[] slots;

        public ParkingSystem(int big, int medium, int small) {
            slots = new int[]{0, big, medium, small};
        }

        public boolean addCar(int carType) {
            if (slots[carType] > 0) {
                --slots[carType];
                return true;
            }
            return false;
        }
    }
}
