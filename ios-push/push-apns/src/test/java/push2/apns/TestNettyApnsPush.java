package push2.apns;

import org.junit.Test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolHandler;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpScheme;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http2.DefaultHttp2Connection;
import io.netty.handler.codec.http2.DelegatingDecompressorFrameListener;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2SecurityUtil;
import io.netty.handler.codec.http2.HttpConversionUtil;
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
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class TestNettyApnsPush {
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

    public static class ApnsChannelHandlerInitializer extends ChannelInitializer<SocketChannel> {
        private Http2Connection connection = new DefaultHttp2Connection(false);
        private SslContext sslCtx;
        private HttpToHttp2ConnectionHandler connectionHandler = new HttpToHttp2ConnectionHandlerBuilder()
            .frameListener(new DelegatingDecompressorFrameListener(
                connection,
                new InboundHttp2ToHttpAdapterBuilder(connection)
                    .maxContentLength(10000)
                    .propagateSettings(true)
                    .build()))
            .connection(connection)
            .build();

        public ApnsChannelHandlerInitializer(SslContext sslCtx) {
            this.sslCtx = sslCtx;
        }

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(sslCtx.newHandler(ch.alloc()));
            ch.pipeline().addLast(new ApnsProviderHandler());
            ch.pipeline().addLast(new HttpResponseDecoder());
            ch.pipeline().addLast(new HttpRequestEncoder());
            //ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));
            ch.pipeline().addLast(new ApplicationProtocolNegotiationHandler("") {
                @Override
                protected void configurePipeline(ChannelHandlerContext ctx, String protocol) {
                    if (ApplicationProtocolNames.HTTP_2.equals(protocol)) {
                        ChannelPipeline p = ctx.pipeline();
                        p.addLast(connectionHandler);
                    }
                }
            });

            //ch.newPromise().awaitUninterruptibly();
        }
 /*       private final Http2FrameLogger logger;


        private final int maxContentLength;

        private HttpToHttp2ConnectionHandler connectionHandler;

        private HttpResponseHandler responseHandler;

        private Http2SettingsHandler settingsHandler;

        private String name;

        private Http2PingHandler pingHandler;*/


    }

    public static class ApnsProviderHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
            ByteBuf content = msg.content();
            if (content.isReadable()) {
                int contentLength = content.readableBytes();
                byte[] arr = new byte[contentLength];
                content.readBytes(arr);
                System.out.println("response" + new String(arr, 0, contentLength, CharsetUtil.UTF_8));
            }
        }



/*        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(JsonUtil.object2JSON("channelRead" + msg));
        }*/
    }

    public static class ApnsChannelPoolHandler extends AbstractChannelPoolHandler {

        @Override
        public void channelReleased(Channel ch) throws Exception {
            System.out.println("channelReleased. Channel ID: " + ch.id());
        }

        @Override
        public void channelAcquired(Channel ch) throws Exception {
            System.out.println("channelAcquired. Channel ID: " + ch.id());
        }

        @Override
        public void channelCreated(Channel ch) throws Exception {
            System.out.println("channelCreated. Channel ID: " + ch.id());
        }
    }


    @Test
    public void testPush()  throws Exception {
     //   new ApnsClient().push();

        SslContext sslCtx = SslContextBuilder.forClient()
            .sslProvider(SslProvider.JDK)
            /* NOTE: the cipher filter may not include all ciphers required by the HTTP/2 specification.
             * Please refer to the HTTP/2 specification for cipher requirements. */
            .ciphers(Http2SecurityUtil.CIPHERS, SupportedCipherSuiteFilter.INSTANCE)
            .trustManager(InsecureTrustManagerFactory.INSTANCE)
            .applicationProtocolConfig(new ApplicationProtocolConfig(
                Protocol.ALPN,
                // NO_ADVERTISE is currently the only mode supported by both OpenSsl and JDK providers.
                SelectorFailureBehavior.NO_ADVERTISE,
                // ACCEPT is currently the only mode supported by both OpenSsl and JDK providers.
                SelectedListenerFailureBehavior.ACCEPT,
                ApplicationProtocolNames.HTTP_2,
                ApplicationProtocolNames.HTTP_1_1))
            .build();

        Bootstrap bootstrap = new Bootstrap()
            .group(new NioEventLoopGroup(6))
            .remoteAddress(HOST, PORT)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .handler(new ApnsChannelHandlerInitializer(sslCtx));
        bootstrap.connect();
        FixedChannelPool pool = new FixedChannelPool(bootstrap, new ApnsChannelPoolHandler(), 10);

        //ChannelFuture f = bootstrap.connect(HOST, PORT).sync();
        //Channel channel = bootstrap.connect().syncUninterruptibly().channel();

        DefaultFullHttpRequest request = new DefaultFullHttpRequest(
            HttpVersion.HTTP_1_1,
            HttpMethod.POST,
            "https://" + HOST + "/3/device/" + DEVICE_TOKEN,
            Unpooled.copiedBuffer(payload.getBytes()));
        request.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        request.headers().add(HttpHeaderNames.HOST, HOST);
        request.headers().add(HttpConversionUtil.ExtensionHeaderNames.SCHEME.text(), HttpScheme.HTTPS.name());
        request.headers().add(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
        request.headers().add(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.DEFLATE);
        request.headers().add("authorization", "bearer " + JWT.getToken(TEAM_ID, KEY_ID, ACCESS_SECRET));
        request.headers().add("apns-topic", "com.hujiang.hjm.normandy");
        // 发送http请求
     //   channel.writeAndFlush(request);


        pool.acquire().addListener((GenericFutureListener<Future<Channel>>) future -> {
            if(future.isSuccess()) {
                Channel channel = future.getNow();
                channel.writeAndFlush(request);
                pool.release(channel);
            }
        });

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }

}
