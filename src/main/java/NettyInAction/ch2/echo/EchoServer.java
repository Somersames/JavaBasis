package NettyInAction.ch2.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by szh on 2017/5/22.
 */
public class EchoServer
{
    private final int port;
    public EchoServer(int port){
        this.port= port;
    }
    public static void main(String args[]) throws InterruptedException {
        new EchoServer(9001).start();
    }
    private void start() throws InterruptedException {
        final EchoServerHandler serverHandler =new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try
        {
            ServerBootstrap b =new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(9002))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture f =b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
