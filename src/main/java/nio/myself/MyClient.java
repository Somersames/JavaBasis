package nio.myself;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by szh on 2017/5/4.
 */
public class MyClient {
    private void startClient() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9002));
        ByteBuffer bytebuffer = ByteBuffer.allocateDirect(1024);
//        String message = "测试";
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
//        try {
        while (true) {

            String message = br.readLine();

            for (int i = 0; i < message.length(); i++) {
                bytebuffer.put((byte) message.charAt(i));
            }
            bytebuffer.flip();

            while (bytebuffer.hasRemaining()) {
                socketChannel.write(bytebuffer);
            }

            bytebuffer.clear();
            socketChannel.read(bytebuffer);
            bytebuffer.flip();
            while (bytebuffer.hasRemaining()){
                System.out.print((char)bytebuffer.get()); //  System.out.print 不会导致换行
            }
            System.out.println();
            bytebuffer.clear();
        }
    }
    public static void main(String args[]) throws IOException {
        new MyClient().startClient();
    }
}
