package leetcode;

import java.util.HashMap;

/**
 * @description: Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * <p>
 * A good array is an array where the number of different integers in that array is exactly k.
 * <p>
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 * @author: LISHUAI
 * @createDate: 2022/6/10 19:48
 * @version: 1.0
 */

public class LeetCode_992 {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 1, 2, 3};
//        int k = 2;
        int[] nums = {5, 7, 5, 2, 3, 3, 4, 1, 5, 2, 7, 4, 6, 6, 3, 3, 4, 4, 7};
        int k = 7;
//        int[] nums = {1, 2, 1, 3, 4};
//        int k = 3;
//        int[] nums = {2, 1, 1, 1, 2};
//        int k = 1;
//        int[] nums = {1, 1, 1, 2, 1, 2, 2, 1};
//        int k = 1;
//        int[] nums = {2, 2, 1, 2, 2, 2, 1, 1};
//        int k = 2;
        int i = subarraysWithKDistinct(nums, k);
        System.out.println(i);
    }

    public static int subarraysWithKDistinct(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len + 1];
        int[] mr = new int[len + 1];
        int count = 0, ans = 0, left = 0, right = 0;
        int minCount = 0, minIndex = 0;

        while (right < len) {
            if (sum[nums[right]] == 0) {
                count++;
            }
            sum[nums[right]]++;
            if (mr[nums[right]] == 0) {
                minCount++;
            }
            mr[nums[right]]++;
            while (minCount >= k) {
                if (mr[nums[minIndex]] == 1 && minCount == k) {
                    break;
                }
                if (mr[nums[minIndex]] == 1) {
                    minCount--;
                }
                mr[nums[minIndex++]]--;
            }

            while (count > k) {
                if (sum[nums[left]] == 1) {
                    count--;
                }
                sum[nums[left++]]--;
            }

            if (count == k) {
                ans += (minIndex - left + 1);
            }
            right++;
        }

        return ans;
    }


    /**
     * other people method
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraysWithKDistinct_03(int[] nums, int k) {
        return atmost(nums, k) - atmost(nums, k - 1);
    }


    public static int atmost(int[] nums, int k) {
        //j=start  &  i= curr=0 ;
        int i = 0;
        int j = 0;
        int size = nums.length;

        //Hashmap to store number of times the  element occurs in the nums array from [start,curr]
        HashMap<Integer, Integer> map = new HashMap<>();

        //count to store all possible Subarray with atmost K different Integer
        int count = 0;

        while (i < size) {
            // checking weather curr element Index already occurred or not  in the range [i,j]
            if (map.containsKey(nums[i])) {

                //if true we will get the prevIndex of the current element and increase  it occurance by 1 since there is no change in the size of hashmap;
                int val = map.get(nums[i]);
                map.put(nums[i], val + 1);
            } else {

                // storing the curr element Index that  has not occured yet
                map.put(nums[i], 1);

                //if size  of the hashmap  is greater than k which mean there are more than k element in the range [i,j]
                //so we will check the total occurance of element at start pointer and try to increment the start pointer till the time  size of hashmap  is lests than k

                while (map.size() > k) {
                    if (map.get(nums[j]) == 1) {
                        map.remove(nums[j]);
                    } else {
                        int temp = map.get(nums[j]);

                        map.put(nums[j], temp - 1);
                    }
                    j++;
                }
            }
            //counting all possible Subarray with atmost K different Integers:
            count += (i - j + 1);
            i++;


        }
        return count;

    }
}
