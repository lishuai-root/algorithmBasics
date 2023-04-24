package leetcode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 * <p>
 * Return the number of pairs of different nodes that are unreachable from each other.
 * @author: LISHUAI
 * @createDate: 2023/3/26 19:15
 * @version: 1.0
 */

public class LeetCode_2316 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = "测".getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes) {
            System.out.print(b + " : ");
            for (int i = 7; i >= 0; i--) {
                if ((b & (1 << i)) == 0) {
                    System.out.print("0");
                } else {
                    System.out.print("1");
                }
            }
            System.out.println();
        }
        char c = 0;
        c = (char) (0xF & bytes[0]);
        c <<= 6;
        c |= (0x3F & bytes[1]);
        c <<= 6;
        c |= (0x3F & bytes[2]);
        System.out.println((int) c);
        System.out.println((int) '测');
        char cs = '测';
        byte b2 = (byte) (0x80 | (0x3F & cs));
        byte b1 = (byte) (0x80 | (0x3F & (cs >>> 6)));
        byte b0 = (byte) (0xE0 | (0x0F & (cs >>> 12)));
        System.out.println(b0 + "  " + b1 + " " + b2);

        System.out.println("-------------------------------GBK---------------------------------");
        byte[] bts = "我".getBytes("GBK");
        System.out.println(bts.length);
        for (byte b : bts) {
            System.out.print(b + " : ");
            for (int i = 7; i >= 0; i--) {
                if ((b & (1 << i)) == 0) {
                    System.out.print("0");
                } else {
                    System.out.print("1");
                }
            }
            System.out.println();
        }
        char cts = (char) ((bts[0] << 8) & 0x0000FF00 | (bts[1] & 0xFF));
        System.out.println(cts);
        System.out.println(Integer.toBinaryString(52946));
        System.out.println(Integer.toBinaryString(65490));
        System.out.println(Integer.toBinaryString(((bts[0] << 8))));
        System.out.println(Integer.toBinaryString(((bts[0] << 8) & 0x0000FF00 | (bts[1] & 0xFF))));
        String gbk2 = new String(bts, 0, bts.length, "GBK");
        System.out.println(gbk2);
        System.out.println((int) '我');
        String gbk = new String(new char[]{cts}, 0, 1);
        System.out.println(gbk);
        byte[] gbks = gbk.getBytes("UTF-8");
        System.out.println(Arrays.toString(gbks));
        String gbk1 = URLEncoder.encode(gbk, "UTF-8");
        System.out.println(gbk1);
        System.out.println((int) gbk2.charAt(0));
        System.out.println(Integer.toBinaryString(25105));
    }

    public static long countPairs(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] ints : edges) {
            uf.union(ints[0], ints[1]);
        }
        long ans = 0;
        Set<Integer> set = new HashSet<>(n);
        int k = n;
        for (int i = 0; i < n; i++) {
            int j = uf.find(i);
            if (!set.contains(j)) {
                int size = uf.size(j);
                k -= size;
                ans += ((long) k * size);
                set.add(j);
            }
        }
        return ans;
    }

    static class UF {
        int[] uf;
        int[] size;

        public UF(int size) {
            uf = new int[size];
            for (int i = 0; i < size; i++) {
                uf[i] = i;
            }
            this.size = new int[size];
            Arrays.fill(this.size, 1);
        }

        public void union(int p, int q) {
            p = find(p);
            q = find(q);
            if (p != q) {
                uf[q] = p;
                size[p] += size[q];
            }
        }

        public int find(int p) {
            return uf[p] == p ? p : (uf[p] = find(uf[p]));
        }

        public int size(int p) {
            return size[p];
        }
    }
}
