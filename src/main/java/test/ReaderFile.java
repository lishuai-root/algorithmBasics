package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/9/17 22:43
 * @version: 1.0
 */

public class ReaderFile {

    public static void main(String[] args) {
        fn_001();
    }

    private static void fn_001() {

        String path = "C:\\Users\\是李帅啊\\Desktop\\new 1.txt";

        Scanner scanner = null;

        String[] line = null;


        try {
            scanner = new Scanner(new FileInputStream(path));

            while (scanner.hasNextLine()) {

                path = scanner.nextLine();

                line = path.split(" ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {

                if (scanner != null) {

                    scanner.close();
                }
            } catch (Exception e) {

            }
        }
    }
}
