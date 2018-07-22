package com.test.yk.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by yangkai on 2017/5/26.
 */
@Aspect
@Component
public class OpLoggerRecorder {

    @Around(value = " @annotation(opLogger) ")
    public Object process(ProceedingJoinPoint point, OpLogger opLogger) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();

        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
        return null;
    }

    @Before(value = " @annotation(opLogger) ")
    public void permissionCheck(JoinPoint point, OpLogger opLogger) {
        Object[] args = point.getArgs();
        point.getSignature().getDeclaringTypeName();

        point.getSignature().getName();

    }
}
