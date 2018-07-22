package push2.apns.demo;


import com.training.push.apns.JWT;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;

import java.net.URI;

public class HttpClient {

    private static final String HOST = "api.push.apple.com";
    private static final int PORT = 443;
    private static final String TEAM_ID = "4S5ZWGJSPN";
    private static final String KEY_ID = "CPPMHRM7CC";
    private static final String ACCESS_SECRET = "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgjZTdQgZM/8AQspuD9G7aTsQTEBqomiu4TJ0LxU5gaI+gCgYIKoZIzj0DAQehRANCAASbKaMLxR56q2N0oQ8YXcOFEhHL7cAuNcLUwkMTLplUKoaRzSY9Me85UnjtuMxjmYpFkbmYtFtrNULzedlBXk/K";
    private static final String DEVICE_TOKEN = "82697704db71dd64f9d42365c440e6f53afa858a5467e017fbda5377406a3203";
    private static final String payload = "{\n" +
        "    \"aps\" : { \"alert\" : \"Hello World\" },\n" +
        "    \"acme2\" : [ \"bang\",  \"whiz\" ]\n" +
        "}";

    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new HttpClientInboundHandler());
                }
            });

            // Start the client.
            //ChannelFuture f = b.connect(host, port).sync(); // (5)
            Channel c = b.connect(host, port).syncUninterruptibly().channel();
            URI uri = new URI("https://" + host + "/3/device/" + DEVICE_TOKEN);
            System.out.println("connect to " +uri.toASCIIString() );
            String msg = "Are you ok?";
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,
                uri.toASCIIString(), Unpooled.wrappedBuffer(payload.getBytes()));

            // 构建http请求
            request.headers().set(HttpHeaders.Names.HOST, host);
            request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
            request.headers().set("authorization", "bearer " + JWT.getToken(TEAM_ID, KEY_ID, ACCESS_SECRET));
            request.headers().set("apns-topic", "com.hujiang.hjm.normandy");
            // 发送http请求
/*            f.channel().write(request);
            f.channel().flush();
            f.channel().closeFuture().sync();*/
            c.writeAndFlush(request);
            c.closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
        client.connect(HOST, PORT);
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
}
