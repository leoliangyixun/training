package com.yk.spring.boot.mvc.handler;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangkai on 2016/10/23.
 */
//@Slf4j
@Log4j2
//@ControllerAdvice
public class GlobalExceeptionHandler {

    @ExceptionHandler(value = Exception.class)
    public void handleException(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            throw e;
        }

        log.info(e);
    }
}
