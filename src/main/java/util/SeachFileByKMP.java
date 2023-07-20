package util;

import lombok.SneakyThrows;

import java.io.*;
import java.util.Random;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2022/8/30 20:12
 * @version: 1.0
 */

public class SeachFileByKMP {


    @SneakyThrows
    public static void main(String[] args) {
//        writeFile();
        File file = new File("E:\\All_workspace\\IDEA_workspace\\bigFile.txt");
        String ps = "nsfklfqxnlpoiuczheyzbmqluveupnufusqjsjuleuc";
        long start = System.currentTimeMillis();
        int i = searchFileByKMP(file, ps);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(i);
//        long start = System.currentTimeMillis();
//        fn_001();
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//        start = System.currentTimeMillis();
//        fn_002();
//        end = System.currentTimeMillis();
//        System.out.println(end - start);
    }

    private static void fn_001() {
        File file = new File("E:\\All_workspace\\IDEA_workspace\\bigFile.txt");
        try (InputStream input = new FileInputStream(file)) {
            byte[] buffer = new byte[2048 << 2];
            int index;
            while ((index = input.read(buffer)) != -1) {

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fn_002() {
        String line = null;
        File file = new File("E:\\All_workspace\\IDEA_workspace\\bigFile.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeFile() {
        File file = new File("E:\\All_workspace\\IDEA_workspace\\bigFile.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            Random random = new Random();
            for (int j = 0; j < 20; j++) {
                for (int i = 0; i < 100000000; i++) {
                    int i1 = random.nextInt(26);
                    writer.write(((char) ('a' + i1)));
                    if (i % 1000 == 0) {
                        writer.write("\n");
                    }
                }
            }

            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int searchFileByKMP(File file, String ps) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;
            int[] next = getNext(ps);
            int i = 0, j = 0, ans = 0, len = 0;
            char[] p = ps.toCharArray();
            while (j < p.length && (line = reader.readLine()) != null) {
                char[] chars = line.toCharArray();
                i = 0;
                while (i < chars.length && j < p.length) {
                    if (j == -1 || chars[i] == p[j]) {
                        ans = Math.max(ans, j + 1);
                        ++i;
                        ++j;
                    } else {
                        j = next[j];
                    }
                    if (j == p.length) {
                        System.out.println(len + i - j);
                        j = 0;
                    }
                }
                len += i;
            }
            if (j == p.length) {
                return len - j;
            } else {
                return -1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int KMP(String ts, String ps) {

        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();
        int i = 0; // 主串的位置
        int j = 0; // 模式串的位置
        int[] next = getNext(ps);
        int ans = 0;
        while (i < t.length && j < p.length) {
            if (j == -1 || t[i] == p[j]) { // 当j为-1时，要移动的是i，当然j也要归0
                ans = Math.max(ans, j + 1);
                i++;
                j++;
            } else {
                // i不需要回溯了
                // i = i - j + 1;
                j = next[j]; // j回到指定位置
            }
        }
//        System.out.println(ans);
        if (j == p.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;

    }
}
