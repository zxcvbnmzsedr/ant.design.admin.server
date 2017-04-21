package com.tianzeng.react.interfaces;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.config.exception.MyException;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by tianzeng on 17-4-21.
 */
@Aspect
@Component
public class WebLogAspect {
    private Logger logger = Logger.getLogger(getClass());
    @Pointcut("@annotation(com.tianzeng.react.interfaces.CheckRole)")
    private void cut() {
        System.out.println("环绕通知之开始");
        System.out.println("环绕通知之开始");
    }
    @Around("cut()")
    public void advice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知之开始");
            joinPoint.proceed();

        System.out.println("环绕通知之结束");
    }
    //当想获得注解里面的属性，可以直接注入改注解
    @Before("cut()&&@annotation(myLog)")
    public void record(JoinPoint joinPoint, CheckRole myLog) throws MyException {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        logger.info("TOKEN "+token);
        logger.info("权限"+myLog.value());
//        Assert.throwException("权限不足");
    }

}
