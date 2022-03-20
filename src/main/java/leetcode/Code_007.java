package leetcode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/28 18:52
 * @version: 1.0
 */
public class Code_007 {

    public static void main(String[] args) {
    }

    public static int[] process(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
            return null;

        Set<Integer> set = new HashSet<Integer>();

        Set<Integer> s = new HashSet<>();

        int len = nums1.length;

        for (int i = 0; i < len; i++) {
            set.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i]))
                s.add(nums2[i]);
        }

        len = s.size();

        int[] acs = new int[len];

        Iterator<Integer> iterator = s.iterator();

        for (int i = 0; i < len; i++) {
            acs[i] = iterator.next();
        }

        return acs;
    }
}