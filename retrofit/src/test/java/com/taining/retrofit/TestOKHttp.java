package com.taining.retrofit;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

public class TestOKHttp {
    private final OkHttpClient okHttpClient = new OkHttpClient();

    @Test
    public void test() {
        try {

            ClassPathResource resource = new ClassPathResource("1.jpeg");
            byte[] data = IOUtils.toByteArray(resource.getInputStream());

            OkHttpClient client = okHttpClient.newBuilder().readTimeout(10000, TimeUnit.MILLISECONDS).build();

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    //.addFormDataPart("file", "1.jpeg", RequestBody.create(MultipartBody.FORM, data))
                    .addFormDataPart("file", "1.jpeg", RequestBody.create(MediaType.parse("application/octet-stream"), data))
                    .addFormDataPart("userid", "123456")
                    .build();

            Request request = new Request.Builder()
                    .url("http://localhost:8093/account/api/avatar/upload")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(response.body().toString());
        } catch (Exception ex) {

            
        }
    }


}
