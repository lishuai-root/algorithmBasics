package leetcode;

/**
 * @description: (This problem is an interactive problem.)
 * <p>
 * You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
 * <p>
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 * <p>
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * @author: LISHUAI
 * @createDate: 2022/6/25 21:00
 * @version: 1.0
 */

public class LeetCode_1095 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83, 82};
        int target = 1;
        MountainArray mountain = getMountain(nums);
        int inMountainArray = findInMountainArray(target, mountain);
        System.out.println(inMountainArray);
    }

    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        int max = getMid(mountainArr, 0, mountainArr.length() - 1);
        int index = getIndex(mountainArr, 0, max, target, 1);
        if (index == -1) {
            index = getIndex(mountainArr, max + 1, len - 1, target, -1);
        }
        return index;
    }

    private static int getMid(MountainArray mountainArr, int left, int right) {
        int mid = right;
        while (left <= right) {
            mid = left + ((right - left) >>> 1);
            int cur = mountainArr.get(mid);
            int l = mid - 1 >= left ? mountainArr.get(mid - 1) : Integer.MIN_VALUE;
            int r = mid + 1 <= right ? mountainArr.get(mid + 1) : Integer.MAX_VALUE;
            if (cur > l && cur > r) {
                return mid;
            } else if (cur < r) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return mid;
    }

    private static int getIndex(MountainArray mountainArr, int left, int right, int target, int k) {
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >>> 1);
            int c = (mountainArr.get(mid) - target) * k;
            if (c == 0) {
                break;
            } else if (c > 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return mountainArr.get(mid) == target ? mid : -1;
    }

    private static MountainArray getMountain(int[] nums) {
        return new MountainArray() {
            int[] arr = nums;

            @Override
            public int get(int index) {
                return arr[index];
            }

            @Override
            public int length() {
                return arr.length;
            }
        };
    }

    static interface MountainArray {
        public int get(int index);

        public int length();
    }
}
