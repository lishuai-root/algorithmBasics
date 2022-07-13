package bitOperation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/5/31 22:12
 * @version: 1.0
 */
public class BitArray {

    private static int[] arrays = {1, 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 8, 9, 9};

    private static int[] arr1 = new int[]{1, 4, 5, 6, 7};

    private static int[] arr2 = new int[]{2, 3, 9, 12, 13};

    public static void main(String[] args) {

        int[] ints = makeArray(1000000000);

        long start = System.currentTimeMillis();
        int[] ints1 = fn_001(ints);
        long end = System.currentTimeMillis();

        System.out.println("times : " + (end - start));

        start = System.currentTimeMillis();
        int[] ints2 = fn_003(ints);
        end = System.currentTimeMillis();

        System.out.println("times : " + (end - start));

    }

    public static int[] makeArray(int size) {

        int[] arr = new int[size + 2];

        for (int i = 0; i < size; i++) {

            if ((i & 1) == 1) {

                arr[i] = i - 1;
            } else {

                arr[i] = i;
            }
        }

        arr[size] = size;

        arr[size + 1] = size - 1;

        return arr;
    }


    /**
     * 给定一个数组，其中有两种数出现了奇数次，其他数字都出现了偶数次，找出两个出现了奇数次的数
     * <p>
     * 1.将数组中的所有数字进行亦或运算，得到eor
     * <p>
     * 2.因为两个出现奇数次的数字不相等，所以eor的结果一定不为0
     * <p>
     * 3.找出eor中某一位为1的位(此处取最右侧为1的位)，将整个数组分为两类
     * 亦或结果为1，代表改为上两个数组一定不相等，一个为1，一个为0
     * 3.1:第一类是该位为0的
     * 3.2:第二类是该位为1的
     * 两个出现了奇数次的数一定或被分开在这两类中
     * <p>
     * 4.将这两类数中其中任意一类进行亦或运算，得到eorOne
     * <p>
     * 5.eorOne就是出现了奇数次的两个数中的一个，然后将eor和eorOne进行亦或得到另外一个数
     *
     * @param arr
     */
    private static int[] fn_001(int[] arr) {

        int eor = 0, eorOne = 0, right = 0;

        int[] nums = new int[2];

        for (int i : arr)
            eor = eor ^ i;

        right = eor & (-eor);   //  取出eor中最右侧为1的位

        for (int i : arr) {
            if ((i & right) == 0)
                eorOne = eorOne ^ i;    //  数组中指定位为0的数亦或
        }

//        System.out.println(eorOne + " , " + (eorOne ^ eor));

        nums[0] = eorOne;
        nums[1] = eorOne ^ eor;

        return nums;
    }

    private static int[] fn_003(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();

        int[] nums = new int[2];

        int index = -1;

        for (int i = 0; i < arr.length; i++) {

            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        Set<Integer> integers = map.keySet();

        Iterator<Integer> iterator = integers.iterator();

        while (index != 1 && iterator.hasNext()) {

            Integer next = iterator.next();

            if ((map.get(next) & 1) == 1) {

                nums[++index] = next;
            }
        }

        return nums;
    }

    /**
     * leetcode : 4
     * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     * <p>
     * 使用归并求解
     *
     * @param nums1
     * @param nums2
     */
    private static double fn_002(int[] nums1, int[] nums2) {

        int leftLen = nums1.length;

        int rightLen = nums2.length;

        int[] merge = null;

        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) return 0d;

        if (nums1 == null || nums1.length == 0) merge = nums2;
        else if (nums2 == null || nums2.length == 0) merge = nums1;

        if ((nums1 == null || nums1.length == 0) || (nums2 == null || nums2.length == 0)) {
            if (merge == null || merge.length == 0) return 0d;
            else if (merge.length == 1) return merge[0];
        }

        merge = new int[leftLen + rightLen];

        int left = 0, right = 0, mergeIndex = 0;

        while (left < leftLen && right < rightLen) {
            if (nums1[left] < nums2[right])
                merge[mergeIndex++] = nums1[left++];
            else
                merge[mergeIndex++] = nums2[right++];
        }

        while (left < leftLen)
            merge[mergeIndex++] = nums1[left++];

        while (right < rightLen)
            merge[mergeIndex++] = nums2[right++];

        if ((mergeIndex = merge.length) % 2 == 0)
            return ((double) merge[mergeIndex >> 1] + (double) merge[(mergeIndex >> 1) - 1]) / 2;
        else
            return merge[mergeIndex >> 1];

    }


    /**
     * leetcode : 4
     * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     * <p>
     *
     * @param nums1
     * @param nums2
     */
    private static double fn_003(int[] nums1, int[] nums2) {

        int leftLen = nums1.length;

        int rightLen = nums2.length;

        int allLen = leftLen + rightLen;

        int left = 0, right = 0, index = allLen >> 1;

        if (allLen % 2 == 0)
            index = (allLen >> 1) - 1;

        System.out.println("allLen  :  " + index);

        int[] merge = null;

        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) return 0d;

        if (nums1 == null || nums1.length == 0) merge = nums2;
        else if (nums2 == null || nums2.length == 0) merge = nums1;

        if ((nums1 == null || nums1.length == 0) || (nums2 == null || nums2.length == 0)) {
            if (merge == null || merge.length == 0) return 0d;
            else if (merge.length == 1) return merge[0];
        }

        while (left < leftLen && right < rightLen) {
            if (nums1[left] < nums2[right]) {
                if (left + right == index && allLen % 2 == 0) {
                    System.out.println("left  " + left);
                    System.out.println("left  " + right);
                    if (left + 1 == leftLen)
                        return ((double) (nums1[left] + nums2[right])) / 2;
                    else
                        return ((double) nums1[left] + (double) Math.min(nums1[left + 1], nums2[right])) / 2;
                } else if (left + right == index && allLen % 2 != 0) {
                    return nums1[left];
                } else
                    left++;
            } else if (nums1[left] > nums2[right]) {
                if (left + right == index && allLen % 2 == 0) {
                    System.out.println("right  " + left);
                    System.out.println("right  " + right);
                    if (right + 1 == rightLen)
                        return ((double) (nums2[right] + nums1[left])) / 2;
                    else
                        return ((double) nums2[right] + (double) Math.min(nums1[left], nums2[right + 1])) / 2;
                } else if (left + right == index && allLen % 2 != 0) {
                    return nums2[right];
                } else
                    right++;
            } else {
                if (allLen % 2 == 0) {
                    System.out.println("right  " + left);
                    System.out.println("right  " + right);
                    if (right + 1 == rightLen)
                        return ((double) (nums2[right] + nums1[left])) / 2;
                    else
                        return ((double) nums2[right] + (double) Math.min(nums1[left], nums2[right + 1])) / 2;
                } else if (allLen % 2 != 0) {
                    return nums2[right];
                }

                left++;
            }

        }


        while (left < leftLen) {
            if (left + right == index && allLen % 2 == 0) {
                return ((double) (nums1[left] + nums1[left + 1])) / 2;
            } else if (left == index && allLen % 2 != 0) {
                return nums1[left];
            } else
                left++;
        }


        while (right < rightLen) {
            if (left + right == index && allLen % 2 == 0) {
                return ((double) (nums2[right] + nums2[right + 1])) / 2;
            } else if (right == index && allLen % 2 != 0) {
                return nums2[right];
            } else
                right++;
        }

        return -1;

    }

}
