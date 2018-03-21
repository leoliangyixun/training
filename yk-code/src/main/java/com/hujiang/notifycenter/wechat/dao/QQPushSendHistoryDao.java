/**
 * 
 */
package com.hujiang.notifycenter.wechat.dao;

import org.springframework.stereotype.Component;

import com.hujiang.basic.framework.rest.common.BaseService;
import com.hujiang.notifycenter.wechat.support.model.po.QQPushSendHistory;

/**
 * @author yangkai
 *
 */
@Component
public class QQPushSendHistoryDao extends BaseService<QQPushSendHistory> {

    @Override
    protected String namespace() {
        return "com.hujiang.notifycenter.wechat.support.model.po.QQPushSendHistory";
    }
}
