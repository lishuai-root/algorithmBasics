package leetcode;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2021/8/3 20:54
 * @version: 1.0
 */

public class Test {
    public static void main(String[] args) throws Exception {
//        Method method = Test.class.getDeclaredMethod("testMethod", new Class<?>[]{String.class, int.class});
//        System.out.println(method.hashCode());
//        Method method1 = Test.class.getDeclaredMethod("testMethod", new Class<?>[]{String.class, int.class});
//        System.out.println(method.hashCode());
//        System.out.println(method == method1);
//        System.out.println(method.equals(method1));
//        System.out.println(Thread.currentThread().getContextClassLoader());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getContextClassLoader());
//            }
//        }).start();
//
//        Thread.sleep(1000);

//        for (int i = 0; i < 2; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        ServerSocket serverSocket = new ServerSocket();
////                        System.out.println(serverSocket.getReuseAddress());
//                        serverSocket.setReuseAddress(true);
//                        serverSocket.bind(new InetSocketAddress("127.0.0.1", 8088));
//                        System.out.println(Thread.currentThread().getName() + " started");
//                        while (true) {
//                            Socket socket = serverSocket.accept();
//                            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                            String line = reader.readLine();
//                            if (line != null) {
//                                System.out.println(Thread.currentThread().getName() + " : " + line);
//                                if ("exit".equals(line)) {
//                                    break;
//                                }
//                            }
//                        }
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }).start();
//        }

        ServerSocket serverSocket = new ServerSocket();
        ServerSocket serverSocket1 = new ServerSocket();
//                        System.out.println(serverSocket.getReuseAddress());
//        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 8088));
        Socket accept = serverSocket.accept();
//        int read = accept.getInputStream().read();
//        System.out.println(read);
//        accept.getOutputStream().write('c');
        serverSocket.close();
        System.out.println("close");
//        serverSocket1.setReuseAddress(true);
//        serverSocket1.bind(new InetSocketAddress("127.0.0.1", 8088));
//        System.out.println("accept");
////        serverSocket1.close();
//        Socket accept1 = serverSocket1.accept();
//        System.out.println("accept end");
//        System.out.println(accept1.getInputStream().read());

//        Thread.currentThread().join();
    }

    public String testMethod(String name, int age) {
        return "";
    }
}
