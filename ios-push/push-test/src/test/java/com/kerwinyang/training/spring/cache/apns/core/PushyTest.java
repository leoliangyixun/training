package com.kerwinyang.training.spring.cache.apns.core;

import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.auth.ApnsSigningKey;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;
import com.turo.pushy.apns.util.concurrent.PushNotificationFuture;
import com.turo.pushy.apns.util.concurrent.PushNotificationResponseListener;
import io.netty.util.concurrent.Future;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

@RunWith(SpringJUnit4ClassRunner.class)
public class PushyTest {

    @Test
    public void testPush() throws NoSuchAlgorithmException, InvalidKeyException, IOException, InterruptedException {
        final ApnsClient apnsClient = new ApnsClientBuilder()
                .setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST)
                .setSigningKey(ApnsSigningKey.loadFromPkcs8File(new File("/Users/yangkai/Downloads/AuthKey_CPPMHRM7CC.p8"), "4S5ZWGJSPN", "CPPMHRM7CC"))
                .build();


        final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
        payloadBuilder.setAlertBody("Example!");

        final String payload = "{\n" +
            "    \"aps\" : { \"alert\" : \"Hello World\" },\n" +
            "    \"acme2\" : [ \"bang\",  \"whiz\" ]\n" +
            "}";
        final String token = TokenUtil.sanitizeTokenString("82697704db71dd64f9d42365c440e6f53afa858a5467e017fbda5377406a3203");

        final SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(token, "com.hujiang.hjm.normandy", payload);

        final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>>
                sendNotificationFuture = apnsClient.sendNotification(pushNotification);

        try {
            final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse = sendNotificationFuture.get();

            if (pushNotificationResponse.isAccepted()) {
                System.out.println("Push notification accepted by APNs gateway.");
            } else {
                System.out.println("Notification rejected by the APNs gateway: " +
                        pushNotificationResponse.getRejectionReason());

                if (pushNotificationResponse.getTokenInvalidationTimestamp() != null) {
                    System.out.println("\t…and the token is invalid as of " +
                            pushNotificationResponse.getTokenInvalidationTimestamp());
                }
            }
        } catch (final ExecutionException e) {
            System.err.println("Failed to send push notification.");
            e.printStackTrace();
        }


        sendNotificationFuture.addListener(new PushNotificationResponseListener<SimpleApnsPushNotification>() {

            @Override
            public void operationComplete(final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>> future) throws Exception {
                // When using a listener, callers should check for a failure to send a
                // notification by checking whether the future itself was successful
                // since an exception will not be thrown.
                if (future.isSuccess()) {
                    final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
                            sendNotificationFuture.getNow();
                    System.out.println(pushNotificationResponse.getPushNotification());
                    // Handle the push notification response as before from here.
                } else {
                    // Something went wrong when trying to send the notification to the
                    // APNs gateway. We can find the exception that caused the failure
                    // by getting future.cause().
                    future.cause().printStackTrace();
                }
            }
        });


        final Future<Void> closeFuture = apnsClient.close();
        closeFuture.await();
    }


}
