/**
 * 
 */
package com.hujiang.notifycenter.wechat.dao;

import org.springframework.stereotype.Component;

import com.hujiang.basic.framework.rest.common.BaseService;
import com.hujiang.notifycenter.wechat.support.model.po.SendHistory;

/**
 * @author yangkai
 *
 */
@Component
public class SendHistoryDao extends BaseService<SendHistory> {

    @Override
    protected String namespace() {
        return "com.hujiang.notifycenter.wechat.support.model.po.SendHistory";
    }

}
