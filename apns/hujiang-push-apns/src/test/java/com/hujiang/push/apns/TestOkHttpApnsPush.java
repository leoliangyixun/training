package com.hujiang.push.apns;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.BufferedSink;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestOkHttpApnsPush {
    private static final String ENDPOINT_PROD = "https://api.push.apple.com";
    private static final String TEAM_ID = "4S5ZWGJSPN";
    private static final String KEY_ID = "CPPMHRM7CC";
    private static final String ACCESS_SECRET = "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgjZTdQgZM/8AQspuD9G7aTsQTEBqomiu4TJ0LxU5gaI+gCgYIKoZIzj0DAQehRANCAASbKaMLxR56q2N0oQ8YXcOFEhHL7cAuNcLUwkMTLplUKoaRzSY9Me85UnjtuMxjmYpFkbmYtFtrNULzedlBXk/K";
    private static final String DEVICE_TOKEN = "82697704db71dd64f9d42365c440e6f53afa858a5467e017fbda5377406a3203";

    private static final String payload = "{\n" +
            "    \"aps\" : { \"alert\" : \"Hello World\" },\n" +
            "    \"acme2\" : [ \"bang\",  \"whiz\" ]\n" +
            "}";

    @Test
    public void testPush() throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(10, 10, TimeUnit.MINUTES))
                .build();

        Request request = new Request.Builder()
                .url(ENDPOINT_PROD + "/3/device/" + DEVICE_TOKEN)

                .post(new RequestBody() {
                    @Override
                    public MediaType contentType() {
                        return MediaType.parse("application/json; charset=utf-8");
                    }

                    @Override
                    public void writeTo(BufferedSink sink) throws IOException {
                        sink.write(payload.getBytes(Charset.forName("UTF-8")));
                    }
                })
                .header("authorization", "bearer " + JWT.getToken(TEAM_ID, KEY_ID, ACCESS_SECRET))
                .header("apns-topic", "com.hujiang.hjm.normandyXX")
                .build();


        Response response = null;

        try {
            response = client.newCall(request).execute();
            int code = response.code();

            String body = response.body() != null ? response.body().string() : null;
            System.out.println(code);
            System.out.println(body);

        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (response != null) {
                response.body().close();
            }
        }

    }
}
