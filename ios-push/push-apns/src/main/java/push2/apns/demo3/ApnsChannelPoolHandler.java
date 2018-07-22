package push2.apns.demo3;

import io.netty.channel.Channel;
import io.netty.channel.pool.AbstractChannelPoolHandler;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http2.Http2SecurityUtil;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.SupportedCipherSuiteFilter;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import javax.net.ssl.SSLException;

public class ApnsChannelPoolHandler extends AbstractChannelPoolHandler {

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

        ch.pipeline().addLast(new HttpClientCodec());
        // ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));
        //    ch.pipeline().addLast(new HttpResponseHandler());
        ch.pipeline().addLast(new Http2ClientInitializer("test", createSslContext(), Integer.MAX_VALUE));
        System.out.println("channelCreated. Channel ID: " + ch.id());
    }

    private SslContext createSslContext () throws SSLException {
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
