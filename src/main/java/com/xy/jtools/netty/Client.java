package com.xy.jtools.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

	public static void main(String[] args) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {

						@Override
						protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
							ctx.writeAndFlush("xxxxx");
						}

					});
				}
			});
			bootstrap.option(ChannelOption.TCP_NODELAY, true);
			// 发起异步连接
			ChannelFuture future = bootstrap.connect("127.0.0.1", 8888).sync();
			// 等待客户端关闭
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			// 
		} finally {
			group.shutdownGracefully();
		}
	}
}
