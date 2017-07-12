package com.hujiang.pass3;

import org.apache.http.HttpStatus;

import com.hujiang.basic.framework.core.exception.AppException;
import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.foe.common.bean.exception.entity.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by yangkai on 2017/6/9.
 */
@Slf4j
public class BaseController {

    @SuppressWarnings("unchecked")
    protected <R, T extends BaseRes<R>> ResponseEntity<BaseRes<R>> responseHandle(ResponseHandler<R, T> handler) {
        ResponseEntity<BaseRes<R>> re;
        try {
            R r = null;
            BaseRes<R> res = handler.handle(r);
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
