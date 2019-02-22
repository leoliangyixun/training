package com.training.pass2;

import com.hujiang.basic.framework.core.exception.AppException;
import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.pass.account.config.ResponseHandler;
import com.hujiang.pass.support.model.dto.res.BaseRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by yangkai on 2017/6/9.
 */
@Slf4j
public class BaseController {

    protected <R, T extends BaseRes<R>> ResponseEntity<BaseRes<R>> responseHandle(ResponseHandler<R, T> handler) {
        ResponseEntity<BaseRes<R>> re;
        try {
            BaseRes<R> res = handler.handle(null);
            re = new ResponseEntity<>(res.toUserCenterStandardRes(), HttpStatus.OK);
        } catch (AppException e) {
            log.error("[error stack]: ", e);
            re = new ResponseEntity<>(BaseRes.fail(e.getCode(), e.getMessage(), (R)e.getReturnObj()), HttpStatus.OK);
        } catch (SysException e) {
            log.error("[error stack]: ", e);
            re = new ResponseEntity<>((BaseRes<R>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("[error stack]: ", e);
            re = new ResponseEntity<>((BaseRes<R>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return re;
    }
}
