package com.hujiang.notifycenter.client.push;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HJPushClient {
    private PushClient pushClient;
    private BadgeClient badgeClient;

    public HJPushClient(String appKey, String appSecret) {
        this.pushClient = new PushClient(appKey, appSecret).retryCount(3).timeout(100L);
        this.badgeClient = new BadgeClient(appKey, appSecret).retryCount(3).timeout(100L);
    }

}
