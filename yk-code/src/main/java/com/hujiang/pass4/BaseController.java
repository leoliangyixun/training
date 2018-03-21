package com.hujiang.pass.account.controller.api;

import com.hujiang.basic.framework.core.exception.AppException;
import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.pass.account.config.ResponseHandler;
import com.hujiang.pass.account.config.ResponseCallback;
import com.hujiang.pass.support.model.dto.res.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by yangkai on 2017/6/9.
 */
@Slf4j
public class BaseController {

    protected <U, T extends BaseRes<?>> ResponseEntity<BaseRes<?>> responseHandle(ResponseHandler<U, T> responseHandler, ResponseCallback<U> responseCallback) {
        ResponseEntity<BaseRes<?>> re;
        try {
            U u = responseCallback.get();
            BaseRes<?> res = responseHandler.handle(u);
            re = new ResponseEntity<>(res.toUserCenterStandardRes(), HttpStatus.OK);
        } catch (AppException e) {
            log.error("[error stack]: ", e);
            re = new ResponseEntity<>(BaseRes.fail(e.getCode(), e.getMessage(), e.getReturnObj()), HttpStatus.OK);
        } catch (SysException e) {
            log.error("[error stack]: ", e);
            re = new ResponseEntity<>((BaseRes<?>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("[error stack]: ", e);
            re = new ResponseEntity<>((BaseRes<?>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return re;
    }
}
