package test;

import util.RandomString;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/12/26 20:48
 * @version: 1.0
 */

public class Test_008 {
    private static final String FILE_PATH = "E:\\All_workspace\\IDEA_workspace\\algorithmBasics\\src\\main\\resources\\custom.txt";
    private static final long FILE_SIZE = 40000000;
    private static final Set<String> SET = new HashSet<>();
    private static final Set<Integer> INTEGER_SET = new HashSet<>();
    private static final int[] BITS = new int[(Integer.MAX_VALUE >>> 5) * 2 + 2];
    private static final int SKIP = Integer.MAX_VALUE >>> 5 + 1;
    private static int removalSize = 0;
    private static String s;

    public static void main(String[] args) throws IOException {

//        fn_001();

        fn_002();

        System.out.println("set size : " + SET.size());

        System.out.println("INTEGER_SET size : " + INTEGER_SET.size());

        System.out.println("removalSize : " + removalSize);
    }

    private static void fn_002() throws FileNotFoundException {

        j(FILE_PATH);

//        f(FILE_PATH);
    }

    private static void fn_001() throws IOException {

        createRandomString(SET, FILE_SIZE, 16);
    }

    private static void f(String filePath) throws FileNotFoundException {

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {

            throw new NullPointerException("文件不存在。");
        }

        Scanner scanner = new Scanner(new FileReader(file));

        String line = null;

        String[] strs;

        while (scanner.hasNextLine()) {

            line = scanner.nextLine();

            strs = line.split(",");

            for (String s : strs) {

                removal(s.hashCode());

//                SET.add(s);
            }

        }

        scanner.close();
    }

    private static void j(String filePath) {

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {

            throw new NullPointerException("文件不存在。");
        }

        InputStream input = null;

        byte[] bytes = new byte[1024 * 1024 * 1024];

        int size, h = 0;

        try {

            input = new BufferedInputStream(new FileInputStream(file));

            while ((size = input.read(bytes)) != -1) {

                for (int i = 0; i < size; i++) {

                    if (44 == bytes[i]) {

                        removal(h ^ (h >>> 16));

                        h = 0;

                        continue;
                    }

                    h = 31 * h + (bytes[i] & 0xff);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (input != null) {

                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void removal(int code) {

        if (code == 0) {

            return;
        }

        int index = 0;

        if (code < 0) {

            code = -code;

            index = SKIP;
        }

//        INTEGER_SET.add(code);

        index += code >>> 5;

        int b = code & 31;

        if (b == 0) {

            index--;

            b = 32;
        }

        b--;

        if ((BITS[index] & (1 << b)) == 0) {

            BITS[index] |= (1 << b);

            removalSize++;
        }
    }

    private static void createRandomString(Set<String> set, long size, int strSize) throws IOException {

        if (strSize < 0) {

            throw new IllegalArgumentException("随机字符串大小不可小于零。");
        }

        String line;

        OutputStream out = createFileStream(FILE_PATH);

        for (int i = 1; i <= size - 1; i++) {

            line = RandomString.getStringNumber(0, strSize);

            set.add(line);

            out.write(line.getBytes());

            out.write(",".getBytes());

            if ((i & 15) == 0) {

                s = line;

                out.write("\n".getBytes());
            }
        }

        set.add(s);

        out.write(s.getBytes());

        out.write(",".getBytes());

        out.flush();

        boolean b = closeStream(out);
    }

    private static OutputStream createFileStream(String filePath) throws IOException {

        if (filePath == null || filePath.trim().equals("")) {

            throw new IllegalArgumentException("文件名称不可为空。");
        }

        OutputStream out = null;

        File file = new File(filePath);

        if (!file.exists()) {

            if (!file.createNewFile()) {

                throw new NullPointerException("文件不存在。");
            }
        }

        out = new BufferedOutputStream(new FileOutputStream(file), 1024 * 1024);

        return out;
    }

    private static boolean closeStream(Closeable stream) throws IOException {

        if (stream != null) {

            stream.close();
        }

        return true;
    }
}
