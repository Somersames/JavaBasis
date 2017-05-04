package nio.myself;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by szh on 2017/5/3.
 */
public class MyServer {
    public void startServer() throws IOException {
        ServerSocketChannel serverSocketChannel= ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9002));
        Selector selector =Selector.open(); // 打开选择器
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 将新进来的通道注册到选择器当中
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys =selector.selectedKeys();
            for(SelectionKey selectionKey : selectionKeys){
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel =ssc.accept(); // 返回SocketChannel ,非阻塞模式下会立即返回
                    socketChannel.configureBlocking(false); // 非阻塞IO
                    socketChannel.register(selector,selectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    socketChannel.write(byteBuffer);
                }
            }
            selectionKeys.clear();
        }
    }
    public static void  main(String args[]) throws IOException {
        new MyServer().startServer();
    }

}
