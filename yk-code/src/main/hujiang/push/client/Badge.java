package com.hujiang.notifycenter.client.push.model;

import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Maps;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.notifycenter.gundam.common.model.dto.JSON;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yangkai
 * @date 2018/5/16
 * @email yangkai@hujiang.com
 * @description
 */
public class Badge implements JSON {
    public static final String DEVICE_ID = "device_id";
    public static final String USER_ID = "user_id";
    public static final String APP_UNIQUE_ID = "app_unique_id";
    public static final String BADGE = "badge";
    public static final String IS_ASYNC = "is_async";
    public static final String IS_MULTI_SYNC ="is_multi_sync";

    private static final long serialVersionUID = -8312521191497482508L;
    /**
     * 沪江下所有应用设备ID
     */
    private String deviceId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 应用唯一ID，iOS bundle id或android package name
     */
    private String appUniqueId;
    private Integer badge;
    private Boolean isMultiSync = false;
    private Boolean isAsync = false;

    public Badge() {}

    private Badge(String deviceId, Long userId, String appUniqueId, Integer badge, Boolean isMultiSync, Boolean isAsync) {
        this.deviceId = deviceId;
        this.userId = userId;
        this.appUniqueId = appUniqueId;
        this.badge = badge;
        this.isMultiSync = isMultiSync;
        this.isAsync = isAsync;
    }

    public static Builder builder() {
        return new Builder();
    }


    @JSONField(serialize = false)
    public boolean isMultiSync() {
        return isMultiSync != null && isMultiSync;
    }

    @JSONField(serialize = false)
    public boolean isByUser() {
        return userId != null && userId > 0L;
    }

    @JSONField(serialize = false)
    public boolean isByDevice() {
        return StringUtils.isNotBlank(deviceId);
    }

    @Override
    public Object toJSON() {
        Map<String, Object> data = Maps.newHashMap();
        data.put(DEVICE_ID, deviceId);
        data.put(USER_ID, userId);
        data.put(APP_UNIQUE_ID, appUniqueId);
        data.put(BADGE, badge);
        data.put(IS_MULTI_SYNC, isMultiSync);
        data.put(IS_ASYNC, isAsync);

        return data;
    }

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this.toJSON());
    }

    public static class Builder {
        private String deviceId;
        private Long userId;
        private String appUniqueId;
        private Integer badge;
        private Boolean isMultiSync = false;
        private Boolean isAsync;

        public Builder setDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setAppUniqueId(String appUniqueId) {
            this.appUniqueId = appUniqueId;
            return this;
        }

        public Builder setBadge(Integer badge) {
            this.badge = badge;
            return this;
        }

        public Builder isMultiSync(Boolean isMultiSync) {
            if (isMultiSync != null) {
                this.isMultiSync = isMultiSync;
            }
            return this;
        }

        public Builder isAsync(Boolean isAsync) {
            if (isAsync != null) {
                this.isAsync = isAsync;
            }
            return this;
        }

        public Badge build() {
            return new Badge(deviceId, userId, appUniqueId, badge, isMultiSync, isAsync);
        }
    }
}
