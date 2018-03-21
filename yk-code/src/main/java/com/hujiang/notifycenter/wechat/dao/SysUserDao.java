/**
 * 
 */
package com.hujiang.notifycenter.wechat.dao;

import org.springframework.stereotype.Component;

import com.hujiang.basic.framework.rest.common.BaseService;
import com.hujiang.notifycenter.wechat.support.model.po.SysUser;

/**
 * @author yangkai
 *
 */
@Component
public class SysUserDao extends BaseService<SysUser> {

    @Override
    protected String namespace() {
        return "com.hujiang.notifycenter.wechat.support.model.po.SysUser";
    }

}
