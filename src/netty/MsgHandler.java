package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MsgHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		ctx.write(null);//写到ChannelPipeline中当前ChannelHandler的下一个ChannelHandler
		ctx.channel().write(null);//写到ChannelPipeline中最后一个ChannelHandler然后数据从尾部开始向头部方向流动会经过所有的ChannelHandler
		msg.writeInt(1);
		ctx.writeAndFlush(new Object()).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					// do sth
				}else{
					// do sth
				}
			}
		});
	}
}
