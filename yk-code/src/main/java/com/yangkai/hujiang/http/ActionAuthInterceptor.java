package com.yangkai.hujiang.http;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.pass.integration.annotation.RequestTypeValidation;

import com.hujiang.pass.support.constants.RequestMsgType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by yangkai on 2017/5/26.
 */
@Aspect
@Component
public class ActionAuthInterceptor {

    @Around(value = " @annotation(actionAuth) ")
    public Object process(ProceedingJoinPoint point, RequestTypeValidation requestTypeValidation) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();

        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
        return null;
    }

    @Before(value = " @annotation(requestTypeValidation) ")
    public void before(JoinPoint jp, RequestTypeValidation requestTypeValidation) {

        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        JSONObject job = new JSONObject();
       // String[] paramNames = pnd.getParameterNames(method);
        RequestMsgType[] types = requestTypeValidation.types();

        Object[] args = jp.getArgs();
        jp.getSignature().getDeclaringTypeName();

        jp.getSignature().getName();

    }
}
