package NettyInAction.ch2.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szh on 2017/5/22.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    static List<ChannelHandlerContext> channelHandlerContexts =new ArrayList<>();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("服务端接收到的数据是"+in.toString(CharsetUtil.UTF_8));
        channelHandlerContexts.add(ctx);
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /*
        因为这里固定住发送的消息，所以需要在这里关闭Channel关闭Channel的是后面add方法，当调用后面的addListener方法的时候是会将前面的信息发送完后
        在来关闭，
        channel.close()也有该功能但是其为直接关闭的

         */
         //ctx.writeAndFlush(Unpooled.copiedBuffer("服务端的测试",CharsetUtil.UTF_8)).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
