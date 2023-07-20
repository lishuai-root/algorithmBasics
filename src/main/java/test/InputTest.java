package test;

import java.io.*;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2023/3/20 20:33
 * @version: 1.0
 */

public class InputTest {

    public static void main(String[] args) throws IOException {
//        writeFile();
        lineFile();
//        File file = new File("C:\\Users\\是李帅啊\\Desktop\\inputTest.txt");
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            long start = System.currentTimeMillis();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                if (line.getBytes(StandardCharsets.UTF_8).length > 30) {
//                    int index = line.indexOf("（");
//                    int t = line.indexOf("，");
//                    index = t == -1 ? index : Math.min(index, t);
//                    t = line.indexOf(" ");
//                    index = t == -1 ? index : Math.min(index, t);
//                }
//            }
//            long end = System.currentTimeMillis();
//            System.out.println("time : " + (end - start));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private static void lineFile() throws IOException {
        File file = new File("C:\\Users\\是李帅啊\\Desktop\\inputTest.txt");
        File outFile = new File("C:\\Users\\是李帅啊\\Desktop\\inputTest_out.txt");
        if (!outFile.exists()) {
            outFile.createNewFile();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)))) {
            String line;
            long start = System.currentTimeMillis();
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write("\n");
            }
            writer.flush();
            long end = System.currentTimeMillis();
            System.out.println("time : " + (end - start));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeFile() {
        File file = new File("C:\\Users\\是李帅啊\\Desktop\\inputTest.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            long start = System.currentTimeMillis();
            String line = null;
            for (int i = 0; i < 5000000; i++) {
                writer.write("12345678910&这是李帅的大文件测试数据啊，好像还 不够大，所有（再加一点字符，让它变得更大。其实可以更大一点的。\n");
            }
            writer.flush();
            long end = System.currentTimeMillis();
            System.out.println("time : " + (end - start));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
