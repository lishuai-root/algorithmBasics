package leetcode;

/**
 * @description: You are given an even integer n.
 * You initially have a permutation perm of size n where perm[i] == i (0-indexed).
 * <p>
 * In one operation, you will create a new array arr, and for each i:
 * <p>
 * If i % 2 == 0, then arr[i] = perm[i / 2].
 * If i % 2 == 1, then arr[i] = perm[n / 2 + (i - 1) / 2].
 * You will then assign arr to perm.
 * <p>
 * Return the minimum non-zero number of operations you need to perform on perm to return the permutation to its initial value.
 * @author: LISHUAI
 * @createDate: 2022/3/26 22:11
 * @version: 1.0
 */

public class LeetCode_1806 {

    public static void main(String[] args) {
//        int n = 4;
//        int i = reinitializePermutation(n);
//        System.out.println(i);

        for (int i = 2; i < 100; i += 2) {
            System.out.println(i + " : " + reinitializePermutation(i));
        }
    }

    public static int reinitializePermutation(int n) {
        int[] perm = new int[n], tmp = new int[n], t;
        int ans = 0, num = 1;
        for (int i = 0; i < n; i++) {
            perm[i] = i;
        }

        while (num > 0) {
            ans++;
            num = 0;
            for (int i = 0; i < n; i++) {

                if ((i & 1) == 0) {
                    tmp[i] = perm[i >>> 1];
                } else {

                    tmp[i] = perm[(n >>> 1) + (i - 1 >>> 1)];
                }
                if (tmp[i] != i) {
                    num++;
                }
            }
            t = perm;
            perm = tmp;
            tmp = t;
        }

        return ans;
    }

    /**
     * other people method
     */
    class Solution {
        public int reinitializePermutation(int n) {
            if (n == 2) return 1;
            int count = 0;
            int i = 1;
            // forward
            do {
                i = (i * 2) % (n - 1);
                count++;
            } while (i != 1);

//		// backward
//		do{
//            if(i % 2 == 0) i = i / 2;
//            else i = n / 2 + (i-1) / 2;
//            count++;
//        }while(i != 1);

            return count;

            // next position f(i)
            // f(i) = 2i, if i < n/2
            // f(i) = (i - (n/2))*2 + 1 = 2i - n + 1  = 2i - (n-1) if i >= n/2
            // thus f(i) = 2i mod n - 1
            // thus we need to find smallest k s.t. 2^k * i = i mod n - 1
            // note that choosing i = 1 since it if k satisfies 1, it satisfies other i \in {1, 2, ... , n- 2}
            // \phi(n-1) based on Euler's theorem is a solution, but it might not be smallest and we have k | \phi(n-1)
            // for example, if n = 22, \phi(n-1) = 12, but k = 6 is the smallest solution satisfying 2^k = 1 mod 21.
        }
    }
}
