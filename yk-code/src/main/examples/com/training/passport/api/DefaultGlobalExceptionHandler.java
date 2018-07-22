/**
 * 
 */
package com.training.passport.api;

import javax.servlet.http.HttpServletRequest;

import com.hujiang.pass.support.constants.SysConst;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hujiang.basic.framework.core.exception.AppException;
import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.pass.support.model.dto.response.BaseRes;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangkai
 *
 */
@ControllerAdvice
@Slf4j
public class DefaultGlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseRes<Object> handle(HttpServletRequest req, Exception e) throws Exception {
        log.error(String.format("url:%s, reason:%s", getReqUrl(req), e.getMessage()), e);

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        if (e instanceof SysException) {
            SysException e1 = (SysException) e;
            return build(e1.getCode(), e1.getMessage(), e1.getReturnObj());
        } else if (e instanceof AppException) {
            AppException e2 = (AppException) e;
            return build(e2.getCode(), e2.getMessage(), e2.getReturnObj());
        } else {
            return build(50000, "system error!", null);
        }

    }

    private BaseRes<Object> build(int status, String message, Object data) {
        BaseRes<Object> res = new BaseRes<>();
        res.setStatus(status);
        res.setMessage(message);
        res.setData(data);
        return res;
    }

    private String getReqUrl(HttpServletRequest req) {
        try {
            String path = req.getRequestURI();
            String queryStr = req.getQueryString();
            if (queryStr != null) {
                path += "?" + req.getQueryString();
            }
            return path;
        } catch (Exception ex) {
            log.error(SysConst.ERROR_STACK, ex);
            return ex.getMessage();
        }
    }

}
