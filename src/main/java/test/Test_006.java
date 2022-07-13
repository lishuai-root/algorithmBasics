package test;

import org.springframework.util.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/10/30 19:47
 * @version: 1.0
 */

public class Test_006 {

    public int result = 10;


    public Test_006() {

        System.out.println("create a new Test_006 Object ! ! !");
    }

    public static void main(String[] args) {

        String filePath = "E:\\资料\\test.lock";

//        rn_001(filePath);

        File file = new File(filePath);

        FileOutputStream out = null;

        FileChannel channel = null;

        FileLock lock = null;

        try {

            out = new FileOutputStream(file, true);

            channel = out.getChannel();

            lock = channel.lock(0, 10, false);

            int i = 0;

            while (!(new File("E:\\资料\\delete.lock").exists())) {

                out.write((i + "\n\r").getBytes());

                out.flush();

                i++;

                System.out.println(i);

                Thread.sleep(1000);
            }


        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {

                out.flush();

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

    private static void rn_001(String filePath) {

        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {

                    createNewFile(filePath);
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {

            threads[i].start();
        }
    }


    private static void createNewFile(String filePath) {

        File file = new File(filePath);

        Assert.notNull(file, "file must not be null");


        try {

            boolean newFile = file.createNewFile();

            System.out.println(Thread.currentThread().getName() + " --- " + newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
