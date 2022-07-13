package test;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/6/16 19:30
 * @version: 1.0
 */

public class BigFileReadTest {

    private static int length = 0;

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//
//        fn_001();
//
//        long end = System.currentTimeMillis();
//
//        System.out.println("time : " + (end - start));

//        try {
//            fn_002();
//        } finally {
//            System.out.println(length);
//        }
        fn_003();
    }

    private static void fn_003() {

        URL resource = Thread.currentThread().getContextClassLoader().getResource("src/main/java/test");

        File file = new File("src/main/java/test");

        File[] files = file.listFiles();

        for (File f : files) {

            System.out.println(f.getAbsolutePath());
        }
    }

    private static void fn_002() {

        length++;

        fn_002();
    }

    @SneakyThrows
    private static void fn_001() {

        String fileName = "C:\\Users\\李帅\\Desktop\\big_file.txt";

        FileInputStream inputStream = new FileInputStream(new File(fileName));

        Scanner scanner = new Scanner(inputStream);

        String line = null;

        int size = 0;

        while (scanner.hasNextLine()) {

            line = scanner.nextLine();

            size = Math.max(size, line.getBytes().length);
        }

        System.out.println("max size : " + size);

    }

}
