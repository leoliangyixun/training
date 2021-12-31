package com.kerwinyang.training.spring.cache.apns.core;

import com.clevertap.apns.ApnsClient;
import com.clevertap.apns.Notification;
import com.clevertap.apns.NotificationResponse;
import com.clevertap.apns.NotificationResponseListener;
import com.clevertap.apns.clients.ApnsClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@RunWith(SpringJUnit4ClassRunner.class)
public class APNsHttp2Test {
    
    @Test
    public void testAPNsHttp2Sync() throws UnrecoverableKeyException, KeyManagementException, CertificateException,
            NoSuchAlgorithmException, KeyStoreException, IOException {
        // Using provider authentication tokens
        final ApnsClient client = new ApnsClientBuilder()
                .inSynchronousMode()
                .withProductionGateway(true)
                .withApnsAuthKey("MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgjZTdQgZM/8AQspuD9G7aTsQTEBqomiu4TJ0LxU5gaI+gCgYIKoZIzj0DAQehRANCAASbKaMLxR56q2N0oQ8YXcOFEhHL7cAuNcLUwkMTLplUKoaRzSY9Me85UnjtuMxjmYpFkbmYtFtrNULzedlBXk/K")
                .withTeamID("4S5ZWGJSPN")
                .withKeyID("CPPMHRM7CC")
                //.withDefaultTopic("<your app's topic>")
                .build();

        // Build your notification
        Notification n = new Notification.Builder("82697704db71dd64f9d42365c440e6f53afa858a5467e017fbda5377406a3203").alertBody("Hello").build();
        // Send the notification

        // Synchronous
        NotificationResponse result = client.push(n);
        System.out.println(result);
    }

    @Test
    public void testAPNsHttp2Asyn() throws UnrecoverableKeyException, KeyManagementException, CertificateException,
            NoSuchAlgorithmException, KeyStoreException, IOException {
        // Using provider authentication tokens
        final ApnsClient client = new ApnsClientBuilder()
                .inAsynchronousMode()
                //.withProductionGateway()
                .withApnsAuthKey("-----BEGIN PRIVATE KEY-----\n" + 
                        "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgjZTdQgZM/8AQspuD\n" + 
                        "9G7aTsQTEBqomiu4TJ0LxU5gaI+gCgYIKoZIzj0DAQehRANCAASbKaMLxR56q2N0\n" + 
                        "oQ8YXcOFEhHL7cAuNcLUwkMTLplUKoaRzSY9Me85UnjtuMxjmYpFkbmYtFtrNULz\n" + 
                        "edlBXk/K\n" + 
                        "-----END PRIVATE KEY-----")
                .withTeamID("'4S5ZWGJSPN").withKeyID("CPPMHRM7CC")
                //.withDefaultTopic("<your app's topic>")
                .build();

        // Build your notification
        Notification n = new Notification.Builder("4f872918800d5010c332ad3e0e68cc040d2979d3db7fedc20f0e98bc70235556").alertBody("Hello").build();
        // Send the notification

        // Asynchronous
        client.push(n, new NotificationResponseListener() {
            @Override
            public void onSuccess(Notification notification) {
                System.out.println("success!");
            }

            @Override
            public void onFailure(Notification notification, NotificationResponse nr) {
                System.out.println("failure: " + nr);
            }
        });
    }
}
