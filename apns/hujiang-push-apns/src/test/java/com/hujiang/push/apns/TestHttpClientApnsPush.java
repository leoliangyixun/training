package com.hujiang.push.apns;

import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http2.config.H2Config;
import org.apache.hc.core5.io.ShutdownType;
import org.junit.Test;

import com.hujiang.push.apns.JWT;

import java.util.concurrent.Future;

public class TestHttpClientApnsPush {
    private static final String ENDPOINT_DEV = "https://api.development.push.apple.com";
    private static final String ENDPOINT_PROD = "https://api.push.apple.com";
    private static final String TEAM_ID = "4S5ZWGJSPN";
    private static final String KEY_ID = "CPPMHRM7CC";
    private static final String ACCESS_SECRET = "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgjZTdQgZM/8AQspuD9G7aTsQTEBqomiu4TJ0LxU5gaI+gCgYIKoZIzj0DAQehRANCAASbKaMLxR56q2N0oQ8YXcOFEhHL7cAuNcLUwkMTLplUKoaRzSY9Me85UnjtuMxjmYpFkbmYtFtrNULzedlBXk/K";
    private static final String DEVICE_TOKEN = "82697704db71dd64f9d42365c440e6f53afa858a5467e017fbda5377406a3203";

    @Test
    public void testApnsPush() throws Exception {
        CloseableHttpAsyncClient client = HttpAsyncClients.customHttp2()
                .setH2Config(H2Config.DEFAULT)
                .setDefaultHeaders(null)
                .build();
        client.start();
        SimpleHttpRequest request = SimpleHttpRequest.post(ENDPOINT_PROD + "/3/device/" + DEVICE_TOKEN );
        request.setBodyText("{\n" +
                "    \"aps\" : { \"alert\" : \"Hello World\" },\n" +
                "    \"acme2\" : [ \"bang\",  \"whiz\" ]\n" +
                "}", ContentType.APPLICATION_JSON);
        request.addHeader("authorization", "bearer " + JWT.getToken(TEAM_ID, KEY_ID, ACCESS_SECRET));
        request.addHeader("apns-topic", "com.hujiang.hjm.normandy");
        //AsyncRequestBuilder.post(new HttpHost("https://api.development.push.apple.com"), "/3/device/4f872918800d5010c332ad3e0e68cc040d2979d3db7fedc20f0e98bc70235556").addHeader(null, null).build();
        Future<SimpleHttpResponse> feature =  client.execute(request, new FutureCallback<SimpleHttpResponse>() {
            @Override
            public void completed(SimpleHttpResponse result) {
                System.out.println("push success: ");
            }

            @Override
            public void failed(Exception ex) {

            }

            @Override
            public void cancelled() {

            }
        });
        System.out.println("推送结果: " + feature.get().getBodyText());

        client.shutdown(ShutdownType.GRACEFUL);
    }
}
