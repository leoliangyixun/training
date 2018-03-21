package com.hujiang.oauth.service.validator;

import com.hujiang.oauth.core.cache.Cache;
import com.hujiang.oauth.core.cache.CacheExecutor;
import com.hujiang.oauth.core.validator.Validator;
import com.hujiang.oauth.core.validator.ValidatorContext;
import com.hujiang.oauth.service.LoginService;
import com.hujiang.oauth.support.constants.HJAuthException;
import com.hujiang.oauth.support.constants.ResponseCode;
import com.hujiang.oauth.support.model.bo.LoginUser;
import com.hujiang.oauth.support.model.dto.UuidDTO;
import com.hujiang.oauth.support.utils.Assert;

@Deprecated
public class LoginValidator implements Validator<UuidDTO> {

    @Override
    public boolean validate(ValidatorContext context, UuidDTO obj) {
        Assert.notNull(obj, "illegal arguments");
        //验证用户是否登录
        Cache redisExecutor = context.get(CacheExecutor.CACHE_EXECUTOR, Cache.class);
        LoginService loginService = context.get(LoginService.LOGIN_SERVICE, LoginService.class);
        Assert.notNull(redisExecutor, "cache executor is null");
        Assert.notNull(loginService, "login service is null");
        LoginUser loginUser = loginService.getLoginUser();
        //用户未登录
        if(loginUser == null) {
            throw new HJAuthException(ResponseCode.USER_NOT_LOGIN);
        }

        return true;
    }
}
