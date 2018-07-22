package com.hujiang.notifycenter.client.push;

import com.hujiang.basic.framework.core.config.BaseProperties;
import com.hujiang.basic.framework.core.util.HttpClientUtil;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.basic.framework.rest.model.DataResult;
import com.hujiang.notifycenter.client.push.model.Badge;
import com.hujiang.notifycenter.gundam.common.util.Preconditions;
import com.hujiang.notifycenter.gundam.common.util.Util;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author yangkai
 * @date 2018/6/8
 * @email yangkai@hujiang.com
 * @description
 */
@Slf4j
public class BadgeClient extends BaseClient<BadgeClient> {
    private static final String NOTIFY_CENTER_BADGE_API = "notifycenter.badge.url";

    public BadgeClient(String appKey, String appSecret) {
        super(appKey, appSecret);
    }

    /**
     * call push rest api to report badge
     * @param badge
     * @return
     */
    public DataResult<?> sync(Badge badge) {
        String url = BaseProperties.getString(NOTIFY_CENTER_BADGE_API);
        Preconditions.notNull(url, "please check"+ NOTIFY_CENTER_BADGE_API  + " in your properties");
        Preconditions.notNull(appKey," appKey is null");
        Preconditions.notNull(appSecret , " appSecret is null");
        Preconditions.notNull(url, "url is null");

        Date now = new Date();
        Header[] headers = new BasicHeader[] {new BasicHeader("Content-Type", "application/json")};

        String badgeUrl = url + "?appKey=" + appKey + "&sign=" + Util.md5(appKey + "&" + now.getTime() + "&" + appSecret) + "&ts=" + now.getTime();
        String data =  JsonUtil.object2JSON(badge.toJSON());
        String result = HttpClientUtil.sendHttpPostByRetry(badgeUrl, data, timeout, TimeUnit.MILLISECONDS, retryCount, headers);
        log.info("url: {}, data: {}, result: {}", url, data, result);
        return Util.parseDataResult(result);
    }


}
