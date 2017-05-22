package NettyInAction.ch2.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by szh on 2017/5/22.
 */
public class EchoClient {
    private final  int port;
    static List<SocketChannel> socketChannels =new ArrayList<>();
    public EchoClient(int port) {
        this.port = port;
    }
      static EchoClientHandle  e =new EchoClientHandle();
    private void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress("127.0.0.1",9002)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(e);
                        }
                    });
            ChannelFuture f= b.connect().sync();
            Channel channel =f.channel();
            Scanner scanner =new Scanner(System.in);
//            ByteBuf byteBuf=null;
            while (true){
                String Message =scanner.nextLine();
                if(Message.length() != 0){
//                    byteBuf.writeBytes(Message.getBytes());
                    System.out.println(Message);
//                    f.channel().writeAndFlush(Unpooled.copiedBuffer(Message,CharsetUtil.UTF_8));
//                    f.channel().flush();
                    System.out.println(f.channel().isActive());
                    e.list.get(0).writeAndFlush(Unpooled.copiedBuffer(Message,CharsetUtil.UTF_8));
                }if(Message =="exit"){
                    break;
                }
            }
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }
    public static void main(String args[]) throws InterruptedException {
        new EchoClient(9001).start();

    }
}
