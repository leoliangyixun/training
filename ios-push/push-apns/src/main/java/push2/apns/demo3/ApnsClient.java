package push2.apns.demo3;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpScheme;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http2.Http2SecurityUtil;
import io.netty.handler.codec.http2.HttpConversionUtil;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.SupportedCipherSuiteFilter;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import push2.apns.JWT;

import javax.net.ssl.SSLException;

import java.net.InetSocketAddress;

public class ApnsClient {
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

    private static final InetSocketAddress addr = new InetSocketAddress(HOST, PORT);

    private static ChannelPoolMap<InetSocketAddress, SimpleChannelPool> poolMap;

    public static void main(String[] args) throws Exception {
        ApnsClient client = new ApnsClient();
        client.push();
/*        for (int i = 0 ; i< 10; i++) {
            client.push();
        }*/
    }

    public void push() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(ChannelOption.TCP_NODELAY, true)
            .remoteAddress(HOST, PORT)
           .handler(new Http2ClientInitializer("test", createSslContext(), Integer.MAX_VALUE));

        FullHttpRequest request = new DefaultFullHttpRequest(
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

/*        Channel channel = bootstrap.connect().syncUninterruptibly().channel();
        Thread.sleep(3000);
        channel.writeAndFlush(request).addListener(new GenericFutureListener<ChannelFuture>() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("finished");
            }
        });*/

        FixedChannelPool pool = new FixedChannelPool(bootstrap, new ApnsChannelPoolHandler(), 10);
        pool.acquire().addListener((GenericFutureListener<Future<Channel>>) future -> {
            if (future.isSuccess()) {
               Channel channel = future.get();
                if (channel.isWritable()) {
                    channel.writeAndFlush(request).addListener(new GenericFutureListener<ChannelFuture>() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            System.out.println("finished");

                            pool.release(channel);
                        }
                    });

                }
            }
        });


    }
        private  SslContext createSslContext () throws SSLException {
            SslProvider provider = SslProvider.JDK;
            return SslContextBuilder.forClient()
                .sslProvider(provider)
                /* NOTE: the cipher filter may not include all ciphers required by the HTTP/2 specification.
                 * Please refer to the HTTP/2 specification for cipher requirements. */
                .ciphers(Http2SecurityUtil.CIPHERS, SupportedCipherSuiteFilter.INSTANCE)
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .applicationProtocolConfig(ApplicationProtocolConfig.DISABLED)
                .build();
        }

}
