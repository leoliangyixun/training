package com.kerwinyang.training.spring.cache.netty;

import org.junit.Test;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolHandler;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

import java.net.InetSocketAddress;

public class TestNetty {

    public static class NettyServerHandler extends ChannelInboundHandlerAdapter {

    }

    public static class NettyChannelPoolHandler extends AbstractChannelPoolHandler {

        @Override
        public void channelReleased(Channel ch) throws Exception {

        }

        @Override
        public void channelAcquired(Channel ch) throws Exception {

        }

        @Override
        public void channelCreated(Channel ch) throws Exception {

        }
    }


    public static class NettyServer {

        public void run(String host, int port) throws Exception {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                            sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
                                .addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                .addLast(new DefaultEventExecutorGroup(8))
                                .addLast(new NettyServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

                ChannelFuture future = bootstrap.bind(host, port).sync();
                future.channel().closeFuture().sync();
            } finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        }
    }

    public static class NettyClient {
        public void run(String host, int port) throws Exception {
            final EventLoopGroup group = new NioEventLoopGroup();
            final Bootstrap strap = new Bootstrap();
            strap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true);

            FixedChannelPool pool = new FixedChannelPool(strap, new NettyChannelPoolHandler(), 2);
            Future<Channel> f = pool.acquire();
            f.addListener((FutureListener<Channel>) f1 -> {
                if (f1.isSuccess()) {
                    Channel ch = f1.getNow();
                    ch.writeAndFlush("hello world");
                    // Release back to pool
                    pool.release(ch);
                }
            });
        }
    }

    @Test
    public void test() throws Exception {
        new NettyServer().run("127.0.0.1",8080);
        new NettyClient().run("127.0.0.1", 8080);
    }

}
