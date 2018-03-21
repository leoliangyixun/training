package com.hujiang.notifycenter.wechat.dao;

import org.springframework.stereotype.Component;

import com.hujiang.basic.framework.rest.common.BaseService;
import com.hujiang.notifycenter.wechat.support.model.po.WXAccountInfo;

/**
 * Created by yangkai on 2016/12/15.
 */
@Component
public class WXAccountInfoDao extends BaseService<WXAccountInfo> {

    @Override
    protected String namespace() {
        return "com.hujiang.notifycenter.wechat.support.model.po.WXAccountInfo";
    }
}
