package com.qsmy.netty.protobuf;

import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author qsmy
 */
public class ProtoBufClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageProto.ResponseMsg m = (MessageProto.ResponseMsg) msg;
        System.out.println("Server say: " + m.getReceiveOne() + "," + m.getMsg());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MessageProto.RequestMsg.Builder builder = MessageProto.RequestMsg.newBuilder();
        builder.setMsgType(ByteString.copyFromUtf8("CBSP"));
        builder.setReceiveOne("小明");
        builder.setMsg("你好，我找你有事");

        ctx.writeAndFlush(builder.build());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client is close");
    }
}