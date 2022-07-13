package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/11/8 22:18
 * @version: 1.0
 */

public class Test_005 {

    public static void main(String[] args) {

//        String filePath = "E:\\资料\\test.lock";
//
//        fn_001();

        System.out.println(",".getBytes());

    }

    public static void fn_001() {

        String filePath = "E:\\资料\\test.lock";

//        rn_001(filePath);

        File file = new File(filePath);

        FileOutputStream out = null;

        FileChannel channel = null;

        FileLock lock = null;

        try {

            out = new FileOutputStream(file, true);

            channel = out.getChannel();

            lock = channel.lock(9, 20, false);

            if (lock == null) {

                System.out.println("lock : " + lock);
            }

            int i = 0;

            while (!(new File("E:\\资料\\delete1.lock").exists())) {

                out.write((i + " --- out\n\r").getBytes());

                out.flush();

                i++;

                System.out.println(i);

                Thread.sleep(1000);
            }


        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (lock != null) {

                    lock.release();

                    lock.close();
                }

                if (channel != null) {

                    channel.close();
                }

                if (out != null) {

                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
