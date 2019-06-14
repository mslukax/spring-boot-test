package com.springboot.demo.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component //导入依赖容器中
//服务请求日志Aop
public class RequestLog {
    private static final Logger logger = LoggerFactory.getLogger(RequestLog.class);

    @Pointcut("execution(* com.springboot.demo..*.*(..))") //绑定要切入的层
    public void ControllerLog(){}

    @Before("ControllerLog()") //请求前时写log
    public void doBeforeRequest(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL:" +request.getRequestURI());
    }


    @AfterReturning(returning =  "ret", pointcut = "ControllerLog()") //返回后写log
    public void doAfterReturning(Object ret){
        logger.info("Response : " + ret);
    }
}
