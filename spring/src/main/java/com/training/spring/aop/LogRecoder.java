/**
 * 
 */
package com.training.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yangkai
 *
 */
@Aspect
@Component
public class LogRecoder {

    private static final Logger logger= LoggerFactory.getLogger(LogRecoder.class);
    public LogRecoder() {
       
    }

    @Around("@annotation(opLog)")
    public Object around(ProceedingJoinPoint point, OptLogger opLog) throws Throwable {

        point.getArgs();
        Object result = point.proceed();

        return result;
    }
    

}
