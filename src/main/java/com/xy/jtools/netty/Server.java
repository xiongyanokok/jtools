package com.xy.jtools.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

	public static void main(String[] args) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workerGroup);
			server.channel(NioServerSocketChannel.class);
			server.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {

						@Override
						protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
							System.out.println(msg);
							ctx.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE);
						}
					});
				}
			});
			server.option(ChannelOption.SO_BACKLOG, 1024);
			server.childOption(ChannelOption.SO_KEEPALIVE, true);
			// 绑定端口号
			ChannelFuture future = server.bind(8888).sync();
			// 等待服务端关闭
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			//
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

}
