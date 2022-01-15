package com.qsmy.netty.test1;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author qsmy
 */
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*final Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "=======>server：" + msg.toString());
        ctx.writeAndFlush("server received you msg");

        CHANNEL_GROUP.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("[客户]" + channel.remoteAddress() + " 发送了消息" + msg + "\n");
            } else {
                ch.writeAndFlush("[自己]发送了消息" + msg + "\n");
            }
        });*/

        String body = (String)msg;
        System.out.println("server receive order : " + body + ";the counter is: " + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        final Channel channel = ctx.channel();
        CHANNEL_GROUP.writeAndFlush("[客户端]" + channel.remoteAddress() + " 加入聊天" + DATE_TIME_FORMATTER.format(LocalDateTime.now()) + " \n");
        CHANNEL_GROUP.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        final Channel channel = ctx.channel();
        CHANNEL_GROUP.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离开了\n");
        System.out.println("channelGroup size" + CHANNEL_GROUP.size());
    }

}
