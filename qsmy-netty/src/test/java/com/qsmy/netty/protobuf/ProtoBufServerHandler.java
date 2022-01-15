package com.qsmy.netty.protobuf;

import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author qsmy
 */
public class ProtoBufServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MessageProto.ResponseMsg.Builder builder = MessageProto.ResponseMsg.newBuilder();
        builder.setMsgType(ByteString.copyFromUtf8("CBSP"));
        builder.setReceiveOne("小红");
        builder.setMsg("你好，你有啥事");

        ctx.writeAndFlush(builder.build());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageProto.RequestMsg m = (MessageProto.RequestMsg) msg;
        System.out.println("Client say: " + m.getReceiveOne() + "," + m.getMsg());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}