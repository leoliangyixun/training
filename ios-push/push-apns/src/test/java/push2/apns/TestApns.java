package push2.apns;

import org.junit.Test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpScheme;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http2.DefaultHttp2Connection;
import io.netty.handler.codec.http2.DelegatingDecompressorFrameListener;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2SecurityUtil;
import io.netty.handler.codec.http2.HttpConversionUtil.ExtensionHeaderNames;
import io.netty.handler.codec.http2.HttpToHttp2ConnectionHandler;
import io.netty.handler.codec.http2.HttpToHttp2ConnectionHandlerBuilder;
import io.netty.handler.codec.http2.InboundHttp2ToHttpAdapterBuilder;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.ApplicationProtocolConfig.Protocol;
import io.netty.handler.ssl.ApplicationProtocolConfig.SelectedListenerFailureBehavior;
import io.netty.handler.ssl.ApplicationProtocolConfig.SelectorFailureBehavior;
import io.netty.handler.ssl.ApplicationProtocolNames;
import io.netty.handler.ssl.ApplicationProtocolNegotiationHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.SupportedCipherSuiteFilter;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.net.URI;

public class TestApns {
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

    @Test
    public void test() throws Exception {
        SslContext sslCtx = SslContextBuilder.forClient()
            .sslProvider(SslProvider.JDK)
            /* NOTE: the cipher filter may not include all ciphers required by the HTTP/2 specification.
             * Please refer to the HTTP/2 specification for cipher requirements. */
            .ciphers(Http2SecurityUtil.CIPHERS, SupportedCipherSuiteFilter.INSTANCE)
            .trustManager(InsecureTrustManagerFactory.INSTANCE)
          //  .applicationProtocolConfig(ApplicationProtocolConfig.DISABLED)
            .applicationProtocolConfig(new ApplicationProtocolConfig(
                Protocol.ALPN,
                // NO_ADVERTISE is currently the only mode supported by both OpenSsl and JDK providers.
                SelectorFailureBehavior.NO_ADVERTISE,
                // ACCEPT is currently the only mode supported by both OpenSsl and JDK providers.
                SelectedListenerFailureBehavior.ACCEPT,
                ApplicationProtocolNames.HTTP_2,
                ApplicationProtocolNames.HTTP_1_1))
            .build();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                private Http2Connection connection = new DefaultHttp2Connection(false);
                private HttpToHttp2ConnectionHandler connectionHandler = new HttpToHttp2ConnectionHandlerBuilder()
                    .frameListener(new DelegatingDecompressorFrameListener(
                        connection,
                        new InboundHttp2ToHttpAdapterBuilder(connection)
                            .maxContentLength(10000)
                            .propagateSettings(true)
                            .build()))
                    .connection(connection)
                    .build();

                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(sslCtx.newHandler(ch.alloc()));
                    ch.pipeline().addLast(connectionHandler);
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast( new HttpResponseDecoder());
                    // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new HttpClientCodec());
                    ch.pipeline().addLast(new HttpClientInboundHandler());
                }
            });

            // Start the client.
            Channel c = b.connect(HOST, PORT).syncUninterruptibly().channel();// (5)

            URI uri = new URI("https://"+HOST + "/3/device/" + DEVICE_TOKEN );

            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,
                uri.toASCIIString(), Unpooled.wrappedBuffer(payload.getBytes()));

            // 构建http请求
            request.headers().set(HttpHeaderNames.HOST, HOST);
            request.headers().set(ExtensionHeaderNames.SCHEME.text(), HttpScheme.HTTPS.name());
            request.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
            request.headers().set("authorization", "bearer " + JWT.getToken(TEAM_ID, KEY_ID, ACCESS_SECRET));
            request.headers().set("apns-topic", "com.hujiang.hjm.normandy");
            // 发送http请求
            c.write(request);
            c.flush();
            c.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

    }

    public static class HttpClientInboundHandler extends ChannelInboundHandlerAdapter {
        private ByteBufToBytes reader;

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpResponse) {
                HttpResponse response = (HttpResponse) msg;
                System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
                if (HttpHeaders.isContentLengthSet(response)) {
                    reader = new ByteBufToBytes((int) HttpHeaders.getContentLength(response));
                }
            }

            if (msg instanceof HttpContent) {
                HttpContent httpContent = (HttpContent) msg;
                ByteBuf content = httpContent.content();
                reader.reading(content);
                content.release();

                if (reader.isEnd()) {
                    String resultStr = new String(reader.readFull());
                    System.out.println("NettyServer said:" + resultStr);

                    ctx.close();
                }
            }
        }

    }

    public static class ByteBufToBytes {
        private ByteBuf temp;

        private boolean end = true;

        public ByteBufToBytes(int length) {
            temp = Unpooled.buffer(length);
        }

        public void reading(ByteBuf datas) {
            datas.readBytes(temp, datas.readableBytes());
            if (this.temp.writableBytes() != 0) {
                end = false;
            } else {
                end = true;
            }
        }

        public boolean isEnd() {
            return end;
        }

        public byte[] readFull() {
            if (end) {
                byte[] contentByte = new byte[this.temp.readableBytes()];
                this.temp.readBytes(contentByte);
                this.temp.release();
                return contentByte;
            } else {
                return null;
            }
        }

        public byte[] read(ByteBuf datas) {
            byte[] bytes = new byte[datas.readableBytes()];
            datas.readBytes(bytes);
            return bytes;
        }
    }


}
