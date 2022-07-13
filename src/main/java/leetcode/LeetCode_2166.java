package leetcode;

import java.util.Arrays;

/**
 * @description: A Bitset is a data structure that compactly stores bits.
 * <p>
 * Implement the Bitset class:
 * <p>
 * Bitset(int size) Initializes the Bitset with size bits, all of which are 0.
 * void fix(int idx) Updates the value of the bit at the index idx to 1. If the value was already 1, no change occurs.
 * void unfix(int idx) Updates the value of the bit at the index idx to 0. If the value was already 0, no change occurs.
 * void flip() Flips the values of each bit in the Bitset. In other words, all bits with value 0 will now have value 1 and vice versa.
 * boolean all() Checks if the value of each bit in the Bitset is 1. Returns true if it satisfies the condition, false otherwise.
 * boolean one() Checks if there is at least one bit in the Bitset with value 1. Returns true if it satisfies the condition, false otherwise.
 * int count() Returns the total number of bits in the Bitset which have value 1.
 * String toString() Returns the current composition of the Bitset. Note that in the resultant string, the character at the ith index should coincide with the value at the ith bit of the Bitset.
 * @author: LISHUAI
 * @createDate: 2022/4/24 18:54
 * @version: 1.0
 */

public class LeetCode_2166 {

    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println((i / 32) + " : " + (i & 31));
//        }
//        System.out.println((39 + 31) >>> 5);
//        System.out.println(0xFFFFFFFF);
//        System.out.println(~0);
//        System.out.println(39 >>> 5);

//        for (int i = 0; i < 32; i++) {
//            System.out.println((1 << i) / 2);
//        }

//        System.out.println(Integer.toBinaryString(3));
//        System.out.println(Integer.bitCount(3));
        char[] ABC = new char[32];
        Arrays.fill(ABC, '0');
        int size = 100000;
        int[] bits = new int[(size + 31) >>> 5];
        long start = System.currentTimeMillis();
        String s = toString(bits, size, ABC);
        long end = System.currentTimeMillis();
        System.out.println("times : " + (end - start));

        Arrays.fill(bits, -1);

        start = System.currentTimeMillis();
        String s1 = toString(bits, size, ABC);
        end = System.currentTimeMillis();
        System.out.println("times : " + (end - start));
        System.out.println(s.equals(s1));

//        System.out.println(s);
//        System.out.println(s1);

        Bitset bitset = new Bitset(100000);
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            bitset.fix(i);
            bitset.unfix(i);
            bitset.flip();
            if (i % 100 == 0) {
                bitset.toString();
            }

        }
        end = System.currentTimeMillis();
        System.out.println("times : " + (end - start));
    }


    public static String toString(int[] bits, int size, char[] ABC) {
        StringBuilder sbr = new StringBuilder();
        int s = size;

        for (int bit : bits) {

            int cur = bit;
            int c, k = 0;
            while ((c = -cur & cur) != 0) {
                int y = Integer.bitCount(c - 1);
                if (y >= s) {
                    break;
                }
                sbr.append(ABC, k, y - k)
                        .append('1');
                k = y + 1;
                cur ^= c;
            }
            sbr.append(ABC, k, Math.min(32 - k, s - k));
            s -= 32;
        }

        return sbr.toString();
    }

    static class Bitset {

        private char[] ABC;
        private char[] DEF;

        private int size, bitOne = 0;

        public Bitset(int size) {
            ABC = new char[size];
            DEF = new char[size];
            Arrays.fill(ABC, '0');
            Arrays.fill(DEF, '1');
            this.size = size;
        }

        public void fix(int idx) {
            if (ABC[idx] == '0') {
                ABC[idx] = '1';
                DEF[idx] = '0';
                ++bitOne;
            }
        }

        public void unfix(int idx) {
            if (ABC[idx] == '1') {
                ABC[idx] = '0';
                DEF[idx] = '1';
                --bitOne;
            }
        }

        public void flip() {
            char[] chars = ABC;
            ABC = DEF;
            DEF = chars;
            bitOne = size - bitOne;
        }

        public boolean all() {
            return size == bitOne;
        }

        public boolean one() {
            return bitOne > 0;
        }

        public int count() {
            return bitOne;
        }

        @Override
        public String toString() {
            return new String(ABC);
        }


//        public String toString_02() {
//            char[] chars = new char[size];
//            Arrays.fill(chars, '0');
//
//            for (int i = 0; i < bits.length; i++) {
//                int cur = bits[i];
//                int c;
//                while ((c = -cur & cur) != 0) {
//                    int y = Integer.bitCount(c - 1);
//                    if ((i << 5) + y >= size) {
//                        break;
//                    }
//                    chars[(i << 5) + y] = '1';
//                    cur ^= c;
//                }
//            }
//            return new String(chars);
//        }
    }
}


class Bitset {

    private final char[] ABC = new char[32];
    private int[] bits;
    private int size, bitOne = 0, len;

    public Bitset(int size) {
        Arrays.fill(ABC, '0');
        len = size >>> 5;
        bits = new int[(size + 31) >>> 5];
        this.size = size;
    }

    public void fix(int idx) {
        int x = idx >>> 5;
        int y = idx & 31;
        if ((bits[x] & (1 << y)) == 0) {
            ++bitOne;
            bits[x] |= (1 << y);
        }
//            bits[x] |= (1 << y);
    }

    public void unfix(int idx) {
        int x = idx >>> 5;
        int y = idx & 31;
        if ((bits[x] & (1 << y)) != 0) {
            --bitOne;
            bits[x] ^= (1 << y);
        }
    }

    public void flip() {
        for (int i = 0; i < bits.length; i++) {
            bits[i] = ~bits[i];
        }
        bitOne = size - bitOne;
    }

    public boolean all() {
        return size == bitOne;
    }

    public boolean one() {
        return bitOne > 0;
    }

    public int count() {
        return bitOne;
    }

    @Override
    public String toString() {
        char[] chars = new char[size];
        Arrays.fill(chars, '0');

        for (int i = 0; i < bits.length; i++) {
            int cur = bits[i];
            int c;
            while ((c = -cur & cur) != 0) {
                int y = Integer.bitCount(c - 1);
                if ((i << 5) + y >= size) {
                    break;
                }
                chars[(i << 5) + y] = '1';
                cur ^= c;
            }
        }
        return new String(chars);
    }
}
