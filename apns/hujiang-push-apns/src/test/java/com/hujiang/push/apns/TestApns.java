package com.hujiang.push.apns;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import org.apache.hc.core5.http2.config.H2Config;
import org.apache.hc.core5.io.ShutdownType;
import org.junit.Test;

public class TestApns {

    private static final String ENDPOINT_DEVELOPMENT = "https://api.development.push.apple.com";
    private static final String TEAM_ID = "4S5ZWGJSPN";
    private static final String KEY_ID = "CPPMHRM7CC";

    @Test
    public void test1() {
        CloseableHttpClient client = HttpClients.createDefault();


    }

    @Test
    public void test2() {
        CloseableHttpAsyncClient client = HttpAsyncClients.customHttp2().setH2Config(null).setDefaultHeaders(null).build();

        HttpPost post = new HttpPost("");
        client.start();
      //  post.setEntity();

      //  client.execute(post, null);
//   /3/device/4f872918800d5010c332ad3e0e68cc040d2979d3db7fedc20f0e98bc70235556
        client.shutdown(ShutdownType.GRACEFUL);
    }
}
