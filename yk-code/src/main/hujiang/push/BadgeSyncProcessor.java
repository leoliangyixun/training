/**
 * 
 */
package com.hujiang.notifycenter.push.api.processor;

import static com.hujiang.notifycenter.push.core.ContextConfig.AUTHENTICATION_TOKEN_LOCAL_CACHE;
import static com.hujiang.notifycenter.push.core.ContextConfig.BADGE_DEVICE;
import static com.hujiang.notifycenter.push.core.ContextConfig.BADGE_USER;

import com.hujiang.basic.framework.core.config.BaseProperties;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.basic.framework.plugin.cache.ops.ValueOps;
import com.hujiang.notifycenter.gundam.common.model.constant.DeviceInfoStatus;
import com.hujiang.notifycenter.push.apns.ApnsClient;
import com.hujiang.notifycenter.push.apns.common.ApnsServer;
import com.hujiang.notifycenter.push.apns.common.DeliveryPriority;
import com.hujiang.notifycenter.push.apns.model.ApnsNotification;
import com.hujiang.notifycenter.push.apns.model.Provider;
import com.hujiang.notifycenter.push.core.ApnsClientInitializer;
import com.hujiang.notifycenter.push.core.Processor;
import com.hujiang.notifycenter.push.model.bo.App;
import com.hujiang.notifycenter.push.model.bo.PushByTag;
import com.hujiang.notifycenter.push.model.dto.Badge;
import com.hujiang.notifycenter.push.model.po.DeviceInfoPo;
import com.hujiang.notifycenter.push.service.DeviceInfoService;

import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BadgeSyncProcessor implements Processor<Badge> {

    @Autowired
    @Qualifier("badge")
    private ValueOps valueOps;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Override
    public void process(Badge badge) {
        syncBadge2Server(badge);
        //TODO 待优化(异步)
        //syncBadge2Device(badge);
    }

    /**
     * 同步未读消息数到服务器
     * @param badge
     */
    private void syncBadge2Server(Badge badge) {
        if (badge.isByUser()) {
            //登录用户
            valueOps.set(String.format(BADGE_USER, badge.getUserId(), badge.getAppUniqueId()), badge.getBadge());
        } else if (badge.isByDevice()){
            //未登录用户
            valueOps.set(String.format(BADGE_DEVICE, badge.getDeviceId(), badge.getAppUniqueId()), badge.getBadge());
        }
    }

    /**
     * 同步未读消息数到客户端
     * @param badge
     */
    private void syncBadge2Device(Badge badge) {
        if (badge.isMultiSync()) {
            App app = badge.getApp();
            ApnsClient client = ApnsClientInitializer.client(ApnsServer.PROD, app.getAppCategory());

            String authenticationToken = null;
            String payload = JsonUtil.object2JSON(badge.toJSON());
            String topic = app.getIosBundleId();
            DeliveryPriority priority = DeliveryPriority.IMMEDIATE;
            Set<PushByTag> audiences = getAudiences(badge);
            try {
                authenticationToken = AUTHENTICATION_TOKEN_LOCAL_CACHE.get(Provider.create(app.getApnsKeyId(), app.getApnsTeamId(), app.getApnsAuthenticationToken()));
            } catch (Exception ex) {
                log.error("[error stack]: ", ex);
                log.warn("acquire APNs Authentication Token failed, badge: {}", badge);
                //获取APNs token 失败，直接退出
                return;
            }

            String token = authenticationToken;
            Boolean isOpen = BaseProperties.getProperty("apns.badge.isOpen", Boolean.class, true);
            if (CollectionUtils.isNotEmpty(audiences)) {
                audiences.forEach(e -> {
                    ApnsNotification notification = new ApnsNotification(token, e.getDeviceToken(), payload, topic, null, priority, null, isOpen);
                    //APNs异步推送
                    client.push(notification, null);
                });
            } else {
                log.warn("no audiences, ignore report badge , badge: {}", badge);
            }
        }
    }

    private Set<PushByTag> getAudiences(Badge badge) {
        Set<PushByTag> audiences = null;
        if (badge.isByUser()) {
            audiences =  getAudiencesByUser(badge);
        } else if(badge.isByDevice()){
            audiences =  getAudiencesByDevice(badge);
        }

        return audiences;
    }

    private Set<PushByTag> getAudiencesByUser(Badge badge) {
        Set<Long> all = Sets.newHashSet(badge.getUserId());
        Set<DeviceInfoPo> uids1 = null;
        List<DeviceInfoPo> deviceInfoByUids = deviceInfoService.getDeviceInfoListByUids(badge.getAppUniqueId(), all);
        if (CollectionUtils.isNotEmpty(deviceInfoByUids)) {
            uids1 = deviceInfoByUids.stream().filter(e -> e.getStatus() == DeviceInfoStatus.ALLOW.getStatus()).collect(Collectors.toSet());
        }

        return CollectionUtils.isNotEmpty(uids1) ? uids1.stream().map(PushByTag::new).collect(Collectors.toSet()) : null;
    }

    private Set<PushByTag> getAudiencesByDevice(Badge badge) {
        Set<String> all = Sets.newHashSet(badge.getDeviceId());
        Set<DeviceInfoPo> dids1 = null;
        List<DeviceInfoPo> deviceInfoByDids = deviceInfoService.getDeviceInfoListByDids(badge.getAppUniqueId(), all);
        if (CollectionUtils.isNotEmpty(deviceInfoByDids)) {
            dids1 = deviceInfoByDids.stream().filter(e -> e.getStatus() == 1).collect(Collectors.toSet());
        }

        return CollectionUtils.isNotEmpty(dids1) ? dids1.stream().map(PushByTag::new).collect(Collectors.toSet()) : null;
    }
}
