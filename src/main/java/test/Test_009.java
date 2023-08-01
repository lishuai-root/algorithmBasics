package test;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * @description:
 * @author: LISHUAI
 * @createDate: 2022/8/16 22:46
 * @version: 1.0
 */

public class Test_009 {

    public static void main(String[] args) throws Exception {
//        Socket socket = new Socket();
//        socket.connect(new InetSocketAddress("127.0.0.1", 8088));
//        OutputStream outputStream = socket.getOutputStream();
//        Scanner scanner = new Scanner(System.in);
////        int i = scanner.nextInt();
//        System.out.println("write");
//        int count = 0;
//        while (true) {
//            outputStream.write(1);
//            outputStream.flush();
//        }

//        outputStream.flush();
//        System.out.println("write end");
//        System.out.println(socket.getInputStream().read());
//        Thread.currentThread().join();

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress("127.0.0.1", 8088));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
    }
}
