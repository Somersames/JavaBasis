package NettyInAction.ch2.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szh on 2017/5/22.
 */
public class EchoClientHandle extends SimpleChannelInboundHandler<ByteBuf>{
    public String Message;
    public static List<ChannelHandlerContext> list =new ArrayList<>();
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("客端接受到的数据是" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  // 这和方法是当连接成功之后的一个方法调用
        ctx.writeAndFlush(Unpooled.copiedBuffer("连接成功",CharsetUtil.UTF_8));
        list.add(ctx);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客端接受到的数据是" + buf.toString(CharsetUtil.UTF_8));
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

    }
}
